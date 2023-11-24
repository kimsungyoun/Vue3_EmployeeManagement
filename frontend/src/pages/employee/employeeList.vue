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
    <div class="page" v-if="state.totalPages">
        <input type="button" @click="previous()" value="이전"/>        
        <input type="button" v-for="pageNumber in state.totalPages" :key="pageNumber" :value="pageNumber"  @click="changePage(pageNumber)">
        <input type="button" @click="next()" value="다음"/>
    </div>
</div>    
</template>

<script setup>
import router from "@/script/router";
import axios from "axios";
import lib from "@/script/lib"
import { onMounted, reactive } from "vue";

const state = reactive({
    items:[],
    currentPage:0,
    totalPages:0,
})

const load = ()=>{
    fetchData();
}

const fetchData=()=>{
    const pageSize = 20;
    const url = `/api/employee?page=${state.currentPage}&size=${pageSize}`;

    axios.get(url).then(({data})=>{
        state.items = data.content;
        state.totalPages = data.totalPages;
    })
}

const info=(id)=>{
    router.push({path : `/employeeView/${id}`});
}

const search = ()=>{
    var keyword = document.getElementById('keyword');
    var searchkey = document.getElementById('searchkey');
    
    state.currentPage = 0;
    state.totalPages = 0;

    if(searchkey.value != ""){
        const pageSize = 20;
        const url = `/api/employeeSearch/${keyword.value}/${searchkey.value}?page=${state.currentPage}&size=${pageSize}`;

        axios.get(url).then(({data})=>{
            state.items = data.content;
            state.totalPages = data.totalPages;
        })
    }else{
        load();
    }
}

const changePage = (pageNumber) => {
    state.currentPage = pageNumber-1;
    fetchData();
};

const previous = () =>{
    if(state.currentPage != 0){
        state.currentPage = state.currentPage - 1;
        fetchData();
    }
}

const next =()=>{
    if(state.totalPages - 1 > state.currentPage){
        state.currentPage = state.currentPage + 1;
        fetchData();
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
    margin-right: 20px;
}
.employeeList{
    margin: 20px 0;
    padding: 10px;
}
thead td{
    background-color: rgba(152,171,223,0.7);
    color: #fff;
}
table{
    border-spacing: 0;
    text-align: center;
    width: 100%;
}
tr, td {
    border: 1px solid #98abdf;
}
td{
    width: 20%;
}
ul{
    display: flex;
    flex-direction: row;
    justify-content: center;
}
#keyword, #searchkey, li{
    margin-right: 10px;
}
.page{
    display: flex;
    flex-direction: row;
    justify-content: center;
}
input[type=button]{
    margin-right: 5px;
}
a:hover{
    cursor: pointer;
}
</style>