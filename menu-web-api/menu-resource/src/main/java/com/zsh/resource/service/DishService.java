package com.zsh.resource.service;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsh.resource.domain.dto.PublishDishDto;

/**
* @author 18179
* @description 针对表【dish】的数据库操作Service
* @createDate 2023-03-16 22:32:49
*/
public interface DishService extends IService<Dish> {
    /**
     * 获取热度推荐
     */
    CommonResult<Object> getHotRec();

    /**
     * 保存发布的菜谱数据
     * @param publishDishDto 需要保存的数据
     */
    CommonResult<Object> saveDish(PublishDishDto publishDishDto);
}
