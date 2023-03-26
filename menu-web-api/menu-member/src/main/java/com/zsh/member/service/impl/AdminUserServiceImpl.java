package com.zsh.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.common.utils.JwtTokenUtils;
import com.zsh.member.domain.AdminUser;
import com.zsh.member.domain.dto.admin.UserDto;
import com.zsh.member.service.AdminUserService;
import com.zsh.member.mapper.AdminUserMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【admin_user】的数据库操作Service实现
* @createDate 2023-03-22 11:13:13
*/
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser>
    implements AdminUserService{

    /**
     * 管理员用户登录
     * @param userDto 管理员登录信息
     * @return token
     */
    @Override
    public CommonResult<Object> login(UserDto userDto) {
        QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AdminUser::getUsername, userDto.getUsername());
        AdminUser user = this.getOne(wrapper);
        if (ObjectUtils.isNotEmpty(user) && userDto.getPassword().equals(user.getPassword())) {
            // 生成token返回
            String token = JwtTokenUtils.generateToken(String.valueOf(user.getId()), user.getUsername());
            return CommonResult.success(token);
        }
        return CommonResult.fail();
    }
}




