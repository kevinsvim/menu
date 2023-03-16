package com.zsh.member.domain.dto;

import lombok.Data;

/**
 * Created by zsh on 2023/3/7
 * 注册信息DTO
 * @author zsh
 */
@Data
public class RegisterDto {

    private String username;
    private String phone;
    private String password;
    private String code;
}
