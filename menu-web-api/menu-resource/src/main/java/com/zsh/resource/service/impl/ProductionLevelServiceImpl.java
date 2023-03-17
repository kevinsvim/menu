package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.*;
import com.zsh.resource.domain.vo.ProductLevelAndTimeVo;
import com.zsh.resource.service.*;
import com.zsh.resource.mapper.ProductionLevelMapper;
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
    private final FlavorService flavorService;
    private final CookTechnologyService cookTechnologyService;
    private final KitchenwareService kitchenwareService;
    public ProductionLevelServiceImpl(ProductionTimeService timeService, FlavorService flavorService,
                                      CookTechnologyService cookTechnologyService, KitchenwareService kitchenwareService) {
        this.timeService = timeService;
        this.flavorService = flavorService;
        this.cookTechnologyService = cookTechnologyService;
        this.kitchenwareService = kitchenwareService;
    }
    @Override
    public CommonResult<Object> getPublishDataEcho() {

        List<ProductionTime> productionTimes = timeService.list();
        List<ProductionLevel> productionLevels = this.list();
        List<Flavor> flavors = flavorService.list();
        List<CookTechnology> cookTechnologies = cookTechnologyService.list();
        List<Kitchenware> kitchenwares = kitchenwareService.list();
        return CommonResult.success(new ProductLevelAndTimeVo(productionLevels, productionTimes, flavors, cookTechnologies, kitchenwares));
    }

}




