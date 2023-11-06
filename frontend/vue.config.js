module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        //target: process.env.VUE_APP_API_URL,
      }
    }
  }
}

