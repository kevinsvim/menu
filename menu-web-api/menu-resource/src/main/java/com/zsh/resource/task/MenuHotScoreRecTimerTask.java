package com.zsh.resource.task;

import com.zsh.resource.recommend.cf.MemberHotRec;
import com.zsh.resource.recommend.cf.MenuHotValueCFRec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    public MenuHotScoreRecTimerTask(MenuHotValueCFRec hotValueCFRec, MemberHotRec memberHotRec) {
        this.hotValueCFRec = hotValueCFRec;
        this.memberHotRec = memberHotRec;
    }

    // 每隔一分钟执行(用于测试)
//    @Scheduled(cron = "0 */1 * * * ?")
    // 每天凌晨一点执行(生产环境)
    @Scheduled(cron = "0 0 1 * * ?")
    public void hotScoreTaskRun() {
        // 调用热度值算法生成结果保存redis
        try {
            hotValueCFRec.recommendHotRec();
        } catch (Exception e) {
            log.error("Hot Score Rec Timer Task exception...");
        }
    }
    //每七天执行一次
    @Scheduled(cron=" 0 0 0 */7 * ?")
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
