<template>
<div id="container">
  <h2>근무 관리 페이지</h2>
  <div class="select-month">
    <select>
      <option>1월</option>
      <option>2월</option>
      <option>3월</option>
      <option>4월</option>
      <option>5월</option>
    </select>
    <input type="button" value="조회"/>
  </div>
  <div id="manage_container">
    <table>
      <tr>
        <td>직원명</td>
        <td>10/01</td>
        <td>10/01</td>
        <td>10/01</td>
        <td>10/01</td>
        <td>10/01</td>
      </tr>
      <tr>
        <td>김성윤</td>
        <td>출근</td>
        <td>휴일</td>
        <td>연차</td>
        <td>외근</td>
        <td>출근</td>
      </tr>
      <tr>
        <td>김성윤</td>
        <td>출근</td>
        <td>휴일</td>
        <td>연차</td>
        <td>외근</td>
        <td>출근</td>
      </tr>
      <tr>
        <td>김성윤</td>
        <td>출근</td>
        <td>휴일</td>
        <td>연차</td>
        <td>외근</td>
        <td>출근</td>
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
}
tr td{
  text-align: center;
  border: 1px solid #000000;
}

</style>