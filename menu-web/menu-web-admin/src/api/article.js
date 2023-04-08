import http from '@/utils/request'

const api_url = '/resource'

export default {
    // 获取所有主题分类
    getThemes() {
        return http({
            url: `${api_url}/theme/getThemes`,
            method: 'get'
        })
    },
    // 保存主题
    saveTheme(data) {
        return http({
            url: `${api_url}/theme/saveTheme`,
            method: 'post',
            data
        })
    },
    // 发布文章
    saveArticle(data) {
        return http({
            url: `${api_url}/admin/article/saveArticle`,
            method: 'post',
            data
        })
    }
}
