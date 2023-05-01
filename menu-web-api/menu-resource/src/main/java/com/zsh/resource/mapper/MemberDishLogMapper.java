package com.zsh.resource.mapper;

import com.zsh.resource.domain.MemberDishLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.dto.DishPreData;
import com.zsh.resource.domain.vo.personal.PersonalDishVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @author 18179
* @description 针对表【member_dish_log】的数据库操作Mapper
* @createDate 2023-04-10 12:12:52
* @Entity com.zsh.resource.domain.MemberDishLog
*/
@Mapper
public interface MemberDishLogMapper extends BaseMapper<MemberDishLog> {

    List<PersonalDishVo> getDishCollectPage(@Param("userId") String userId, @Param("startSize") int startSize, @Param("pageSize") Integer pageSize);


    List<DishPreData> selectDishList();
}




