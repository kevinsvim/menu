package com.zsh.resource.mapper;

import com.zsh.resource.domain.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.vo.ArticleDetailVo;
import com.zsh.resource.domain.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @author 18179
* @description 针对表【article】的数据库操作Mapper
* @createDate 2023-04-05 16:39:03
* @Entity com.zsh.resource.domain.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleVo> selectPageArticle(@Param("startSize") Integer startSize, @Param("pageSize") Integer pageSize);

    ArticleDetailVo getArticleDetail(@Param("id")String id);

    List<ArticleVo> selectPageArticleByThemeId(@Param("startSize")int startSize, @Param("pageSize") Integer pageSize, @Param("themeId") String themeId);

}




