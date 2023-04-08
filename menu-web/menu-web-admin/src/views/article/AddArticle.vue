<template>
  <div>
    <div style="display: flex; align-items: center; margin: 20px 0">
      <div style="flex: 1; font-weight: bold">文章标题:</div>
      <div style="flex: 13">
        <el-input style="height: 40px" v-model="article.title"/>
      </div>
    </div>

  </div>
  <div style="display: flex; align-items: center;">
    <div style="flex: 1; font-weight: bold">文章内容:</div>
    <div style="flex: 13">
      <el-scrollbar height="70vh">
        <div style="border: 1px solid #ccc">
          <Toolbar
              style="border-bottom: 1px solid #ccc"
              :editor="editorRef"
              :defaultConfig="toolbarConfig"
              :mode="mode"
          />
          <Editor
              style="height: 450px; overflow-y: hidden;"
              v-model="article.content"
              :defaultConfig="editorConfig"
              :mode="mode"
              @onCreated="handleCreated"
          />
        </div>
      </el-scrollbar>
    </div>
  </div>
  <div style="margin-top: 10px; margin-left: 6vw">
    <el-button @click="handleSubmit" type="primary">提交</el-button>
  </div>

  <el-dialog v-model="dialogTableVisible" title="第二步">
    <el-row  align="middle">
      <el-col :span="3">
        <span>选择主题图片</span>
      </el-col>
      <el-col :span="21">
        <el-upload
            class="upload-demo"
            action="http://localhost:8080/oss/upload/avatar"
            :on-remove="removeSignalImage"
            :limit="1"
            :on-success="handleUploadSuccess"
        >
          <el-button type="primary">Click to upload</el-button>

        </el-upload>
      </el-col>
    </el-row>
    <el-row align="middle" style="margin-top: 30px">
      <el-col :span="3">
        <span>选择分类</span>
      </el-col>
      <el-col :span="19">
        <el-select v-model="article.themeId" clearable placeholder="Select">
          <el-option
              v-for="item in themes"
              :key="item.id"
              :label="item.title"
              :value="item.id"
          />
        </el-select>
      </el-col>
    </el-row>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogTableVisible = false">取消</el-button>
        <el-button type="primary" @click="finalHandleSubmit">
          保存
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import '@wangeditor/editor/dist/css/style.css'
import {onBeforeUnmount, ref, shallowRef, reactive} from 'vue'
import {Editor, Toolbar} from '@wangeditor/editor-for-vue'
import articleApi from '@/api/article.js'
import {ElMessage} from "element-plus";
import ossApi from '@/api/oss.js'
export default {
  name: "Add",
  components: { Editor, Toolbar },
  setup() {
    // 编辑器实例，必须用 shallowRef
    const article = reactive({
      content: '',
      title: '',
      imageUrl: '',
      themeId: ''
    })
    const themes = reactive([])
    const dialogTableVisible = ref(false)
    const editorRef = shallowRef()

    const toolbarConfig = {}
    const editorConfig = { MENU_CONF: {} }
    // 有关上传图片的配置
    editorConfig.MENU_CONF['uploadImage'] = {
      // 后台地址
      server: 'http://localhost:8080/oss/upload/avatar',
      fieldName: 'file',
      // 2M
      maxFileSize: 5 * 1024 * 1024,
      // 最多上传10个文件
      maxNumberOfFiles: 10,
      // 超时时间，默认为 10 秒
      timeout: 5 * 1000,
      customInsert(res, insertFn) {
        // res 即服务端的返回结果
        // 从 res 中找到 url alt href ，然后插入图片
        insertFn(res.data, '这是一张图片', res.data)
      },
    }

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value
      if (editor == null) return
      editor.destroy()
    })

    const handleCreated = (editor) => {
      editorRef.value = editor
    }
    // 处理内容提交
    const handleSubmit = () => {
      if (article.content !== '' && article.title !== '') {
        // 数据回显
        articleApi.getThemes().then(res => {
          themes.push(...res.data)
        })
        dialogTableVisible.value = true
      } else {
        ElMessage.error('标题或内容为空')
      }

    }
    const finalHandleSubmit = () => {
      console.log('最终提交的数据为:', article)
      dialogTableVisible.value = false
      articleApi.saveArticle(article).then(res => {
        ElMessage.success('文章发布成功!')
      })
    }
    // 处理图片上传
    const handleUploadSuccess = (res, file, index) => {
      article.imageUrl = res.data
    }
    // 移除文件触发的方法
    const removeSignalImage = (file, fileList) => {
      ossApi.removeImage(article.imageUrl).then(result => {})
    }
    return {
      editorRef,
      toolbarConfig,
      editorConfig,
      article,
      themes,
      dialogTableVisible,
      mode: 'default',
      handleCreated,
      handleSubmit,
      handleUploadSuccess,
      removeSignalImage,
      finalHandleSubmit
    };
  }
}
</script>

<style scoped>

</style>
