package com.zsh.resource.mapper;

import com.zsh.resource.domain.ProductionTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 18179
* @description 针对表【production_time】的数据库操作Mapper
* @createDate 2023-03-16 19:02:18
* @Entity com.zsh.resource.domain.ProductionTime
*/
@Mapper
public interface ProductionTimeMapper extends BaseMapper<ProductionTime> {

}




