module.exports = {
  devServer: {
    proxy: 'http://127.0.0.1:80/api',
    // disableHostCheck: true,
      // proxy: {
      //     '/': {
      //         "target": 'http://127.0.0.1:8080/board',
      //         "pathRewrite": { '^/': '' },
      //         "changeOrigin": true,
      //         "secure": false
      //     }
      // }
  },
  transpileDependencies: [
    'vuetify'
  ],

  
}
