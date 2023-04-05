import http from "@/utils/http";

const api_url = '/resource/noteComment'

export default {
  // 添加一条评论记录
  addComment(content,noteId) {
    return http({
      url: `${api_url}/addComment`,
      method: "post",
      params: {
        content,
        noteId
      }
    });
  },

  // 根据评论id删除一条记录
  deleteCommentById(commentId) {
    return http({
      url: `${api_url}/deleteCommentById/${commentId}`,
      method: "delete"
    });
  },

};

