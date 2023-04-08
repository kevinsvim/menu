import http from '@/utils/http'

export default {
  getPageArticle(currentPage, pageSize) {
    return http({
      url: `/resource/front/article/getPageArticle/${currentPage}/${pageSize}`,
      method: 'get'
    })
  },
  getArticleDetail(articleId) {
    return http({
      url: `/resource/front/article/getArticleDetail/${articleId}`,
      method: 'get'
    })
  },
  getThemes() {
    return http({
      url: `/resource/theme/getThemes`,
      method: 'get'
    })
  },
  getPageArticleByThemeId(currentPage, pageSize, themeId) {
    return http({
      url: `/resource/front/article/getPageArticleByThemeId/${currentPage}/${pageSize}/${themeId}`,
      method: 'get'
    })
  },
}
