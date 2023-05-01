import http from '@/utils/http'

const api_url = '/resource/tag'
export default {
  getTags() {
    return http({
      url: `${api_url}/getTags`,
      method: 'get'
    })
  },
  saveTags(tags) {
    return http({
      url: `${api_url}/saveTags`,
      method: 'post',
      data: tags
    })
  }
}
