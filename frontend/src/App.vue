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
</style>
