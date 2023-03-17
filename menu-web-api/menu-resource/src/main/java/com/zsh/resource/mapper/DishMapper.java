package com.zsh.resource.mapper;

import com.zsh.resource.domain.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18179
* @description 针对表【dish】的数据库操作Mapper
* @createDate 2023-03-16 22:32:49
* @Entity com.zsh.resource.domain.Dish
*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}




