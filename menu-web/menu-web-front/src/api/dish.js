import http from '@/utils/http'

const api_url = "/resource/dish"
export default {
  // 获取每日精选菜谱
  getDailySelection() {
    return http({
      url: `${api_url}/getDailySelection`,
      method: 'get'
    })
  },
  // 查询出所有的精选菜谱
  getHotScoreMenu() {
    return http({
      url: `${api_url}/hotScore`,
      method: 'get'
    })
  },
  // 根据id获取分页菜谱数据
  getDishPageByCategoryId(categoryId, pageSize, currentPage) {
    return http({
      url: `${api_url}/getAllDishByCategoryId/${categoryId}/${pageSize}/${currentPage}`,
      method: 'get'
    })
  }
}
