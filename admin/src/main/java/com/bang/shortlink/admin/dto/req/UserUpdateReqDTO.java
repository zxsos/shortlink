package com.bang.shortlink.admin.dto.req;

import lombok.Data;

/**
 *  用户更新DTO
 */
@Data
public class UserUpdateReqDTO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String mail;
}
