package com.bang.shortlink.admin.service;

import com.bang.shortlink.admin.dao.entity.UserDO;
import com.bang.shortlink.admin.dto.resp.UserRespDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/*
 用户接口层
 */
public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名获取用户信息
     * @param username  用户名
     * @return  用户信息
     */
    UserRespDTO getUserInfoByUsername(String username);
}
