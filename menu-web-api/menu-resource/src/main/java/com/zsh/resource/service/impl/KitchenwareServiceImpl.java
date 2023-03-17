package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.Kitchenware;
import com.zsh.resource.service.KitchenwareService;
import com.zsh.resource.mapper.KitchenwareMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【kitchenware】的数据库操作Service实现
* @createDate 2023-03-17 10:58:04
*/
@Service
public class KitchenwareServiceImpl extends ServiceImpl<KitchenwareMapper, Kitchenware>
    implements KitchenwareService{

}




