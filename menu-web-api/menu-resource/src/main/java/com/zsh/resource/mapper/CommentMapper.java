package com.zsh.resource.mapper;

import com.zsh.resource.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 18179
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2023-03-29 17:55:17
* @Entity com.zsh.resource.domain.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVo> getCommentsByDishId(@Param("id") Long id);
}




