package com.zsh.resource.domain.vo.personal;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsh on 2023/4/9
 * 个人信息封装返回
 * @author zsh
 */
@Data
public class PersonalVo implements Serializable {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户信息
     */
    private MemberInfoVo memberInfoVo;

    /**
     * 菜谱分页数据
     */
    private PersonalDishPageVo personalDishPageVo;

    /**
     * 日记分页数据
     */
    private PersonalNotePageVo personalNotePageVo;

    /**
     * 收藏分页数据[菜谱 + 日记]
     */
    private PersonalCollectVo personalCollectVo;
}
