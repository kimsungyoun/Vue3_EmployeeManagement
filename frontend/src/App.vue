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

h1, h2{
  text-align : center;
  padding:0;
  margin: 10px 0;
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
