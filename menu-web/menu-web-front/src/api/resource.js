import http from '@/utils/http'

export default {
  // 获取烹饪难度和烹饪时间
  getPublishDataEcho() {
    return http({
      url: '/resource/level/getPublishDataEcho',
      method: 'get'
    })
  },
  // 保存发布的数据
  savePublishData(publishData) {
    return http({
      url: '/resource/dish/saveDish',
      method: 'post',
      data: publishData
    })
  }
}
