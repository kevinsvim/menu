<template>
  <div class="menuNar">
    <div style="flex: 3"></div>
    <div style="flex: 1"><span  @click="accessHomePage" :class="selectHomePageState">首页</span></div>
    <div style="flex: 1">菜谱</div>
    <div style="flex: 1">饮食健康</div>
    <div style="flex: 1" @click="note"><span :class="selectNoteState">笔记</span></div>
    <div style="flex: 1" @click="comic"><span :class="selectComicState">动漫</span></div>
    <div style="flex: 2">
<!--      <el-button type="warning" class="publish" @click="selectPublish">发布</el-button>-->
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
    <div style="flex: 1">
      <div style="float: left">
        <el-button @click="login" type="" link>登录</el-button>
      </div>
      <div style="float: left">|</div>
      <div style="float: left">
        <el-button @click="register" type="" link>注册</el-button>
      </div>
    </div>
    <div style="flex: 1">
      <SearchView style="margin-top: 7px; margin-left: 20px"/>
    </div>
    <div style="flex: 3"></div>
  </div>

</template>

<script>
import { useRouter } from 'vue-router/dist/vue-router'
import { userTokenStore } from '@/store'
import SearchView from '@/components/menu/SearchView'
import { reactive, computed } from 'vue'
import { indexTransfer, publishTransfer } from '@/utils/navigationDataTransfer'
export default {
  name: 'MenuNar',
  emits: ['homePage'],
  components: {
    SearchView,
  },
  setup (props, { emit }) {
    const store = userTokenStore()
    const router = useRouter()
    const login = () => {
      router.push('/login')
    }
    const register = () => {
      router.push('/register')
    }
    /**
     * 点击导航栏功能开始
     */
    const state = reactive({
      // 首页是否为选中状态
      homePageState: true,
      noteState: false,
      comicState: false
    })
    const selectHomePageState = computed( () => ({
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
    // 点击首页
    function accessHomePage() {
      // 判断当前首页是否处于选中状态
      if (!state.homePageState) {
        // 将首页设置为选中状态
        state.homePageState = true
        // 通知父组件修改状态
        emit('homePage', indexTransfer)
      }
    }
    // 点击笔记
    function note() {
      if (!state.noteState) {
        // 将笔记页设置为选中状态
        state.noteState = true
        // 清除上一个的选中状态
        clearState(1)
        // 跳转到笔记页显示

      }
    }
    function comic() {
      if (!state.comicState) {
        state.comicState = true
        clearState(2)
      }
    }
    // 点击发布菜谱
    function publishMenu() {
      // 校验token是否登录

      // 跳转路径
      router.push("/publishMenu")
    }
    // 清除导航栏功能的选中状态
    function clearState(type) {
      state.noteState = false
      state.homePageState = false
      state.comicState = false
      switch (type) {
        case 0:
          state.homePageState = true
          break
        case 1:
          state.noteState = true
            break
        case 2:
          state.comicState = true
          break
        default:
          break
      }
    }

    return {
      login,
      register,
      accessHomePage,
      selectHomePageState,
      selectNoteState,
      note,
      selectComicState,
      comic,
      publishMenu
    }
  }
}
</script>

<style lang="less" scoped>
.logo {
  margin-top: 5px;
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
  height: 8vh;
  align-items: center;
  justify-content: center;
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
