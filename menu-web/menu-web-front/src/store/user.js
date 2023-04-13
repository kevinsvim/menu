import {defineStore} from 'pinia'

export const userStore = defineStore('user', {
  state: () => ({
    id: '',
    username: '',
    imageUrl: ''
  }),
  actions: {
    saveUser(user) {
      this.id = user.id
      this.username = user.username
      this.imageUrl = user.imageUrl
      localStorage.setItem("id", user.id)
      localStorage.setItem("username", user.username)
      localStorage.setItem("imageUrl", user.imageUrl)
    },
    getId() {
      try {
        return this.id || localStorage.getItem("id") || ''
      } catch (e) {
        localStorage.setItem("token", "")
        throw e
      }
    },
    getUsername() {
      try {
        return this.username || localStorage.getItem("username") || ''
      } catch (e) {
        localStorage.setItem("username", "")
        throw e
      }
    },
    getImageUrl() {
      try {
        return this.imageUrl || localStorage.getItem("imageUrl") || ''
      } catch (e) {
        localStorage.setItem("imageUrl", "")
        throw e
      }
    },
    // 判断用户是否已经登录
    isLogin() {
      return this.id !== '' || localStorage.getItem('token') !== ''
    },
    // 移除用户信息
    removeUserInfo() {
      localStorage.removeItem("id")
      localStorage.removeItem("username")
      localStorage.removeItem("imageUrl")
    }
  }
})
