<template>
<header>
  <div id="menu">
    <ul>
      <li><router-link to="/">메인</router-link></li>
      <div class="navbar" v-if="$store.state.account.id"> 
        <li>|</li>
        <li><router-link to="/employee" >직원</router-link></li>
        <li>|</li>
        <li><router-link to="/work">근무</router-link></li>
      </div>
    </ul>
    <ul v-if="$route.path !='/' && $route.path !='/login'">
      <li><button @click="request()">요청</button></li>
      <li>
        <router-link to="/login" v-if="!$store.state.account.id">로그인</router-link>
        <a to="/" @click="logout()" v-else><button>로그아웃</button></a>
      </li>
    </ul>
  </div>
</header>
</template>

<script setup>
import axios from "axios";
import store from "@/script/store";
import router from "@/script/router";

const logout = () => {
  axios.post("/api/account/logout").then(() => {
    store.commit('setAccount', 0);
    router.push({ path: "/" });
    window.alert("로그아웃 완료");
  })
};

const request=()=>{
  alert("요청!")
}
</script>

<style scoped>
header{
  background-color: #8293c4;
  padding: 15px 40px;
}

button{
    background-color: #98abdf;
    border: 1px solid #98abdf;
    color: #fff;
    padding: 5px 10px;
    border-radius: 20px;
}

#menu{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
header ul li a {
  cursor: pointer;
  color: #fff;
  text-decoration: none;
}

ul , li{
  display: flex;
  direction: ltr;
  padding : 0;
  color: #FFF;
  margin-right: 15px;
  margin-top: 5px;
  margin-bottom: 5px;
}

.navbar{
  display: flex;
  flex-direction: row;
}
</style>