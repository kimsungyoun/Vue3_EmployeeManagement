<template>
  <div id="container">
    <h2>메인 페이지</h2>
    <div>
      <a href="http://www.bnosoft.co.kr/">
        <img src="@/assets/bno.png" alt="bno soft home page"/>
      </a>
      <p v-if="!store.state.account.id">어서오세요. {{store}}</p>

    </div>
  </div>
</template>

<script>
import store from "@/script/store";
export default {
  computed:{
    store(){
      return store
    }
  }
}
</script>

<style scoped>
#container{
  text-align: center;
}
img{
  width: 50%;
}
</style>