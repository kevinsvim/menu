package com.zsh.resource.domain.dto;

import lombok.Data;

/**
 * Created by zsh on 2023/4/9
 * 个人主页请求条件封装
 * @author zsh
 */
@Data
public class PersonalDto {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 菜谱当前页
     */
    private Integer menuCurrentPage;

    /**
     * 菜谱每页大小
     */
    private Integer menuPageSize;

    /**
     * 笔记当前页
     */
    private Integer noteCurrentPage;

    /**
     * 笔记每页大小
     */
    private Integer notePageSize;

    /**
     * 收藏菜谱当前页
     */
    private Integer collectMenuCurrentPage;

    /**
     * 收藏菜谱每页大小
     */
    private Integer collectMenuPageSize;

    /**
     * 收藏日记当前页
     */
    private Integer collectNoteCurrentPage;

    /**
     * 收藏日记每页大小
     */
    private Integer collectNotePageSize;
}
