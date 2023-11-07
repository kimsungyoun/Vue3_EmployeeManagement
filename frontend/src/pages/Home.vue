<template>
  <div id="container">
    <div>
      <h1 v-if="!$store.state.account.id">어서오세요</h1>
      <h1 v-else>환영합니다</h1>
      
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
#container{
  background-image: url('@/assets/mainpage.jpg');
  height: 100vh;
  width: 100vw;
  background-size: cover;
  background-position: 100% 100%;
  margin: 0;
  opacity: 70%;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;

}

#container > div{
  display:flex;
  flex-direction: column;
  align-content: space-between;
}
h1{
  margin-bottom:500px;
}
button{
  background-color: #98abdf;
  border: 1px solid #98abdf;
  border-radius: 5px;
  padding: 10px;
  font-size: 20px;
  color:#FFFFFF;
  text-decoration: none;
}

button:hover{
  cursor: pointer;
}
</style>