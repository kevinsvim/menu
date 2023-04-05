<template>
  <div style="margin: 10px 19.5vw">
    <div style="margin-top: 20px; margin-bottom: 20px; text-align: left">
      <span style="font-size: 19px; font-weight: bold">日记图集上传
      </span>
      <iconfont-svg icon="icon-tuji" size="20"/>
    </div>
    <div class="image_upload">
      <el-upload
        :action="url"
        list-type="picture-card"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove"
        :limit="8"
        :on-success="response => handleUploadSuccess(response)"
        :before-remove="removeSignalImage"
      >
        <el-icon>
          <Plus/>
        </el-icon>
      </el-upload>

      <el-dialog v-model="dialogVisible">
        <img w-full :src="dialogImageUrl" alt="Preview Image"/>
      </el-dialog>
    </div>
    <div style="margin-top: 30px; margin-bottom: 10px; text-align: left;">
       <span style="font-size: 19px; font-weight: bold">日记标题
      </span>
      <iconfont-svg icon="icon-tuji" size="20"/>
    </div>
    <div>
      <el-input
        v-model="note.title"
        maxlength="15"
        placeholder="Please input"
        show-word-limit
        type="text"
        style="height: 40px"
      />
    </div>
    <div style="margin-top: 30px; margin-bottom: 10px; text-align: left;">
       <span style="font-size: 19px; font-weight: bold">日记介绍
      </span>
      <iconfont-svg icon="icon-tuji" size="20"/>
    </div>
    <div>
      <el-input
        v-model="note.intro"
        maxlength="200"
        placeholder="Please input"
        show-word-limit
        type="textarea"
        height="100px"
      />
    </div>
    <div style="margin-top: 30px">
      <el-button :disabled="dialogVisible" type="success" style="width: 100px; height: 40px" @click="publishNote">发布</el-button>
    </div>
  </div>
  <FoodFooter/>
</template>

<script>
import { ref, reactive } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import IconfontSvg from "@/components/iconfonts/IconSvg";
import FoodFooter from "@/views/footer/FoodFooter";
import request from "@/utils/http";
import noteApi from '@/api/note'
import { useRouter } from 'vue-router'
import {ElMessage} from "element-plus";
import oss from "@/api/oss";
export default {
  name: 'PublishNote',
  components: {
    IconfontSvg,
    Plus,
    FoodFooter
  },
  setup() {
    const router = useRouter()
    // 保存内容
    const note = reactive({
      fileList: [],
      title: '',
      intro: ''
    })
    const url = request.defaults.baseURL + '/oss/upload/avatar'

    const dialogImageUrl = ref('')
    const dialogVisible = ref(false)

    const handleRemove = (uploadFile, uploadFiles) => {
      console.log(uploadFile, uploadFiles)
    }

    const handlePictureCardPreview = (uploadFile) => {
      dialogImageUrl.value = uploadFile.url
      dialogVisible.value = true
    }

    const handleUploadSuccess = (res) => {
      note.fileList.push(res.data)
    }
    // 发布日记
    const publishNote = () => {
      dialogVisible.value =  true
      noteApi.saveNoteApi(note).then(res => {
        ElMessage.error('发布成功~')
        router.push('/')
        dialogVisible.value = false
      }).catch(error => {
        ElMessage.error('网络出差了，稍后再试吧~')
        dialogVisible.value = false
      })
    }
    // 移除文件触发的方法
    const removeSignalImage = (file, fileList) => {
      oss.removeImage(file.response.data).then(result => {
      })
    }
    return {
      note,
      dialogVisible,
      dialogImageUrl,
      url,
      handlePictureCardPreview,
      handleRemove,
      handleUploadSuccess,
      publishNote,
      removeSignalImage
    }
  }
}
</script>

<style scoped>
.image_upload {
  min-height: 100px;
  width: 100%;
  border: solid 1px;
  box-shadow: 0 -5px 10px 0 #ff0000,
  -6px 0 6px 0 #3bee17,
  6px 0 6px 0 #2279ee,
  0 5px 19px 0 #eede15;
}
</style>
