package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.Article;
import com.zsh.resource.domain.vo.ArticleDetailVo;
import com.zsh.resource.domain.vo.ArticleVo;
import com.zsh.resource.domain.vo.PageArticleVo;
import com.zsh.resource.service.ArticleService;
import com.zsh.resource.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 18179
* @description 针对表【article】的数据库操作Service实现
* @createDate 2023-04-05 16:39:03
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    private final ArticleMapper articleMapper;
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }
    @Override
    public PageArticleVo getPageArticle(Integer currentPage, Integer pageSize) {
        PageArticleVo pageArticleVo = new PageArticleVo();
        // 如果是第一次需要去查询总数
        if (currentPage == 1) {
            pageArticleVo.setTotal(this.count());
        }
        pageArticleVo.setCurrentPage(currentPage);
        pageArticleVo.setPageSize(pageSize);
        List<ArticleVo> list =  articleMapper.selectPageArticle((currentPage - 1) * pageSize, pageSize);
        pageArticleVo.setArticleVos(list);
        return pageArticleVo;
    }

    @Override
    public ArticleDetailVo getArticleDetail(String id) {
        return articleMapper.getArticleDetail(id);
    }

    @Override
    public PageArticleVo getPageArticleByThemeId(Integer currentPage, Integer pageSize, String themeId) {
        PageArticleVo pageArticleVo = new PageArticleVo();
        // 如果是第一次需要去查询总数
        if (currentPage == 1) {
            LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Article::getThemeId, themeId);
            pageArticleVo.setTotal(this.count(wrapper));
        }
        pageArticleVo.setCurrentPage(currentPage);
        pageArticleVo.setPageSize(pageSize);
        List<ArticleVo> list =  articleMapper.selectPageArticleByThemeId((currentPage - 1) * pageSize, pageSize, themeId);
        pageArticleVo.setArticleVos(list);
        return pageArticleVo;
    }
}




