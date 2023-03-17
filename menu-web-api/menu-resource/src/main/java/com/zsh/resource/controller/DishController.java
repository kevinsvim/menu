package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.dto.PublishDishDto;
import com.zsh.resource.service.DishService;
import org.springframework.web.bind.annotation.*;


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
    public CommonResult<Object> saveDish(@RequestBody PublishDishDto publishDishDto) {
        return dishService.saveDish(publishDishDto);
    }

}
