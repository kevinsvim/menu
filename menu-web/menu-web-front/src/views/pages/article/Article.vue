<template>
  <div class="article_home">
    <div v-show="isShowReturn && activeName=== 'theme'" style="text-align: left">
      <el-button type="primary" @click="toLastStep">
        <iconfont-svg icon="icon-shangyibu" size="20"/>
        上一步</el-button>
    </div>
    <div style="margin-left: 10px">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="全部文章" name="article">
          <div v-if="article.records.length !== 0">
            <el-row :gutter="30">
              <el-col :span="16">
                <el-row v-for="item in article.records" style="margin-top: 20px;">
                  <el-col @click="toDetail(item.id)" :span="8" style="text-align: center">
                    <img :src="item.imageUrl"
                         style="width: 210px; height: 115px; border-radius:8px; cursor: pointer">
                  </el-col>
                  <el-col :span="16" style="text-align: left">
                    <div class="article_title">
                      <span class="article_title">{{ item.title }}</span>
                    </div>
                    <el-row style="text-align: left">
                      <el-col :span="12">
                        <span style="line-height: 40px; font-size: 13px">来自：{{ item.themeTitle }}</span>
                      </el-col>
                      <el-col :span="12">
                        <span style="line-height: 40px; font-size: 13px">作者：{{ item.author }}</span>
                      </el-col>
                    </el-row>
                    <el-row>
                      <span style="font-size: 13px; color: #999; line-height: 30px">{{ item.createTime }}</span>
                    </el-row>
                    <el-row>
                      <span @click="() => toDetail(item.id)" style="font-size: 14px; color: rgb(37, 109, 198); line-height: 30px; cursor:pointer;">查看全文</span>
                    </el-row>
                  </el-col>

                </el-row>
                <div style="margin-top: 40px; margin-bottom: 50px">
                  <el-pagination
                    v-model:current-page="article.currentPage"
                    v-model:page-size="article.pageSize"
                    :disabled=false
                    :background=true
                    layout="prev, pager, next, jumper"
                    :total="article.total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                  />
                </div>
              </el-col>
              <el-col :span="7" :offset="1">
                <div style="text-align: left">
                  <span style="font-size: 15px; font-weight: bold">主题站</span>
                </div>
                <div style="display: flex; text-align: left;" v-for="item in 6">
                  <div style="flex: 2; margin-top: 20px">
                    <el-avatar shape="square" :size="70" :src="'https://cp1.douguo.com/upload/post/1357362275.png'"/>
                  </div>
                  <div style="flex: 5; margin-top: 20px">
                    <div>
                      <span>生活小窍门</span>
                    </div>
                    <div>
                      <p class="intro">
                        搜罗世界各地美食，领略全球饮食文化，绘制你的专属美食地图。领略全球饮食文化，绘制你的专属美食地图。</p>
                    </div>
                  </div>
                </div>

              </el-col>
            </el-row>
          </div>
          <div v-else>
            <el-empty description="还没有数据!" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="全部主题站" name="theme">
          <div v-show="isShowTheme">
            <el-row>
              <el-col :span="9" v-for="item in themes" style="text-align: left;">
                <div class="card2" @click="() => toArticleListByThemeId(item.id, item.title)">
                  <div style="height: 6vw; width: 319px; padding-left: 15px; padding-top: 15px;;">
                    <el-row>
                      <el-col :span="8">
                        <el-avatar shape="square" :size="90" :src="item.imageUrl"/>
                      </el-col>
                      <el-col :span="15">
                        <div>
                          <span>{{ item.title }}</span>
                        </div>
                        <div>
                          <span style="font-size: 12px; color: #999;">{{ item.description }}</span>
                        </div>
                      </el-col>
                    </el-row>
                  </div>
                  <div style="height: 3vw; width: 100%; background-color: rgb(250, 250, 250);">
                    <el-row justify="end" style="text-align: right; padding-right: 10px">
                      <el-col :span="23">
                        <span style="font-size: 13px">{{ item.articleNum }}篇文章</span>
                      </el-col>
                    </el-row>
                  </div>
                </div>
              </el-col>
            </el-row>
          </div>
          <div v-show="!isShowTheme" style="text-align: left">
            <div style="margin-top: 20px">
              <span style="font-weight: bold; font-size: 20px; line-height: 20px;">{{ currentThemeTitle }}</span>
            </div>
            <div v-if="articleWithCondition.records.length !== 0">
              <el-row :gutter="30">
                <el-col :span="16">
                  <el-row v-for="item in articleWithCondition.records" style="margin-top: 20px;">
                    <el-col :span="8" style="text-align: left">
                      <img :src="item.imageUrl"
                           style="width: 210px; height: 115px; border-radius:8px">
                    </el-col>
                    <el-col :span="16" style="text-align: left">
                      <div class="article_title">
                        <span>{{ item.title }}</span>
                      </div>
                      <el-row style="text-align: left">
                        <el-col :span="12">
                          <span style="line-height: 40px; font-size: 13px">来自：{{ item.themeTitle }}</span>
                        </el-col>
                        <el-col :span="12">
                          <span style="line-height: 40px; font-size: 13px">作者：{{ item.author }}</span>
                        </el-col>
                      </el-row>
                      <el-row>
                        <span style="font-size: 13px; color: #999; line-height: 30px">{{ item.createTime }}</span>
                      </el-row>
                      <el-row>
                        <span @click="() => toDetail(item.id)"
                          style="font-size: 14px; color: rgb(37, 109, 198); line-height: 30px; cursor:pointer;">查看全文</span>
                      </el-row>
                    </el-col>

                  </el-row>
                  <div style="margin-top: 40px; margin-bottom: 50px">
                    <el-pagination
                      v-model:current-page="article.currentPage"
                      v-model:page-size="article.pageSize"
                      :disabled=false
                      :background=true
                      layout="prev, pager, next, jumper"
                      :total="article.total"
                      @size-change="handleSizeChange"
                      @current-change="handleCurrentChange"
                    />
                  </div>
                </el-col>
              </el-row>

            </div>
            <div v-else>
              <el-empty description="还没有数据!" />
            </div>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

  </div>
</template>

<script>
import articleApi from '@/api/article'
import {ref, reactive, getCurrentInstance, onMounted} from "vue";
import dayjs from "dayjs";
import {useRouter} from "vue-router/dist/vue-router";
import {
  ARTICLE_CONSTANT,
  ARTICLE_EVENT,
  COMIC_CONSTANT,
  HOME_CONSTANT,
  MENU_CONSTANT,
  NOTE_CONSTANT
} from "@/utils/nav";
import IconfontSvg from "@/components/iconfonts/IconSvg";

export default {
  name: "Article",
  components: {IconfontSvg},
  setup() {
    const router = useRouter()
    // 切换tab激活变量
    const activeName = ref('article')
    // 所有的分页文章数据
    let article = reactive({
      pageSize: 10,
      currentPage: 1,
      total: 0,
      records: []
    })
    // 根据themeId查询的所有分页文章数据
    const articleWithCondition = reactive({
      pageSize: 10,
      currentPage: 1,
      total: 0,
      records: []
    })
    const isShowReturn = ref(false)
    // 所有主题theme
    const themes = reactive([])
    const currentThemeTitle = ref('')
    // 通知导航栏发生变更
    // 获取到 全局事件总线
    const {Bus} = getCurrentInstance().appContext.config.globalProperties
    // 修改导航栏的状态
    const updateSelectState = () => {
      localStorage.setItem('navSelect', ARTICLE_CONSTANT)
      // 通知导航栏组件刷新选中状态
      Bus.emit(ARTICLE_EVENT, {state: true})
    }
    const isShowTheme = ref(true)
    // 获取之前localStorage保存状态变量
    onMounted(() => {
      let state = localStorage.getItem('article')
      if (state === null) {
        localStorage.setItem('article', activeName.value)
      } else {
        activeName.value = state
      }
    })
    // 获取文章
    const getArticles = () => {
      articleApi.getPageArticle(article.currentPage, article.pageSize).then(res => {
        article.records = []
        article.total = res.data.total
        article.pageSize = res.data.pageSize
        article.currentPage = res.data.currentPage
        res.data.articleVos.map(x => {
          x.createTime = dayjs(x.createTime).format('YYYY-MM-DD')
          article.records.push(x)
        })
      })
    }
    const handleSizeChange = (val) => {
      console.log(`${val} items per page`)
    }
    const handleCurrentChange = (val) => {
      article.currentPage = val
      getArticles()
    }
    // 去详情页
    const toDetail = (id) => {
      let data = router.resolve({path: '/articleDetail', query: {id: id}})
      window.open(data.href, '_blank')
    }
    // 点击tab切换时
    const handleClick = (tab, event) => {
      if (activeName.value === 'article') {
        activeName.value = 'theme'
      }
      localStorage.setItem('article', activeName.value)
    }
    // 获取所有主题分类
    const getThemes = () => {
      articleApi.getThemes().then(res => {
        themes.push(...res.data)
      })
    }
    // 根据themeId获取具体分类下的所有分页数据
    const toArticleListByThemeId = (themeId, themeName) => {
      currentThemeTitle.value = themeName
      articleApi.getPageArticleByThemeId(articleWithCondition.currentPage,articleWithCondition.pageSize, themeId).then(res => {
        articleWithCondition.records = []
        articleWithCondition.total = res.data.total
        articleWithCondition.pageSize = res.data.pageSize
        articleWithCondition.currentPage = res.data.currentPage
        res.data.articleVos.map(x => {
          x.createTime = dayjs(x.createTime).format('YYYY-MM-DD')
          articleWithCondition.records.push(x)
        })
        isShowReturn.value = true
        isShowTheme.value = false
      })
    }
    const toLastStep = () => {
      isShowReturn.value = false
      isShowTheme.value = true
    }
    updateSelectState()
    getThemes()
    getArticles()
    return {
      article,
      activeName,
      themes,
      articleWithCondition,
      isShowTheme,
      isShowReturn,
      currentThemeTitle,
      toLastStep,
      toArticleListByThemeId,
      handleClick,
      handleCurrentChange,
      handleSizeChange,
      toDetail
    }
  }
}
</script>
<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 480px;
}
</style>
<style scoped>
.article_home {
  padding: 0 15vw;
}

.card2 {
  height: 9vw;
  width: 319px;
  background-color: rgb(255, 255, 255);
  border-radius: 3px;
  border: 1px solid rgb(232, 232, 232);
  margin-top: 20px;
  cursor: pointer;
}

.article_title {
  vertical-align: top;
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  display: block;
  word-break: keep-all;
  white-space: nowrap;
}

:deep(.el-tabs__nav-wrap::after) {
  bottom: unset !important;
}

.intro {
  font-size: 13px;
  color: #999;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
}
</style>
