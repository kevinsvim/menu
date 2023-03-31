<template>
  <div>
    <!--    精选最新分类食材菜单-->
    <div class="category_body">
      <el-tabs v-model="activeName" class="demo-tabs" v-on:tab-click="handleClick">
        <el-tab-pane name="concentration">
          <template #label>
            <span class="custom-tabs-label">
              <span>精选</span>
            </span>
          </template>
        </el-tab-pane>
        <el-tab-pane name="latest">
          <template #label>
            <span class="custom-tabs-label">
              <span>最新</span>
            </span>
          </template>
        </el-tab-pane>
        <el-tab-pane name="category">
          <template #label>
            <span class="custom-tabs-label">
              <span>分类</span>
            </span>
          </template>
        </el-tab-pane>
        <el-tab-pane name="material">
          <template #label>
            <span class="custom-tabs-label">
              <span>食材</span>
            </span>
          </template>
        </el-tab-pane>
        <el-tab-pane  name="menu">
          <template #label>
            <span class="custom-tabs-label">
              <span>菜单</span>
            </span>
          </template>
        </el-tab-pane>
      </el-tabs>
    </div>
    <router-view/>
  </div>
</template>

<script>
import NavigationBar from "@/views/pages/nav/NavigationBar";
import IconfontSvg from "@/components/iconfonts/IconSvg";
import { useRoute } from 'vue-router'
import { useRouter } from 'vue-router/dist/vue-router'
import { ref } from 'vue'
export default {
  name: "Category",
  components: {
    IconfontSvg,
    NavigationBar,
  },
  setup() {
    // tabs默认显示的数据
    const activeName = ref()
    const router = useRouter()
    const route =  useRoute()
    // 根据路由获取选中的标题
    const getPath = () => {
      activeName.value = route.path.split('/')[2]
    }
    getPath()
    // 点击tab切换
    const handleClick = (tab, event) => {
      displayPage(tab.paneName)
    }
    // 路由跳转
    const displayPage = (name) => {
      switch (name) {
        case 'concentration':
          router.push('/dish/concentration')
          break
        case 'latest':
          router.push('/dish/latest')
          break
        case 'category':
          router.push('/dish/category')
          break
        case 'material':
          router.push('/dish/material')
          break
        case 'menu':
          router.push('/dish/menu')
          break
        default:
          break
      }
    }

    return {
      activeName,
      handleClick
    }
  }
}
</script>

<style scoped lang="less">
.category_body {
  padding: 0 15vw;
}

.filter_title {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  div {
    flex: 1;
    span {
      font-size: 15px;
    }
  }
}
// 去掉tab标签页的底部分割线
:deep(.el-tabs__nav-wrap::after) {
  height: 0 !important;
}
</style>
