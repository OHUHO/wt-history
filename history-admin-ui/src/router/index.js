import Vue from 'vue';
import Router from 'vue-router';
import * as path from 'path';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: () => import('../components/common/Home.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/dashboard',
                    component: () => import('../views/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/user',
                    component: () => import('../views/UserPage.vue'),
                    meta: { title: '用户管理' }
                },
                {
                    path: '/article',
                    component: () => import('../views/ArticlePage.vue'),
                    meta: { title: '文章管理' },
                },
                {
                    path:'/article/id',
                    component:() => import('../views/ArticleDetailsPage.vue'),
                    meta:{title: '文章详情', noCache:false},
                },

                {
                    path: '/history',
                    component: () => import('../views/HistoryPage.vue'),
                    meta: { title: '历史板块' }
                },


                {
                    path: '/404',
                    component: () => import('../views/404.vue'),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: () => import('../views/403.vue'),
                    meta: { title: '403' }
                },
            ]
        },
        {
            path: '/login',
            component: () => import('../views/Login.vue'),
            meta: { title: '管理员登录' }
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
