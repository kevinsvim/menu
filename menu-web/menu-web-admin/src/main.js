import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router/index.js';
import "@/assets/fonts/icon-fonts/iconfont.js";
import iconfontSvg from "@/components/iconfonts/IconSvg.vue";
import { createPinia } from 'pinia'
import http from './utils/request.js'

const app = createApp(App)

app.config.globalProperties.$http = http
app.component("iconfont-svg",iconfontSvg)
// 路由
app.use(createPinia())
app.use(router)
app.use(ElementPlus).mount('#app')
