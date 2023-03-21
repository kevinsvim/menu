import http from '@/utils/http'

export default {
  // 获取banner列表
  getBannerList() {
    return http({
      url: '/resource/banner/front/bannerList',
      method: 'get'
    })
  }
}
