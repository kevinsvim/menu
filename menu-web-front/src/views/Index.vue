<template>
  <div class="home"
       v-loading="indexLoading"
       element-loading-text="Loading..."
       :element-loading-spinner="svg"
       element-loading-svg-view-box="-10, -10, 50, 50"
       element-loading-background="rgba(122, 122, 122, 0.8)"
  >
    <!-- 导航栏 -->
    <NavigationBar
      @publish="selectWhich"
      @homePage="selectWhich"
    />
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
    <!-- 尾部-->
    <FoodFooter/>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import NavigationBar from '@/views/home/NavigationBar'
import FoodFooter from '@/views/footer/FoodFooter'
import dish from '@/api/dish'
import Publish from '@/views/home/PublishMenu'
import HomePage from '@/views/home/HomePage'
import { reactive, ref } from 'vue'
import BannerFront from '@/components/banner/BannerFront'
import CardTypeOne from '@/components/card/CardTypeOne'
import CardTypeTwo from '@/components/card/CardTypeTwo'
import { ArrowRightBold } from '@element-plus/icons'

export default {
  name: 'HomeView',
  components: {
    HomePage,
    NavigationBar,
    FoodFooter,
    Publish,
    BannerFront,
    CardTypeOne,
    CardTypeTwo,
    ArrowRightBold
  },

  setup () {
    const indexLoading = ref(false)
    const svg = `
        <path class="path" d="
          M 30 15
          L 28 17
          M 25.61 25.61
          A 15 15, 0, 0, 1, 15 30
          A 15 15, 0, 1, 1, 27.99 7.5
          L 15 15
        " style="stroke-width: 4px; fill: rgba(0, 0, 0, 0)"/>
      `
    const title = ref('高配版阳春面')
    // 导航栏功能选中状态
    const selectStatus = reactive({
      selectIndex: true,
      selectPublish: false,
    })

    // 选中功能状态
    function selectWhich (type) {
      console.log(type)
      selectStatus.selectPublish = false
      selectStatus.selectIndex = false
      switch (type) {
        case 1:
          selectStatus.selectIndex = true
          break
        case 6:
          selectStatus.selectPublish = true
          break
        default:
          break
      }
    }

    const router = useRouter()
    const login = () => {
      router.push('/login')
    }

    const register = () => {
      router.push('/register')
    }

    const logout = () => {
      router.push('/logout')
    }

    /**
     * 加载每日精选菜谱
     */
    function getDailySection () {
      dish.getDailySelection().then(result => {

      }).catch(error => {

      })
    }

    return {
      selectStatus,
      indexLoading,
      svg,
      title,
      selectWhich,
      login,
      register,
      logout
    }
  }
}
</script>
<style lang="less" scoped>
.home {
  .daily_selection {
    margin: 20px 10vw;
    text-align: center;

    .item1 {
      font-size: 20px;
      font-weight: bold;
    }

    .item2 {
      font-size: 14px;
      color: cornflowerblue;
      cursor: pointer;
    }
  }
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
