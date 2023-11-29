<template>
<header>
  <div class="menu">
    <a href="http://www.bnosoft.co.kr/" target="_blank"><img src="@/assets/logo3.png"></a>
    <div class="navbar">
      <ul>
        <div class="navbar" v-if="store.state.account.no">
          <li>
            <router-link to="/notice">공지</router-link>
          </li>
          <li>|</li>
          <li>
            <router-link to="/employeeList">직원</router-link>
          </li>
          <li>|</li>
          <li>
            <router-link to="/workList">근무</router-link>
          </li>
          <div class="navbar" v-if="store.state.account.position =='a'">
            <li>|</li>
            <li>
              <router-link to="/admin">관리</router-link>
            </li>
          </div>  
        </div>
      </ul>
    </div>

    <div class="setting">
      <ul v-if="$route.path !='/' && $route.path !='/employeeAdd'">
        <li>
          <input type="button" @click="myPage(store.state.account.id)" value="마이페이지">
        </li>
        
        <li v-if="store.state.account.position !='a' ">
          <input type="button"  value="출근"/>
          <input type="button" @click="request()" value="요청"/>
        </li>
        <li v-else>
          <input type="button" @click="addEmployee()" value="직원등록"/>
        </li>

        <li v-if="!store.state.account.id">
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
    window.alert("로그아웃!");
  })
};

const myPage = (empid)=>{
  router.push({path:`/myPage/${empid}`});
}
const request = ()=>{
  router.push({path:'/writeRequest'});
}
const addEmployee =()=>{
  router.push({path:'/employeeAdd'});
}
</script>

<style scoped>
.menu{
  background-color: #98abdf;
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 0;
  margin:0;
}
.navbar{
  display: flex;
  flex-direction: row;
  margin: auto;
}

img{
  width: 50px;
  height: 25px;
  margin-left: 30px;
}
header ul li a {
  cursor: pointer;
  color: #fff;
  text-decoration: none;
}
.navbar ul , li{
  display: flex;
  padding : 0;
  color: #FFF;
  margin-left: 10px;
}
.setting ul, li{
  display: flex;
  
}

</style>