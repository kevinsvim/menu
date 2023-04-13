package com.zsh.resource.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsh.resource.domain.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsh.resource.domain.vo.DishCategoryVo;
import com.zsh.resource.domain.vo.DishConcentrationVo;
import com.zsh.resource.domain.vo.DishDetailVo;
import com.zsh.resource.domain.vo.MemberRecVo;
import com.zsh.resource.domain.vo.personal.MemberInfoVo;
import com.zsh.resource.domain.vo.personal.PersonalDishVo;
import com.zsh.resource.domain.vo.personal.PersonalNoteVo;
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
    DishDetailVo getDishDetail(@Param("id") String id);

    /**
     * 根据分类id获取菜谱集合
     */
    IPage<DishCategoryVo> getDishPage (IPage<DishCategoryVo> page, @Param("categoryId") String categoryId);

    /**
     * 获取个人主页菜谱分页数据
     * @param userId
     * @param startSize 起始页
     * @param pageSize 每页大小
     */
    List<PersonalDishVo> getPersonDishPage(@Param("userId") String userId, @Param("startSize") int startSize, @Param("pageSize") Integer pageSize);

    List<PersonalNoteVo> getPersonNotePage(@Param("userId") String userId, @Param("startSize") int startSize, @Param("pageSize") Integer pageSize);

    List<MemberRecVo> getMemberRecInfo(@Param("memberIds") List<String> memberIds);

    MemberInfoVo getMemberDetailInfo(@Param("memberId") String memberId);
}




