package com.zsh.resource.mapper;

import com.zsh.resource.domain.DishRecLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.vo.personal.PersonalDishVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @author 18179
* @description 针对表【member_dish_log】的数据库操作Mapper
* @createDate 2023-04-10 11:26:12
* @Entity com.zsh.resource.domain.MemberDishLog
*/
@Mapper
public interface DishRecLogMapper extends BaseMapper<DishRecLog> {

}




