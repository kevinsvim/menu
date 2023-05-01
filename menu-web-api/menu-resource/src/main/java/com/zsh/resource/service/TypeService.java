package com.zsh.resource.service;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsh.resource.domain.dto.SearchSuggestDto;
import com.zsh.resource.domain.vo.CategoryTreeVo;
import com.zsh.resource.domain.vo.SuggestVo;

import java.util.List;

/**
* @author 18179
* @description 针对表【type】的数据库操作Service
* @createDate 2023-03-21 22:54:35
*/
public interface TypeService extends IService<Type> {

    List<CategoryTreeVo> getTreeCategory(Integer status);

    CommonResult<Object> saveCategory(Type type);

    CommonResult<Object> removeCategory(Integer categoryLevel, Long categoryId);

    List<SuggestVo> getSearchSuggestList(SearchSuggestDto searchSuggestDto);

    void insertTags(List<String> tags, String userId);
}
