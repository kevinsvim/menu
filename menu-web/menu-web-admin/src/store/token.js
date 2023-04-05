import { defineStore } from 'pinia'

export const tokenStore = defineStore('tokenId', {
    state: () => ({
        token: ''
    }),
    actions: {
        saveToken(token) {
            this.token = token
            window.localStorage.setItem("admin_token", token)
        },
        getToken() {
            try {
                return this.token || window.localStorage.getItem("admin_token") || ''
            } catch (e) {
                window.localStorage.setItem("admin_token", "")
                throw e
            }
        }
    }
})
