package com.zsh.resource.task;

import com.zsh.resource.domain.ItemCfAccuracy;
import com.zsh.resource.recommend.DataPreprocess.DishDataPreProcess;
import com.zsh.resource.recommend.cf.MemberHotRec;
import com.zsh.resource.recommend.cf.MenuHotValueCFRec;
import com.zsh.resource.recommend.cf.MenuItemCFRec;
import com.zsh.resource.service.ItemCfAccuracyService;
import com.zsh.resource.service.ItemRecResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zsh on 2023/3/14
 * 热度值推荐定时器(每天凌晨一点执行)
 * @author zsh
 */
@Component
@Slf4j
public class MenuHotScoreRecTimerTask {

    private final MenuHotValueCFRec hotValueCFRec;
    private final MemberHotRec memberHotRec;
    private final DishDataPreProcess dishDataPreProcess;
    private final MenuItemCFRec menuItemCFRec;
    private final ItemCfAccuracyService itemCfAccuracyService;
    private final ItemRecResultService itemRecResultService;
    public MenuHotScoreRecTimerTask(MenuHotValueCFRec hotValueCFRec, MemberHotRec memberHotRec,
                                    DishDataPreProcess dishDataPreProcess, MenuItemCFRec menuItemCFRec,
                                    ItemCfAccuracyService itemCfAccuracyService, ItemRecResultService itemRecResultService) {
        this.hotValueCFRec = hotValueCFRec;
        this.memberHotRec = memberHotRec;
        this.dishDataPreProcess = dishDataPreProcess;
        this.menuItemCFRec = menuItemCFRec;
        this.itemCfAccuracyService = itemCfAccuracyService;
        this.itemRecResultService = itemRecResultService;
    }

    // 每隔一分钟执行(用于测试)
//    @Scheduled(cron = "0 */1 * * * ?")
    // 每天凌晨一点执行(生产环境)
    @Scheduled(cron = "0 0 1 * * ?")
    public void hotScoreTaskRun() {
        // 调用热度值算法生成结果保存redis
        hotValueCFRec.recommendHotRec();
    }

    /**
     * Item CF 准确度定时器
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void itemCFRecTaskRun() {
        // 调用热度值算法生成结果保存redis
        try {
            // 数据预处理
            dishDataPreProcess.process();
            // item算法
            double precision = menuItemCFRec.precision(4, 8);
            ItemCfAccuracy itemCfAccuracy = new ItemCfAccuracy();
            itemCfAccuracy.setAccuracy(precision);
        } catch (Exception e) {
            log.error("Item Cf Accuracy Timer Task exception...");
        }
    }

    //每七天执行一次
    @Scheduled(cron = " 0 0 0 */7 * ?")
    // 每隔一分钟执行（测试）
//    @Scheduled(cron = "0 */1 * * * ?")
    public void memberRecTaskRun() {
        // 生成推荐结果保存redis
        try {
            memberHotRec.memberRecommend();
        } catch (Exception e) {
            log.error("Hot Score Rec Timer Task exception...");
        }
    }

}
