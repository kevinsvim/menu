// @ts-ignore
import request from '../utils/request.js'

export default {
  getTreeCategory(): Promise<any> {
    return request({
      url: '/resource/category/getTreeCategory',
      method: 'get'
    })
  }
}
