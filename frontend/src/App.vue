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

const route = useRoute();

watch(route, () => {});
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
  background-color: #98abdf;
  border: 1px solid #98abdf;
  border-radius: 5px;
  padding: 5px;
  color:#FFFFFF;
}
</style>
