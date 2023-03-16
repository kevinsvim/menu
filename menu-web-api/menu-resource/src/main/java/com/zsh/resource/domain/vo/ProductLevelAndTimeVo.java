package com.zsh.resource.domain.vo;

import com.zsh.resource.domain.ProductionLevel;
import com.zsh.resource.domain.ProductionTime;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by zsh on 2023/3/16
 *
 * @author zsh
 */
@Data
@AllArgsConstructor
public class ProductLevelAndTimeVo {
    private List<ProductionLevel> productionLevels;
    private List<ProductionTime> productionTimes;
}
