<template>
  <Header/>
  <RouterView/>
  <Footer/> 
</template>

<script setup>
import Header from '@/components/Header.vue';
import Footer from '@/components/Footer.vue';
</script>

<style>
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
