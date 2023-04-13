package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.MemberNoteLog;
import com.zsh.resource.service.NoteLogService;
import com.zsh.resource.mapper.MemberNoteLogMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【note_log】的数据库操作Service实现
* @createDate 2023-04-03 16:22:36
*/
@Service
public class NoteLogServiceImpl extends ServiceImpl<MemberNoteLogMapper, MemberNoteLog>
    implements NoteLogService{

    @Override
    public CommonResult<Object> updateUp(String userId, String noteId, Integer type) {
        LambdaQueryWrapper<MemberNoteLog> noteLogWrapper = new LambdaQueryWrapper<>();
        noteLogWrapper.eq(MemberNoteLog::getNoteId, noteId).eq(MemberNoteLog::getMemberId, userId);
        int count = this.count(noteLogWrapper);
        if (count > 0) {
            this.baseMapper.updateUpType(type == 1, noteId, userId);
        } else {
            MemberNoteLog memberNoteLog = new MemberNoteLog();
            memberNoteLog.setIsUp(true);
            memberNoteLog.setClickNum(0);
            memberNoteLog.setCommentNum(0);
            memberNoteLog.setNoteId(noteId);
            memberNoteLog.setIsUp(false);
            memberNoteLog.setMemberId(userId);
            this.save(memberNoteLog);
        }

        return CommonResult.success();
    }

    @Override
    public CommonResult<Object> updateCollect(Integer type, String noteId, String memberId) {
        LambdaQueryWrapper<MemberNoteLog> noteLogWrapper = new LambdaQueryWrapper<>();
        noteLogWrapper.eq(MemberNoteLog::getNoteId, noteId).eq(MemberNoteLog::getMemberId, memberId);
        int count = this.count(noteLogWrapper);
        if (count > 0) {
            this.baseMapper.updateCollectType(type == 1, noteId, memberId);
        } else {
            MemberNoteLog memberNoteLog = new MemberNoteLog();
            memberNoteLog.setIsCollect(true);
            memberNoteLog.setClickNum(0);
            memberNoteLog.setCommentNum(0);
            memberNoteLog.setNoteId(noteId);
            memberNoteLog.setIsUp(false);
            memberNoteLog.setMemberId(memberId);
            this.save(memberNoteLog);
        }

        return CommonResult.success();
    }
}




