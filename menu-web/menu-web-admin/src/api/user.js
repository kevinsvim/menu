import http from '@/utils/request'

export default {
    // 移除上传的文件
    login(data) {
        return http({
            url: '/member/admin/login',
            method: 'post',
            data
        })
    }
}
