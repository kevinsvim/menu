package com.zsh.member.domain.dto;

import lombok.Data;

/**
 * Created by zsh on 2023/3/6
 *
 * @author zsh
 */
@Data
public class LoginDto {
    /**
     * 登录账号类型
     * (0-用户名/1-手机号/2-邮箱)
     */
    private Integer type;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
