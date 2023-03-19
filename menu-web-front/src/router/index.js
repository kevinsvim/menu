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
    component: () => import('@/views/pages/PublishMenu')
  },
  {
    path: '/publishNote',
    name: 'PublishNote',
    component: () => import('@/views/pages/PublishNote')
  },
  {
    path: '/detail/:id',
    name: 'MenuDetails',
    component: () => import('@/views/pages/MenuDetails')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
