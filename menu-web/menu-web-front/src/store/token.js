import { defineStore } from 'pinia'

export const tokenStore = defineStore('tokenId', {
  state: () => ({
    token: ''
  }),
  actions: {
    saveToken(token) {
      this.token = token
      window.localStorage.setItem("token", token)
    },
    getToken() {
      try {
        return this.token || window.localStorage.getItem("token") || ''
      } catch (e) {
        window.localStorage.setItem("token", "")
        throw e
      }
    }
  }
})
