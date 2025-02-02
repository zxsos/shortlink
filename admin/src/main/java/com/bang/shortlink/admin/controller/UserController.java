package com.bang.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bang.shortlink.admin.common.convention.result.Result;
import com.bang.shortlink.admin.common.convention.result.Results;
import com.bang.shortlink.admin.dto.req.UserLoginReqDTO;
import com.bang.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.bang.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.bang.shortlink.admin.dto.resp.UserActualRespDTO;
import com.bang.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.bang.shortlink.admin.dto.resp.UserRespDTO;
import com.bang.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/*
 *用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
     *根据用户名获取用户信息
     */
    @GetMapping("/api/shortlink/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserInfoByUsername(username));
    }

    /*
     *根据用户名获取无脱敏用户信息
     */
    @GetMapping("/api/shortlink/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserInfoByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 查询用户是否存在
     */
    @GetMapping("/api/shortlink/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 注册用户
     *
     * @param requestParam
     * @return
     */
    @PostMapping("/api/shortlink/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * 更新用户
     *
     * @param requestParam
     * @return
     */
    @PutMapping("/api/shortlink/admin/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();

    }

    /**
     * 用户登录
     */
    @PostMapping("/api/shortlink/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }

    /**
     * 检查用户是否登录
     */
    @GetMapping("/api/shortlink/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));
    }

    /**
     * 用户退出登录
     */
    @DeleteMapping("/api/shortlink/admin/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token) {
        userService.logout(username, token);
        return Results.success();
    }
}
