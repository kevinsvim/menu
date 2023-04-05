import http from '@/utils/request.js'

const api_url = '/resource'
export default {
    // 获取树形分类数据
    getTreeCategory() {
        return http({
            url: `${api_url}/category/getTreeCategory/2`,
            method: 'get'
        })
    },
    // 保存分类
    saveCategory(data) {
        return http({
            url: `${api_url}/category/saveCategory`,
            method: 'post',
            data
        })
    },
    // 查看三级分类是否挂载有菜谱
    isCanRemoveThreeCategory(categoryId) {
        return http({
            url: `${api_url}/category/isCanRemoveThreeCategory/${categoryId}`,
            method: 'get'
        })
    },
    // 移除相应分类
    removeCategory(categoryLevel, categoryId) {
        return http({
            url: `${api_url}/category/removeCategory`,
            method: 'delete',
            params: {
                categoryLevel,
                categoryId
            }
        })
    }
}
