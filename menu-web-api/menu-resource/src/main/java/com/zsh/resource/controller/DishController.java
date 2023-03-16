package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.DishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @GetMapping("/hotScore")
    public CommonResult<Object> getHotRec() {
        
        return dishService.getHotRec();
    }
}
