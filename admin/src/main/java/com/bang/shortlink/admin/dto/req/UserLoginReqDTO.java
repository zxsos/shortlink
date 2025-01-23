package com.bang.shortlink.admin.dto.req;

import lombok.Data;

/**
 * 用户登录DTO
 */
@Data
public class UserLoginReqDTO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
