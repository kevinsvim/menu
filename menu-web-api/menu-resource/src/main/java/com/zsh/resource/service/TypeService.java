package com.zsh.resource.service;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Type;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 18179
* @description 针对表【type】的数据库操作Service
* @createDate 2023-03-21 22:54:35
*/
public interface TypeService extends IService<Type> {

    CommonResult<Object> getTreeCategory();

    CommonResult<Object> saveCategory(Type type);

    CommonResult<Object> removeCategory(Integer categoryLevel, Long categoryId);
}
