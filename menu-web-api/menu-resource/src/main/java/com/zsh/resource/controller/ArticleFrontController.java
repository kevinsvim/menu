package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.vo.ArticleDetailVo;
import com.zsh.resource.domain.vo.PageArticleVo;
import com.zsh.resource.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zsh on 2023/4/5
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/front/article")
public class ArticleFrontController {

    private final ArticleService articleService;
    public ArticleFrontController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 分页查询文章数据
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return 分页数据
     */
    @GetMapping("/getPageArticle/{currentPage}/{pageSize}")
    public CommonResult<Object> getPageArticles(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        PageArticleVo pageArticleVo = articleService.getPageArticle(currentPage, pageSize);

        return CommonResult.success(pageArticleVo);
    }

    /**
     * 根据文章id查询详细信息
     * @param id 文章id(articleId)
     * @return 文章详情
     */
    @GetMapping("/getArticleDetail/{id}")
    public CommonResult<Object> getArticleDetail(@PathVariable String id) {
        ArticleDetailVo articleDetailVo = articleService.getArticleDetail(id);
        return CommonResult.success(articleDetailVo);
    }

    /**
     * 根据themeId分页查询数据
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @param themeId 主题id
     * @return 分页数据
     */
    @GetMapping("/getPageArticleByThemeId/{currentPage}/{pageSize}/{themeId}")
    public CommonResult<Object> getPageArticles(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, @PathVariable("themeId") String themeId) {
        PageArticleVo pageArticleVo = articleService.getPageArticleByThemeId(currentPage, pageSize, themeId);

        return CommonResult.success(pageArticleVo);
    }
}
