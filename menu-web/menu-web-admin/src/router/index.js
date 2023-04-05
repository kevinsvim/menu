import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
    {
        path: '/login',
        name: 'Login',
        meta: {
          showRouter: false
        },
        component: () => import('@/views/login/Index.vue')
    },
    {
        path: '/',
        component: () => import('@/views/home/Index.vue'),
        children: [
            {
                path: 'home',
                name: 'Home',
                component: () => import('@/views/Index.vue'),
                meta: {
                    title: '首页',
                    showRouter: true,
                    icons: 'menu-icon-shouye'
                },
            },
            {
                path: 'category',
                name: 'Category',
                // component: () => import('@/views/home/Index.vue'),
                meta: {
                    title: '分类管理',
                    showRouter: true,
                    icons: 'menu-icon-fenleiguanli'
                },
                children: [
                    {
                        path: '/category/list',
                        name: 'List',
                        component: ()=> import('@/views/category/List.vue'),
                        meta: {
                            title: '分类列表',
                            showRouter: true
                        }
                    },
                    {
                        path: '/category/update',
                        name: 'Update',
                        component: () => import('@/views/category/Update.vue'),
                        meta: {
                            title: '更新分类',
                            showRouter: true
                        }
                    },
                    {
                        path: '/category/batchSave',
                        name: 'BatchSave',
                        component: () => import('@/views/category/BatchSave.vue'),
                        meta: {
                            title: '批量上传',
                            showRouter: true
                        }
                    }
                ]
            },
            {
                path: 'article',
                name: 'Article',
                meta: {
                    title: '文章管理',
                    showRouter: true,
                    icons: 'menu-icon-fenleiguanli'
                },
                children: [
                    {
                        path: '/addArticle',
                        name: 'AddArticle',
                        component: () => import('@/views/article/Add.vue'),
                        meta: {
                            title: '添加文章',
                            showRouter: true
                        }
                    }
                ]
            }
        ]
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
