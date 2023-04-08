<template>
<div>

</div>
</template>

<script>
import { ref, reactive } from "vue";
import ingredientApi from "@/api/ingredient";
export default {
  name: "Material",
  components: {

  },
  setup() {
    // 页面数据
    const ingredient = reactive({
      currenPage: 1,
      pageSize: 10,
      total: 0,
      records: []
    })
    const getIngredients = () => {
      ingredientApi.getIngredients(ingredient.currenPage, ingredient.pageSize).then(res => {
        ingredient.currentPage = res.data.current
        ingredient.total = res.data.total
        ingredient.records.push(...res.data.records)
      })
    }
    getIngredients()
    return {
      ingredient
    }
  }
}
</script>

<style scoped>

</style>
