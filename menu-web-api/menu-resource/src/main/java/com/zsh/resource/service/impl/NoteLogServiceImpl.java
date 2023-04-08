package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.NoteLog;
import com.zsh.resource.domain.RecLog;
import com.zsh.resource.service.NoteLogService;
import com.zsh.resource.mapper.NoteLogMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【note_log】的数据库操作Service实现
* @createDate 2023-04-03 16:22:36
*/
@Service
public class NoteLogServiceImpl extends ServiceImpl<NoteLogMapper, NoteLog>
    implements NoteLogService{

    @Override
    public CommonResult<Object> updateUp(String userId, String noteId, Integer type) {
        LambdaQueryWrapper<NoteLog> noteLogWrapper = new LambdaQueryWrapper<>();
        noteLogWrapper.eq(NoteLog::getNoteId, noteId).eq(NoteLog::getMemberId, userId);
        int count = this.count(noteLogWrapper);
        if (count > 0) {
            this.baseMapper.updateUpType(type == 1, noteId, userId);
        } else {
            NoteLog noteLog = new NoteLog();
            noteLog.setIsUp(true);
            noteLog.setClickNum(0);
            noteLog.setCommentNum(0);
            noteLog.setNoteId(noteId);
            noteLog.setIsUp(false);
            noteLog.setMemberId(userId);
            this.save(noteLog);
        }

        return CommonResult.success();
    }

    @Override
    public CommonResult<Object> updateCollect(Integer type, String noteId, String memberId) {
        LambdaQueryWrapper<NoteLog> noteLogWrapper = new LambdaQueryWrapper<>();
        noteLogWrapper.eq(NoteLog::getNoteId, noteId).eq(NoteLog::getMemberId, memberId);
        int count = this.count(noteLogWrapper);
        if (count > 0) {
            this.baseMapper.updateCollectType(type == 1, noteId, memberId);
        } else {
            NoteLog noteLog = new NoteLog();
            noteLog.setIsCollect(true);
            noteLog.setClickNum(0);
            noteLog.setCommentNum(0);
            noteLog.setNoteId(noteId);
            noteLog.setIsUp(false);
            noteLog.setMemberId(memberId);
            this.save(noteLog);
        }

        return CommonResult.success();
    }
}




