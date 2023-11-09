<template>
  <div id="container">
      <div class="login-container">
        <h2>로그인</h2>
        <div class="loginForm">
          <div class="text-container">
            <ul>
              <li><span>아이디</span></li>
              <li><input type="text" id="floatingInput" placeholder="아이디" @keyup.enter="submit()" v-model="state.form.email"></li>
              
              <li><span>패스워드</span></li>
              <li><input type="password" id="floatingPassword" placeholder="패스워드" v-model="state.form.password"></li>
            </ul>
          </div>
          <div class="btn-container">
            <button @click="submit()" @keyup="submit()">로그인</button>
          </div>
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
    console.log(res);
    alert("로그인 성공!");
  }).catch( () => {
    window.alert("로그인 실패!")
  });
};
</script>

<style scoped>
.login-container{
  display:flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

ul{
  padding:0;
}

.loginForm{
  width: 250px;
  padding: 10px;
  border: 1px solid #98abdf;
  margin-bottom: 20px;
  border-radius: 25px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.btn-container{
  margin: 10px 0;
  display: flex;
  flex-direction: row;
  justify-content: center;
}


</style>