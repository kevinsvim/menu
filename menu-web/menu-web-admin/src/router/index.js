import { createRouter, createWebHashHistory } from 'vue-router'
import { tokenStore } from "@/store/token";
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
                    icons: 'menu-icon-shouye',
                    auth: true
                },
            },
            {
                path: 'category',
                name: 'Category',
                meta: {
                    title: '分类管理',
                    showRouter: true,
                    icons: 'menu-icon-fenleiguanli',
                    auth: true
                },
                children: [
                    {
                        path: '/category/list',
                        name: 'List',
                        component: ()=> import('@/views/category/List.vue'),
                        meta: {
                            title: '分类列表',
                            showRouter: true,
                            auth: true
                        }
                    },
                    {
                        path: '/category/update',
                        name: 'Update',
                        component: () => import('@/views/category/Update.vue'),
                        meta: {
                            title: '更新分类',
                            showRouter: true,
                            auth: true
                        }
                    },
                    {
                        path: '/category/batchSave',
                        name: 'BatchSave',
                        component: () => import('@/views/category/BatchSave.vue'),
                        meta: {
                            title: '批量上传',
                            showRouter: true,
                            auth: true
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
                    icons: 'menu-icon-fenleiguanli',
                    auth: true
                },
                children: [
                    {
                        path: '/addArticle',
                        name: 'AddArticle',
                        component: () => import('@/views/article/AddArticle.vue'),
                        meta: {
                            title: '添加文章',
                            showRouter: true,
                            auth: true
                        }
                    },
                    {
                        path: '/themeList',
                        name: 'ThemeList',
                        component: () => import('@/views/article/ThemeList.vue'),
                        meta: {
                            title: '主题列表',
                            showRouter: true,
                            auth: true
                        }
                    },
                    {
                        path: '/addTheme',
                        name: 'AddTheme',
                        component: () => import('@/views/article/AddTheme.vue'),
                        meta: {
                            title: '添加主题',
                            showRouter: true,
                            auth: true
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
                    icons: 'menu-icon-fenleiguanli',
                    auth: true
                },
                children: [
                    {
                        path: '/ingredients/increase',
                        name: 'Increase',
                        component: () => import('@/views/ingredients/Increase.vue'),
                        meta: {
                            title: '添加食材',
                            showRouter: true,
                            auth: true
                        },
                    },
                    {
                        path: '/ingredients/list',
                        name: 'IngredientList',
                        component: () => import('@/views/ingredients/List.vue'),
                        meta: {
                            title: '食材列表',
                            showRouter: true,
                            auth: true
                        },
                    },
                ]
            },
            {
                path: 'dish',
                name: 'Dish',
                meta: {
                    title: '菜谱管理',
                    showRouter: true,
                    icons: 'menu-icon-fenleiguanli',
                    auth: true
                },
                children: [
                    {
                        path: '/dish/list',
                        name: 'DishList',
                        component: () => import('@/views/dish/list.vue'),
                        meta: {
                            title: '菜谱列表',
                            showRouter: true,
                            auth: true
                        },
                    }
                ]
            }
        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

/**
 * 路由跳转前判断用户是否登录
 */
router.beforeEach((to,from,next)=>{
    // to要跳转到的路径
    // from从哪个路径来
    // next往下执行的回调
    // 先从store中获取，获取不到再去localStorage中获取token
    const store = tokenStore()
    let token=store.getToken()
    console.log('路由跳转...')
    // 判断该页面是否需要登录
    if (to.meta.auth && token === '') {
        next({
            path:'/login',
            // 跳转时传递参数到登录页面，以便登录后可以跳转到对应页面
            query:{
                redirect:to.fullPath
            }
        })
    } else {
        next()
    }

})
export default router
