package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.ProductionLevelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zsh on 2023/3/16
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/level")
public class ProductLevelController {

    private final ProductionLevelService levelService;
    public ProductLevelController(ProductionLevelService levelService) {
        this.levelService = levelService;
    }
    @GetMapping("/getPublishDataEcho")
    public CommonResult<Object> getPublishDataEcho() {
        return levelService.getPublishDataEcho();
    }
}
