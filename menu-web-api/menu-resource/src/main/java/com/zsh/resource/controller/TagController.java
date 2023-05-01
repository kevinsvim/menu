package com.zsh.resource.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Type;
import com.zsh.resource.service.TypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zsh on 2023/4/28
 * 标签选择
 * @author zsh
 */
@RestController
@RequestMapping("/resource/tag")
public class TagController {

    private final TypeService typeService;
    public TagController(TypeService typeService) {
        this.typeService = typeService;
    }
    /**
     * 获取标签列表
     */
    @GetMapping("/getTags")
    public CommonResult<Object> getTags() {
        LambdaQueryWrapper<Type> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Type::getTypeLevel, 2);
        return CommonResult.success(typeService.list(wrapper));
    }

    /**
     * 将标签保存到当前用户
     */
    @PostMapping("/saveTags")
    public CommonResult<Object> saveTags(@RequestBody List<String> tags, @RequestHeader("userId") String userId) {
        typeService.insertTags(tags, userId);
        return CommonResult.success();
    }
}
