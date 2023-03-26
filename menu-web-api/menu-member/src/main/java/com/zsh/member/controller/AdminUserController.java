package com.zsh.member.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.member.domain.dto.admin.UserDto;
import com.zsh.member.service.AdminUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zsh on 2023/3/22
 *
 * @author zsh
 */
@RestController
@RequestMapping("/member/admin")
public class AdminUserController {

    private final AdminUserService adminUserService;
    private AdminUserController(AdminUserService userService) {
        this.adminUserService = userService;
    }
    /**
     * 管理员用户登录
     */
    @PostMapping("/login")
    public CommonResult<Object> login(@RequestBody UserDto userDto) {
        return adminUserService.login(userDto);
    }
}
