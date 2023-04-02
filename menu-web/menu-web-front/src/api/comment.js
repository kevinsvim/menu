import request from "@/utils/http";

const api_url = '/resource/comment'

export default {

  // 添加一条评论记录
  addComment(content,dishId) {
    return request({
      url: `${api_url}/addComment`,
      method: "post",
      params: {
        content,
        dishId
      }
    });
  },

  // 根据评论id删除一条记录
  deleteCommentById(commentId) {
    return request({
      url: `${api_url}/deleteCommentById/${commentId}`,
      method: "delete"
    });
  },


};

