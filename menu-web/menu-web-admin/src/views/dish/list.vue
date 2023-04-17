<template>
<div>
<!--  <el-button @click="resetDateFilter">reset date filter</el-button>-->
<!--  <el-button @click="clearFilter">reset all filters</el-button>-->
  <el-table
      ref="tableRef"
      row-key="id"
      :data="menu.records"
      style="width: 100%"
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      >
    <el-table-column
        fixed="left"
        prop="createTime"
        label="创建日期"
        sortable
        width="180"
        column-key="date"
    />
    <el-table-column label="菜谱标题" width="180">
      <template #default="scope">
        <span class="text_style">{{ scope.row.name }}</span>
      </template>
    </el-table-column>
    <el-table-column label="菜谱图片" width="180">
      <template #default="scope">
        <el-avatar shape="square" :size="70" :fit="'fill'" :src="scope.row.imageUrl" />
      </template>
    </el-table-column>
    <el-table-column label="菜谱描述" width="180">
      <template #default="scope">
        <el-tooltip
            class="box-item"
            effect="dark"
            placement="top">
          <template #content>
            <span style="font-size: 13px">
              {{ scope.row.description }}
            </span>
          </template>
          <div>
              <span class="text_style">
            {{ scope.row.description }}
          </span>
          </div>
        </el-tooltip>
      </template>
    </el-table-column>
    <el-table-column label="独家发布" width="150">
      <template #default="scope">
        <span class="text_style">{{ scope.row.sole ? '是' : '否' }}</span>
      </template>
    </el-table-column>
    <el-table-column label="技巧" width="180">
      <template #default="scope">
        <span class="text_style">{{ scope.row.skill }}</span>
      </template>
    </el-table-column>
    <el-table-column
        prop="status"
        label="状态"
        width="100"
        :filters="[
        { text: '已审核', value: 1 },
        { text: '未审核', value: 0 },
      ]"
        :filter-method="filterTag"
        filter-placement="bottom-end"
    >
      <template #default="scope">
        <el-tag
            :type="scope.row.status === 0 ? '' : 'success'"
            disable-transitions
        >{{ scope.row.status === 0 ? '未审核': '已审核' }}</el-tag
        >
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="Operations" width="120">
      <template #default="scope">
        <el-button v-if="scope.row.status === 0" link type="primary" size="small" @click="() => audit(scope.row.id, 1)"
        >审核</el-button
        >
        <el-button v-else link type="primary" size="small" @click="() => audit(scope.row.id, 0)"
        >下架</el-button
        >
        <el-button @click="() => remove(scope.row.id)" link type="primary" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div style="margin-top: 40px; margin-bottom: 50px">
    <el-pagination
        v-model:current-page="menu.currentPage"
        v-model:page-size="menu.pageSize"
        :disabled=false
        :background=true
        layout="prev, pager, next, jumper"
        :total="menu.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
</div>
</template>

<script>
import { ref,reactive } from 'vue'
import dishApi from '@/api/dish.js'
import dayjs from "dayjs";
import {ElMessage} from "element-plus";
export default {
  name: "list",
  components: {

  },
  setup() {
    const tableRef = ref()
    const date_format_YYYY_MMM_DD = 'YYYY-MM-DD'
    const menu = reactive({
      currentPage: 1,
      pageSize: 5,
      total: 0,
      records: []
    })
    // 获取所有菜谱
    const getPageDishes = () => {
      dishApi.getAuditDishPage(menu.currentPage, menu.pageSize).then(res => {
        console.log('data:',res.data)
        menu.records = []
        menu.currentPage = res.data.current
        menu.total = res.data.total
        res.data.records.map(item => {
          item.createTime = dayjs(item.createTime).format(date_format_YYYY_MMM_DD)
          menu.records.push(item)
        })
      })
    }
    getPageDishes()
    const clearFilter = () => {
      tableRef.value.clearFilter()
    }

    const filterTag = (value, row) => {
      return row.status === value
    }
    const handleSizeChange = (val) => {
      console.log(`${val} items per page`)
    }
    const handleCurrentChange = (val) => {
      menu.currentPage = val
      getPageDishes()
    }
    // 删除
    const remove = (id) => {
      dishApi.deleteDishById(id).then(res => {
        getPageDishes()
        ElMessage.success('删除成功')
      }).catch(error => {
        ElMessage.error(error.getMessage)
      })
    }
    // 审核/下架
    const audit = (id, status) => {
      dishApi.audit(id, status).then(res => {
        // 修改状态
        let index = menu.records.findIndex(x => x.id === id);
        menu.records[index].status = status
      }).catch(error => {
        ElMessage.error(error.getMessage)
      })
    }
    return {
      menu,
      remove,
      audit,
      handleCurrentChange,
      handleSizeChange,
      filterTag,
      clearFilter,
    }
  }
}
</script>

<style scoped lang="less">
.text_style {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
