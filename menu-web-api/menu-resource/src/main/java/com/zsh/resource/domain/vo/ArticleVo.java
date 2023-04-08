package com.zsh.resource.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zsh on 2023/4/5
 *
 * @author zsh
 */
@Data
public class ArticleVo {
    private String id;
    private String title;
    private String author;
    private String themeId;
    private String themeTitle;
    private Date createTime;
    private String imageUrl;

}
