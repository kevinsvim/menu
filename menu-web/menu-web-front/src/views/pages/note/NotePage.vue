<template>
<div class="note_home">
  <div style="text-align: left">
    <span style="font-weight: bold; font-size: 19px">日记列表推荐</span>
  </div>
  <el-row>
    <el-col :span="6" v-for="item in pageData.records">
      <NoteCard2
        @click="() => toNoteDetail(item.id)"
        :title="item.title"
        :id="item.id"
        :intro="item.intro"
        :image_url="item.imageList[0]"
      />
    </el-col>
  </el-row>
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
</div>
  <FoodFooter/>
</template>

<script>
import {getCurrentInstance, onDeactivated, reactive, ref} from 'vue'
import { useRouter } from 'vue-router'
import NoteCard2 from "@/components/card/NoteCard2";
import {HOME_CONSTANT, MENU_CONSTANT, NOTE_EVENT, NOTE_CONSTANT} from "@/utils/nav";
import noteApi from '@/api/note'
import FoodFooter from "@/views/footer/FoodFooter";
export default {
  name: "NotePage",
  components: {
    NoteCard2,
    FoodFooter
  },
  setup() {
    // 页数据
    const pageData = reactive({
      pageSize: 12,
      currentPage: 1,
      total: 0,
      pageCount: 1,
      records: []
    })
    // 获取到 全局事件总线
    const { Bus }  = getCurrentInstance().appContext.config.globalProperties
    const router = useRouter()
    // 修改导航栏的状态
    const updateSelectState = () => {
      localStorage.setItem(HOME_CONSTANT, 'unselect')
      localStorage.setItem(MENU_CONSTANT, 'unselect')
      localStorage.setItem(NOTE_CONSTANT, 'select')

      // 通知导航栏组件刷新选中状态
      Bus.emit(NOTE_EVENT, { state: true })
    }
    updateSelectState()

    // 获取笔记列表
    const getNoteList = () => {
      noteApi.getNotes(pageData.currentPage, pageData.pageSize).then(res => {
        pageData.total = res.data.total
        pageData.pageCount = res.data.pages
        res.data.records.map(x => {
          x.imageList = x.imageUrls.split(',')
        })
        pageData.records = res.data.records
      })
    }
    getNoteList()
    const toNoteDetail = (id) => {
      let noteRouterData = router.resolve({path: '/noteDetail', query: { id: id }})
      window.open(noteRouterData.href, '_blank')
    }
    const handleSizeChange = (val) => {
      console.log(`${val} items per page`)
    }
    const handleCurrentChange = (val) => {
      pageData.currentPage = val
      getNoteList()
    }
    return {
      pageData,
      toNoteDetail,
      handleSizeChange,
      handleCurrentChange
    }
  }
}
</script>

<style scoped>
.note_home {
  margin: 0 15vw;
}
</style>
