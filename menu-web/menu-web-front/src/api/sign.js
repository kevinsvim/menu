import http from '@/utils/http'

const api_url = '/member/auth'
export default {
  login(data) {
    return http({
      url: `${api_url}/login`,
      method: 'post',
      data
    })
  },
  register(data) {
    return http({
      url: `${api_url}/register`,
      method: 'post',
      data
    })
  },
  test() {
    return http({
      url: "/resource/dish/test",
      method: 'get'
    })
  }
}
