<template>
  <el-table :data="filterTableData" style="width: 100%">
    <el-table-column label="Date" prop="createTime" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <iconfont-svg icon="menu-icon-shouye"/>
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="Name" prop="title" />
    <el-table-column label="description" prop="description" />
    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
        >Edit</el-button
        >
        <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
        >Delete</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { ref, reactive, computed  } from "vue";
import IconfontSvg from "@/components/iconfonts/IconSvg.vue";
import articleApi from '@/api/article.js'
import dayjs from "dayjs";
export default {
  name: "AddTheme",
  components: {
    IconfontSvg

  },
  setup() {

    const tableData = reactive([])
    const search = ref('')
    // 查询所有主题
    const getThemes = () => {
      articleApi.getThemes().then(res => {
        console.log(res)
        res.data.map(x => {
          x.createTime = dayjs(x.createTime).format('YYYY-MM-DD')
          tableData.push(x)
        })
      })
    }
    // 过滤数据
    const filterTableData = computed(() =>
        tableData.filter(
            (data) =>
                !search.value ||
                data.title.toLowerCase().includes(search.value.toLowerCase())
        )
    )
    const handleEdit = (index, row) => {
      console.log(index, row)
    }
    const handleDelete = (index, row) => {
      console.log(index, row)
    }
    getThemes()
    return {
      tableData,
      search,
      filterTableData,
      handleEdit,
      handleDelete
    }
  }
}
</script>

<style scoped>

</style>
