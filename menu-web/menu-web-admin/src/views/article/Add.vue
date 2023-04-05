<template>
  <div>
    <div style="display: flex; align-items: center; margin: 20px 0">
      <div style="flex: 1; font-weight: bold">文章标题:</div>
      <div style="flex: 13">
        <el-input style="height: 40px" v-model="title"/>
      </div>
    </div>
  </div>
  <div style="display: flex; align-items: center;">
    <div style="flex: 1; font-weight: bold">文章内容:</div>
    <div style="flex: 13">
      <el-scrollbar height="75vh">
      <div style="border: 1px solid #ccc">
        <Toolbar
            style="border-bottom: 1px solid #ccc"
            :editor="editorRef"
            :defaultConfig="toolbarConfig"
            :mode="mode"
        />
        <Editor
            style="height: 500px; overflow-y: hidden;"
            v-model="valueHtml"
            :defaultConfig="editorConfig"
            :mode="mode"
            @onCreated="handleCreated"
        />
      </div>
      </el-scrollbar>
    </div>
  </div>

</template>

<script>
import '@wangeditor/editor/dist/css/style.css'
import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
export default {
  name: "Add",
  components: { Editor, Toolbar },
  setup() {
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef()
    // 内容 HTML
    const valueHtml = ref('')
    const title_temp = ref('')
    const title = ref('')
    // 模拟 ajax 异步获取内容
    onMounted(() => {

    })
    const toolbarConfig = {}
    const editorConfig = { placeholder: '请输入内容...' }

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value
      if (editor == null) return
      editor.destroy()
    })

    const handleCreated = (editor) => {
      editorRef.value = editor
    }

    return {
      editorRef,
      valueHtml,
      mode: 'default',
      toolbarConfig,
      editorConfig,
      handleCreated,
      title_temp,
      title
    };
  }
}
</script>

<style scoped>

</style>
