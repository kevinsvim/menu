package com.zsh.resource.recommend.DataPreprocess;

import com.zsh.resource.domain.MemberDishLog;
import com.zsh.resource.mapper.MemberDishLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by zsh on 2023/4/13
 * 对商品树进行预处理
 * @author zsh
 */
@Slf4j
@Component
public class DishDataPreProcess {

    private final MemberDishLogMapper memberDishLogMapper;

    public DishDataPreProcess(MemberDishLogMapper memberDishLogMapper) {
        this.memberDishLogMapper = memberDishLogMapper;
    }
    public void process() {
        // 1.加载出所有菜谱日志评分数据
        List<MemberDishLog> dishLogs = memberDishLogMapper.selectList(null);
        // 2.对每条数据进行计算评分
        if (ObjectUtils.isNotEmpty(dishLogs)) {
            // 3.生成训练数据文件
            String fileName = System.getProperty("user.dir") + "/menu-resource/src/main/java/com/zsh/resource/recommend/data/dish_ratings.dat";
            String encoding = "UTF-8";
            PrintWriter writer;
            try {
                writer = new PrintWriter(fileName, encoding);
                dishLogs.forEach(item -> {
                    // 计算当前条数据的分数
                    Integer clickNum = item.getClickNum();
                    Integer commentNum = item.getCommentNum();
                    boolean isCollect = item.getIsCollect();
                    Boolean isUp = item.getIsUp();
                    double score;
                    if (clickNum == 0 && commentNum == 0 && !isCollect && !isUp) {
                        score = item.getInitScore();
                    } else {
                        score = 0.20 * (clickNum > 10 ? 10 : clickNum)
                                + 0.30 * (commentNum > 10 ? 10 : commentNum)
                                + 0.35 * (isCollect ? 10 : 0)
                                + 0.15 * (isUp ? 10 : 0);
                        DecimalFormat df = new DecimalFormat("#.00");
                        score = Double.parseDouble(df.format(score));
                    }

                    // 将时间转换为时间戳
                    long timestamp = item.getCreateTime().getTime() / 1000L;
                    writer.println(item.getMemberId() + "::" + item.getDishId() + "::" + score + "::" + timestamp);
                });
                writer.close();
            }catch (IOException e) {
                log.error("cause: {}, msg: {}", e.getCause(), e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        String fileName = System.getProperty("user.dir") + "/menu-resource/src/main/java/com/zsh/resource/recommend/data/rating.dat";
        String encoding = "UTF-8";
        try {
            PrintWriter writer = new PrintWriter(fileName, encoding);
            System.out.println (System.getProperty("user.dir"));
            writer.println("111" + "::" + "111" + "::" + 7.8 + "::" + 1546843);
            writer.println("111" + "::" + "111" + "::" + 7.8 + "::" + 1546843);
            writer.close();
        }catch (IOException e) {
            log.error("cause: {}, msg: {}", e.getCause(), e.getMessage());
        }
    }
}
