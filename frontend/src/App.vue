<template>
  <div class="wrap">
    <Header/>
    <RouterView/>
    <Footer/>
  </div>
  
</template>

<script setup>
import { useRoute } from "vue-router";
import { watch } from "vue";
import Header from '@/components/Header.vue';
import Footer from '@/components/Footer.vue';
import axios from "axios";
import store from"@/script/store";

const route = useRoute();

// 유효성 체크
const check =()=>{
  axios.get("/api/account/check").then(({data})=>{
    store.commit("setAccount", data||0);
  })
}

watch(route, () => {
  check();
});
</script>

<style>

body{
  margin : 0;
}

.wrap{
  position: relative;
  min-height: 100%;
}

h1, h2{
  text-align : center;
  padding:0;
}

#container{
  margin: 10px;
}

ul li{
  list-style: none;
}

input[type=button] {
  background-color: #8293c4;
  border: 1px solid #8293c4;
  border-radius: 5px;
  padding: 5px;
  color:#FFFFFF;
}
</style>
