<template>
  <el-autocomplete
    v-model="search_content"
    :fetch-suggestions="querySearchAsync"
    placeholder="Please input"
    :input-style="{width: 200 + 'px', height: 40 + 'px'}"
    @select="handleSelect"
  >
    <template #append>
      <div class="search">
        <iconfont-svg icon="icon-sousuo1" size="40"/>
      </div>
    </template>
  </el-autocomplete>
</template>

<script>
import {Search} from '@element-plus/icons'
import IconfontSvg from "@/components/iconfonts/IconSvg";
import dishApi from '@/api/dish'
import {ref} from 'vue'
import { useRouter } from "vue-router/dist/vue-router";

export default {
  name: 'SearchBox',
  components: {
    IconfontSvg,
    Search
  },
  setup() {
    const router = useRouter()
    const search_content = ref('')
    // 调用后台获取搜索建议
    const querySearchAsync = (queryString, callback) => {
      if (queryString !== '') {
        // 整理参数
        const params = {
          content: queryString,
          size: 10
        }
        // 空数组接收返回参数
        let suggestList = []
        dishApi.getSearchSuggest(params).then(res => {
          suggestList.push(...res.data)
          callback(suggestList)
        })
      }


    }
    const createFilter = (queryString) => {
      // return (restaurant: LinkItem) => {
      //   return (
      //     restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
      //   )
      // }
    }

    const handleSelect = (item) => {
      // 跳转到指定的菜谱页面
      router.push({path: '/categoryMenuDetails', query: {id: item.id, name: item.value}})
    }
    return {
      search_content,
      querySearchAsync,
      handleSelect
    }
  }
}
</script>

<style scoped lang="less">
.search {
  cursor: pointer;
}

:deep(.el-input-group__append) {
  padding: 0 10px;
  background: var(--el-fill-color-light) radial-gradient(black, transparent);
}

.el-input-group__append {
  padding: 0 10px !important;
  background: var(--el-fill-color-light) radial-gradient(black, transparent) !important;
}
</style>
