import { defineStore } from 'pinia'

export const tokenStore = defineStore('tokenId', {
  state: () => ({
    token: ''
  }),
  actions: {
    saveToken(token) {
      this.token = token
      localStorage.setItem("token", token)
    },
    getToken() {
      try {
        return this.token || localStorage.getItem("token") || ''
      } catch (e) {
        localStorage.setItem("token", "")
        throw e
      }
    },
    // 移除token
    removeToken() {
      localStorage.removeItem("token")
    }
  }
})
