import http from '@/utils/http'

export default {
  // 获取每日精选菜谱
  getDailySelection() {
    return http({
      url: '/resource/dish/getDailySelection',
      method: 'get'
    })
  }
}
