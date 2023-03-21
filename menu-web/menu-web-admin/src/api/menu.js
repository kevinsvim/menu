import request from '@/utils/request'

export default {
  getTreeData() {
    return request({
      url: '/menu/getTreeCategory',
      method: 'get',
      data
    })
  }
}

