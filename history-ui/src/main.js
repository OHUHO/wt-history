import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/scss/bootstrap.scss'
import 'animate.css'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'
import 'vue-cool-lightbox/dist/vue-cool-lightbox.min.css'
import './assets/scss/style.scss'
import 'bootstrap'
import CoolLightBox from 'vue-cool-lightbox'
import VueScrollActive from 'vue-scrollactive'
import AOS from 'aos'
import 'aos/dist/aos.css'
import VueTypedJs from 'vue-typed-js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import {postRequest,putRequest,getRequest,deleteRequest} from "@/api/api";

Vue.config.productionTip = false
Vue.use(CoolLightBox)
Vue.use(VueScrollActive)
Vue.use(VueTypedJs)
Vue.use(ElementUI);

//插件形式使用请求
Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;


router.beforeEach(((to, from, next) => {
  if(window.sessionStorage.getItem('tokenStr')){
    //用户信息不存在
    if(!window.sessionStorage.getItem('user')){
      return getRequest('/common/getUserInfo',"null").then(resp =>{
        if(resp){
          console.log("打印值：",resp)
          window.sessionStorage.setItem('user',JSON.stringify(resp.obj));
          next()
        }
      })
    }
    next()
  }else{
    next()
  }
}))



new Vue({
  router,
  created () {
    AOS.init({
      duration: 800,
      once: true,
    })
  },
  render: h => h(App)
}).$mount('#app')
