<template>
  <Header/>
  <RouterView/>
  <Footer/> 
</template>

<script setup>
import Header from '@/components/Header.vue';
import Footer from '@/components/Footer.vue';
import axios from 'axios';
import store from './script/store';
import { useRoute } from 'vue-router';
import { watch } from 'vue';

const check=()=>{
  axios.get("/api/account/check").then(({data})=>{
    if(data != null){
      store.commit('setAccount', data || 0);
    }else{
      store.commit('setAccount', 0);
    }
  })
};
const route = useRoute();

watch(route , ()=>{
  check();
})
</script>

<style>
#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh; 
}

#content {
  flex: 1; 
}

Footer {
  margin-top: auto; 
}
body{
  margin : 0;
}

ul li{
  list-style: none;
}

input[type="button"]{
  background-color: #98abdf;
  border: 1px solid #98abdf;
  color: #fff;
  padding: 5px 10px;
  border-radius: 10px;
}

input[type=button]:hover{
  cursor: pointer;
}
</style>
