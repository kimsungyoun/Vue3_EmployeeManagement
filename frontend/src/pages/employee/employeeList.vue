<template>
<div class="employee-container">
    <div class="searchform">
        <select id="keyword">
            <option value="empname">직원명</option>
            <option value="emprule">직위/직책</option>
            <option value="empdept">부서</option>
        </select>
        <input type="text" id="searchkey">
        <input type="button" id="searchbtn" @click="search()" value="조회">
    </div>
    <div class="employeeList">
        <table>
            <thead>
                <tr>
                    <td>이름</td>
                    <td>직책/직급</td>
                    <td>부서</td>
                    <td>입사일</td>
                    <td>전화번호</td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(i, idx) in state.items" :key="idx">
                    <td><a @click="info(i.empid )">{{ i.empname }}</a></td>
                    <td>{{ i.emprule }}</td>
                    <td>{{ i.empdept }}</td>
                    <td>{{ lib.formattedTime2(i.emphiredate) }}</td>
                    <td>{{ i.empphone }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="page">
        <ul>
            <li><a href="/employeeList">이전</a></li>
            <li><a href="/employeeList">1</a></li>
            <li><a href="/employeeList">다음</a></li>
        </ul>
    </div>
</div>    
</template>

<script setup>
import router from "@/script/router";
import axios from "axios";
import lib from "@/script/lib"
import { onMounted, reactive } from "vue";

const state = reactive({
    items:[]
})

const load = ()=>{
    axios.get('/api/employee').then(({data})=>{
        state.items = data;
    })
}

const info=(id)=>{
    router.push({path : `/employeeView/${id}`});
}

const search = ()=>{
    var keyword = document.getElementById('keyword');
    var searchkey = document.getElementById('searchkey');

    if(searchkey != ""){
        axios.get(`/api/employeeSearch/${keyword.value}/${searchkey.value}`).then(({data})=>{
            state.items = data;
        })
    }else{
        load()
    }
}

onMounted(()=>{
    load();
})
</script>

<style scoped>
.employee-container{
    margin: 20px 0;
}
.searchform{
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
}
.employeeList{
    margin: 20px 0;
    padding: 10px;
}

table{
    border-spacing: 0;
    text-align: center;
    width: 100%;
}

tr, td {
    border: 1px solid #98abdf;
}

ul{
    display: flex;
    flex-direction: row;
    justify-content: center;
}
li{
    margin-right: 10px;
}
</style>