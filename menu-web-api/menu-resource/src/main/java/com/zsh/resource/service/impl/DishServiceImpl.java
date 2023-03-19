package com.zsh.resource.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.constant.DishConstant;
import com.zsh.resource.domain.Dish;
import com.zsh.resource.domain.Material;
import com.zsh.resource.domain.Step;
import com.zsh.resource.domain.dto.PublishDishDto;
import com.zsh.resource.service.DishService;
import com.zsh.resource.mapper.DishMapper;
import com.zsh.resource.service.MaterialService;
import com.zsh.resource.service.StepService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @author 18179
* @description 针对表【dish】的数据库操作Service实现
* @createDate 2023-03-16 22:32:49
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService{

    private final MaterialService materialService;
    private final StepService stepService;
    private final RedisTemplate<Object, Object> redisTemplate;
    public DishServiceImpl(RedisTemplate<Object, Object> redisTemplate, MaterialService materialService,
                           StepService stepService) {
        this.redisTemplate = redisTemplate;
        this.materialService = materialService;
        this.stepService = stepService;
    }

    /**
     * 获取热度值推荐的数据
     */
    @Override
    public CommonResult<Object> getHotRec() {
        Object hotScore = redisTemplate.opsForValue().get(DishConstant.HOT_SCORE);
        Map<String, String> hotScoreMap = JSON.parseObject(JSON.toJSONString(hotScore), new TypeReference<>() {
        });
        // 根据所有的id查询出所有相关的菜品数据
        Set<String> dishIds = hotScoreMap.keySet();

        // 遍历每一个dishId,从hotScoreMap获取进行菜品排序
        return CommonResult.success(hotScoreMap);
    }

    /**
     * 保存发布的菜谱数据
     * @param publishDishDto 需要保存的数据
     */
    // 开启事务
    @Transactional
    @Override
    public CommonResult<Object> saveDish(PublishDishDto publishDishDto) {
        // 1.数据拷贝到Dish
        Dish dish = new Dish();
        BeanUtils.copyProperties(publishDishDto, dish);
        // 2.保存材料数据
        StringBuilder materialNamesAppend = new StringBuilder();
        StringBuilder dosagesAppend = new StringBuilder();
        List<Material> materials = publishDishDto.getMaterialMenu();
        materials.forEach(material -> {
            materialNamesAppend.append(material.getName()).append(",");
            dosagesAppend.append(material.getDosage()).append(",");
        });
        String materialNames = materialNamesAppend.substring(0, materialNamesAppend.length() - 1);
        String dosages = dosagesAppend.substring(0, dosagesAppend.length() - 1);
        // 保存食材
        Material material = new Material();
        material.setName(materialNames);
        material.setDosage(dosages);
        materialService.save(material);
        // 3.保存菜谱
        dish.setSole(publishDishDto.getIsSole());
        dish.setStatus(0);
        dish.setMaterialId(material.getId());
        this.save(dish);

        // 4.保存Step
        List<Step> steps = publishDishDto.getStepMenu();
        steps.forEach(x -> {
            x.setDishId(dish.getId());
        });
        stepService.saveBatch(steps);

        return CommonResult.success();
    }
}




