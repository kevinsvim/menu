package com.zsh.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Note;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsh.resource.domain.dto.PublishNoteVo;
import com.zsh.resource.domain.vo.NoteDetailVo;

import java.util.List;

/**
* @author 18179
* @description 针对表【note】的数据库操作Service
* @createDate 2023-04-02 22:46:24
*/
public interface NoteService extends IService<Note> {

    CommonResult<Object> insertNote(String userId, PublishNoteVo publishNoteVo);

    IPage<Note> getNotes(Integer currentPage, Integer pageSize);

    NoteDetailVo getNoteDetail(String userId, String id);
}
