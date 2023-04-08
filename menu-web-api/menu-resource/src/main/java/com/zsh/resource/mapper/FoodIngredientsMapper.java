package com.zsh.resource.mapper;

import com.zsh.resource.domain.FoodIngredients;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.vo.IngredientVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 18179
* @description 针对表【food_ingredients】的数据库操作Mapper
* @createDate 2023-04-07 21:16:00
* @Entity com.zsh.resource.domain.FoodIngredients
*/
@Mapper
public interface FoodIngredientsMapper extends BaseMapper<FoodIngredients> {

    List<IngredientVo> getPartIngredients();

}




