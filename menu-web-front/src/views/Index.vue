<template>
  <div class="home">
    <!-- 导航栏 -->
    <NavigationBar
      @publish="selectWhich"
      @homePage="selectWhich"
    />
    <!-- 首页 -->
    <HomePage
      v-show="selectStatus.selectIndex"
    />
    <!-- 发布页 -->
    <Publish
      v-show="selectStatus.selectPublish"
    />
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
import { reactive } from 'vue'

export default {
  name: 'HomeView',
  components: {
    HomePage,
    NavigationBar,
    FoodFooter,
    Publish
  },

  setup () {
    // 导航栏功能选中状态
    const selectStatus = reactive({
      selectIndex: true,
      selectPublish: false,
    })

    // 选中功能状态
    function selectWhich(type) {
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
    function getDailySection() {
      dish.getDailySelection().then(result => {

      }).catch(error => {

      })
    }

    return {
      selectStatus,
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
</style>
