<template>
<header>
  <div id="menu">
    <div class="left">
      <a href="http://www.bnosoft.co.kr/" target="_blank"><img src="@/assets/logo3.png"></a>
      <ul>
        <div class="navbar" v-if="$store.state.account.no">
          <li><router-link to="/main">메인</router-link></li>
          <li>|</li>
          <li><router-link to="/employeeList" >직원</router-link></li>
          <li>|</li>
          <li><router-link to="/workList">근무</router-link></li>
          <div class="navbar" v-if="$store.state.account.position =='a'">
            <li>|</li>
            <li><router-link to="/admin">관리</router-link></li>
          </div>  
        </div>
      </ul>
    </div>

    <div class="right">
      <ul v-if="$route.path !='/' && $route.path !='/employeeAdd'">
        <li v-if="$store.state.account.position !='a' ">
          <input type="button" @click="request()" value="요청"/>
        </li>
        <li v-else>
          <input type="button" @click="addEmployee()" value="직원등록"/>
        </li>
        <li v-if="!$store.state.account.no">
          <router-link to="/" ><input type="button" value="로그인"/></router-link>
        </li>
        <li v-else>
          <input type="button" @click="logout()" value="로그아웃"/>
        </li>
      </ul>
    </div>
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
#menu{
  display: flex;
  flex-direction: row;
  background-color: #98abdf;
  padding: 5px 40px;
  justify-content: space-between;
  margin:0;
}

img{
  width: 50px;
  height: 25px;
  margin-right: 10px;
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
}

.navbar{
  display: flex;
  flex-direction: row;
}

.left{
  display: flex;
  flex-direction: row;
  align-items: center;
}
</style>