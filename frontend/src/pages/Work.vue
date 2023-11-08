<template>
<div id="container">
  <h1>{{ state.form.day }}</h1>

  <div class="select-month">
    <input type="text" id="datepicker" >
    <input type="button" @click="searchDay()" value="조회"/>
  </div>

  <div id="manage_container">
    <table>
      <tr>
        <td>직원명</td>
        <td>상태</td>
        <td>출근시간</td>
        <td>퇴근시간</td>
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
      </tr>
    </table>
  </div>
</div>
</template>

<script setup>
import {onMounted, reactive} from "vue";
import axios from "axios";
import router from "@/script/router";

const state = reactive({
  items: [],
  form:{
    name:"",
    status:"",
    on:"",
    off:"",
    day:""
  }
})

const load = ()=>{
  var today = new Date;
  var year = today.getFullYear();
  var month = ('0'+ (today.getMonth()+1)).slice(-2);
  var day = ('0'+today.getDate()).slice(-2);
  var dateString = year+'-'+month+'-'+day;
  
  state.form.day = dateString;
  axios.post("/api/work", state.form.day).then(({data})=>{
    state.items = data;
  })
}

const searchDay=()=>{
  state.form.day = document.getElementById("searchDay").value;
  const searchDay = state.form.day
  if(searchDay != ""){
    axios.post("/api/work", searchDay).then(({data})=>{
        state.items = data;
    })
  }else{
    load();
    alert("날짜를 입력하세요");
  }
}

const reason = (workno)=>{
    router.push({path:'/workReason', params:{no: workno}});
    console.log(router)
};

const datepickerScript=()=>{
  const script = document.createElement('script');
  //script.src="https://code.jquery.com/jquery-1.12.4.js";
  //script.src="https://code.jquery.com/ui/1.12.1/jquery-ui.js";
  script.async = true;
  document.body.appendChild(script);
}



onMounted(()=>{
  load();
  datepickerScript();
})

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

</style>