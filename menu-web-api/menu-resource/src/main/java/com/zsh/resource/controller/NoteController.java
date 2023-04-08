package com.zsh.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Note;
import com.zsh.resource.domain.dto.PublishNoteVo;
import com.zsh.resource.domain.vo.NoteDetailVo;
import com.zsh.resource.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zsh on 2023/4/2
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/note")
public class NoteController {

    private final NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * 发布日记
     * @param userId 发布用户
     * @param publishNoteVo 发布数据
     */
    @PostMapping("/saveNote")
    public CommonResult<Object> saveNote(@RequestHeader("userId") String userId, @RequestBody PublishNoteVo publishNoteVo) {
        return noteService.insertNote(userId, publishNoteVo);
    }

    /**
     * 获取分页数据
     * @param currentPage 当前页
     * @param pageSize 每页大小
     */
    @GetMapping("/getNoteList/{currentPage}/{pageSize}")
    public CommonResult<Object> getNoteList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        IPage<Note> list = noteService.getNotes(currentPage, pageSize);
        return CommonResult.success(list);
    }

    /**
     * 获取笔记详细数据
     * @param userId 用户id
     * @param id 笔记id
     */
    @GetMapping("/getNoteDetail/{id}")
    public CommonResult<Object> getNoteDetail(@RequestHeader(value = "userId", required = false) String userId, @PathVariable String id) {
        NoteDetailVo list = noteService.getNoteDetail(userId, id);
        return CommonResult.success(list);
    }
}
