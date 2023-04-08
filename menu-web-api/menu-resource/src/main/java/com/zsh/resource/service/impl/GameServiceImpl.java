package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.Game;
import com.zsh.resource.service.GameService;
import com.zsh.resource.mapper.GameMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【game】的数据库操作Service实现
* @createDate 2023-04-04 16:23:38
*/
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game>
    implements GameService{

}




