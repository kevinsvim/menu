<template>
  <div class="theme">
    <el-card class="box-card">
      <template #header>
        <div class="card-header" style="display: flex; align-items: center;">
          <span style="font-weight: bold; font-size: 17px">添加文章主题</span>
          <iconfont-svg icon="menu-icon-tianjiaziguize" size="30"/>
        </div>
      </template>
      <el-form :model="theme" label-width="100px">
        <el-form-item>
          <template #label>
          <span style="font-weight: bold; font-size: 15px; color: #999">
            主题名
          </span>
          </template>
          <el-input style="height: 40px" v-model="theme.title"/>
        </el-form-item>
        <el-form-item>
          <template #label>
            <div style="margin-top: 8vh; display: flex; align-items: center">
              <span style="font-weight: bold; font-size: 15px; color: #999;">
                图片上传
              </span>
              <el-tooltip content="您仅能上传一张图片">
                <iconfont-svg icon="menu-icon-icon13" size="20"/>
              </el-tooltip>
            </div>
          </template>
          <el-upload
              class="upload-demo"
              drag
              :limit="1"
              action="http://localhost:8080/oss/upload/avatar"
              :on-success="handlerUploadSuccess"
          >
            <iconfont-svg icon="menu-icon-yunshangchuan" size="40"/>
            <div class="el-upload__text">
              <em>click to upload</em>
            </div>
            <template #tip>
              <div class="el-upload__tip">
                jpg/png files with a size less than 1MB
              </div>
            </template>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <template #label>
          <span style="font-weight: bold; font-size: 15px; color: #999">
            描述
          </span>
          </template>
          <el-input
              v-model="theme.description"
              :autosize="{ minRows: 2, maxRows: 6 }"
              type="textarea"
              placeholder="Please input"
          />
        </el-form-item>
      </el-form>
      <div style="margin-top: 20px;margin-left: 20px">
        <el-button type="primary" @click="saveTheme">
          <span style="line-height: 30px">保存</span>
        </el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import {ref, reactive} from "vue";
import IconfontSvg from "@/components/iconfonts/IconSvg.vue";
import articleApi from '@/api/article.js'
import {ElMessage} from "element-plus";
import { useRouter } from 'vue-router'
export default {
  name: "AddTheme",
  components: {IconfontSvg},
  setup() {

    const router = useRouter()
    let theme = reactive({
      title: '',
      imageUrl: '',
      description: ''
    })

    const saveTheme = () => {
      articleApi.saveTheme(theme).then(res => {
        ElMessage.success('保存成功!')
        theme = {}
      })
    }
    const handlerUploadSuccess = (res, file, index) => {
      theme.imageUrl = res.data
      router.push('/themeList')
    }
    return {
      theme,
      saveTheme,
      handlerUploadSuccess
    }
  }
}
</script>

<style scoped>
.theme {
  padding: 30px 30px;
}
</style>
