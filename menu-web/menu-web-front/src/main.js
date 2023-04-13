import Vue, { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import { createPinia } from 'pinia'
import http from './utils/http'
import "@/assets/fonts/icon_fonts/iconfont.js";
import iconfontSvg from "@/components/iconfonts/IconSvg.vue";
// 总线通信
import mitt from 'mitt'
const app = createApp(App)


app.config.globalProperties.Bus = mitt()
app.use(ElementPlus)

app.use(createPinia())
app.component("iconfont-svg",iconfontSvg)
app.config.globalProperties.$http = http
app.use(router).mount('#app')

