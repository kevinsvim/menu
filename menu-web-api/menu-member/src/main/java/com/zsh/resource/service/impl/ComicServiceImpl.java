package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.Comic;
import com.zsh.resource.service.ComicService;
import com.zsh.resource.mapper.ComicMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【comic】的数据库操作Service实现
* @createDate 2023-04-04 15:45:56
*/
@Service
public class ComicServiceImpl extends ServiceImpl<ComicMapper, Comic>
    implements ComicService{

}




