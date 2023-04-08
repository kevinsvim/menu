package com.zsh.resource.service;

import com.zsh.resource.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsh.resource.domain.vo.ArticleDetailVo;
import com.zsh.resource.domain.vo.PageArticleVo;

/**
* @author 18179
* @description 针对表【article】的数据库操作Service
* @createDate 2023-04-05 16:39:03
*/
public interface ArticleService extends IService<Article> {

    PageArticleVo getPageArticle(Integer currentPage, Integer pageSize);

    ArticleDetailVo getArticleDetail(String id);

    PageArticleVo getPageArticleByThemeId(Integer currentPage, Integer pageSize, String themeId);
}
