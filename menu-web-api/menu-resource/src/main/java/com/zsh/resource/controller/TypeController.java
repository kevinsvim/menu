package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zsh on 2023/3/21
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/category")
public class TypeController {

    private final TypeService typeService;
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    /**
     * 获取树形列表
     */
    @GetMapping("/getTreeCategory")
    public CommonResult<Object> getTreeCategory() {
        return typeService.getTreeCategory();
    }

}
