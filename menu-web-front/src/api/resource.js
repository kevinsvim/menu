import http from '@/utils/http'

export default {
  // 获取烹饪难度和烹饪时间
  getAllCookingLevelAndTime() {
    return http({
      url: '/resource/level/getAllProductLevelAndTime',
      method: 'get'
    })
  }
}
