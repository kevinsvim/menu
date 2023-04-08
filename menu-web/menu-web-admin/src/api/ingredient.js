import http from '@/utils/request.js'

export default {
  saveIngredient(data) {
    return http({
      url: `/resource/ingredient/saveIngredient`,
      method: 'post',
      data
    })
  },
  /**
   * 获取所有的分页食材数据
   * @param currentPage 当前页
   * @param pageSize 每页大小
   * @returns {*}
   */
  getIngredients(currentPage, pageSize) {
    return http({
      url: `resource/ingredient/getPageIngredients/${currentPage}/${pageSize}`,
      method: 'get'
    })
  },
  updateIngredient(data) {
    return http({
      url: `resource/ingredient/updateIngredient`,
      method: 'put',
      data
    })
  },
  removeIngredientById(id) {
    return http({
      url: `resource/ingredient/removeIngredientById/${id}`,
      method: 'delete'
    })
  }
}
