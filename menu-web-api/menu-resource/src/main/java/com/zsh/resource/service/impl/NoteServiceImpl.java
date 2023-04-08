package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Follow;
import com.zsh.resource.domain.Note;
import com.zsh.resource.domain.NoteComment;
import com.zsh.resource.domain.NoteLog;
import com.zsh.resource.domain.dto.PublishNoteVo;
import com.zsh.resource.domain.vo.DishCategoryVo;
import com.zsh.resource.domain.vo.NoteDetailVo;
import com.zsh.resource.mapper.FollowMapper;
import com.zsh.resource.mapper.NoteCommentMapper;
import com.zsh.resource.mapper.NoteLogMapper;
import com.zsh.resource.service.NoteService;
import com.zsh.resource.mapper.NoteMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 18179
* @description 针对表【note】的数据库操作Service实现
* @createDate 2023-04-02 22:46:24
*/
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note>
    implements NoteService{

    private final NoteMapper noteMapper;
    private final NoteCommentMapper noteCommentMapper;
    private final NoteLogMapper noteLogMapper;
    private final FollowMapper followMapper;
    public NoteServiceImpl(NoteMapper noteMapper, NoteLogMapper noteLogMapper,
                           NoteCommentMapper noteCommentMapper, FollowMapper followMapper) {
        this.noteMapper = noteMapper;
        this.noteCommentMapper = noteCommentMapper;
        this.noteLogMapper = noteLogMapper;
        this.followMapper = followMapper;
    }
    @Override
    public CommonResult<Object> insertNote(String userId, PublishNoteVo publishNoteVo) {
        Note note = new Note();
        String imageUrls = StringUtils.join(publishNoteVo.getFileList(), ",");
        note.setImageUrls(imageUrls);
        note.setIntro(publishNoteVo.getIntro());
        note.setMemberId(Long.valueOf(userId));
        note.setTitle(publishNoteVo.getTitle());
        note.setState(1);
        this.save(note);
        return CommonResult.success();
    }

    @Override
    public IPage<Note> getNotes(Integer currentPage, Integer pageSize) {
        Page<Note> page = new Page<>(currentPage, pageSize);
        return this.page(page);
    }

    @Override
    public NoteDetailVo getNoteDetail(String userId, String id) {
        // 1.加载笔记详情数据
        NoteDetailVo noteDetail = noteMapper.getNoteDetail(id);
        // 2.加载笔记评论
        LambdaQueryWrapper<NoteComment> commentWrapper= new LambdaQueryWrapper<>();
        commentWrapper.eq(NoteComment::getNoteId, id);
        List<NoteComment> noteComments = noteCommentMapper.selectList(commentWrapper);
        noteDetail.setNoteComments(noteComments);
        // 3.加载笔记日志表
        List<NoteLog> noteLogs = noteLogMapper.selectList(null);
        // 设置当前用户是否点赞，是否收藏，日记点赞数量
        int upNum = 0;
        noteDetail.setIsUp(false);
        noteDetail.setIsCollect(false);
        for (NoteLog noteLog : noteLogs) {
            upNum += noteLog.getIsUp() ? 1 : 0;
            if (StringUtils.isNotEmpty(userId) && userId.equals(noteLog.getMemberId())) {
                noteDetail.setIsUp(noteLog.getIsUp());
                noteDetail.setIsCollect(noteLog.getIsCollect());
            }
        }
        noteDetail.setUpNum(upNum);
        // 设置当前用户是否关注该日记用户
        LambdaQueryWrapper<Follow> followWrapper = new LambdaQueryWrapper<>();
        followWrapper.eq(Follow::getMemberId, noteDetail.getPublishId()).eq(Follow::getFansId, userId);
        noteDetail.setIsFollow(followMapper.selectCount(followWrapper) > 0);
        return noteDetail;
    }
}




