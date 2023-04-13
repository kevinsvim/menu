package com.zsh.resource.domain.vo.personal;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zsh on 2023/4/10
 * 个人收藏信息封装
 * @author zsh
 */
@Data
public class PersonalCollectVo implements Serializable {
    /**
     * 菜谱收藏
     */
    private PersonalDishPageVo personalDishCollectVo;

    /**
     * 日记收藏
     */
    private PersonalNotePageVo personalNoteCollectVo;
}
