import http from '@/utils/request.js'

const api_url = '/resource/dish/admin'
export default {
    getAuditDishPage(currentPage, pageSize) {
        return http({
            url: `${api_url}/getPageDish/${currentPage}/${pageSize}`,
            method: 'get'
        })
    },
    deleteDishById(id) {
        return http({
            url: `${api_url}/removeDishById/${id}`,
            method: 'delete'
        })
    },
    audit(id, status) {
        return http({
            url: `${api_url}/auditDish/${id}/${status}`,
            method: 'put'
        })
    }
}
