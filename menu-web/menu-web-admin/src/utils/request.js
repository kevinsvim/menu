import axios from 'axios'
import {useRouter} from "vue-router/dist/vue-router";

/**
 * 创建axios实例
 * @type {AxiosInstance}
 */
const http = axios.create({
    // 后端请求url
    baseURL: 'http://localhost:8080',
    // 请求超时时间ms
    timeout: 5000,
})

/**
 * request拦截器
 * 在请求发送前做处理(比如加token、对请求参数做处理)
 */
http.interceptors.request.use(config => {
    // 每次请求前需要携带token
    config.headers['Authorization'] = localStorage.getItem('admin_token') || ''
    // 某些接口不需要携带token
    return config
}, error => {
    return Promise.reject(error)
})

/**
 * response 拦截器
 * 可以在接口响应后做统一处理结果
 */

http.interceptors.response.use(response => {

        console.log('response:', response)
        // 未登录
        const router = useRouter()
        if (response.data.code === 401) {
            router.push('/login').then(r => {})
        }
        let res = response.data
        // 如果返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        return res
    }, error => {
        return Promise.reject(error)
    }
)

export default http
