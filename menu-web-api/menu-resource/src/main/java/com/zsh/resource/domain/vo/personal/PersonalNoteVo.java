package com.zsh.resource.domain.vo.personal;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zsh on 2023/4/10
 *
 * @author zsh
 */
@Data
public class PersonalNoteVo implements Serializable {

    /**
     * 日记id
     */
    private String id;

    /**
     * 日记图片【注意是字符串集合("url1,url2,url3")，后续需要分割处理】
     */
    private String imageUrls;

    /**
     * 日记标题
     */
    private String title;

    /**
     * 发布人id
     */
    private String publisherId;

    /**
     * 点赞量
     */
    private Integer upNum;

}
