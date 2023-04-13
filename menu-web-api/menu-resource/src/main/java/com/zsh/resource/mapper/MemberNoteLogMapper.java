package com.zsh.resource.mapper;

import com.zsh.resource.domain.MemberNoteLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.vo.personal.PersonalNoteVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @author 18179
* @description 针对表【note_log】的数据库操作Mapper
* @createDate 2023-04-03 16:22:36
* @Entity com.zsh.resource.domain.NoteLog
*/
@Mapper
public interface MemberNoteLogMapper extends BaseMapper<MemberNoteLog> {

    void updateUpType(@Param("type") boolean type, @Param("noteId") String noteId, @Param("userId") String userId);

    void updateCollectType(@Param("type") boolean type, @Param("noteId") String noteId, @Param("userId") String memberId);

    List<PersonalNoteVo> getNoteCollectPage(@Param("userId") String userId, @Param("startSize") int startSize, @Param("pageSize") Integer pageSize);
}




