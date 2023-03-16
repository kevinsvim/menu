package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.ProductionTime;
import com.zsh.resource.service.ProductionTimeService;
import com.zsh.resource.mapper.ProductionTimeMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【production_time】的数据库操作Service实现
* @createDate 2023-03-16 19:02:18
*/
@Service
public class ProductionTimeServiceImpl extends ServiceImpl<ProductionTimeMapper, ProductionTime>
    implements ProductionTimeService{

}




