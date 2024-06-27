module.exports = {
    pages:{
        index:{
            entry:'src/main.js',
        }
    },
    lintOnSave: false,   //关闭语法检查

    //开启代理服务器(通过8085转发给8887)，使用vue-cli 实现
    devServer: {
        port:8085,
        proxy: 'http://localhost:8887',
    }
}
