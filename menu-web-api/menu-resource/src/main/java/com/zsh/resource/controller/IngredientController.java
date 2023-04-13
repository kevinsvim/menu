package com.zsh.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.FoodIngredients;
import com.zsh.resource.domain.vo.IngredientVo;
import com.zsh.resource.service.FoodIngredientsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zsh on 2023/4/7
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/ingredient")
public class IngredientController {

    private final FoodIngredientsService ingredientsService;
    public IngredientController(FoodIngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    /**
     * 保存食材
     * @param ingredients 食材数据
     * @return 成功
     */
    @PostMapping("saveIngredient")
    public CommonResult<Object> saveIngredient(@RequestBody FoodIngredients ingredients) {

        return CommonResult.success(ingredientsService.save(ingredients));
    }

    /**
     * 分页获取食材数据
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return 分页数据
     */
    @GetMapping("getPageIngredients/{currentPage}/{pageSize}")
    public CommonResult<Object> getIngredients(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize")Integer pageSize) {
        IPage<FoodIngredients> pages = ingredientsService.getPageIngredient(currentPage, pageSize);
        return CommonResult.success(pages);
    }

    /**
     * 更新食材数据
     * @param foodIngredients y
     * @return 200
     */
    @PutMapping("updateIngredient")
    public CommonResult<Object> updateIngredient(@RequestBody FoodIngredients foodIngredients) {
        return CommonResult.success(ingredientsService.updateById(foodIngredients))    ;
    }

    /**
     * 根据id移除某个食材
     * @param id 食材id
     * @return 200
     */
    @DeleteMapping("removeIngredientById/{id}")
    public CommonResult<Object> deleteIngredientById(@PathVariable("id") String id) {
        return CommonResult.success(ingredientsService.removeById(id));
    }

    /**
     * 获取数据
     * @return 200
     */
    @GetMapping("getPartIngredients")
    public CommonResult<Object> getIngredient() {
        List<IngredientVo> ingredientVos = ingredientsService.getPartIngredients();
        return CommonResult.success(ingredientVos);
    }

    @GetMapping("getIngredientById/{id}")
    public CommonResult<Object> getIngredientById(@PathVariable String id) {
        return CommonResult.success(ingredientsService.getById(id));
    }
}
