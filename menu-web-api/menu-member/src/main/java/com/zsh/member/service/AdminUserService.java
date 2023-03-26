package com.zsh.member.service;

import com.zsh.common.result.CommonResult;
import com.zsh.member.domain.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsh.member.domain.dto.admin.UserDto;

/**
* @author 18179
* @description 针对表【admin_user】的数据库操作Service
* @createDate 2023-03-22 11:13:13
*/
public interface AdminUserService extends IService<AdminUser> {

    CommonResult<Object> login(UserDto userDto);
}
