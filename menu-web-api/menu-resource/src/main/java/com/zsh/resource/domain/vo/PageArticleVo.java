package com.zsh.resource.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by zsh on 2023/4/5
 *
 * @author zsh
 */
@Data
public class PageArticleVo {
    private Integer total;
    private Integer currentPage;
    private Integer pageSize;
    private List<ArticleVo> articleVos;
}
