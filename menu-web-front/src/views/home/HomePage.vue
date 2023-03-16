<template>
  <div>
    <!-- 轮播图-静态 -->
    <BannerFront/>
    <!-- 精选菜谱推荐 -->
    <div class="daily_selection">
      <el-row>
        <el-col :span="12">
          <div style="text-align: left;"><span class="item1">精选菜谱推荐</span></div>
        </el-col>
        <el-col :span="12">
          <div style="text-align: right; margin-top: 10px"><span class="item2">更多
          <el-icon :size="11" color="cornflowerblue">
            <ArrowRightBold/>
          </el-icon>
        </span>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 20px">
        <el-col :span="6">
          <CardTypeTwo :title="title"/>
        </el-col>
        <el-col :span="6">
          <CardTypeTwo :title="title"/>
        </el-col>
        <el-col :span="6">
          <CardTypeTwo :title="title"/>
        </el-col>
        <el-col :span="6">
          <CardTypeTwo :title="title"/>
        </el-col>
      </el-row>
      <el-row style="margin-top: 20px">
        <el-col :span="6">
          <CardTypeTwo :title="title"/>
        </el-col>
        <el-col :span="6">
          <CardTypeTwo :title="title"/>
        </el-col>
        <el-col :span="6">
          <CardTypeTwo :title="title"/>
        </el-col>
        <el-col :span="6">
          <CardTypeTwo :title="title"/>
        </el-col>
      </el-row>
    </div>
    <!-- 笔记 -->
    <div class="daily_selection">
      <el-row>
        <el-col :span="12">
          <div style="text-align: left;"><span class="item1">笔记</span></div>
        </el-col>
        <el-col :span="12">
          <div style="text-align: right; margin-top: 10px"><span class="item2">更多
          <el-icon :size="11" color="cornflowerblue">
            <ArrowRightBold/>
          </el-icon>
        </span>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 20px">
        <el-col :span="5" style="text-align: left">
          <CardTypeOne :title="title"/>
        </el-col>
        <el-col :span="5" style="text-align: left">
          <CardTypeOne :title="title"/>
        </el-col>
        <el-col :span="5" style="text-align: left">
          <CardTypeOne :title="title"/>
        </el-col>
        <el-col :span="5" style="text-align: left">
          <CardTypeOne :title="title"/>
        </el-col>
        <el-col :span="4" style="text-align: left">
          <CardTypeOne :title="title"/>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import BannerFront from '@/components/banner/BannerFront'
import CardTypeOne from '@/components/card/CardTypeOne'
import CardTypeTwo from '@/components/card/CardTypeTwo'
import { ArrowRightBold } from '@element-plus/icons'
import { ref } from 'vue'

export default {
  name: 'HomePage',
  components: {
    BannerFront,
    CardTypeOne,
    CardTypeTwo,
    ArrowRightBold
  },
  setup () {
    const title = ref('高配版阳春面')
    return {
      title
    }
  }
}
</script>

<style scoped>
.daily_selection {
  margin: 20px 10vw;
  text-align: center;
}

.item1 {
  font-size: 20px;
  font-weight: bold;
}

.item2 {
  font-size: 14px;
  color: cornflowerblue;
  cursor: pointer;
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
