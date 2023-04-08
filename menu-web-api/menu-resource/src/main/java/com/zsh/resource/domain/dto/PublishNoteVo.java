package com.zsh.resource.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by zsh on 2023/4/2
 *
 * @author zsh
 */
@Data
public class PublishNoteVo {

    /**
     * 笔记图集
     */
    private List<String> fileList;

    /**
     * 标题
     */
    private String title;

    /**
     * 介绍
     */
    private String intro;

}
