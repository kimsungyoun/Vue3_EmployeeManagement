<template>
  <Header/>
  <RouterView/>
  <Footer/>
</template>

<script>
import {useRoute} from "vue-router";
import {watch} from "vue";
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue';

export default {
  name: 'App',
  components: {
    Header,
    Footer,

  },
  setup(){
    const route = useRoute();
    watch(route, ()=>{

    })
  }
}
</script>

<style>
body{
  margin : 0;
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
