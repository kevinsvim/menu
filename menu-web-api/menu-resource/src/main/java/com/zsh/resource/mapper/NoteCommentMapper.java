package com.zsh.resource.mapper;

import com.zsh.resource.domain.NoteComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18179
* @description 针对表【note_comment】的数据库操作Mapper
* @createDate 2023-04-03 11:39:28
* @Entity com.zsh.resource.domain.NoteComment
*/
@Mapper
public interface NoteCommentMapper extends BaseMapper<NoteComment> {

}




