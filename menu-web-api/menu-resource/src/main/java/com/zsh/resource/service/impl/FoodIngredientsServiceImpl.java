package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.FoodIngredients;
import com.zsh.resource.domain.vo.IngredientVo;
import com.zsh.resource.service.FoodIngredientsService;
import com.zsh.resource.mapper.FoodIngredientsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 18179
* @description 针对表【food_ingredients】的数据库操作Service实现
* @createDate 2023-04-07 21:16:00
*/
@Service
public class FoodIngredientsServiceImpl extends ServiceImpl<FoodIngredientsMapper, FoodIngredients>
    implements FoodIngredientsService{

    private final FoodIngredientsMapper ingredientsMapper;
    public FoodIngredientsServiceImpl(FoodIngredientsMapper ingredientsMapper) {
        this.ingredientsMapper = ingredientsMapper;
    }
    @Override
    public IPage<FoodIngredients> getPageIngredient(Integer currentPage, Integer pageSize) {
        IPage<FoodIngredients> page = new Page<>(currentPage, pageSize);
        this.page(page);
        return page;
    }

    @Override
    public List<IngredientVo> getPartIngredients() {
        return ingredientsMapper.getPartIngredients();
    }
}




