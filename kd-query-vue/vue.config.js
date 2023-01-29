const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
});
// 跨域配置
module.exports = {
  devServer: {
    open: true,
    host: 'localhost',
    port: 8081,
    proxy: {
      '/api': {
        target: 'http://localhost:8923',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
};
