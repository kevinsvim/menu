import {defineStore} from 'pinia'

export const userStore = defineStore('user', {
  state: () => ({
    id: '',
    username: '',
    imageUrl: ''
  }),
  actions: {
    saveUser(user) {
      console.log(user)
      this.id = user.id
      this.username = user.username
      this.imageUrl = user.imageUrl
      window.localStorage.setItem("id", user.id)
      window.localStorage.setItem("username", user.username)
      window.localStorage.setItem("imageUrl", user.imageUrl)
    },
    getId() {
      try {
        return this.id || window.localStorage.getItem("id") || ''
      } catch (e) {
        window.localStorage.setItem("token", "")
        throw e
      }
    },
    getUsername() {
      try {
        return this.username || window.localStorage.getItem("username") || ''
      } catch (e) {
        window.localStorage.setItem("username", "")
        throw e
      }
    },
    getImageUrl() {
      try {
        return this.imageUrl || window.localStorage.getItem("imageUrl") || ''
      } catch (e) {
        window.localStorage.setItem("imageUrl", "")
        throw e
      }
    },
    // 判断用户是否已经登录
    isLogin() {
      return !(this.imageUrl === '' && localStorage.getItem('token') === '')
    }
  }
})
