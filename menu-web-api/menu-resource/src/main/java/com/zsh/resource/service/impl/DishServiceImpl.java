package com.zsh.resource.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.common.result.CommonResult;
import com.zsh.resource.constant.DishConstant;
import com.zsh.resource.domain.*;
import com.zsh.resource.domain.dto.PersonalDto;
import com.zsh.resource.domain.dto.PublishDishDto;
import com.zsh.resource.domain.vo.DishCategoryVo;
import com.zsh.resource.domain.vo.DishConcentrationVo;
import com.zsh.resource.domain.vo.DishDetailVo;
import com.zsh.resource.domain.vo.MemberRecVo;
import com.zsh.resource.domain.vo.personal.*;
import com.zsh.resource.mapper.*;
import com.zsh.resource.service.DishService;
import com.zsh.resource.service.MaterialService;
import com.zsh.resource.service.MemberDishLogService;
import com.zsh.resource.service.StepService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 18179
 * @description 针对表【dish】的数据库操作Service实现
 * @createDate 2023-03-16 22:32:49
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    private final MaterialService materialService;
    private final StepService stepService;
    private final DishMapper dishMapper;
    private final RedisTemplate<Object, Object> redisTemplate;
    private final CommentMapper commentMapper;
    private final RecLogMapper recLogMapper;
    private final FollowMapper followMapper;
    private final NoteMapper noteMapper;
    private final MemberNoteLogMapper memberNoteLogMapper;
    private final MemberDishLogMapper memberDishLogMapper;

    public DishServiceImpl(RedisTemplate<Object, Object> redisTemplate, MaterialService materialService,
                           StepService stepService, DishMapper dishMapper,
                           CommentMapper commentMapper, RecLogMapper recLogMapper,
                           FollowMapper followMapper, NoteMapper noteMapper,
                           MemberNoteLogMapper memberNoteLogMapper, MemberDishLogMapper memberDishLogMapper) {
        this.redisTemplate = redisTemplate;
        this.materialService = materialService;
        this.stepService = stepService;
        this.dishMapper = dishMapper;
        this.commentMapper = commentMapper;
        this.recLogMapper = recLogMapper;
        this.followMapper = followMapper;
        this.noteMapper = noteMapper;
        this.memberNoteLogMapper = memberNoteLogMapper;
        this.memberDishLogMapper = memberDishLogMapper;
    }

    /**
     * 获取热度值推荐的数据
     */
    @Override
    public CommonResult<Object> getHotRec() {
        Object hotScore = redisTemplate.opsForValue().get(DishConstant.HOT_SCORE);
        Map<String, String> hotScoreMap = JSON.parseObject(JSON.toJSONString(hotScore), new TypeReference<>() {
        });
        // 根据所有的id查询出所有相关的菜品数据
        Set<String> dishIds = hotScoreMap.keySet();

        // 遍历每一个dishId,从hotScoreMap获取进行菜品排序
        return CommonResult.success(hotScoreMap);
    }

    /**
     * 保存发布的菜谱数据
     *
     * @param publishDishDto 需要保存的数据
     */
    // 开启事务
    @Transactional
    @Override
    public CommonResult<Object> saveDish(PublishDishDto publishDishDto) {
        // 1.数据拷贝到Dish
        Dish dish = new Dish();
        BeanUtils.copyProperties(publishDishDto, dish);
        // 2.保存材料数据
        StringBuilder materialNamesAppend = new StringBuilder();
        StringBuilder dosagesAppend = new StringBuilder();
        List<Material> materials = publishDishDto.getMaterialMenu();
        materials.forEach(material -> {
            materialNamesAppend.append(material.getName()).append(",");
            dosagesAppend.append(material.getDosage()).append(",");
        });
        String materialNames = materialNamesAppend.substring(0, materialNamesAppend.length() - 1);
        String dosages = dosagesAppend.substring(0, dosagesAppend.length() - 1);
        // 保存食材
        Material material = new Material();
        material.setName(materialNames);
        material.setDosage(dosages);
        materialService.save(material);
        // 3.保存菜谱
        dish.setSole(publishDishDto.getIsSole());
        dish.setStatus(0);
        dish.setMaterialId(material.getId());
        this.save(dish);

        // 4.保存Step
        List<Step> steps = publishDishDto.getStepMenu();
        steps.forEach(x -> {
            x.setDishId(dish.getId());
        });
        stepService.saveBatch(steps);

        return CommonResult.success();
    }

    /**
     * 获取热度值推荐的内容
     */
    @Override
    public List<DishConcentrationVo> getConcentrationMenu() {
        // 1.获取缓存中推荐的菜谱
        Object hotScore = redisTemplate.opsForValue().get(DishConstant.HOT_SCORE);
        Map<String, String> hotScoreMap = JSON.parseObject(JSON.toJSONString(hotScore), new TypeReference<>() {
        });
        Map<String, DishConcentrationVo> resMap = new HashMap<>();
        if (hotScore != null) {
            Set<String> dishIds = hotScoreMap.keySet();
            // 2.取出前8个菜谱id
            List<String> ids = dishIds.stream().limit(8).collect(Collectors.toList());
            // 3.根据id集合查询相关信息
            List<DishConcentrationVo> list = dishMapper.getConcentrationRecContent(ids);
            resMap = list.stream().collect(Collectors.toMap(DishConcentrationVo::getId, k1 -> k1));
            LambdaQueryWrapper<RecLog> wrapper = new LambdaQueryWrapper<>();
            // 拼接参数查询所有相关日志
            for (String id : ids) {
                wrapper.or().eq(RecLog::getDishId, id);
            }
            List<RecLog> recLogs = recLogMapper.selectList(wrapper);
            for (RecLog recLog : recLogs) {
                DishConcentrationVo dishVo = resMap.get(recLog.getDishId());
                dishVo.setViews(dishVo.getViews() + recLog.getClickNum());
                dishVo.setUpNum(dishVo.getUpNum() + (recLog.getIsUp() ? 1 : 0));
                dishVo.setCollectNum(dishVo.getCollectNum() + (recLog.getIsCollect() ? 1 : 0));
                dishVo.setCommentNum(dishVo.getCommentNum() + recLog.getCommentNum());
            }
        }
        List<DishConcentrationVo> res = new ArrayList<>();
        for (Map.Entry<String, DishConcentrationVo> entry : resMap.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    /**
     * 获取菜谱页详情
     *
     * @param id     菜谱id
     * @param userId 当前登录的用户id
     */
//    @Cacheable(value = "dishDetail", key = "#id + #userId")
    @Override
    public DishDetailVo getDishDetail(String id, String userId) {
        // 分为用户登录和未登录两种情况
        // 查询菜谱相关数据
        DishDetailVo dishDetail = dishMapper.getDishDetail(id);
        dishDetail.setIsUp(false);
        dishDetail.setIsCollect(false);
        // 去日志查询该菜谱点赞、收藏数量
        LambdaQueryWrapper<RecLog> recLogWrapper = new LambdaQueryWrapper<>();
        recLogWrapper.eq(RecLog::getDishId, id);
        List<RecLog> recLogs = recLogMapper.selectList(recLogWrapper);
        // 对点赞与收藏进行求和
        long upNum = 0, collectNum = 0;
        for (RecLog recLog : recLogs) {
            upNum += recLog.getIsUp() ? 1 : 0;
            collectNum += recLog.getIsCollect() ? 1 : 0;
            // 判断当前用户是否已经点赞、收藏该菜谱 [前提是userId不能为空,为空则表示该用户还未登录]
            if (StringUtils.isNotEmpty(userId) && recLog.getMemberId().equals(userId)) {
                dishDetail.setIsCollect(recLog.getIsCollect());
                dishDetail.setIsUp(recLog.getIsUp());
            }
        }
        dishDetail.setUpNum(upNum);
        dishDetail.setCollectNum(collectNum);
        // 处理菜谱用料名/菜谱用料用量
        List<Material> materials = new ArrayList<>();
        String[] materialNames = dishDetail.getMaterialName().split(",");
        String[] dosages = dishDetail.getDosage().split(",");
        for (int i = 0; i < materialNames.length; i++) {
            Material material = new Material();
            material.setName(materialNames[i]);
            material.setDosage(dosages[i]);
            materials.add(material);
        }
        dishDetail.setMaterials(materials);
        // 查询评论数据
        dishDetail.setComments(commentMapper.getCommentsByDishId(id));
        // 查看步骤相关数据
        LambdaQueryWrapper<Step> stepWrapper = new LambdaQueryWrapper<>();
        stepWrapper.eq(Step::getDishId, id);
        List<Step> steps = stepService.list(stepWrapper);
        dishDetail.setSteps(steps);
        dishDetail.setIsFollow(false);
        // 查看该用户是否已经关注该菜谱发布者用户
        if (ObjectUtils.isNotEmpty(userId)) {
            LambdaQueryWrapper<Follow> followWrapper = new LambdaQueryWrapper<>();
            followWrapper.eq(Follow::getMemberId, dishDetail.getUserId());
            followWrapper.eq(Follow::getFansId, userId);
            Integer count = followMapper.selectCount(followWrapper);
            dishDetail.setIsFollow(count > 0);
        }
        return dishDetail;
    }

    /**
     * 根据分类id获取所有的菜谱
     */
    @Override
    public IPage<DishCategoryVo> getAllDishByCategoryId(String categoryId, Integer pageSize, Integer pageNum) {
        Page<DishCategoryVo> page = new Page<>(pageNum, pageSize);
        return dishMapper.getDishPage(page, categoryId);
    }

    /**
     * 个人主页信息获取
     *
     * @param userId      当前登录的用户id
     * @param personalDto 条件信息
     */
    @Cacheable(cacheNames = "personal", key = "#root.methodName + '[' + #personalDto.userId + ']'")
    @Override
    public PersonalVo getPersonal(String userId, PersonalDto personalDto) {
        PersonalVo personalVo = new PersonalVo();
        // 个人信息获取
        MemberInfoVo memberInfoVo = dishMapper.getMemberDetailInfo(personalDto.getUserId());
        personalVo.setMemberInfoVo(memberInfoVo);
        // 菜谱分页数据
        PersonalDishPageVo personalDishPageVo = new PersonalDishPageVo();
        // 日记分页数据
        PersonalNotePageVo personalNotePageVo = new PersonalNotePageVo();
        // 收藏数据[菜谱 + 日记]
        PersonalCollectVo personalCollectVo = new PersonalCollectVo();
        PersonalDishPageVo personalDishCollectVo = new PersonalDishPageVo();
        PersonalNotePageVo personalNoteCollectVo = new PersonalNotePageVo();
        // 如果是第一次需要查询总数
        if (isFirstTime(personalDto)) {
            // 查询菜谱总数
            LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
            dishWrapper.eq(Dish::getPublisherId, userId);
            Integer dishTotal = dishMapper.selectCount(dishWrapper);
            personalDishPageVo.setTotal(dishTotal);
            // 查询日记总数
            LambdaQueryWrapper<Note> noteWrapper = new LambdaQueryWrapper<>();
            noteWrapper.eq(Note::getMemberId, userId);
            Integer noteTotal = noteMapper.selectCount(noteWrapper);
            personalNotePageVo.setTotal(noteTotal);
            // 查询收藏数据总数[菜谱 + 日记]
            LambdaQueryWrapper<RecLog> recLogWrapper = new LambdaQueryWrapper<>();
            recLogWrapper.eq(RecLog::getMemberId, userId);
            Integer dishCollectTotal = recLogMapper.selectCount(recLogWrapper);
            personalDishCollectVo.setTotal(dishCollectTotal);
            LambdaQueryWrapper<MemberNoteLog> noteLogWrapper = new LambdaQueryWrapper<>();
            noteLogWrapper.eq(MemberNoteLog::getMemberId, userId);
            Integer noteCollectTotal = memberNoteLogMapper.selectCount(noteLogWrapper);
            personalNoteCollectVo.setTotal(noteCollectTotal);
        }
        // 处理个人主页菜谱分页数据
        List<PersonalDishVo> dishPages = dishMapper.getPersonDishPage(personalDto.getUserId(),
                (personalDto.getMenuCurrentPage() - 1) * personalDto.getMenuPageSize(), personalDto.getMenuPageSize());
        personalDishPageVo.setPersonalDishVos(dishPages);
        personalDishPageVo.setCurrentPage(personalDto.getMenuCurrentPage());
        personalDishPageVo.setPageSize(personalDto.getMenuPageSize());
        personalVo.setPersonalDishPageVo(personalDishPageVo);
        // 查询笔记对应的分页数据
        List<PersonalNoteVo> notePages = dishMapper.getPersonNotePage(personalDto.getUserId(),
                (personalDto.getMenuCurrentPage() - 1) * personalDto.getMenuPageSize(), personalDto.getMenuPageSize());
        personalNotePageVo.setPersonalNoteVos(notePages);
        personalNotePageVo.setCurrentPage(personalDto.getNoteCurrentPage());
        personalNotePageVo.setPageSize(personalDto.getNotePageSize());
        personalVo.setPersonalNotePageVo(personalNotePageVo);
        // 处理收藏[菜谱 + 笔记]
        List<PersonalDishVo> dishCollectPages = memberDishLogMapper.getDishCollectPage(personalDto.getUserId(),
                (personalDto.getCollectMenuCurrentPage() - 1) * personalDto.getCollectMenuPageSize(), personalDto.getCollectMenuPageSize());
        personalDishCollectVo.setPersonalDishVos(dishCollectPages);
        personalDishCollectVo.setCurrentPage(personalDto.getCollectMenuCurrentPage());
        personalDishCollectVo.setPageSize(personalDto.getCollectMenuPageSize());
        List<PersonalNoteVo> noteCollectPages = memberNoteLogMapper.getNoteCollectPage(personalDto.getUserId(),
                (personalDto.getCollectNoteCurrentPage() - 1) * personalDto.getCollectNotePageSize(), personalDto.getCollectNotePageSize());
        personalNoteCollectVo.setPersonalNoteVos(noteCollectPages);
        personalNoteCollectVo.setCurrentPage(personalDto.getCollectNoteCurrentPage());
        personalNoteCollectVo.setPageSize(personalDto.getCollectNotePageSize());

        personalCollectVo.setPersonalDishCollectVo(personalDishCollectVo);
        personalCollectVo.setPersonalNoteCollectVo(personalNoteCollectVo);
        personalVo.setPersonalCollectVo(personalCollectVo);
        return personalVo;
    }

    private boolean isFirstTime(PersonalDto personalDto) {
        return personalDto.getMenuCurrentPage() == 1 ||
                personalDto.getNoteCurrentPage() == 1 ||
                personalDto.getCollectMenuCurrentPage() == 1 ||
                personalDto.getCollectNoteCurrentPage() == 1;
    }

    @Cacheable(cacheNames = "memberRecInfo")
    @Override
    public List<MemberRecVo> getRecMember() {
        // 从缓存中获取推荐结果
        Object obj = redisTemplate.opsForValue().get(DishConstant.MEMBER_REC);
        // 将对象反序列化为集合
        List<String> memberIds = JSON.parseObject(JSON.toJSONString(obj), new TypeReference<>() {
        });
        List<MemberRecVo> memberRecVos = dishMapper.getMemberRecInfo(memberIds);
        Map<String, MemberRecVo> memberMaps = memberRecVos.stream().collect(Collectors.toMap(MemberRecVo::getId, x -> x));
        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Follow::getMemberId, memberIds);
        List<Follow> follows = followMapper.selectList(wrapper);
        follows.forEach(item -> {
            memberMaps.get(item.getMemberId()).setFansNum( memberMaps.get(item.getMemberId()).getFansNum() + 1);
        });
        List<MemberRecVo> result = new ArrayList<>(8);
        for (Map.Entry<String, MemberRecVo> entry : memberMaps.entrySet()) {
            result.add(entry.getValue());
        }
        // 根据id集合查询相应信息
        return result;
    }
}




