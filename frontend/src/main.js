import { createApp } from 'vue'
import App from './App.vue'
import router from "@/script/router"
import store from "@/script/store"

createApp(App).use(store).use(router).mount('#app')
