package com.bang.shortlink.admin.service;

import com.bang.shortlink.admin.dao.entity.UserDO;
import com.bang.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.bang.shortlink.admin.dto.resp.UserRespDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/*
 用户接口层
 */
public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserRespDTO getUserInfoByUsername(String username);

    /**
     * 查询用户是否存在
     *
     * @param username 用户名
     * @return 用户信息
     */
    Boolean hasUsername(String username);

    /**
     * 注册用户
     *
     * @param requestParam
     */
    void register(UserRegisterReqDTO requestParam);
}
