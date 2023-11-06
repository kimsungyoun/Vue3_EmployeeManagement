<template>
<div id="container">
  <h1>2023년 10월 24일</h1>

  <div class="select-month">
    <input type="button" value="조회"/>
  </div>

  <div id="manage_container">
    <table>
      <tr>
        <td>직원명</td>
        <td>상태</td>
        <td>출근시간</td>
        <td>퇴근시간</td>
      </tr>
      <tr>
        <td>김과장</td>
        <td>출근</td>
        <td>08:34</td>
        <td>18:05</td>
      </tr>
      <tr>
        <td>김부장</td>
        <td>외근</td>
        <td>-</td>
        <td>-</td>
      </tr>
      <tr>
        <td>김대리</td>
        <td>반차</td>
        <td>09:05</td>
        <td>13:10</td>
      </tr>
      <tr>
        <td>김사원</td>
        <td>지각</td>
        <td>09:05</td>
        <td>18:20</td>
      </tr>
      <tr>
        <td>김인턴</td>
        <td>출근</td>
        <td>08:35</td>
        <td>18:20</td>
      </tr>

    </table>
  </div>
</div>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import lib from "../script/lib";

export default {
  setup(){
    const state = reactive({
      items: []
    })

    const load = ()=>{
      axios.get("/api/attendance").then(({data})=>{
        state.items = data;
      })
    }

    load();

    return {state,lib};
  },
}
</script>

<style scoped>
.select-month{
  display: flex;
  flex-direction: row;
  justify-content: right;
  margin-bottom: 10px;
}

#manage_container{
  display: flex;
  flex-direction: row;
  text-align: center;
  overflow-x: auto;
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