package com.zsh.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18179
* @description 针对表【member】的数据库操作Mapper
* @createDate 2023-03-06 21:04:30
* @Entity generator.domain.Member
*/
@Mapper
public interface MemberMapper extends BaseMapper<Member> {


}
