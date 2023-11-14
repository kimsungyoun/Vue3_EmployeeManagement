<template>
  <div id="container">
      <div class="login-container">
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
          <button id="signup" @click="signup()">회원가입</button>
          <button id="submit" @click="submit()">로그인</button>  
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

const submit = () => {
  axios.post("/api/account/login", state.form).then((res)=>{  
    store.commit('setAccount', res.data);
    sessionStorage.setItem("id", res.data);

    router.push({path: '/'});
    console.log(res.data);
    alert("어서오세요!");
  }).catch( () => {
    window.alert("로그인 실패!")
  });
};

const signup=()=>{
  router.push({ path: '/employee/add' });
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
}

.btn-container{
  margin: 10px 0;
  display: flex;
  flex-direction: row;
  justify-content: center;
}

button{
  background-color: #8293c4;
  border: 1px solid #8293c4;
  border-radius: 5px;
  padding: 5px;
  color:#FFFFFF;
}

#signup{
  margin-right: 10px;
}
</style>