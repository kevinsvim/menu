import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import { createPinia } from 'pinia'
import http from './utils/http'

const app = createApp(App)
app.use(ElementPlus)
app.use(createPinia())
app.config.globalProperties.$http = http
app.use(router).mount('#app')

