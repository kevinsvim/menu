import http from '@/utils/http'

export default {
  // 获取每日精选菜谱
  getDailySelection() {
    return http({
      url: '/resource/dish/getDailySelection',
      method: 'get'
    })
  },
  // 查询出所有的精选菜谱
  getHotScoreMenu() {
    return http({
      url: '/resource/dish/hotScore',
      method: 'get'
    })
  }
}
