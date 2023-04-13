<template>
<div class="comic_home">
  <el-row>
    <el-col :span="18" style="text-align: left;">
      <el-row>
        <el-col :span="12"  v-for="item in comic" style="margin-top: 30px">
          <el-row>
            <el-col>
              <el-image style="width: 350px; height: 350px; cursor: pointer" :src="item.imageUrl" :fit="'fill'" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :offset="9">
              <el-link :underline="false">{{ item.title }}</el-link>
            </el-col>
          </el-row>
        </el-col>
      </el-row>

    </el-col>
    <el-col :span="6" style="text-align: left">
      <div style="margin-top: 30px">
        <span style="font-weight: bold">小游戏</span>
      </div>
      <div style="margin-top: 15px" v-for="item in game">
          <el-link :underline="false" :href="item.link">
            <span style="font-size: 12px">{{ item.name }}</span>
          </el-link>
      </div>
    </el-col>
  </el-row>

</div>
  <FoodFooter/>
</template>

<script>
import FoodFooter from "@/views/footer/FoodFooter";
import comicApi from '@/api/comic'
import gameApi from '@/api/game'
import { reactive, getCurrentInstance } from "vue";
import {COMIC_CONSTANT, COMIC_EVENT} from "@/utils/nav";

export default {
  name: "ComicList",
  components: {
    FoodFooter
  },
  setup() {
    const comic = reactive([])
    const game = reactive([])
    // 通知导航栏
    // 获取到 全局事件总线
    const { Bus }  = getCurrentInstance().appContext.config.globalProperties
    // 修改导航栏的状态
    const updateSelectState = () => {
      localStorage.setItem('navSelect', COMIC_CONSTANT)
      // 通知导航栏组件刷新选中状态
      Bus.emit(COMIC_EVENT, { state: true })
    }
    // 获取动漫列表
    const getComicList = () => {
      comicApi.getAllComic().then(res => {
        comic.push(...res.data)
      })
    }
    // 获取游戏列表
    const getGameList = () => {
      gameApi.getGames().then(res => {
        gameApi.getGames().then(res => {
          game.push(...res.data)
        })
      })
    }
    updateSelectState()
    getComicList()
    getGameList()
    return {
      comic,
      game
    }
  }
}
</script>

<style scoped>
.comic_home {
  padding: 0 15vw;
}
</style>
