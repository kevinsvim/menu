package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.RecLog;
import com.zsh.resource.service.RecLogService;
import com.zsh.resource.mapper.RecLogMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【rec_log】的数据库操作Service实现
* @createDate 2023-03-13 12:06:08
*/
@Service
public class RecLogServiceImpl extends ServiceImpl<RecLogMapper, RecLog>
    implements RecLogService{

}




