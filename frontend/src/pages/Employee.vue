<template>
<div id="container">
  <h2>직원 관리 페이지</h2>
  <div id="search-container">
    <select id="keyword">
      <option value="empname">직원명</option>
      <option value="empdept">부서</option>
      <option value="emprule">직위</option>
    </select>
    <input type="text" id="searchKey"/>
    <input type="button" id="searchBtn" @click="search()" @keyup="search()" value="검색"/>
  </div>

  <div id="list-container">
    <table>
      <thead>
        <tr>
          <td>직원명</td>
          <td>부서</td>
          <td>직책</td>
          <td>입사일</td>
          <td>사용 일수</td>
          <td>남은 일수</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(i, idx) in state.items" :key="idx">
          <td><a @click="info(i.employee.empid)"> {{i.employee.empname}} </a> </td>
          <td>{{i.employee.empdept}}</td>
          <td>{{i.employee.emprule}}</td>
          <td>{{lib.formattedTime(i.employee.emphiredate)}}</td>
          <td>{{i.lmuse}}</td>
          <td>{{i.lmtotal - i.lmuse}}</td>
        </tr>
      </tbody>
    </table>
  </div>

  <div class="page">

  </div>

  <div class="addBtn">
    <input type="button" @click="addEmployee()" value="직원 등록"/>
  </div>
</div>
</template>

<script setup>
import { reactive} from "vue";
import axios from "axios";
import lib from "../script/lib";
import router from "@/script/router";

const state = reactive({
  items: []
});

// 데이터 로드
const load = () => {
  axios.get("/api/employee").then(({data}) => {
    state.items = data;
  });
};

// 상세보기
const info = (id) => {
  router.push({ path: `/employeeInfo/${id}`});
};

// 직원 등록 화면으로 이동
const addEmployee = () => {
  router.push({ path: '/employee/add' });
};

// 검색 기능
const search=() => {
  let keyword = document.getElementById("keyword");
  let searchKey= document.getElementById("searchKey");
  if(searchKey.value != ""){
    axios.get(`/api/employeeSearch/${keyword.value}/${searchKey.value}`).then(({data})=>{
      state.items = data;
    }).catch((error)=>{
      console.log(error);
    })
  }else{
    axios.get("/api/employee").then(({data}) => {
      state.items = data;
    });
  }
  
};

load();


</script>

<style scoped>
#search-container{
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-bottom: 10px;
}

#list-container{
  display: flex;
  flex-direction: column;
  text-align: center;
}
table{
  border-spacing: 0;
}

td{
  border: 1px solid #98abdf;
}

.addBtn{
  margin: 10px;
  text-align: right;
}

#keyword{
  margin-right: 10px;
}
#searchKey{
  margin-right: 10px;
  border: 1px solid #000000;
  border-radius: 5px;
}
</style>