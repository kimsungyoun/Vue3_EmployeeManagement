<template>
<header>
  <div id="menu">
    <ul>
      <li><router-link to="/">메인</router-link></li>
      <div class="navbar" v-if="$store.state.account.id">
        <li>|</li>
        <li><router-link to="/employeeList" >직원</router-link></li>
        <li>|</li>
        <li><router-link to="/workList">근무</router-link></li>
      </div>
      <div class="navbar" v-if="$store.state.account.position =='a'">
        <li>|</li>
        <li><router-link to="/admin">관리</router-link></li>
      </div>
    </ul>
    
    <ul v-if="$route.path !='/' && $route.path !='/login'">
      <li v-if="$store.state.account.position !='a' ">
        <input type="button" @click="request()" value="요청"/>
      </li>
      <li v-else>
        <input type="button" @click="addEmployee()" value="직원등록"/>
      </li>
      <li>
        <router-link to="/login" v-if="!$store.state.account.id"><input type="button" value="로그인"/></router-link>
        <a to="/" @click="logout()" v-else><input type="button" value="로그아웃"/></a>
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

const request = ()=>{
  router.push({path:'/writeRequest'});
}

const addEmployee =()=>{
  router.push({path:'/employeeAdd'});
}
</script>

<style scoped>
header{
  background-color: #8293c4;
  padding: 15px 40px;
}

input[type="button"]{
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