import http from "@/utils/http";

const api_url = '/resource/note'

export default {
  saveNoteApi(data) {
    return http ({
      url: `${api_url}/saveNote`,
      method: 'post',
      data
    })
  },

  getNotes(currentPage, pageSize) {
    return http({
      url: `${api_url}/getNoteList/${currentPage}/${pageSize}`,
      method: 'get'
    })
  },

  getNoteDetail(id) {
    return http({
      url: `${api_url}/getNoteDetail/${id}`,
      method: `get`
    })
  }
};

