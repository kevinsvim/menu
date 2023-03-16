import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { ElMessage } from 'element-plus'

export const userTokenStore = defineStore('menu_token', () => {
  // 相当于选项API中的State
  const tokenJson = ref('')
  // computed相当于选项API中的getters
  const token = computed(() => {
    try {
      // 先从JS层面获取，如果没有尝试从localStorage中获取，前两者都不符合则赋予"{}"，防止解析异常
      return JSON.parse(tokenJson.value || window.localStorage.getItem("menu_token_info") || '{}')
    } catch (err) {
      ElMessage.error('Token JSON字符串格式错误，转换失败..')
      // token字符串异常,则将其设置为空
      window.localStorage.setItem("menu_token_info", "")
      throw err
    }
  })
  function saveToken(data) {
    tokenJson.value = data
    window.localStorage.setItem("menu_token_info", data)
  }
  return { token, saveToken }
})
