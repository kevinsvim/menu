package com.zsh.resource.domain.vo;

import com.zsh.resource.domain.NoteComment;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by zsh on 2023/4/3
 *
 * @author zsh
 */
@Data
public class NoteDetailVo {

    /**
     * 日记id
     */
    private String id;

    /**
     * 日记图集
     */
    private String images;

    /**
     * 日记标题
     */
    private String title;

    /**
     * 日记内容
     */
    private String intro;

    /**
     * 发布日期
     */
    private Date publishDate;

    /**
     * 发布用户id
     */
    private String publishId;

    /**
     * 发布用户头像
     */
    private String imageUrl;

    /**
     * 发布用户名
     */
    private String username;

    /**
     * 日记评论
     */
    private List<NoteComment> noteComments;

    /**
     * 是否关注该发布者用户
     */
    private Boolean isFollow;

    /**
     * 是否收藏
     */
    private Boolean isCollect;

    /**
     * 是否点赞
     */
    private Boolean isUp;

    /**
     * 点赞数量
     */
    private Integer upNum;
}
