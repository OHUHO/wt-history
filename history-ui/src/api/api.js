// 引入 axios
import axios from "axios";
//引入 element-ui 信息
import {Message} from "element-ui";
import router from "@/router";


//请求拦截器
axios.interceptors.request.use(config=>{
    if(window.sessionStorage.getItem('tokenStr')){
        config.headers['Authorization'] = window.sessionStorage.getItem('tokenStr');
    }
    return config;
},error=>{
    console.log(error);
})

//响应拦截器
axios.interceptors.response.use(success=>{
    //业务逻辑错误
    if(success.status && success.status == 200){
        if(success.data.code == 500 || success.data.code == 401 || success.status.code == 403){
            /*Message({
                type: 'error',
                message: success.data.message,
                //offset:50
            })*/
            return;
        }
        if(success.data.message){
            /*Message({
                type: 'success',
                message: success.data.message,
                //offset:50
            })*/
        }
    }
    return success.data;

},error => {
    if(error.response.code == 504 || error.response.code == 404){
        Message({
            type: 'error',
            message: '服务器错误',
            //offset:50
        })
    }else if(error.response.code == 403){
        Message({
            type: 'error',
            message: '权限不足，请联系管理员！',
            //offset:50
        })
    }else if(error.response.code == 401){
        Message({
            type: 'error',
            message: '尚未登录，请登录！',
            //offset:50
        })
        router.replace('/');
    }else{
        if(error.response.data.message){
            Message({
                type: 'error',
                message: error.response.data.message,
                //offset:50
            })
        }else{
            Message({
                type: 'error',
                message: '未知错误！',
                //offset:50
            })
        }
    }
    return;
})



let base = 'http://localhost:8085';

//传送json格式的post请求
export const postRequest =(url,params)=>{
    return axios({
        method:'post',
        url: `${base}${url}`,
        data: params
    })
}

//传送json格式的put请求
export const putRequest=(url,params)=>{
    return axios({
        method:'put',
        url:`${base}${url}`,
        data:params
    })
}

//传送json格式的get请求
export const getRequest=(url,params)=>{
    return axios({
        method:'get',
        url:`${base}${url}/${params}`,
    })
}

//传送json格式的delete请求
export const deleteRequest=(url,params)=>{
    return axios({
        method:'delete',
        url:`${base}${url}`,
        data:params
    })
}
