package com.bang.shortlink.admin.service;

import com.bang.shortlink.admin.dao.entity.GroupDO;
import com.bang.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.bang.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.bang.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 短链接分组接口层
 */
public interface GroupService extends IService<GroupDO> {
    /**
     * 新增短连接分组
     *
     * @param groupName
     */
    void saveGroup(String groupName);

    /**
     * 查询用户短链接分组集合
     */
    List<ShortLinkGroupRespDTO> listGroup();

    /**
     * 修改短链接分组名
     *
     * @param requestParam
     */
    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);

    /**
     * 删除短链接分组
     */
    void deleteGroup(String gid);

    /**
     * 短链接分组排序
     * @param requestParam
     */
    void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam);
}