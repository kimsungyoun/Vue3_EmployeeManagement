<template>
<div id="container">
  <h1>2023년 10월 24일</h1>

  <div class="select-month">
    <input type="button" value="조회"/>
  </div>

  <div id="manage_container">
    <workList/>
  </div>
</div>
</template>

<script setup>
import {reactive} from "vue";
import axios from "axios";
import workList from "@/components/workList.vue";

const state = reactive({
  items: []
})

const load = ()=>{
  axios.get("/api/attendance").then(({data})=>{
    state.items = data;
  })
}

load();

</script>

<style scoped>
.select-month{
  display: flex;
  flex-direction: row;
  justify-content: right;
  margin-bottom: 10px;
}

/* #manage_container{
  display: flex;
  flex-direction: row;
  text-align: center;
  overflow-x: auto;
} */

workList{
  width :100%;
}
table{
  border-spacing: 0;
  width :100%;
}
tr td{
  text-align: center;
  border: 1px solid #98abdf;
}
</style>