package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.NoteLogService;
import com.zsh.resource.service.RecLogService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zsh on 2023/4/3
 *
 * @author zsh
 */

@RestController
@RequestMapping("/resource/noteLog")
public class NoteLogController {

    private final NoteLogService noteLogService;

    public NoteLogController(NoteLogService noteLogService) {
        this.noteLogService = noteLogService;
    }

    /**
     * 是否取消收藏 【0 - 取消收藏】 【1 - 收藏】
     */
    @PatchMapping("/collect")
    public CommonResult<Object> collect(@RequestParam("type") Integer type, @RequestParam("noteId") String noteId,
                                        @RequestParam("memberId") String memberId) {
        return noteLogService.updateCollect(type, noteId, memberId);
    }

    /**
     * 是否取消点赞 【0 - 取消点赞】 【1 - 点赞】
     */
    @PatchMapping("/up")
    public CommonResult<Object> up(@RequestHeader("userId") String userId, @RequestParam("type") Integer type, @RequestParam("noteId") String noteId) {
        return noteLogService.updateUp(userId, noteId, type);
    }
}
