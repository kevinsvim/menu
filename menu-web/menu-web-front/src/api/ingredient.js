import http from '@/utils/http'

export default {
  getPartIngredients() {
    return http({
      url: '/resource/ingredient/getPartIngredients',
      method: 'get'
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
}
