<template>
  <div>
    <el-input v-model="filterText" placeholder="Filter keyword"/>
    <el-card shadow="always" style="margin-top: 20px;">
      <el-scrollbar height="60vh">
        <el-tree
            ref="treeRef"
            class="filter-tree"
            :data="categoryTreeData"
            :props="defaultProps"
            default-expand-all
            :filter-node-method="filterNode"
        />
      </el-scrollbar>
    </el-card>
  </div>
</template>

<script>
import { reactive, ref, watch } from 'vue'
import {ElTree} from 'element-plus'
import resource from '@/api/resource.js'

export default {
  name: "List",
  components: {},
  setup() {
    // 适配类型
    const defaultProps = {
      children: 'children',
      label: 'name',
    }
    const filterText = ref('')

    // 监听搜索框数据
    const treeRef = ref()
    watch(filterText, (val) => {
      treeRef.value.filter(val)
    })
    // 数据搜索
    const filterNode = (value, data) => {
      if (!value) return true
      return data.name.toLowerCase().indexOf(value) !== -1
    }
    // 初始化调用
    let categoryTreeData = reactive([]);

    const getTreeList = () => {
      resource.getTreeCategory().then(response => {
        categoryTreeData = categoryTreeData.push(...response.data)
      }).catch(error => {
      })
    }
    getTreeList();


    return {
      filterNode,
      categoryTreeData,
      defaultProps,
      filterText,
      treeRef
    }
  },

}
</script>

<style scoped>

</style>
