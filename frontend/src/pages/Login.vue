<template>
  <main>
      <div id="login-container">
        <h2>Please sign in</h2>
        <ul>
          <li>
            <label for="floatingInput">E-mail</label>
          </li>
          <li>
            <input type="email" id="floatingInput" placeholder="name@example.com" @keyup.enter="submit()" v-model="state.form.email">
          </li>
          <li>
            <label for="floatingPassword">Password</label>
          </li>
          <li>
            <input type="password" id="floatingPassword" placeholder="Password" v-model="state.form.password">
          </li>
          <li>
            <button @click="submit()">Sign in</button>
          </li>
        </ul>
      </div>
  </main>

</template>

<script>
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
        console.log(res.data);
        router.push({path:'/'});
        window.alert("로그인 성공!");
      }).catch( () => {
        window.alert("로그인 실패!")
      });
    }

    return {state,submit};
  }
}
</script>

<style scoped>
#login-container > h2{
  justify-content: center;
}

ul{
  padding:0;
}

</style>