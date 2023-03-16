import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
  {
    path: '/',
    name: 'index',
    component: () => import('@/views/Index')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/sign/SignIn')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/sign/SignUp')
  },
  {
    path: '/logout',
    name: 'Logout',
    component: () => import('@/views/sign/Logout')
  },
  {
    path: '/publishMenu',
    name: 'PublishMenu',
    component: () => import('@/views/home/PublishMenu')
  },
  {
    path: '/publishNote',
    name: 'PublishNote',
    component: () => import('@/views/home/PublishNote')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
