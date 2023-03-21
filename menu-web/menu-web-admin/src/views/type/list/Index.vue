<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;"/>

    <el-tree
      ref="tree2"
      :data="data2"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />

  </div>
</template>

<script>
import menu from '@/api/menu.js'

export default {

  data() {
    return {
      filterText: '',
      data2: [],
      defaultProps: {
        children: 'twoSubjectQueryVOList',
        label: 'title'
      }
    }
  },
  created() {
    this.getTreeList()
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },

  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.title.toLowerCase().indexOf(value) !== -1   //适配小写
    },
    getTreeList() {
      menu.getTreeData()
        .then(response => {
          this.data2 = response.data.list
        })
        .catch(error => {})
    }
  }
}
</script>


