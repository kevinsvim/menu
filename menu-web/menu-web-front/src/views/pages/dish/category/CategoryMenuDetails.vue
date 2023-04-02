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
      <div style="margin-top: 40px; margin-bottom: 50px">
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
          <span style="font-size: 25px; font-weight: bold">大家都在搜索</span>
        </div>
        <div style="margin-top: 20px">
          <el-row :gutter="30">
            <el-col :span="12">
              <span style="font-size: 17px; line-height: 60px">
                家常菜
              </span>
            </el-col>
            <el-col :span="12">
              <span style="font-size: 17px; line-height: 60px">
                家常菜
              </span>
            </el-col>
            <el-col :span="12">
              <span style="font-size: 17px; line-height: 60px">
                家常菜
              </span>
            </el-col>
            <el-col :span="12">
              <span style="font-size: 17px; line-height: 60px">
                家常菜
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
import { ref,reactive } from 'vue'
export default {
  name: "CategoryMenuDetail",
  components: {
    CategoryMenuDetailCard
  },
  setup() {
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
    // 获取指定分类下的所有菜谱
    if (route.query.id) {
      categoryId.value = route.query.id
      categoryName.value = route.query.name
    }
    const getDishByCategoryId = () => {
      dish.getDishPageByCategoryId(categoryId.value, pageData.pageSize, pageData.currentPage).then(res => {
        console.log(res.data)
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
    getDishByCategoryId()
    return {
      pageData,
      categoryName,
      handleSizeChange,
      handleCurrentChange
    }
  }
}
</script>

<style scoped lang="less">
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
