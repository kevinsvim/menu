package com.zsh.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsh.common.result.CommonResult;
import com.zsh.member.domain.Member;
import com.zsh.member.domain.dto.LoginDto;
import com.zsh.member.domain.dto.RegisterDto;

/**
* @author 18179
* @description 针对表【member】的数据库操作Service
* @createDate 2023-03-06 21:04:30
*/
public interface MemberService extends IService<Member> {

    CommonResult<Object> login(LoginDto loginDto);

    CommonResult<String> register(RegisterDto registerDto);
}
