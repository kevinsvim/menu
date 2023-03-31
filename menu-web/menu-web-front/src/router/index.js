import { createRouter, createWebHashHistory } from 'vue-router'
import { storeToRefs } from 'pinia'
import { tokenStore } from "@/store/token";
const routes = [
  {
    path: '/sign',
    name: 'Sign',
    component: () => import('@/views/sign/Sign'),
    meta: {
      auth: false
    }
  },
  {
    path: '/logout',
    name: 'Logout',
    component: () => import('@/views/sign/Logout'),
    meta: {
      auth: true
    }
  },
  {
    path: '/',
    redirect: '/index',
    component: () => import('@/views/pages/nav/NavigationBar'),
    children: [
      {
        path: 'index',
        name: 'Index',
        component: () => import('@/views/Index'),
        meta: {
          auth: false
        }
      },
      {
        path: 'publishMenu',
        name: 'PublishMenu',
        component: () => import('@/views/pages/PublishMenu'),
        meta: {
          auth: true
        }
      },
      {
        path: 'publishNote',
        name: 'PublishNote',
        component: () => import('@/views/pages/PublishNote'),
        meta: {
          auth: true
        }
      },
      {
        path: 'dish',
        name: 'Dish',
        redirect: '/category',
        component: () => import('@/views/pages/nav/CategoryFilterNav'),
        children: [
          {
            path: 'category',
            name: 'Category',
            component: () => import('@/views/pages/dish/DishCategory'),
            meta: {
              auth: true
            }
          },
          {
            path: 'concentration',
            name: 'Concentration',
            component: () => import('@/views/pages/dish/DishConcentration'),
            meta: {
              auth: true
            }
          },
          {
            path: 'latest',
            name: 'Latest',
            component: () => import('@/views/pages/dish/DishLatest'),
            meta: {
              auth: true
            }
          },
          {
            path: 'material',
            name: 'Material',
            component: () => import('@/views/pages/dish/DishMaterial'),
            meta: {
              auth: true
            }
          },
          {
            path: 'menu',
            name: 'Menu',
            component: () => import('@/views/pages/dish/DishMenu'),
            meta: {
              auth: true
            }
          },
        ]
      },
      {
        path: 'detail',
        name: 'MenuDetails',
        component: () => import('@/views/pages/MenuDetails'),
        meta: {
          auth: false
        }
      },
    ]
  }
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
  // 判断该页面是否需要登录
  if (to.meta.auth && token === '') {
    next({
      path:'/sign',
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
