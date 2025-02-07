package com.bang.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bang.shortlink.admin.common.biz.user.UserContext;
import com.bang.shortlink.admin.common.convention.result.Result;
import com.bang.shortlink.admin.dao.entity.GroupDO;
import com.bang.shortlink.admin.dao.mapper.GroupMapper;
import com.bang.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.bang.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.bang.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.bang.shortlink.admin.remote.ShortLinkRemoteService;
import com.bang.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.bang.shortlink.admin.service.GroupService;
import com.bang.shortlink.admin.toolkit.RandomGenerator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 短链接分组接口实现层
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    /**
     * 后续重构为 SpringCloud Feign 调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @Override
    public void saveGroup(String groupName) {
        saveGroup(UserContext.getUsername(), groupName);
    }

    @Override
    public void saveGroup(String username, String groupName) {
        String gid;
        do {
            gid = RandomGenerator.generateRandom();
        } while (!hasGid(username, gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .sortOrder(0)
                .username(username)
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        // 查询分组列表
        List<GroupDO> groupDOList = baseMapper.selectList(Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime));

        // 获取分组对应的短链接数量
        List<String> gids = groupDOList.stream().map(GroupDO::getGid).toList();
        Result<List<ShortLinkGroupCountQueryRespDTO>> countResult = shortLinkRemoteService.listGroupShortLinkCount(gids);

        // 将 GroupDO 转换为 ShortLinkGroupRespDTO，并设置短链接数量
        Map<String, Integer> gidToCountMap = countResult.getData().stream()
                .collect(Collectors.toMap(ShortLinkGroupCountQueryRespDTO::getGid, ShortLinkGroupCountQueryRespDTO::getShortLinkCount));

        return groupDOList.stream()
                .map(groupDO -> {
                    ShortLinkGroupRespDTO respDTO = BeanUtil.copyProperties(groupDO, ShortLinkGroupRespDTO.class);
                    respDTO.setShortLinkCount(gidToCountMap.getOrDefault(groupDO.getGid(), 0));
                    return respDTO;
                })
                .toList();
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, requestParam.getGid())
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        baseMapper.update(groupDO, updateWrapper);
    }

    @Override
    public void deleteGroup(String gid) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setDelFlag(1);
        baseMapper.update(groupDO, updateWrapper);
    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {
        requestParam.forEach(each -> {
            GroupDO groupDO = GroupDO.builder()
                    .sortOrder(each.getSortOrder())
                    .build();
            LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    .eq(GroupDO::getGid, each.getGid())
                    .eq(GroupDO::getDelFlag, 0);
            baseMapper.update(groupDO, updateWrapper);
        });
    }

    private boolean hasGid(String username, String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, Optional.ofNullable(username).orElse(UserContext.getUsername()));
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        return hasGroupFlag == null;
    }
}
