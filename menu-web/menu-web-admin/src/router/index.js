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
                        component: () => import('@/views/article/AddArticle.vue'),
                        meta: {
                            title: '添加文章',
                            showRouter: true
                        }
                    },
                    {
                        path: '/themeList',
                        name: 'ThemeList',
                        component: () => import('@/views/article/ThemeList.vue'),
                        meta: {
                            title: '主题列表',
                            showRouter: true
                        }
                    },
                    {
                        path: '/addTheme',
                        name: 'AddTheme',
                        component: () => import('@/views/article/AddTheme.vue'),
                        meta: {
                            title: '添加主题',
                            showRouter: true
                        }
                    },
                ]
            },
            {
                path: 'ingredients',
                name: 'Ingredients',
                meta: {
                    title: '食材管理',
                    showRouter: true,
                    icons: 'menu-icon-fenleiguanli'
                },
                children: [
                    {
                        path: '/ingredients/increase',
                        name: 'Increase',
                        component: () => import('@/views/ingredients/Increase.vue'),
                        meta: {
                            title: '添加食材',
                            showRouter: true
                        },
                    },
                    {
                        path: '/ingredients/list',
                        name: 'IngredientList',
                        component: () => import('@/views/ingredients/List.vue'),
                        meta: {
                            title: '食材列表',
                            showRouter: true
                        },
                    },
                ]
            },
        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
