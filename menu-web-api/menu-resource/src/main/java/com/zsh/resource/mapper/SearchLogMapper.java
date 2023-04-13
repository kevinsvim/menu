package com.zsh.resource.mapper;

import com.zsh.resource.domain.SearchLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18179
* @description 针对表【search_log】的数据库操作Mapper
* @createDate 2023-04-12 10:09:42
* @Entity com.zsh.resource.domain.SearchLog
*/
@Mapper
public interface SearchLogMapper extends BaseMapper<SearchLog> {

}




