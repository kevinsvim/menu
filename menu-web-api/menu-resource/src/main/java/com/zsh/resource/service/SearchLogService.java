package com.zsh.resource.service;

import com.zsh.resource.domain.SearchLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 18179
* @description 针对表【search_log】的数据库操作Service
* @createDate 2023-04-12 10:09:42
*/
public interface SearchLogService extends IService<SearchLog> {

    List<SearchLog> selectList();
}
