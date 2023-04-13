<template>
<div class="categoryMenuDetail">
  <el-row :gutter="20">
    <el-col :span="18">
      <div class="categoryText">
        <span>
          "{{categoryName}}"的相关菜谱
        </span>
      </div>
      <CategoryMenuDetailCard
        v-if="pageData.total !== 0"
        v-for="item in pageData.records"
        style="margin-top: 20px"
        :username="item.username"
        :avatar="item.avatar"
        :collect-num="item.collectNum"
        :menu-name="item.name"
        :image-url="item.imageUrl"
        :up-num="item.upNum"
        :menu-id="item.id"
      />
      <div v-else>
        <el-empty description="还没有数据!" />
      </div>
      <div v-if="pageData.total !== 0" style="margin-top: 40px; margin-bottom: 50px">
        <el-pagination
          v-model:current-page="pageData.currentPage"
          v-model:page-size="pageData.pageSize"
          :disabled=false
          :background=true
          layout="prev, pager, next, jumper"
          :page-count="pageData.pageCount"
          :total="pageData.total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>

    </el-col>
    <el-col :span="6">
      <div style="margin-top: 10vh">
        <div>
          <span style="font-size: 21px; font-weight: bold">大家都在搜索
            <iconfont-svg size="30" icon="icon-sousuo1"/>
          </span>
        </div>
        <div style="margin-top: 20px">
          <el-row :gutter="30">
            <el-col :span="12" v-for="item in searchRec">
              <span>
                <span class="search_span" @click="() => toSearchDishPage(item.id, item.name)">{{ item.name }}</span>
              </span>
            </el-col>
          </el-row>
        </div>
      </div>
    </el-col>
  </el-row>

</div>
</template>

<script>
import CategoryMenuDetailCard from "@/components/detail/CategoryMenuDetailCard";
import dish from "@/api/dish";
import { useRoute } from 'vue-router'
import { ref, reactive, getCurrentInstance } from 'vue'
import {MENU_CONSTANT, MENU_EVENT} from "@/utils/nav";
import {useRouter} from "vue-router/dist/vue-router";
import IconfontSvg from "@/components/iconfonts/IconSvg";

export default {
  name: "CategoryMenuDetail",
  components: {
    IconfontSvg,
    CategoryMenuDetailCard
  },
  setup() {
    const router = useRouter()
    const route = useRoute()
    const categoryId =  ref('')
    const categoryName = ref('')
    // 页数据
    const pageData = reactive({
      pageSize: 10,
      currentPage: 1,
      total: 0,
      pageCount: 1,
      records: []
    })
    const searchRec = reactive([])
    // 获取到 全局事件总线
    const { Bus }  = getCurrentInstance().appContext.config.globalProperties
    // 修改导航栏的状态
    const updateSelectState = () => {
      localStorage.setItem('navSelect', MENU_CONSTANT)
      // 通知导航栏组件刷新选中状态
      Bus.emit(MENU_EVENT, { state: true })
    }
    updateSelectState()

    // 获取搜索热度推荐
    const getSearchList = () => {
      dish.getHotSearchDish().then(res => {
        searchRec.push(...res.data)
      })
    }
    getSearchList()
    // 获取指定分类下的所有菜谱
    if (route.query.id) {
      categoryId.value = route.query.id
      categoryName.value = route.query.name
    }
    const getDishByCategoryId = () => {
      dish.getDishPageByCategoryId(categoryId.value, pageData.pageSize, pageData.currentPage).then(res => {
        pageData.total = res.data.total
        pageData.pageCount = res.data.pages
        pageData.records = res.data.records
      })
    }
    const handleSizeChange = (val) => {
      console.log(`${val} items per page`)
    }
    const handleCurrentChange = (val) => {
      pageData.currentPage = val
      getDishByCategoryId()
    }
    // 去食谱页面
    const toSearchDishPage = (id, name) => {
      let data = router.resolve({path: '/categoryMenuDetails', query: {id: id, name: name}})
      open(data.href, '_blank')
    }
    getDishByCategoryId()
    return {
      pageData,
      categoryName,
      searchRec,
      toSearchDishPage,
      handleSizeChange,
      handleCurrentChange
    }
  }
}
</script>

<style scoped lang="less">
.search_span {
  cursor: pointer;
  line-height: 40px;
  font-size: 14px;
}

.search_span:hover {
  color: rgb(244, 182, 68);
}

.categoryMenuDetail {
  padding: 20px 15vw;
  text-align: left;
}

.categoryText {
  margin-bottom: 20px;
  span {
    font-size: 21px;
    font-weight: bold;
  }
}

</style>
