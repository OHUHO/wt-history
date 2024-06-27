import Vue from 'vue'
import VueRouter from 'vue-router'
import TimelinePage from '../views/TimelinePage'
import IndexPage from "@/views/IndexPage"
import MapsPage from "@/views/MapsPage";
import LoginPage from "@/views/LoginPage";
import RegisterPage from "@/views/RegisterPage";
import MapDetailsPage from "@/views/MapDetailsPage";
import DynastyPage from "@/views/DynastyPage";
import ArticlePage from "@/views/ArticlePage";
import WritePage from "@/views/WritePage";
import CenterPage from "@/views/CenterPage";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Index',
        component: IndexPage,
        meta: {title: '中国历史平台',},
    },
    {
        path: '/timeline',
        name: 'Timeline',
        component: TimelinePage,
        meta: {title: '中国历史平台 || 时间线条',},
    },
    {
        path: '/dynasty/:dynastyId',
        name: 'Dynasty',
        component: DynastyPage,
        meta: {title: '中国历史平台',},
    },
    {
        path: '/maps',
        name: 'Maps',
        component: MapsPage,
        meta: {title: '中国历史平台 || 时空地图',},
    },
    {
        path: '/map/:dynastyId',
        name: 'Map',
        component: MapDetailsPage,
        meta: {title: '中国历史平台'}
    },
    {
        path: '/login',
        name: 'Login',
        component: LoginPage,
        meta: {title: '中国历史平台 || 登录',},
    },
    {
        path: '/register',
        name: 'Register',
        component: RegisterPage,
        meta: {title: '中国历史平台 || 注册',},
    },
    {
        path: '/article/:articleId',
        name: 'Article',
        component: ArticlePage,
        meta: {title: '中国历史平台 || 文章详情'}

    },
    {
        path: '/write',
        name: 'Write',
        component: WritePage,
        meta: {title: '中国历史平台 || 写文章'}
    },
    {
        path: '/center',
        name: 'Center',
        component: CenterPage,
        meta: {title: '中国历史平台 || 个人中心'}
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    document.title = to.meta.title;
    next();
    window.scrollTo(0, 0)
});

export default router
