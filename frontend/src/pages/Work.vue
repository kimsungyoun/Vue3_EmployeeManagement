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
        <td>{{i[0].empname}}</td>
        <td v-if="i[1].workstatus == '출근'">
            {{i[1].workstatus}}
        </td>
        <td v-else>
          <a id="reason" @click="reason(i[1].workno)">
            {{i[1].workstatus}}
          </a>
        </td>
        <td>{{i[1].workon}}</td>
        <td>{{i[1].workoff}}</td>
        <td>{{i[1].worktime}}</td>
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
const picked =ref(new Date());

onMounted(()=>{
  load();
})

const load = ()=>{
  state.form.day = lib.formattedTime(picked.value);
  axios.post("/api/work", state.form.day).then(({data})=>{
    state.items = data;
  })
}

const searchDay=()=>{
  state.form.day = lib.formattedTime(picked.value);

  if(state.form.day != ""){
    axios.post("/api/work", state.form.day).then(({data})=>{
        state.items = data;
    })
  }else{
    load();
    alert("날짜를 입력하세요");
  }
}

const reason = (no)=>{
    router.push({path:`/workReason/${no}`, params: {workno : no}});  
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
}

#reason{
    background-color: #98abdf;
    border: 1px solid #98abdf;
    border-radius: 5px;
    color: #ffffff;
}
#reason:hover{
    cursor: pointer;
}

#searchBtn{
  margin-left: 5px;
}
</style>