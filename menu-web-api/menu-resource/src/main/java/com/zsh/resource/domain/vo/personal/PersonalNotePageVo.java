package com.zsh.resource.domain.vo.personal;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsh on 2023/4/10
 *
 * @author zsh
 */
@Data
public class PersonalNotePageVo implements Serializable {
    private Integer total;
    private Integer currentPage;
    private Integer pageSize;
    private List<PersonalNoteVo> personalNoteVos;
}
