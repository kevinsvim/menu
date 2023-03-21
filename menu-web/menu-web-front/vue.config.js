const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // 关闭eslint检查(true、false、warning)
  lintOnSave: false,
  productionSourceMap: false,
  publicPath: '/',
  outputDir: 'dist',
  devServer: {
    port: 9000,
    host: '127.0.0.1',
    open: true,
    // 开启浏览器自动更新
    hot: true,
    // proxy: {
    //   '/api': {  //   若请求的前缀不是这个'/api'，那请求就不会走代理服务器
    //     target: 'http://127.0.0.1:8080',
    //     pathRewrite: { '^/api': '' }, //将所有含/api路径的，去掉/api转发给服务器
    //     ws: true,  //用于支持websocket
    //     changeOrigin: true   //用于控制请求头中的host值
    //   }
    // }
  },

})
