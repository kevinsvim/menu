package com.zsh.resource.mapper;

import com.zsh.resource.domain.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18179
* @description 针对表【follow】的数据库操作Mapper
* @createDate 2023-03-30 22:18:07
* @Entity com.zsh.resource.domain.Follow
*/
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {

}




