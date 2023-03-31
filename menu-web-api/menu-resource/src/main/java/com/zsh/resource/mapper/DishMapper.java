package com.zsh.resource.mapper;

import com.zsh.resource.domain.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.vo.DishConcentrationVo;
import com.zsh.resource.domain.vo.DishDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @author 18179
* @description 针对表【dish】的数据库操作Mapper
* @createDate 2023-03-16 22:32:49
* @Entity com.zsh.resource.domain.Dish
*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
    /**
     * 根据id集合获取对应的信息
     */
    List<DishConcentrationVo> getConcentrationRecContent(@Param("ids") List<String> ids);

    /**
     * 查询菜谱详情数据
     */
    DishDetailVo getDishDetail(Long id);
}




