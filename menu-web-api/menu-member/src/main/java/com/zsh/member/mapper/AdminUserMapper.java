package com.zsh.member.mapper;

import com.zsh.member.domain.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18179
* @description 针对表【admin_user】的数据库操作Mapper
* @createDate 2023-03-22 11:13:13
* @Entity com.zsh.member.domain.AdminUser
*/
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {

}




