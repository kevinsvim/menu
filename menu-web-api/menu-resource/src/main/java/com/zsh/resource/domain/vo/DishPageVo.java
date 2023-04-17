package com.zsh.resource.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by zsh on 2023/4/13
 *
 * @author zsh
 */
@Data
public class DishPageVo {
    private Integer currentPage;
    private Integer pageSize;
    private Integer total;

    List<DishAuditVo> dishPageVos;
}
