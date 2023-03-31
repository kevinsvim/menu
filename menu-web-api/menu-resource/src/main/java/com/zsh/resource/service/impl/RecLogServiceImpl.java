package com.zsh.resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.domain.RecLog;
import com.zsh.resource.service.RecLogService;
import com.zsh.resource.mapper.RecLogMapper;
import org.springframework.stereotype.Service;

/**
* @author 18179
* @description 针对表【rec_log】的数据库操作Service实现
* @createDate 2023-03-13 12:06:08
*/
@Service
public class RecLogServiceImpl extends ServiceImpl<RecLogMapper, RecLog>
    implements RecLogService{

    /**
     * 收藏与否
     * @param type 【0 - 取消收藏】 【1 - 收藏】
     */
    @Override
    public CommonResult<Object> updateCollect(Integer type, Long dishId, Long memberId) {
        LambdaQueryWrapper<RecLog> recLogWrapper = new LambdaQueryWrapper<>();
        recLogWrapper.eq(RecLog::getDishId, dishId).eq(RecLog::getMemberId, memberId);
        int count = this.count(recLogWrapper);
        if (count > 0) {
            this.baseMapper.updateCollectType(type == 1, dishId, memberId);
        } else {
            RecLog recLog = new RecLog();
            recLog.setIsCollect(true);
            recLog.setClickNum(0);
            recLog.setCommentNum(0);
            recLog.setDishId(dishId);
            recLog.setIsUp(false);
            recLog.setMemberId(memberId);
            this.save(recLog);
        }

        return CommonResult.success();
    }
}




