<template>
<div class="category_body" v-for="oneCategory in categoryTree">
  <!-- 三级目录结构 -->
  <div class="one_title">
    <span>
      {{ oneCategory.name }}
    </span>
  </div>
  <div v-for="twoCategory in oneCategory.children">
    <div class="two_title">
      <span>
        {{ twoCategory.name }}
      </span>
    </div>
    <div style="margin-top: 10px">
      <el-row >
        <el-col :span="4" v-for="threeCategory in twoCategory.children">
          <span class="three_title">
            {{ threeCategory.name }}
          </span>
        </el-col>
      </el-row>
    </div>
  </div>

</div>
</template>

<script>

import resource from "@/api/resource";
import { reactive } from 'vue'
export default {
  name: "Category",
  components: {

  },
  setup() {
    const categoryTree = reactive([])
    // 获取树形结构数据
    const getTreeCategory = () => {
      resource.getTreeCategory(2).then(res => {
        categoryTree.push(...res.data)
      })
    }
    getTreeCategory()

    return {
      categoryTree
    }
  }
}
</script>

<style scoped lang="less">
.category_body {
  padding: 0 15vw;
  text-align: left;
  line-height: 30px;
}
.one_title {
  margin-top: 30px;
  span {
    font-size: 20px;
    font-weight: bold;
    line-height: 30px;
  }
}
.two_title {
  margin-top: 10px;
  span {
    font-size: 16px;
    font-weight: bold;
  }
}
.three_title {
  line-height: 30px;
  margin-top: 10px;
  color: #256DC6;
  font-size: 14px;
  white-space: nowrap;
  cursor: pointer;
}
</style>
