<template>
  <div class="home"
       v-loading="indexLoading"
       element-loading-text="Loading..."
       :element-loading-spinner="svg"
       element-loading-svg-view-box="-10, -10, 50, 50"
       element-loading-background="rgba(122, 122, 122, 0.8)"
  >
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
            <div style="text-align: right; margin-top: 10px; margin-right: 50px"><span class="item2">更多
          <el-icon :size="11" color="cornflowerblue">
            <ArrowRightBold/>
          </el-icon>
        </span>
            </div>
          </el-col>
        </el-row>
        <el-row justify="center">
          <el-col :span="6" v-for="(menu,index) in concentrationMenu" :key="menu.id" style="margin-top: 20px;">
            <div @click="() => toDetail(menu.id)">
              <DishCard
                :title="menu.name"
                :image-url="menu.imageUrl"
                :username="menu.username"
                :collect-num="menu.collectNum"
                :up-num="menu.upNum"
                :views="menu.views"
                :comment-num="menu.commentNum"
                :evaluate="menu.evaluate"
                :dish-id="menu.id"
                :publish-date="menu.publishDate"
              />
            </div>
          </el-col>
        </el-row>
        <div style="margin-top: 30px; margin-bottom: 30px">
          <el-row>
            <el-col :span="15">
              <!-- 大家都在搜索 -->
              <el-row>
                <div style="text-align: left">
                  <span class="item1">大家都在搜</span>
                </div>
                <div style="max-height: 170px; width: 100%; padding: 10px 5px; text-align: left; overflow: hidden">
                  <span class="search_span" v-for="item in searchRec">
                      <span @click="() => toSearchDishPage(item.id, item.name)">{{ item.name }}</span>
                  </span>
                </div>
              </el-row>
              <!-- 热门食材 -->
              <div style="display: flex; justify-content: space-between; margin-top: 30px">
                <div>
                  <span class="item1">热门食材</span>
                </div>
                <div style="margin-top: 10px; margin-right: 30px">
                  <span class="item2" @click="toIngredient">更多
                    <el-icon :size="11" color="cornflowerblue">
                      <ArrowRightBold/>
                    </el-icon>
                  </span>
                </div>
              </div>
              <div class="material-list">
                <el-row>
                  <el-col :span="4" v-for="item in ingredient" style="margin-top: 20px">
                    <img @click="() => toIngredientDetail(item.id)" :src="item.imageUrl" :alt="item.name">
                    <div style="vertical-align: bottom">
                      <span @click="() => toIngredientDetail(item.id)" class="material_name">{{ item.name }}</span>
                    </div>
                  </el-col>
                </el-row>
              </div>
              <!-- 精彩主题文章 -->
              <div style="display: flex; justify-content: space-between; margin-top: 30px; margin-bottom: 20px">
                <div>
                  <span class="item1">精彩主题文章</span>
                </div>
                <div style="margin-top: 10px;margin-right: 30px">
                    <span @click="toArticle" class="item2">更多
                      <el-icon :size="11" color="cornflowerblue">
                        <ArrowRightBold/>
                      </el-icon>
                    </span>
                </div>
              </div>
              <div>
                <el-row v-for="(item, index) in article">
                  <el-col :span="8" style="text-align: left" v-if="index === 0">
                    <img @click="() => toArticleDetail(item.id)" :src="item.imageUrl"
                         style="width: 210px; height: 115px; border-radius:8px">
                  </el-col>
                  <el-col :span="16" v-if="index === 0">
                    <div>
                      <div class="article_title">
                        <span @click="() => toArticleDetail(item.id)" class="title_text">{{ item.title }}</span>
                      </div>
                      <div style="text-align: left; line-height: 50px">
                        <span style="font-size: 13px; color: #999">
                          {{ item.createTime }}
                        </span>
                      </div>
                      <el-row>
                        <el-col :span="12" style="text-align: left;">
                          <span style="line-height: 40px; font-size: 13px">来自：{{ item.themeTitle }}</span>
                        </el-col>
                        <el-col :span="12">
                          <span style="line-height: 40px; font-size: 13px">作者：{{ item.author }}</span>
                        </el-col>
                      </el-row>
                    </div>

                  </el-col>
                </el-row>
                <el-row :gutter="40" style="margin-top: 20px">
                  <el-col :span="12" v-for="item in article">
                    <div class="separator_dian">
                      <iconfont-svg icon="icon-dian"/>
                      <span
                        @click="() => toArticleDetail(item.id)"
                        class="list_text">{{ item.title }}</span>
                    </div>
                  </el-col>

                </el-row>
              </div>
            </el-col>
            <!-- 美食达人、动漫-->
            <el-col :span="9" style="padding-right: 25px; padding-top: 30px; padding-left: 40px">
              <el-row style="text-align: left">
                <el-col :span="12">
                  <span class="item1">美食达人</span>
                </el-col>
                <el-col :span="11" style="text-align: right">
                  <span class="item2">更多
                    <el-icon :size="11" color="cornflowerblue">
                      <ArrowRightBold/>
                    </el-icon>
                  </span>
                </el-col>
              </el-row>
              <el-row style="margin-top: 25px; text-align: left" v-for="item in memberRec">
                <el-col :span="4">
                  <el-avatar style="cursor: pointer" shape="square" :size="45"
                             :src="item.imageUrl"
                             @click="() => toPersonal(item.id)"
                  />
                </el-col>
                <el-col :span="8">
                  <div>
                    <span @click="() => toPersonal(item.id)" class="member_name">{{
                        item.nickname || item.username
                      }} </span>
                  </div>
                  <div>
                    <span style="font-size: 12px; color: #999; line-height: 35px">{{ item.fansNum }}粉丝</span>
                  </div>
                </el-col>
                <el-col :span="11" style="margin-top: 8px; text-align: right">
                  <el-button plain>
                    + 关注
                  </el-button>
                </el-col>
              </el-row>
              <el-row style="text-align: left; margin-top: 40px">
                <el-col :span="12">
                  <span class="item1">动漫</span>
                </el-col>
                <el-col :span="11" style="text-align: right">
                   <span class="item2" @click="toComicList">更多
                    <el-icon :size="11" color="cornflowerblue">
                      <ArrowRightBold/>
                    </el-icon>
                  </span>
                </el-col>
              </el-row>
              <el-row :gutter="40" style="margin-top: 20px">
                <el-col :span="24" v-for="item in comic">
                  <div class="separator_dian">
                    <iconfont-svg icon="icon-dian"/>
                    <span
                      style="font-size: 13px; line-height: 40px">{{ item.title }}</span>
                  </div>
                </el-col>

              </el-row>
            </el-col>
          </el-row>

        </div>
      </div>

      <!-- 笔记 -->
      <div class="daily_selection">
        <el-row>
          <el-col :span="12">
            <div style="text-align: left;"><span class="item1">笔记</span></div>
          </el-col>
          <el-col :span="8">
            <div style="text-align: right; margin-top: 10px"><span class="item2">更多
          <el-icon :size="11" color="cornflowerblue">
            <ArrowRightBold/>
          </el-icon>
        </span>
            </div>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 20px;">
          <el-col :span="5" v-for="item in pageData.records">
            <NoteCard
              :title="item.title"
              :id="item.id"
              :intro="item.intro"
              :date="item.createTime"
              :image-url="item.imageUrls.split(',')[0]"
              @click="() => toNoteDetail(item.id)"
            />
          </el-col>
        </el-row>
      </div>
    </div>
    <!-- 尾部-->
    <FoodFooter/>
  </div>
</template>

<script>
import {useRouter} from 'vue-router'
import NavigationBar from '@/views/pages/nav/NavigationBar'
import FoodFooter from '@/views/footer/FoodFooter'
import Publish from '@/views/pages/PublishMenu'
import {reactive, ref} from 'vue'
import BannerFront from '@/components/banner/BannerFront'
import {ArrowRightBold} from '@element-plus/icons'
import resource from "@/api/resource";
import {getCurrentInstance} from 'vue'
import DishCard from "@/components/card/DishCard";
import noteApi from "@/api/note";
import NoteCard2 from "@/components/card/NoteCard2";
import IconfontSvg from "@/components/iconfonts/IconSvg";
import comicApi from '@/api/comic'
import articleApi from '@/api/article'
import ingredientApi from "@/api/ingredient";
import memberApi from '@/api/member'
import NoteCard from "@/components/card/NoteCard";
import dish from "@/api/dish";

export default {
  name: 'HomeView',
  components: {
    IconfontSvg,
    DishCard,
    NavigationBar,
    FoodFooter,
    Publish,
    BannerFront,
    ArrowRightBold,
    NoteCard2,
    NoteCard
  },
  setup() {
    const router = useRouter()
    const pageData = reactive({
      pageSize: 4,
      currentPage: 1,
      total: 0,
      pageCount: 1,
      records: []
    })
    const searchRec = reactive([])
    const article = ref([])
    const comic = reactive([])
    const ingredient = reactive([])
    // 推荐用户
    const memberRec = reactive([])
    // 获取到 全局事件总线
    const {Bus} = getCurrentInstance().appContext.config.globalProperties
    // 获取热度推荐
    const concentrationMenu = reactive([])
    const getDailySection = () => {
      resource.getConcentrationMenu().then(res => {
        concentrationMenu.push(...res.data)
      })
    }
    // 获取搜索热度推荐
    const getSearchList = () => {
      dish.getHotSearchDish().then(res => {
        searchRec.push(...res.data)
      })
    }
    getSearchList()
    const indexLoading = ref(false)
    // 导航栏功能选中状态
    const selectStatus = reactive({
      selectIndex: true,
      selectPublish: false,
    })

    const login = () => {
      router.push('/login')
    }

    const register = () => {
      router.push('/register')
    }

    const logout = () => {
      router.push('/logout')
    }
    // 点击去菜谱详情
    const toDetail = (menuId) => {
      // 通信修改导航栏选中状态
      Bus.emit('NavSelectEvent', {state: 'menu'})
      let routeData = router.resolve({path: '/detail', query: {id: menuId}})
      window.open(routeData.href, '_blank');
    }
    // 获取食材列表
    const getPartIngredients = () => {
      ingredientApi.getPartIngredients().then(res => {
        ingredient.push(...res.data)
      })
    }
    // 获取笔记列表
    const getNoteList = () => {
      noteApi.getNotes(pageData.currentPage, pageData.pageSize).then(res => {
        console.log('最终的笔记列表为:', res)
        pageData.total = res.data.total
        pageData.pageCount = res.data.pages
        pageData.records.push(...res.data.records)
      })
    }
    // 去笔记详情页
    const toNoteDetail = (id) => {
      let noteRouterData = router.resolve({path: '/noteDetail', query: {id: id}})
      window.open(noteRouterData.href, '_blank')
    }
    // 去动漫列表页面
    const toComicList = () => {
      let data = router.resolve('/comic')
      window.open(data.href, '_blank')
    }
    // 获取动漫列表数据
    const getComics = () => {
      comicApi.getAllComic().then(res => {
        for (let i = 0; i < 7; i++) {
          comic.push(res.data[i])
        }
      })
    }
    // 获取用户推荐列表
    const getMembers = () => {
      memberApi.getMemberRec().then(res => {
        console.log('获取的用户推荐列表为:', res)
        memberRec.push(...res.data)
      })
    }
    getMembers()
    // 去文章页面
    const toArticle = () => {
      let data = router.resolve({path: '/article'})
      open(data.href, '_blank')
    }
    // 获取文章数据
    const getArticles = () => {
      articleApi.getPageArticle(1, 6).then(res => {
        article.value.push(...res.data.articleVos)
      })
    }
    // 去文章详情页面
    const toArticleDetail = (id) => {
      let data = router.resolve({path: '/articleDetail', query: {id: id}})
      open(data.href, '_blank')
    }
    // 去食材页面
    const toIngredient = () => {
      let data = router.resolve({path: '/dish/material'})
      open(data.href, '_blank')
    }
    // 去详情页面
    const toIngredientDetail = (id) => {
      let data = router.resolve({path: '/dishMaterialDetail', query: {id: id}})
      open(data.href, '_blank')
    }
    // 去个人页面
    const toPersonal = (id) => {
      let data = router.resolve({path: '/personal', query: {id: id}})
      open(data.href, '_blank')
    }
    // 去食谱页面
    const toSearchDishPage = (id, name) => {
      let data = router.resolve({path: '/categoryMenuDetails', query: {id: id, name: name}})
      open(data.href, '_blank')
    }
    getDailySection()
    getNoteList()
    getArticles()
    getComics()
    getPartIngredients()
    return {
      selectStatus,
      indexLoading,
      concentrationMenu,
      pageData,
      comic,
      article,
      ingredient,
      memberRec,
      searchRec,
      toSearchDishPage,
      toPersonal,
      toIngredientDetail,
      toIngredient,
      toArticleDetail,
      toArticle,
      login,
      register,
      logout,
      toDetail,
      toNoteDetail,
      toComicList
    }
  }
}
</script>
<style lang="less" scoped>
.home {
  .daily_selection {
    margin: 20px 12vw;
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

.title_text {
  line-height: 30px;
  cursor: pointer;
}

.member_name {
  font-size: 14px;
  line-height: 20px;
}

.member_name:hover {
  color: rgb(244, 182, 68);
  cursor: pointer;
}

.title_text:hover {
  color: rgb(244, 182, 68);
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

.separator_dian {
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  display: block;
  word-break: keep-all;
  white-space: nowrap;
}

.list_text {
  font-size: 12px;
  line-height: 40px;
  cursor: pointer;
}

.list_text:hover {
  color: rgb(244, 182, 68);
}

.material-list {
  width: 692px;
  height: 276px;
  background: #F1F7FA;
  margin-top: 20px;
  border-radius: 8px;
  padding: 10px 10px;

  img {
    border-radius: 4px;
    width: 80px;
    height: 80px;
    cursor: pointer;
  }

  .material_name {
    font-size: 14px;
  }

  .material_name:hover {
    color: #ffaa00;
    cursor: pointer;
  }
}

.search_span {
  border: 1px solid #000;
  border-radius: 8px;
  padding: 6px 24px;
  display: inline-block;
  margin: 12px 12px;
  font-size: 13px;
}

.search_span:hover {
  border: 1px solid rgb(244, 182, 68);
  color: rgb(244, 182, 68);
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
