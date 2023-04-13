package com.zsh.resource.mapper;

import com.zsh.resource.domain.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.vo.SuggestVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @author 18179
* @description 针对表【type】的数据库操作Mapper
* @createDate 2023-03-21 22:54:35
* @Entity com.zsh.resource.domain.Type
*/
@Mapper
public interface TypeMapper extends BaseMapper<Type> {

    List<SuggestVo> getSearchSuggests(@Param("size") Integer size, @Param("content") String content);
}




