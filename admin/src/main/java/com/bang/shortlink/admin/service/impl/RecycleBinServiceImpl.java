package com.bang.shortlink.admin.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.bang.shortlink.admin.common.biz.user.UserContext;
import com.bang.shortlink.admin.common.convention.exception.ServiceException;
import com.bang.shortlink.admin.common.convention.result.Result;
import com.bang.shortlink.admin.common.database.BaseDo;
import com.bang.shortlink.admin.dao.entity.GroupDO;
import com.bang.shortlink.admin.dao.mapper.GroupMapper;
import com.bang.shortlink.admin.remote.ShortLinkRemoteService;
import com.bang.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.bang.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.bang.shortlink.admin.service.RecycleBinService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回收站管理
 */
@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {

    private final GroupMapper groupMapper;
    /**
     * 后续重构为 SpringCloud Feign 调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 分页查询回收站短链接
     *
     * @param requestParam 分页短链接请求参数
     * @return 查询短链接响应
     */
    @Override
    public Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(BaseDo::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if(CollUtil.isEmpty(groupDOList))
        {
            throw new ServiceException("无此用户信息");
        }
        requestParam.setGidList(groupDOList.stream().map(GroupDO::getGid).toList());
        return shortLinkRemoteService.pageRecycleBinShortLink(requestParam);
    }
}
