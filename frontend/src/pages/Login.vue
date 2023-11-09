<template>
  <main>
      <div id="login-container">
        <h2>Please sign in</h2>
        <div class="loginForm">
          <ul>
            <li>
              <label for="floatingInput">아이디</label>
            </li>
            <li>
              <input type="text" id="floatingInput" placeholder="EmpId" @keyup.enter="submit()" v-model="state.form.email">
            </li>
            <li>
              <label for="floatingPassword">패스워드</label>
            </li>
            <li>
              <input type="password" id="floatingPassword" placeholder="Password" v-model="state.form.password">
            </li>
            <li>
              <button @click="submit()" @keyup="submit()">로그인</button>
            </li>
          </ul>
        </div>
      </div>
  </main>

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
    alert("로그인 성공!");
  }).catch( () => {
    window.alert("로그인 실패!")
  });
};
</script>

<!-- <script>
import {reactive} from "vue";
import axios from "axios";
import store from "@/script/store";
import router from "@/script/router";

export default {
  setup(){
    const state = reactive({
      form: {
        email: "",
        password: ""
      }
    });

    const submit = () => {
      axios.post("/api/account/login", state.form).then((res)=>{  
        store.commit('setAccount', res.data);
        sessionStorage.setItem("id", res.data);
        router.push({path: '/'});

        window.alert("로그인 성공!");
      }).catch( () => {
        window.alert("로그인 실패!")
      });
    }

    return {state,submit};
  }
}
</script> -->

<style scoped>
#login-container{
  display:flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

ul{
  padding:0;
}
li{
  margin-bottom: 10px;
}

</style>