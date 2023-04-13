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
  },
  // 获取个人主页信息
  getPersonalInfo(data) {
    return http({
      url: '/resource/dish/getPersonalInfo',
      method: 'post',
      data
    })
  },
  // 获取实时搜索建议列表
  getSearchSuggest(data) {
    return http({
      url: '/resource/category/getSuggestList',
      method: 'post',
      data
    })
  },
  // 获取菜谱的热搜列表
  getHotSearchDish() {
    return http({
      url: `/resource/searchLog/searchList`
    })
  }
}
