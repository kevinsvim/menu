package com.zsh.resource.controller.admin;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Article;
import com.zsh.resource.service.ArticleService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zsh on 2023/4/5
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/admin/article")
public class ArticleAdminController {

    private final ArticleService articleService;
    public ArticleAdminController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 保存文章
     */
    @PostMapping("/saveArticle")
    public CommonResult<Object> saveArticle(@RequestBody Article article) {
        return CommonResult.success(articleService.save(article));
    }

}
