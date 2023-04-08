package com.zsh.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsh.resource.domain.FoodIngredients;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsh.resource.domain.vo.IngredientVo;

import java.util.List;

/**
* @author 18179
* @description 针对表【food_ingredients】的数据库操作Service
* @createDate 2023-04-07 21:16:00
*/
public interface FoodIngredientsService extends IService<FoodIngredients> {

    IPage<FoodIngredients> getPageIngredient(Integer currentPage, Integer pageSize);

    List<IngredientVo> getPartIngredients();

}
