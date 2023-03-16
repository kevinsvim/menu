package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.ProductionLevel;
import com.zsh.resource.domain.ProductionTime;
import com.zsh.resource.domain.vo.ProductLevelAndTimeVo;
import com.zsh.resource.service.ProductionLevelService;
import com.zsh.resource.mapper.ProductionLevelMapper;
import com.zsh.resource.service.ProductionTimeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 18179
* @description 针对表【production_level】的数据库操作Service实现
* @createDate 2023-03-16 19:01:58
*/
@Service
public class ProductionLevelServiceImpl extends ServiceImpl<ProductionLevelMapper, ProductionLevel>
    implements ProductionLevelService{

    private final ProductionTimeService timeService;
    public ProductionLevelServiceImpl(ProductionTimeService timeService) {
        this.timeService = timeService;
    }
    @Override
    public CommonResult<Object> getAllProductLevelAndTime() {

        List<ProductionTime> productionTimes = timeService.list();
        List<ProductionLevel> productionLevels = this.list();
        return CommonResult.success(new ProductLevelAndTimeVo(productionLevels, productionTimes));
    }
}




