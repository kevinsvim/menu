package com.zsh.resource.service;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.ProductionLevel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 18179
* @description 针对表【production_level】的数据库操作Service
* @createDate 2023-03-16 19:01:58
*/
public interface ProductionLevelService extends IService<ProductionLevel> {
    /**
     * 获取烹饪难度和时间
     */
    CommonResult<Object> getPublishDataEcho();
}
