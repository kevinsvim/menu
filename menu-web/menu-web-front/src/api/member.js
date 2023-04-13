import http from '@/utils/http'

export default {
  // 发送验证码
  sendMsm(mobile) {
    return http({
      url: `/msm/send/${mobile}`,
      method: 'get'
    })
  },
  // 注册 096539
  userRegister(params) {
    return http({
      url: '/member/auth/register',
      method: 'post',
      data: params
    })
  },
  // 登录
  userLogin(params) {
    return http({
      url: '/member/login',
      method: 'post',
      data: params
    })
  },
  getMemberRec() {
    return http({
      url: '/resource/dish/getRecMember',
      method: 'get'
    })
  }
}
