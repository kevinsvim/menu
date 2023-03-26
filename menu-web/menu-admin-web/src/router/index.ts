import { createRouter, createWebHistory, createWebHashHistory } from 'vue-router';
import BasicLayout from '../layouts/BasicLayout.vue';
import BlankLayout from '../layouts/BlankLayout.vue';
import WelcomePage from '../views/Page1.vue';

import '@/assets/icons'
const hasGithubPages = import.meta.env.VITE_GHPAGES;

export default createRouter({
  history: hasGithubPages ? createWebHashHistory() : createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'index',
      meta: { title: 'Home' },
      component: BasicLayout,
      redirect: '/welcome',
      children: [
        {
          path: '/welcome',
          name: 'welcome',
          meta: { title: '欢迎', icon: 'icon-menu-icon-shanchu' },
          component: WelcomePage,
        },
        {
          path: '/admins',
          name: 'admins',
          meta: { title: '管理页', icon: 'primary', flat: true },
          component: BlankLayout,
          redirect: () => ({ name: 'homepage' }),
          children: [
            {
              path: 'homepage',
              name: 'homepage',
              meta: { title: '首页', icon: 'icon-appstore-two-tone' },
              component: () => import('../views/HomePage.vue'),
            },
            {
              path: 'category',
              name: 'category',
              meta: { title: '分类管理' },
              // component: () => import('../views/admins/PageTypography.vue'),
              children: [
                {
                  path: 'list',
                  name: 'list',
                  meta: { title: '分类列表' },
                  component: () => import('../views/category/List.vue')
                }
              ]
            },
            {
              path: 'dynamic-match/:id(\\d+)',
              name: 'dynamic-match',
              // 路由 path 默认参数再 meta.params 里
              meta: { title: '动态参数页面', params: { id: 1 } },
              component: () => import('../views/admins/DynamicMatch.vue'),
            },
          ],
        },
        {
          path: '/version',
          name: 'version',
          meta: { title: 'Version', icon: 'icon-antdesign' },
          component: () => import('../views/Detail.vue'),
        },
      ],
    },
  ],
});
