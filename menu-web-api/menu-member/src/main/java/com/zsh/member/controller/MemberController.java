package com.zsh.member.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.member.domain.dto.LoginDto;
import com.zsh.member.domain.dto.RegisterDto;
import com.zsh.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zsh on 2023/3/6
 *
 * @author zsh
 */
@Slf4j
@RestController
@RequestMapping("/member/auth")
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody LoginDto loginDto) {
        log.info("登录用户:{}", loginDto.getAccount());
        return memberService.login(loginDto);
    }

    @PostMapping("/register")
    public CommonResult<String> register(@RequestBody RegisterDto registerDto) {
        return memberService.register(registerDto);
    }

    @GetMapping("/test")
    public CommonResult<String> test() {
        log.info("test...");
        return CommonResult.success("test");
    }
}
