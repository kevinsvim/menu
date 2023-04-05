import http from '@/utils/http'

export default {
  // 获取动漫列表
  getGames() {
    return http({
      url: '/resource/game/getGames',
      method: 'get'
    })
  }
}
