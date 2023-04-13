<template>
  <div>
    <el-card :body-style="{ padding: '0px' }">
      <img
        :src="imageUrl"
        class="image"
        @click="() => toNoteDetail"
      />
      <div style="padding: 14px">
        <div class="bottom">
          <span @click="() => toNoteDetail" class="text_show">{{ title }}</span>
        </div>
        <div style="text-align: right; margin-top: 10px; display: flex; justify-content: center; align-items: center">
          <span>
            <iconfont-svg icon="icon-aixin"/>
            <span style="font-size: 12px; margin-left: 2px">{{ upNum || date}}</span>
          </span>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import IconfontSvg from "@/components/iconfonts/IconSvg";
import {useRouter} from "vue-router/dist/vue-router";

export default {
  name: "NoteCard",
  props: ['imageUrl', 'width', 'height', 'upNum', 'noteId', 'intro', 'title', 'date'],
  components: {
    IconfontSvg
  },
  setup(props) {
    const router = useRouter()
    const toNoteDetail = () => {
      let data = router.resolve({path: '/noteDetail', query: {id: props.noteId}})
      open(data.href, '_blank')
    }
    return {
      toNoteDetail
    }
  }
}
</script>

<style scoped lang="less">
.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.image {
  border-top-right-radius: 8px;
  border-top-left-radius: 8px;
  width: 100%;
  display: block;
}
.text_show {
  // 超出的文本隐藏
  overflow: hidden;
  // 溢出用省略号显示
  text-overflow: ellipsis;
  // 默认不换行
  white-space: nowrap;
  line-height: 25px;
  font-size: 14px;
}
</style>
