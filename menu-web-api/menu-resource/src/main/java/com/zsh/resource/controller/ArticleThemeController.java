package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.ArticleTheme;
import com.zsh.resource.service.ArticleThemeService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zsh on 2023/4/5
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/theme")
public class ArticleThemeController {

    private final ArticleThemeService themeService;
    public ArticleThemeController(ArticleThemeService themeService) {
        this.themeService = themeService;
    }

    /**
     * 获取所有主题分类
     */
    @GetMapping("/getThemes")
    public CommonResult<Object> getThemes() {
        return CommonResult.success(themeService.list());
    }

    /**
     * 保存主题
     */
    @PostMapping("/saveTheme")
    public CommonResult<Object> saveTheme(@RequestBody ArticleTheme articleTheme) {
        return CommonResult.success(themeService.save(articleTheme));
    }
}
