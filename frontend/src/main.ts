import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import "@aws-amplify/ui-vue"
import Amplify from "aws-amplify"
import awsconfig from "./config/aws/aws-exports"

Amplify.configure(awsconfig);

Vue.config.productionTip = false

export const eventBus = new Vue

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
