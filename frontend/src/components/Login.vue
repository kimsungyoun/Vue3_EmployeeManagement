<template>
  <div id="container">
      <div class="login-container" v-if="!store.state.account.no">
        <div class="loginForm">
          <h2>로그인</h2>
          <ul>
            <li><span>아이디</span></li>
            <li><input type="text" placeholder="아이디" v-model="state.form.email"></li>
          </ul>
          <ul>
            <li><span>패스워드</span></li>
            <li><input type="password" placeholder="패스워드" v-model="state.form.password"></li>
          </ul>
        </div>
      
        <div class="btn-container">
          <input type="button" value="회원가입" id="signup" @click="signup()"/>
          <input type="button" value="로그인" id="login" @click="login()">  
        </div>
      </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { reactive } from 'vue';
import store from "@/script/store";
import router from "@/script/router";

const state = reactive({
  form:{
    email:"",
    password:"",
  }
})

const login = () => {
  axios.post("/api/account/login", state.form).then((res)=>{ 
    store.commit('setAccount', res.data);
    sessionStorage.setItem("no", res.data.no);
    router.push({path: '/notice'});
    window.alert("환영합니다!");
  }).catch( () => {
    sessionStorage.removeItem("no");
    window.alert("로그인 실패!");
  });
};

const signup=()=>{
  router.push({ path: '/employeeAdd' });
}
</script>

<style scoped>
.login-container{
  display:flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 20px 0;
}

ul{
  padding:0;
  margin:10px 0;
}

#id, #password{
  margin-bottom: 20px;
}

.loginForm{
  height: 200px;
  padding: 30px;
  border: 1px solid #98abdf;
  border-radius: 25px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: rgba(255, 255, 255, 0.6);
  
}

.btn-container{
  margin: 10px 0;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

input{
  padding: 5px;
  border-radius: 10px;
  border: 1px solid #98abdf;
}

#signup{
  margin-right: 10px;
}
</style>