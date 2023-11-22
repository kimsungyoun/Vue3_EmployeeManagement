<template>
<div class="admin-container">
    <div class="request-list">
        <h2>요청목록</h2>
        <div>
            <table>
                <thead>
                    <tr>
                        <td>번호</td>
                        <td>직원명</td>
                        <td>신청일자</td>
                        <td>요청내용</td>
                        <td>처리상태</td>
                    </tr>
                </thead>
                <tbody v-for="(i, idx) in state.items1" :key="idx">
                    <tr v-if="i.status == '진행중'">
                        <td><a @click="process(i.no)">{{ i.no }}</a></td>
                        <td>{{ i.employee.empname }}</td>
                        <td>{{ lib.formattedTime2(i.day) }}</td>
                        <td>{{ i.content }}</td>
                        <td>{{ i.status }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="page" v-if="state.totalPages1">        
            <input type="button" @click="previous1()" value="이전"/>
            <input type="button" v-for="pageNumber in state.totalPages1" :key="pageNumber" :value="pageNumber" @click="changePage1(pageNumber)"/>
            <input type="button" @click="next1()" value="다음"/>
        </div>
    </div>
    <div class="doneRequest-list">
        <h2>처리목록</h2>
        <div>
            <table>
                <thead>
                    <tr>
                        <td>번호</td>
                        <td>직원명</td>
                        <td>신청일자</td>
                        <td>요청내용</td>
                        <td>처리상태</td>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(i, idx) in state.items2" :key="idx">
                        <td>{{ i.no }}</td>
                        <td>{{ i.employee.empname  }}</td>
                        <td>{{ lib.formattedTime2(i.day) }}</td>
                        <td>{{ i.content }}</td>
                        <td>{{ i.status }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="page" v-if="state.totalPages2">    
            <input type="button" @click="previous2()" value="이전"/>    
            <input type="button" v-for="pageNumber in state.totalPages2" :key="pageNumber" :value="pageNumber" @click="changePage2(pageNumber)"/>
            <input type="button" @click="next2()" value="다음"/>
        </div>
    </div>
</div>    
</template>

<script setup>
import axios from "axios";
import lib from "@/script/lib";
import { onMounted, reactive } from "vue";

const state = reactive({
    items1:[],
    currentPage1:0,
    totalPages1:0,
    items2:[],
    currentPage2:0,
    totalPages2:0,
})

const load=()=>{
    fetchData1();
    fetchData2();
}
// 요청 목록
const fetchData1=()=>{
    const pageSize = 5;
    const url = `/api/request?page=${state.currentPage1}&size=${pageSize}`;

    axios.get(url).then(({data})=>{
        state.items1 = data.content;
        state.totalPages1 = data.totalPages;
    })
}
const changePage1 = (pageNumber) => {
  state.currentPage1 = pageNumber-1;
  fetchData1();
};
const previous1 = () =>{
    if(state.currentPage1 != 0){
        state.currentPage1 = state.currentPage1 - 1;
        fetchData1();
    }
}

const next1 =()=>{
    if(state.totalPages1 - 1 > state.currentPage1){
        state.currentPage1 = state.currentPage1 + 1;
        fetchData1();
    }
}
// 처리 목록
const fetchData2=()=>{
    const pageSize = 5;
    const url = `/api/request2?page=${state.currentPage2}&size=${pageSize}`;

    axios.get(url).then(({data})=>{
        state.items2 = data.content;
        state.totalPages2 = data.totalPages;
    })
}
const changePage2 = (pageNumber) => {
  state.currentPage2 = pageNumber-1;
  fetchData2();
};
const previous2 = () =>{
    if(state.currentPage2 != 0){
        state.currentPage2 = state.currentPage2 - 1;
        fetchData2();
    }
}

const next2 =()=>{
    if(state.totalPages2 - 1 > state.currentPage2){
        state.currentPage2 = state.currentPage2 + 1;
        fetchData2();
    }
}
// 요청 처리
const process=(rno)=>{
    alert("요청 번호 : "+rno);
}

onMounted(()=>{
    load();
})
</script>

<style scoped>
.admin-container{
    margin: 20px 10px;
}

.request-list, .doneRequest-list{
    padding: 20px;
    border: 1px solid #98abdf;
    margin: 10px;
    border-radius: 20px;
}
.page{
    display: flex;
    flex-direction: row;
    margin-top: 10px;
    justify-content: center;
}
input[type=button]{
    margin-right: 5px;
}
table{
    border-spacing: 0;
    text-align: center;
    width: 100%;
}
tr, td {
    border: 1px solid #98abdf;
}
a:hover{
    cursor: pointer;
}
</style>