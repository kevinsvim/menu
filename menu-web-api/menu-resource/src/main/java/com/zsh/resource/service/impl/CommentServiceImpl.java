package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.Comment;
import com.zsh.resource.service.CommentService;
import com.zsh.resource.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-03-29 17:55:17
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




