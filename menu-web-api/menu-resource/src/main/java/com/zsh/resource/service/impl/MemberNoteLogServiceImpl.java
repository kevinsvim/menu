package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.NoteRecLog;
import com.zsh.resource.service.MemberNoteLogService;
import com.zsh.resource.mapper.NoteRecLogMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【member_note_log】的数据库操作Service实现
* @createDate 2023-04-10 11:27:02
*/
@Service
public class MemberNoteLogServiceImpl extends ServiceImpl<NoteRecLogMapper, NoteRecLog>
    implements MemberNoteLogService{

}




