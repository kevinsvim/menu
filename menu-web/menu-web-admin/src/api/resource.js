import request from '@/utils/request.js'

export default {
    getTreeCategory() {
        return request({
            url: '/resource/category/getTreeCategory',
            method: 'get'
        })
    }
}
