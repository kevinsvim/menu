package com.zsh.resource.recommend.DataPreprocess;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsh.resource.constant.DishConstant;
import com.zsh.resource.domain.MemberDishLog;
import com.zsh.resource.domain.Step;
import com.zsh.resource.domain.Type;
import com.zsh.resource.domain.dto.DishPreData;
import com.zsh.resource.mapper.MemberDishLogMapper;
import com.zsh.resource.mapper.StepMapper;
import com.zsh.resource.mapper.TypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by zsh on 2023/4/13
 * 对菜谱数据进行预处理
 *
 * @author zsh
 */
@Slf4j
@Component
public class DishDataPreProcess {

    private final MemberDishLogMapper memberDishLogMapper;
    private final TypeMapper typeMapper;
    private final StepMapper stepMapper;
    private final RedisTemplate<Object, Object> redisTemplate;
    private final String fileName = System.getProperty("user.dir") + "/menu-resource/src/main/java/com/zsh/resource/recommend/data/dish_ratings.dat";
    private String encoding = "UTF-8";
    private PrintWriter writer;

    public DishDataPreProcess(MemberDishLogMapper memberDishLogMapper, TypeMapper typeMapper,
                              StepMapper stepMapper, RedisTemplate<Object, Object> redisTemplate) {
        this.memberDishLogMapper = memberDishLogMapper;
        this.typeMapper = typeMapper;
        this.stepMapper = stepMapper;
        this.redisTemplate = redisTemplate;
    }

    public void process() {
        // 1.加载所有的菜谱相关数据
        List<DishPreData> dishList = memberDishLogMapper.selectDishList();
        // 2.查询所有相关标签
        List<Type> types = typeMapper.selectList(null);
        Map<String, Type> typeMap = types.stream().collect(Collectors.toMap(Type::getId, Function.identity()));
        // 3.查出所有步骤
        List<Step> steps = stepMapper.selectList(null);
        Map<String, List<String>> stepMap = steps.stream()
                .collect(Collectors.groupingBy(Step::getDishId, Collectors.mapping(Step::getContent, Collectors.toList())));
        // 4.数据写入文件
        try {
            writer = new PrintWriter(fileName, encoding);
            for (DishPreData dishPreData : dishList) {
                // 步骤
                String step = StringUtils.join(stepMap.get(dishPreData.getId()), "&&");
                // 标签
                Type threeType = typeMap.get(dishPreData.getTypeId());
                Type twoType = typeMap.get(threeType.getParentId());
                Type oneType = typeMap.get(twoType.getParentId());
                String tags = oneType.getName() + "@@" + twoType.getName() + "@@" + threeType.getName();
                String content = dishPreData.getId() + "::" +
                        dishPreData.getName() + "::" + dishPreData.getMaterialName() + "::" +
                        tags + "::" + step;
                writer.println(content);
            }
            writer.close();
            // 预处理日志数据
            List<MemberDishLog> memberDishLogs = memberDishLogMapper.selectList(null);
            // 计算点击率、点赞、收藏数量
            Map<String, Integer> clickCount = new HashMap<>();
            Map<String, Integer> upCount = new HashMap<>();
            Map<String, Integer> collectCount = new HashMap<>();
            for (MemberDishLog memberDishLog : memberDishLogs) {
                String dishId = memberDishLog.getDishId();
                if (clickCount.containsKey(dishId)) {
                    clickCount.compute(dishId, (k, v) -> v + memberDishLog.getClickNum());
                } else {
                    clickCount.put(dishId, memberDishLog.getClickNum() > 0 ? memberDishLog.getClickNum() : 0);
                }
                if (upCount.containsKey(dishId)) {
                    upCount.compute(dishId, (k, v) -> v + (memberDishLog.getIsUp() ? 1 : 0));
                } else {
                    upCount.put(dishId, memberDishLog.getIsUp() ? 1 : 0);
                }
                if (collectCount.containsKey(dishId)) {
                    collectCount.compute(dishId, (k, v) -> v + (memberDishLog.getIsCollect() ? 1 : 0));
                } else {
                    collectCount.put(dishId, memberDishLog.getIsCollect() ? 1 : 0);
                }
            }
            // 存储到redis
            redisTemplate.opsForValue().set(DishConstant.DISH_CLICK_NUM, clickCount);
            redisTemplate.opsForValue().set(DishConstant.DISH_UP_NUM, upCount);
            redisTemplate.opsForValue().set(DishConstant.DISH_COLLECT_NUM, collectCount);

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            log.error("DishDataPreProcess error, cause: {}, msg: {}", e.getCause(), e.getMessage());
        }
    }

    public static void main(String[] args) {
        String fileName = System.getProperty("user.dir") + "/menu-resource/src/main/java/com/zsh/resource/recommend/data/rating.dat";
        String encoding = "UTF-8";
        try {
            PrintWriter writer = new PrintWriter(fileName, encoding);
            System.out.println(System.getProperty("user.dir"));
            writer.println("111" + "::" + "111" + "::" + 7.8 + "::" + 1546843);
            writer.println("111" + "::" + "111" + "::" + 7.8 + "::" + 1546843);
            writer.close();
        } catch (IOException e) {
            log.error("cause: {}, msg: {}", e.getCause(), e.getMessage());
        }
    }
}
