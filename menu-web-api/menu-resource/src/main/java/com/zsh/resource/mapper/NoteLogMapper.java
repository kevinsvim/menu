package com.zsh.resource.mapper;

import com.zsh.resource.domain.NoteLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

/**
* @author 18179
* @description 针对表【note_log】的数据库操作Mapper
* @createDate 2023-04-03 16:22:36
* @Entity com.zsh.resource.domain.NoteLog
*/
@Mapper
public interface NoteLogMapper extends BaseMapper<NoteLog> {

    void updateUpType(@Param("type") boolean type, @Param("noteId") String noteId, @Param("userId") String userId);

    void updateCollectType(@Param("type") boolean type, @Param("noteId") String noteId, @Param("userId") String memberId);
}




