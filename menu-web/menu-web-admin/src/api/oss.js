import http from '@/utils/request'

export default {
    // 移除上传的文件
    removeImage(fileUrl) {
        return http({
            url: `/oss/removeImage/${fileUrl}`,
            method: 'delete'
        })
    }
}
