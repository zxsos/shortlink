package com.bang.shortlink.admin.controller;

import com.bang.shortlink.admin.common.convention.result.Result;
import com.bang.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.bang.shortlink.admin.dto.resp.UserRespDTO;
import com.bang.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable String username) {
        UserRespDTO result = userService.getUserInfoByUsername(username);
        if (result == null) {
            return new Result<UserRespDTO>().setCode(UserErrorCodeEnum.USER_NULL.code()).setMessage(UserErrorCodeEnum.USER_NULL.message());
        } else {
            return new Result<UserRespDTO>().setCode("0").setData(result);
        }
    }
}
