<template>
<div id="container">
  <h1>{{ lib.formattedTime2(state.form.day) }}</h1>

  <div class="select-month">
    <!-- <input type="text" id="searchDay"> -->
    <Datepicker v-model="picked" :typeable="true" :clearable="true" position="left" auto-apply/>
    <input id="searchBtn" type="button" @click="searchDay()" value="조회"/>
  </div>

  <div id="manage_container">
    <table>
      <tr>
        <td>직원명</td>
        <td>상태</td>
        <td>출근시간</td>
        <td>퇴근시간</td>
        <td>근무시간</td>
      </tr>
      <tr v-for="(i, idx) in state.items" :key="idx">
        <td>{{i.employee.empname}}</td>
        
        <td v-if="i.workstatus == '출근'">
            {{i.workstatus}}
        </td>
        <td v-else-if="i.employee.empno == store.state.account.id">
          <a @click="reason(i.employee.empno)">
            <button class="reasonbtn">{{i.workstatus}}</button>
          </a>
        </td>
        <td v-else>
          {{i.workstatus}}        
        </td>

        <td>{{i.workon}}</td>
        <td>{{i.workoff}}</td>
        <td>{{i.worktime}}</td>
      </tr>
    </table>
  </div>
</div>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import lib from "@/script/lib";
import router from "@/script/router";
import Datepicker from "vue3-datepicker" 
import store from "@/script/store";

const state = reactive({
  items: [],
  form:{
    no:"",
    name:"",
    status:"",
    on:"",
    off:"",
    day:""
  }
})
// 오늘 날짜의 데이터를 확인
const picked = ref(new Date());

onMounted(()=>{
  load();
})

// 데이터 로드
const load = ()=>{
  state.form.day = lib.formattedTime(picked.value);
  axios.post("/api/work", state.form.day).then(({data})=>{
    state.items = data;
  })
}

// 날짜 검색
const searchDay=()=>{
  state.form.day = lib.formattedTime(picked.value);
  if(state.form.day != ""){
    axios.post("/api/work", state.form.day).then(({data})=>{
        state.items = data;
    }).catch(()=>{
      alert("입력을 확인해주세요")
    })
  }else{
    load();
  }
}

// 사유 작성 페이지로 이동
const reason = (no)=>{
    router.push({path:`/workReason/${no}`});  
};
</script>

<style scoped>
.select-month{
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
}
#manage_container{
  display: flex;
  flex-direction: column;
}
table{
  border-spacing: 0;
}

tr td{
  text-align: center;
  border: 1px solid #98abdf;
}.reasonbtn{
    background-color: #98abdf;
    border: 1px solid #98abdf;
    border-radius: 5px;
    color: #ffffff;
}
.reasonbtn:hover{
    cursor: pointer;
}

#searchBtn{
  margin-left: 5px;
}
</style>