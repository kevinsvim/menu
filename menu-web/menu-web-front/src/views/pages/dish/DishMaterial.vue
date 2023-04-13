<template>
  <div class="ingredient_home">
    <div class="food_record" v-for="item in ingredient.records">
      <div>
        <el-image @click="() => toIngredientDetail(item.id)" class="image" :src="item.imageUrl" :fit="'fill'"/>
      </div>
      <div style="margin-left: 10px">
        <div>
          <span class="title">食材名: </span>
          <span class="value">{{ item.name }}</span>
        </div>
        <div>
          <span class="title">食材别名: </span>
          <span class="value">{{ item.alias }}</span>
        </div>
        <div>
          <span class="title">创建时间: </span>
          <span class="value">{{ item.createTime }}</span>
        </div>
        <div>
          <span class="title">卡路里: </span>
          <span class="value">{{ item.caloric }}</span>
        </div>
      </div>
    </div>
    <div class="page">
      <el-pagination
        v-model:current-page="ingredient.currentPage"
        v-model:page-size="ingredient.pageSize"
        :disabled=false
        :background=true
        layout="prev, pager, next, jumper"
        :total="ingredient.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
import {reactive, getCurrentInstance} from "vue";
import ingredientApi from "@/api/ingredient";
import { useRouter } from "vue-router/dist/vue-router";
import {MENU_CONSTANT, MENU_EVENT} from "@/utils/nav";

export default {
  name: "Material",
  components: {},
  setup() {
    const router = useRouter()
    // 页面数据
    const ingredient = reactive({
      currenPage: 1,
      pageSize: 10,
      total: 0,
      records: []
    })
    // 通知状态栏发生变更
    const {Bus} = getCurrentInstance().appContext.config.globalProperties
    localStorage.setItem('navSelect', MENU_CONSTANT)
    Bus.emit(MENU_EVENT, {state: true})

    const getIngredients = () => {
      ingredientApi.getIngredients(ingredient.currenPage, ingredient.pageSize).then(res => {
        ingredient.currentPage = res.data.current
        ingredient.total = res.data.total
        ingredient.records.push(...res.data.records)
      })
    }
    const handleSizeChange = (val) => {
      console.log(`${val} items per page`)
    }
    // 当前页数发生变化
    const handleCurrentChange = (val) => {
      ingredient.currentPage = val
      getIngredients()
    }
    const toIngredientDetail = (id) => {
      let resolve = router.resolve({path: '/dishMaterialDetail', query: { id: id }})
      open(resolve.href, '_blank')
    }
    getIngredients()
    return {
      ingredient,
      toIngredientDetail,
      handleCurrentChange,
      handleSizeChange
    }
  }
}
</script>

<style scoped lang="less">
.ingredient_home {
  padding: 20px 15vw;
  text-align: left;

  .food_record {
    display: flex;
    margin-top: 20px;

    .image {
      width: 120px;
      height: 120px;
      border-radius: 10px;
      cursor: pointer;
    }

    .title {
      font-size: 15px;
      font-weight: bold;
      line-height: 30px;
    }

    .value {
      font-size: 14px;
      color: #999;
      line-height: 30px;
    }
  }

  .page {
    margin-top: 40px;
    margin-bottom: 50px;
  }
}
</style>
