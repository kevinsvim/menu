import http from '@/utils/http'

const api_url = '/resource'
export default {
  // 获取烹饪难度和烹饪时间
  getPublishDataEcho() {
    return http({
      url: `/resource/level/getPublishDataEcho`,
      method: 'get'
    })
  },
  // 保存发布的数据
  savePublishData(publishData) {
    return http({
      url: `${api_url}/dish/saveDish`,
      method: 'post',
      data: publishData
    })
  },
  // 获取树形结构数据
  getTreeCategory(status) {
    return http({
      url: `${api_url}/category/getTreeCategory/${status}`,
      method: 'get'
    })
  },
  // 获取精品菜谱数据
  getConcentrationMenu() {
    return http({
      url: `${api_url}/dish/getConcentrationMenu`,
      method: 'get'
    })
  },
  // 根据id获取菜谱详情数据
  getDetailByDishId(id) {
    return http({
      url: `${api_url}/dish/getDishDetailById/${id}`,
      method: 'get'
    })
  },
  /**
   * 收藏与取消收藏
   * @param type 【0-取消收藏】【1-收藏】
   * @param dishId 菜谱id
   * @param memberId 当前登录的用户id
   * @returns {*}
   */
  collect(type, dishId, memberId) {
    return http({
      url: `${api_url}/recLog/collect`,
      method: 'patch',
      params: {
        type: type,
        dishId: dishId,
        memberId: memberId
      }
    })
  }
}
