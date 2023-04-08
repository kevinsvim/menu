package com.zsh.resource.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zsh on 2023/4/6
 * 文章详情页VO
 * @author zsh
 */
@Data
public class ArticleDetailVo {
    /**
     * 文章id
     */
    private String id;

    private String title;

    private String content;

    private String themeId;

    private String themeTitle;

    private String imageUrl;

    private Date createTime;

    private String author;

    private Integer readingTimes;
}
