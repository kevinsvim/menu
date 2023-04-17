package com.zsh.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsh.resource.domain.dto.PersonalDto;
import com.zsh.resource.domain.dto.PublishDishDto;
import com.zsh.resource.domain.vo.*;
import com.zsh.resource.domain.vo.personal.PersonalVo;

import java.util.List;

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

    /**
     *  获取推荐页数据
     */
    List<DishConcentrationVo> getConcentrationMenu();

    /**
     * 获取菜谱详情页数据
     * @param id 菜谱id
     */
    DishDetailVo getDishDetail(String id, String userId);

    /**
     * 根据分类id获取全部的菜谱
     */
    IPage<DishCategoryVo> getAllDishByCategoryId(String categoryId, Integer pageSize, Integer pageNum);

    /**
     * 个人主页信息获取
     *
     * @param userId
     * @param personalDto 条件信息
     * @return 个人主页信息
     */
    PersonalVo getPersonal(String userId, PersonalDto personalDto);

    List<MemberRecVo> getRecMember();

    void deleteDish(String id);
}
