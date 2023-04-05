import http from '@/utils/http'

export default {
  // 获取动漫列表
  getAllComic() {
    return http({
      url: '/resource/comic/getAllComic',
      method: 'get'
    })
  }
}
