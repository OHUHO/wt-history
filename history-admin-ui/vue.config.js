/*
module.exports = {
    baseUrl: './',
    assetsDir: 'static',
    productionSourceMap: false,
     devServer: {
         proxy: {
             '/api':{
                 target:'http://jsonplaceholder.typicode.com',
                 changeOrigin:true,
                 pathRewrite:{
                     '/api':''
                 }
             }
         }
     }
}
*/

module.exports = {
    pages:{
        index:{
            entry:'src/main.js',
        }
    },
    lintOnSave: false,   //关闭语法检查

    //开启代理服务器(通过8086转发给8887)，使用vue-cli 实现
    devServer: {
        port:8086,
        proxy: 'http://localhost:8887',
    }
}
