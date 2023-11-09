<template>
<header>
  <div id="menu">
    <ul>

      <li><router-link to="/">메인</router-link></li>
      <!-- 
      <li v-if="$store.state.account.id">|</li>
      <li><router-link to="/employee" v-if="$store.state.account.id">직원</router-link></li>
      <li v-if="$store.state.account.id">|</li>
      <li><router-link to="/work" v-if="$store.state.account.id">근무</router-link></li>
       -->
      <li >|</li>
      <li><router-link to="/employee" >직원</router-link></li>
      <li >|</li>
      <li><router-link to="/work" >근무</router-link></li>
    </ul>

    <ul>
      <li>
        <router-link to="/login" v-if="!$store.state.account.id">로그인</router-link>
        <a to="/" @click="logout()" v-else>로그아웃</a>
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
    window.alert("로그아웃 되었습니다.");
  })
};

</script>

<!-- <script>
import axios from "axios";
import store from "@/script/store";
import router from "@/script/router";

export default {
  name: 'Header',
  setup(){
    const logout = () => {
      axios.post("/api/account/logout").then(()=>{
        store.commit('setAccount', 0);
        router.push({path: "/"});
        window.alert("로그아웃 되었습니다.")
      });
    }

    return {logout};

  }
}
</script> -->

<style scoped>
header{
  background-color: #98abdf;
  padding: 15px;
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
</style>