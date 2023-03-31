package com.zsh.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.common.result.CommonResultCode;
import com.zsh.common.utils.JwtTokenUtils;
import com.zsh.member.constant.LoginType;
import com.zsh.member.domain.Member;
import com.zsh.member.domain.dto.LoginDto;
import com.zsh.member.domain.dto.RegisterDto;
import com.zsh.member.domain.vo.MemberLoginVo;
import com.zsh.member.service.MemberService;
import com.zsh.member.mapper.MemberMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


/**
 * @author zsh
 * @description 针对表【member】的数据库操作Service实现
 * @createDate 2023-03-06 21:04:30
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private final RedisTemplate<String, String> redisTemplate;
    public MemberServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /**
     * 登录
     */
    @Override
    public CommonResult<Object> login(LoginDto loginDto) {
        // 1. 根据用户名查询相应密码
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        switch (loginDto.getType()) {
            case LoginType.USERNAME:
                wrapper.lambda().eq(Member::getUsername, loginDto.getAccount());
                break;
            case LoginType.PHONE:
                wrapper.lambda().eq(Member::getPhone, loginDto.getAccount());
                break;
            case LoginType.EMAIL:
                wrapper.lambda().eq(Member::getEmail, loginDto.getAccount());
                break;
            default:
                return CommonResult.fail(CommonResultCode.LOGIN_TYPE_ERROR);
        }
        Member member = this.baseMapper.selectOne(wrapper);
        // 2. 校验密码的正确性
        if (ObjectUtils.isNotEmpty(member) && loginDto.getPassword().equals(member.getPassword())) {
            // 3.生成token
            String token = JwtTokenUtils.generateToken(String.valueOf(member.getId()), member.getUsername());
            MemberLoginVo memberLoginVo = new MemberLoginVo();
            memberLoginVo.setId(String.valueOf(member.getId()));
            memberLoginVo.setToken(token);
            memberLoginVo.setUsername(member.getUsername());
            memberLoginVo.setImage_url(member.getImageUrl());
            return CommonResult.success(memberLoginVo);
        }
        return CommonResult.fail(CommonResultCode.LOGIN_ERROR);
    }

    /**
     * 注册
     */
    @Override
    public CommonResult<String> register(RegisterDto registerDto) {
        // 1.校验验证码是否正确
        String verifyCode = redisTemplate.opsForValue().get(registerDto.getPhone());
        if (StringUtils.isNotEmpty(verifyCode) && verifyCode.equals(registerDto.getCode())) {
            // 2.查看用户是否已经注册
            QueryWrapper<Member> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(Member::getPhone, registerDto.getPhone());
            Member member = this.baseMapper.selectOne(wrapper);
            if (ObjectUtils.isEmpty(member)) {
                // 3.未注册则保存用户信息
                Member newMember = new Member();
                BeanUtils.copyProperties(registerDto, newMember);
                this.save(newMember);
                // 4.生成token
                String token = JwtTokenUtils.generateToken(String.valueOf(newMember.getId()), newMember.getUsername());
                return CommonResult.success(token);
            }
        }
        return CommonResult.fail(CommonResultCode.VERIFY_CODE_ERROR);
    }
}
