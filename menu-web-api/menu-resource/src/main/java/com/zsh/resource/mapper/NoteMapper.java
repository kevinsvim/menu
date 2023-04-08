package com.zsh.resource.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsh.resource.domain.Note;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.vo.NoteDetailVo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18179
* @description 针对表【note】的数据库操作Mapper
* @createDate 2023-04-02 22:46:24
* @Entity com.zsh.resource.domain.Note
*/
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

    NoteDetailVo getNoteDetail(String id);
}




