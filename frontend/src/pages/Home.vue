<template>
  <div class="container">
    <div>
      <h1 v-if="!$store.state.account.id">어서오세요</h1>
      <div v-else>
        <h1>환영합니다</h1>
      </div>
      
      <a href="/login" v-if="!$store.state.account.id"><button>로그인</button></a>
      <a to="/" @click="logout()" v-else><button>로그아웃</button></a>
    </div>  
  </div>
</template>

<script setup>
import router from "@/script/router";
import store from "@/script/store";
import axios from "axios";


const logout=()=>{
  axios.post("/api/account/logout").then(()=>{
    store.commit('setAccount', 0);
    router.push({path: "/"});
    window.alert("로그아웃 되었습니다.")
  });
}

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

button{
  background-color: #8293c4;
  border: 1px solid #8293c4;
  border-radius: 5px;
  padding: 10px;
  font-size: 14px;
  color:#FFFFFF;
  text-decoration: none;
}

button:hover{
  cursor: pointer;
}
</style>