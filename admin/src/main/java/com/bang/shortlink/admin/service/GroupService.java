package com.bang.shortlink.admin.service;

import com.bang.shortlink.admin.dao.entity.GroupDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 短链接分组接口层
 */
public interface GroupService extends IService<GroupDO> {
    /**
     * 新增短连接分组
     * @param groupName
     */
    void saveGroup(String groupName);
}