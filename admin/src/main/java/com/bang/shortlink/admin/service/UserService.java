package com.bang.shortlink.admin.service;

import com.bang.shortlink.admin.dao.entity.UserDO;
import com.bang.shortlink.admin.dto.req.UserLoginReqDTO;
import com.bang.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.bang.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.bang.shortlink.admin.dto.resp.UserLoginRespDTO;
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

    /**
     * 更新用户
     *
     * @param requestParam
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 用户登录
     *
     * @param requestParam 用户登录请求参数
     * @return 用户登录返回参数 Token
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登录
     *
     * @param username 用户名
     * @param token    用户登录 Token
     * @return 用户是否登录标识
     */
    Boolean checkLogin(String username, String token);

    /**
     * 退出登录
     *
     * @param username 用户名
     * @param token    用户登录 Token
     */
    void logout(String username, String token);
}
