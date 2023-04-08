<template>
  <div style="padding-left: 17vw; padding-right: 35vw">
    <div>
      <div>
        <span style="font-size: 23px; font-weight: bold">{{ article.title }}</span>
      </div>
      <div style="display: flex; align-items: center; justify-content: center; margin-top: 15px">
        <div style="flex: 1">
          <span>来自: {{ article.themeTitle }}</span>
        </div>
        <div style="flex: 1">
        <span>
          作者: {{ article.author }}
        </span>
        </div>
        <div style="flex: 1; margin-top: 3px">
        <span style="color: #999">
          {{ article.createTime }}
        </span>
        </div>
        <div style="flex: 1">
        <span style="color: #999">
          {{ article.readingTimes }}次阅读
        </span>
        </div>
      </div>
      <el-divider/>
    </div>
    <div style="text-align: left">
      <span style="font-size: 17px; font-weight: bold;">{{ article.title }}</span>
    </div>
    <div v-html="article.content"></div>
    <div>{{ article.id }}</div>
  </div>
  <FoodFooter/>
</template>

<script>
import articleApi from '@/api/article'
import {useRoute} from "vue-router/dist/vue-router";
import {ref, reactive, getCurrentInstance} from "vue";
import FoodFooter from "@/views/footer/FoodFooter";
import {
  ARTICLE_CONSTANT,
  ARTICLE_EVENT,
  COMIC_CONSTANT,
  HOME_CONSTANT,
  MENU_CONSTANT,
  NOTE_CONSTANT,
} from "@/utils/nav";

export default {
  name: "ArticleDetail",
  components: {
    FoodFooter
  },
  setup() {
    const routes = useRoute()
    const article = reactive({
      // 文本内容
      content: '',
      // 标题
      title: '',
      // 分类
      themeTitle: '',
      author: '',
      createTime: '',
      readingTimes: 0
    })
    // 获取到 全局事件总线
    const {Bus} = getCurrentInstance().appContext.config.globalProperties
    if (routes.query.id) {
      articleApi.getArticleDetail(routes.query.id).then(res => {
        let data = res.data
        article.content = data.content
        article.title = data.title
        article.themeTitle = data.themeTitle
        article.author = data.author
        article.createTime = data.createTime
        article.readingTimes = data.readingTimes
      })
    }
    // 修改导航栏的状态
    const updateSelectState = () => {
      localStorage.setItem(HOME_CONSTANT, 'unselect')
      localStorage.setItem(MENU_CONSTANT, 'unselect')
      localStorage.setItem(NOTE_CONSTANT, 'unselect')
      localStorage.setItem(COMIC_CONSTANT, 'unselect')
      localStorage.setItem(ARTICLE_CONSTANT, 'select')

      // 通知导航栏组件刷新选中状态
      Bus.emit(ARTICLE_EVENT, {state: true})
    }
    updateSelectState()
    return {
      article
    }
  }
}
</script>

<style scoped>

</style>
