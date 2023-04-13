package com.zsh.resource.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.resource.domain.SearchLog;
import com.zsh.resource.service.SearchLogService;
import com.zsh.resource.mapper.SearchLogMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
* @author 18179
* @description 针对表【search_log】的数据库操作Service实现
* @createDate 2023-04-12 10:09:42
*/
@Service
public class SearchLogServiceImpl extends ServiceImpl<SearchLogMapper, SearchLog>
    implements SearchLogService{

    private final RedisTemplate<Object, Object> redisTemplate;
    public SearchLogServiceImpl(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Override
    public List<SearchLog> selectList() {
        Object obj = redisTemplate.opsForValue().get("searchList");
        if (ObjectUtils.isEmpty(obj)) {

            List<SearchLog> searchLogs = this.list();
            List<SearchLog> searchSortLogs = searchLogs.stream()
                    .sorted(Comparator.comparing(SearchLog::getSearchNum).reversed())
                    .limit(15)
                    .collect(Collectors.toList());
            redisTemplate.opsForValue().set("searchList", searchLogs);
            redisTemplate.expire("searchList", 1, TimeUnit.DAYS);
            return searchSortLogs;
        }
        return JSONObject.parseObject(JSON.toJSONString(obj), new TypeReference<>(){});
    }
}




