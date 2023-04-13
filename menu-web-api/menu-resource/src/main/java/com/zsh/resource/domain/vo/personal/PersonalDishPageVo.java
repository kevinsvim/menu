package com.zsh.resource.domain.vo.personal;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsh on 2023/4/9
 *
 * @author zsh
 */
@Data
public class PersonalDishPageVo implements Serializable {
    private Integer total;
    private Integer currentPage;
    private Integer pageSize;
    private List<PersonalDishVo> personalDishVos;
}
