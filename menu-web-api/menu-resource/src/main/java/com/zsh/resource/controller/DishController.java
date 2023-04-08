package com.zsh.resource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.dto.PublishDishDto;
import com.zsh.resource.domain.vo.DishCategoryVo;
import com.zsh.resource.domain.vo.DishConcentrationVo;
import com.zsh.resource.domain.vo.DishDetailVo;
import com.zsh.resource.service.DishService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by zsh on 2023/3/13
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/dish")
public class DishController {

    private final DishService dishService;
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    /**
     *  获取热度值推荐
     */
    @GetMapping("/hotScore")
    public CommonResult<Object> getHotRec() {
        
        return dishService.getHotRec();
    }

    /**
     * 存储发布的菜品
     */
    @PostMapping("/saveDish")
    public CommonResult<Object> saveDish(@RequestHeader("userId") String userId, @RequestBody PublishDishDto publishDishDto) {
        publishDishDto.setPublisherId(userId);
        return dishService.saveDish(publishDishDto);
    }

    /**
     * 获取精选菜谱
     */
    @GetMapping("/getConcentrationMenu")
    public CommonResult<Object> getConcentrationMenu() {
        List<DishConcentrationVo> list = dishService.getConcentrationMenu();
        return CommonResult.success(list);
    }

    /**
     * 获取菜谱详情数据
     * @param id 菜谱id
     */
    @GetMapping("/getDishDetailById/{id}")
    public CommonResult<Object> getDishDetailById(@PathVariable("id") String id, @RequestHeader(value = "userId", required = false) String userId) {
        DishDetailVo list = dishService.getDishDetail(id, userId);
        return CommonResult.success(list);
    }

    /**
     * 根据三级分类id获取所有菜谱
     */
    @GetMapping("/getAllDishByCategoryId/{categoryId}/{pageSize}/{currentPage}")
    public CommonResult<Object> getAllDishByCategoryId(@PathVariable("categoryId") String categoryId,
                                                       @PathVariable("pageSize") Integer pageSize,
                                                       @PathVariable("currentPage") Integer currentPage) {
        IPage<DishCategoryVo> data = dishService.getAllDishByCategoryId(categoryId, pageSize, currentPage);
        return CommonResult.success(data);
    }
}
