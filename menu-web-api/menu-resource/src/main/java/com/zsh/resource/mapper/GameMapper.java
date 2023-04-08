package com.zsh.resource.mapper;

import com.zsh.resource.domain.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zsh
* @description 针对表【game】的数据库操作Mapper
* @createDate 2023-04-04 16:23:38
* @Entity com.zsh.resource.domain.Game
*/
@Mapper
public interface GameMapper extends BaseMapper<Game> {

}




