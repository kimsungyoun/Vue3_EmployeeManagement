<template>
  <div class="container" >
    <Login/>
  </div>
</template>

<script setup>
import Login from "@/components/Login.vue";
</script>

<style scoped>
.container {
  margin: 0;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
  position: relative;
  color : #FFFFFF;
}

.container::before {
  content: "";
  position: absolute; 
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5); 
  z-index: -1; 
}

.container::after {
  content: "";
  background-image: url('@/assets/mainpage.jpg');
  background-size: cover;
  background-position: 100% 100%;
  position: absolute; 
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: -2; 
}
</style>