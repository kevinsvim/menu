package com.zsh.resource.mapper;

import com.zsh.resource.domain.RecLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @author 18179
* @description 针对表【rec_log】的数据库操作Mapper
* @createDate 2023-03-13 12:06:08
* @Entity com.zsh.resource.domain.RecLog
*/
@Mapper
public interface RecLogMapper extends BaseMapper<RecLog> {

    List<RecLog> getAllLogData();

    boolean updateCollectType(@Param("type") boolean type, @Param("dishId") Long dishId, @Param("memberId") Long memberId);
}




