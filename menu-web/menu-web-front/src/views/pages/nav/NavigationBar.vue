<template>
  <div class="menuNar">
    <div style="flex: 3"></div>
    <div style="margin-top: 5px">
      <el-image class="logo" :src="logoUrl" :fit="'fill'"/>
    </div>
    <div style="flex: 1"><span @click="accessHomePage" :class="selectHomePageState">首页</span></div>
    <div style="flex: 1; cursor:pointer;">
      <el-tooltip content="Bottom center" effect="customized">
        <span :class="selectMenuStatue">菜谱
          <span style="vertical-align: middle">
            <el-icon style="margin-left: 3px;"><ArrowDown/></el-icon>
          </span>
        </span>
        <template #content>
          <div class="menu_content">
            <div>
              <el-row :gutter="20">
                <el-col :span="4" class="menu_type">
                  <span>
                    <svg t="1679284571781" class="icon" viewBox="0 0 1024 1024" version="1.1"
                         xmlns="http://www.w3.org/2000/svg" p-id="2532" width="17" height="16"><path
                      d="M360.725 33c3.526 1.457 7.052 2.909 10.575 4.374 24.202 10.07 48.704 19.484 72.535 30.362 36.75 16.774 71.604 37.031 104.774 60.142 26.75 18.64 51.616 39.576 75.127 62.087 8.946 8.564 17.269 17.852 25.224 27.354 11.313 13.515 22.512 27.179 32.88 41.417 16.44 22.572 30.486 46.615 41.868 72.193 9.157 20.583 17.27 41.491 22.289 63.505 1.506 6.607 2.988 13.217 4.803 21.235 22.57-30.853 27.756-66.112 34.339-102.473 12.856 20.155 25.33 38.876 36.948 58.113 19.011 31.48 37.17 63.487 51.968 97.194 10.17 23.167 20.189 46.494 28.429 70.384 7.825 22.688 14.158 45.995 19.464 69.411 4.837 21.353 8.16 43.128 8.012 65.212-0.008 1.095 0.677 2.192 1.04 3.287v28.275c-0.367 5.083-0.316 10.237-1.177 15.235-3.069 17.82-5.747 35.75-9.874 53.332-6.161 26.25-17.03 50.81-31.715 73.345-9.397 14.419-20.491 27.767-31.257 41.251-12.39 15.518-27.201 28.644-43.358 40.07-14.476 10.239-29.266 20.104-44.455 29.242-21.161 12.732-43.663 22.72-68 30.057 0.362-1.721 0.494-3.108 0.943-4.385 0.256-0.727-0.038-1.391-0.884-1.995 3.78-2.514 7.52-5.092 11.223-7.714 9.023-6.387 17.294-13.725 24.214-22.4 6.013-7.538 12.209-15.001 17.456-23.061 8.202-12.599 14.272-26.329 17.712-41.003 2.305-9.83 3.801-19.853 5.514-29.815 0.482-2.794 0.453-5.675 0.658-8.517v-15.806c-0.203-0.613-0.585-1.226-0.58-1.838 0.082-12.346-1.774-24.519-4.475-36.456-2.963-13.09-6.5-26.12-10.87-38.804-4.602-13.355-10.197-26.396-15.877-39.347-8.265-18.843-18.406-36.737-29.023-54.335-6.488-10.754-13.455-21.22-20.634-32.488-3.677 20.327-6.573 40.039-19.178 57.287-1.013-4.483-1.841-8.178-2.682-11.872-2.804-12.306-7.334-23.994-12.448-35.501-6.357-14.3-14.2-27.74-23.381-40.359-5.791-7.96-12.046-15.598-18.364-23.153-4.442-5.313-9.09-10.505-14.086-15.293-13.13-12.584-27.017-24.288-41.957-34.708-18.524-12.92-37.99-24.245-58.513-33.622-13.31-6.081-26.993-11.345-40.51-16.974-1.966-0.819-3.936-1.63-5.905-2.445h-1.823c2.422 5.634 5.001 11.206 7.237 16.915 5.352 13.666 9.691 27.673 12.132 42.149 1.853 10.98 3.211 22.066 4.24 33.156 0.716 7.715 1.045 15.546 0.599 23.27-1.31 22.64-5.744 44.64-14.715 65.61-7.112 16.63-16.064 32.158-27.791 45.931-7.035 8.261-14.661 16.02-22.068 23.96-0.871 0.934-2.038 1.592-3.418 2.645-9.25-20.868-19.72-40.931-33.244-60.231-0.746 3.928-1.463 7.05-1.916 10.21-1.707 11.923-5.19 23.362-9.133 34.7-5.79 16.647-13.894 32.063-24.987 45.738-4.47 5.51-10.332 9.877-14.917 15.309-3.614 4.28-6.848 9.116-9.181 14.197-4.825 10.504-7.04 21.814-8.917 33.158l-0.338 2.063c-0.528 3.25-0.448 6.599-0.698 9.898-0.033 0.422-0.378 0.821-0.579 1.231v24.319c0.404 2.901 0.709 5.82 1.23 8.7 1.621 8.97 2.588 18.146 5.19 26.827 2.969 9.909 6.878 19.636 11.374 28.962 5.008 10.388 11.799 19.73 19.437 28.488 12.581 14.425 27.58 25.594 44.386 34.512a198.767 198.767 0 0 0 6.817 3.451c-9.097 0.587-18.404 1.198-27.916 1.832-0.197-0.284-0.353-0.777-0.596-0.823-11.847-2.27-23.729-4.365-35.549-6.763-33.377-6.77-65.133-18.109-95.23-34.062-30.092-15.952-56.95-35.931-79.477-61.735-13.677-15.665-25.836-32.376-34.804-50.96-8.05-16.682-15.05-34.08-20.366-51.805-4.659-15.53-6.391-31.942-9.295-47.987-0.932-5.152-1.478-10.374-2.201-15.564v-43.5c0.36-0.734 0.978-1.448 1.036-2.204 0.449-5.901 0.305-11.89 1.25-17.705 3.502-21.524 7.41-43.074 16.572-63.002 4.178-9.089 9.969-17.74 16.44-25.396 8.21-9.716 18.708-17.527 26.71-27.384 19.863-24.463 34.374-52.038 44.742-81.817 7.06-20.28 13.297-40.742 16.354-62.07 0.81-5.652 2.095-11.237 3.43-18.264 24.216 34.524 42.964 70.413 59.527 107.742 2.47-1.884 4.56-3.06 6.12-4.732 13.263-14.204 26.918-28.082 39.515-42.86 20.998-24.637 37.028-52.413 49.763-82.159 16.064-37.512 24.004-76.865 26.348-117.362 0.8-13.818 0.21-27.827-1.07-41.626-1.844-19.838-4.276-39.669-7.593-59.31-4.371-25.893-12.142-50.95-21.725-75.394-4.003-10.213-8.62-20.18-12.959-30.258h3.265z"
                      fill="#E50404" p-id="2533"></path></svg>
                  </span>
                  <span class="subtitle">精选</span>
                </el-col>
                <el-col :span="4" class="menu_type">
                  <span>
                    <svg t="1679284730898" class="icon" viewBox="0 0 1024 1024" version="1.1"
                         xmlns="http://www.w3.org/2000/svg" p-id="3352" width="17" height="16"><path
                      d="M10.432 1024.064L6.4-0.128h935.872v1023.936l-468.544-245.696-463.296 245.952z m462.912-365.76l362.944 190.208V105.856H112.832l2.88 742.336 357.632-189.888z"
                      fill="#F98064" p-id="3353"></path><path
                      d="M199.104 304.704h46.528l47.296 90.944 18.624 42.176h1.28c-2.56-20.224-6.144-47.552-6.144-70.272v-62.848h43.392v190.08h-46.464l-47.04-91.712-18.624-41.728h-1.28c2.048 21.248 5.888 47.296 5.888 70.336v63.104h-43.456v-190.08zM394.176 304.704h120.64v37.76h-74.88v35.264h63.616v38.336h-63.616v40.384h77.696v38.336H394.176v-190.08zM536.64 304.704h46.72l12.032 86.592 7.68 60.288h1.024c3.648-20.16 7.424-40.64 11.264-60.288l19.392-86.592h38.4l19.968 86.592c3.84 19.392 7.104 39.872 10.944 60.288h1.344c2.304-20.416 4.8-40.64 7.104-60.288l12.288-86.592h43.392l-32.96 190.08h-57.472l-17.28-82.752a524.992 524.992 0 0 1-7.36-44.16h-1.28c-2.304 14.272-4.608 29.568-7.424 44.16L627.84 494.784h-56.448l-34.752-190.08z"
                      fill="#FC632D" p-id="3354"></path></svg>
                  </span>
                  <span class="subtitle">最新</span>
                </el-col>
                <el-col :span="4" class="menu_type">
                <span>
                  <svg t="1679284812244" class="icon" viewBox="0 0 1024 1024" version="1.1"
                       xmlns="http://www.w3.org/2000/svg" p-id="4365" width="20" height="20"><path
                    d="M148.7872 57.4464h177.152c64.9216 0 118.0672 53.1456 118.0672 118.0672v295.2192H148.7872C83.8656 470.7328 30.72 417.5872 30.72 352.5632v-177.152C30.72 110.592 83.8656 57.4464 148.7872 57.4464z m0 531.3536h295.2192v295.2192c0 64.9216-53.1456 118.0672-118.0672 118.0672h-177.152C83.8656 1001.984 30.72 948.9408 30.72 883.9168v-177.152C30.72 641.9456 83.8656 588.8 148.7872 588.8z m0 0M768.7168 559.2064L562.0736 346.7264c-23.6544-17.7152-35.4304-53.1456-35.4304-82.6368s11.776-59.0848 35.4304-82.6368L686.08 57.4464C733.2864 10.24 810.0864 10.24 851.3536 57.4464l124.0064 124.0064c23.6544 23.6544 35.4304 53.1456 35.4304 82.6368s-11.776 59.0848-35.4304 82.6368L768.7168 559.2064z m0-478.208c-17.7152 0-29.4912 5.9392-41.3696 17.7152l-123.904 124.0064c-11.776 11.776-17.7152 23.6544-17.7152 41.3696s5.9392 29.4912 17.7152 41.3696l165.2736 165.2736 165.2736-165.2736c11.776-11.776 17.7152-23.6544 17.7152-41.3696s-5.9392-29.4912-17.7152-41.3696L809.984 98.7136c-11.776-11.776-23.552-17.7152-41.2672-17.7152z m0 0"
                    fill="#1296DB" p-id="4366"></path><path
                    d="M562.0736 588.8h295.2192c64.9216 0 118.0672 53.1456 118.0672 118.0672v177.152c0 64.9216-53.1456 118.0672-118.0672 118.0672h-177.152c-64.9216 0-118.0672-53.1456-118.0672-118.0672V588.8z m0 0"
                    fill="#1296DB" p-id="4367"></path></svg>
                </span>
                  <span class="subtitle">菜单</span>
                </el-col>
              </el-row>
            </div>
            <div style="margin-top: 25px" v-for="(oneCategory) in partCategoryTree" :key="oneCategory.id">
              <el-row :gutter="10">
                <el-col :span="4">
                  <span class="one_category_name">{{ oneCategory.name }}</span>
                </el-col>
                <el-col :span="20">
                  <el-row :gutter="30">
                    <el-col :span="4" v-for="(twoCategory) in oneCategory.children" :key="twoCategory.id">
                      <span class="two_category_name">{{ twoCategory.name }}</span>
                    </el-col>
                  </el-row>
                </el-col>
              </el-row>
            </div>
            <el-divider/>
            <div>
              <el-row :gutter="20">
                <el-col :span="12" :offset="6" class="look_allCategory">
                  <span @click="toLookAllCategory">
                    查看全部分类
                  </span>
                  <span style="color: #256DC6;">
                    <el-icon><ArrowRight/></el-icon>
                  </span>
                </el-col>
              </el-row>
            </div>
          </div>
        </template>
      </el-tooltip>
    </div>
    <div style="flex: 1">
      <el-tooltip content="Bottom center" effect="customized">
        <span>饮食健康</span>
      </el-tooltip>
    </div>
    <div style="flex: 1; cursor:pointer;" @click="note"><span :class="selectNoteState">笔记</span></div>
    <div style="flex: 1; cursor:pointer;" @click="comic"><span :class="selectComicState">动漫</span></div>
    <div style="flex: 2">
      <el-dropdown split-button type="primary">
        发布
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="publishMenu">发布菜谱</el-dropdown-item>
            <el-dropdown-item>发布日记</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>

    <!-- 搜索框 -->
    <div style="flex: 1">
      <SearchBox style="margin-left: 20px"/>
    </div>

    <!-- 登录注册 -->
    <div v-if="!isLogin" style="flex: 1; margin-left: 20px">
      <div>
        <el-button @click="sign" type="" link>登录 | 注册</el-button>
      </div>
    </div>
    <div v-if="isLogin" style="margin-left: 20px">
      <el-avatar :size="50" :src="avatar" />
    </div>
    <!-- 用户已经登录则显示头像 -->
    <div style="flex: 3"></div>
    <!-- 按钮 -->
    <!--    <div class="tag-item current" mx-click="setTag({tag:''})" p-id="940"-->
    <!--         data-spm-anchor-id="a313x.7781069.1998910419.i2"><span p-id="941"-->
    <!--                                                                data-spm-anchor-id="a313x.7781069.1998910419.i4">-->
    <!--      <span style="color: #FFFFFF; font-size: 13px">全部</span>-->
    <!--    </span>-->
    <!--    </div>-->

  </div>
  <el-divider/>
  <router-view/>
</template>

<script>
import {useRouter} from 'vue-router/dist/vue-router'
import {reactive, computed, ref, getCurrentInstance, onBeforeUnmount } from 'vue'
import {HOME_CONSTANT, MENU_CONSTANT, MENU_EVENT} from '@/utils/nav'
import {ArrowDown, ArrowRight} from '@element-plus/icons'
import SearchBox from '@/components/search/SearchBox'
import resource from "@/api/resource";
import {userStore} from '@/store/user'
export default {
  name: 'MenuNar',
  emits: ['homePage'],
  components: {
    ArrowDown,
    ArrowRight,
    SearchBox,
  },
  setup() {
    /**
     * 点击导航栏功能开始
     */
    const state = reactive({
      // 首页是否为选中状态
      homePageState: true,  // 首页
      menuState: false,     // 菜单
      noteState: false,     // 笔记
      comicState: false,    // 漫画
    })
    // 判断用户是否登录
    const isLogin = ref(false)
    const uStore = userStore()
    const avatar = ref('')
    /**
     * 初始化，加载分类数据
     */
    const router = useRouter()
    const allCategoryTree = reactive([])
    const partCategoryTree = reactive([])
    const logoUrl = 'https://menu-api.oss-cn-beijing.aliyuncs.com/icon/logo.png'

    const { Bus }  = getCurrentInstance().appContext.config.globalProperties
    Bus.on(MENU_EVENT, res => {
      console.log('监听到事件.....')
      settingSelectState(2)
    })
    onBeforeUnmount(() => {
      Bus.off(MENU_EVENT)
    })
    const getCategoryTree = () => {
      resource.getTreeCategory(2).then(res => {
        // 放置总数居
        allCategoryTree.push(...res.data)
        // 取出二级分类下的前5个数据
        partCategoryTree.push(...res.data.flatMap(x => x.children).slice(0, 5))
      })
    }
    getCategoryTree()

    const getUserInfo = () => {
      let imageUrl = uStore.getImageUrl()
      if (imageUrl) {
        isLogin.value = true
        avatar.value = imageUrl
      }
    }
    getUserInfo()
    // 查看全部分类
    const toLookAllCategory = () => {
      menu()
      router.push({name: 'Category', params: { activeName: 'category' }})
    }

    const sign = () => {
      router.push('/sign')
    }
    // 初始化或获取导航栏选中状态
    const initSelectState = () => {
      if (localStorage.getItem(HOME_CONSTANT) === null) {
        localStorage.setItem(HOME_CONSTANT, true)
        localStorage.setItem(MENU_CONSTANT, false)
      } else {
        // 取出为true的设置为选中状态
        if (localStorage.getItem(HOME_CONSTANT)) {
          state.homePageState = true
        } else if (localStorage.getItem(MENU_CONSTANT)) {
          state.menuState = true
        }
      }
    }
    initSelectState()
    // 设置选中状态
    const settingSelectState = (type) => {
      state.homePageState = false
      state.menuState = false
      localStorage.setItem(HOME_CONSTANT, false)
      localStorage.setItem(MENU_CONSTANT, false)
      switch (type) {
        case 1:
          state.homePageState = true
          localStorage.setItem(HOME_CONSTANT, true)
          break
        case 2:
          state.menuState = true
          localStorage.setItem(MENU_CONSTANT, true)
          break
      }
    }
    // 监听各个选中状态
    const selectHomePageState = computed(() => ({
      selectStateStyle: state.homePageState,
      unSelectStateStyle: !state.homePageState
    }))
    const selectNoteState = computed(() => ({
      selectStateStyle: state.noteState,
      unSelectStateStyle: !state.noteState
    }))
    const selectComicState = computed(() => ({
      selectStateStyle: state.comicState,
      unSelectStateStyle: !state.comicState
    }))
    const selectMenuStatue = computed(() => ({
      selectStateStyle: state.menuState,
      unSelectStateStyle: !state.menuState
    }))
    // 点击首页
    function accessHomePage() {
      if (!state.homePageState) {
        state.homePageState = true
        settingSelectState(1)
        router.push('/index')
      }
    }
    // 选中菜谱
    function menu() {
      if (!state.menuState) {
        state.menuState = true
        settingSelectState(2)
      }
    }
    // 点击笔记
    function note() {
      if (!state.noteState) {

        // 跳转到笔记页显示

      }
    }

    function comic() {
      if (!state.comicState) {

      }
    }
    // 点击发布菜谱
    function publishMenu() {
      // 跳转路径
      router.push('/publishMenu')
    }


    return {
      sign,
      isLogin,
      avatar,
      accessHomePage,
      selectHomePageState,
      selectNoteState,
      note,
      selectComicState,
      comic,
      publishMenu,
      logoUrl,
      partCategoryTree,
      toLookAllCategory,
      selectMenuStatue
    }
  }
}
</script>

<style lang="less" scoped>
// logo样式
.logo {
  width: 100px;
  height: 30px;
}

// 一级分类名text样式
.one_category_name {
  font-weight: bold;
  font-size: 15px;
  line-height: 30px;
}

// 二级分类名text样式
.two_category_name {
  font-size: 14px;
  line-height: 30px;
  cursor: pointer;
}

// 查看全部分类
.look_allCategory {
  text-align: center;
  cursor: pointer;

  span {
    font-size: 15px;
    font-weight: bold;
    color: #256DC6;
  }
}

.menu_type {
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

.current {
  background: #4A54FF linear-gradient(315deg, #6772FF 0, #00F9E5 100%) center center;
  background-size: 104% 104%;
  color: #fff;
}

.tag-item {
  text-align: center;
  width: 70px;
  height: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  color: #7f7f7f;
  border-radius: 999px;
}

.subtitle {
  font-size: 15px;
  margin-left: 5px;
  margin-bottom: 5px;
}

.menu_content {
  padding: 20px 30px;
  width: 700px;
  min-height: 300px;
}

.logo {
  margin-top: 9px;
  width: 140px;
  height: 40px;
}


// 修改导航栏的字体选中颜色
.el-header {
  :deep(.el-link) {
    --el-link-hover-text-color: #FFE433;
  }

  .publish {
    margin-top: 12px;
    margin-left: 30px;
  }
}

.menuNar {
  display: flex;
  width: 100%;
  height: 6vh;
  align-items: center;
  justify-content: center;
  margin-top: 20px;

  .selectStateStyle {
    cursor: pointer;
    color: orange;
  }

  .unSelectStateStyle {
    cursor: pointer;
  }
}

.example-showcase .el-dropdown + .el-dropdown {
  margin-left: 15px;
}

.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>

<style lang="less">
.el-popper.is-customized {
  padding: 6px 12px;
  /*background: linear-gradient(90deg, rgb(229, 195, 151), rgba(129, 229, 137, 0.52));*/
  background: rgb(247, 249, 250);
}

.el-popper.is-customized .el-popper__arrow::before {
  background: linear-gradient(45deg, #b2e68d, #bce689);
  right: 0;
}

.centerVertically {
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
