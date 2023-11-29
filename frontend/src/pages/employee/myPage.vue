<template>
<div class="container">
    <div class="UserInfo">
        <table>
            <tr>
                <td>이용자명</td>
                <td>{{ state.employee.empname }}</td>
            </tr>
            <tr>
                <td>부서</td>
                <td>{{ state.employee.empdept }}</td>
            </tr>
            <tr>
                <td>직책/직급</td>
                <td>{{ state.employee.emprule }}</td>
            </tr>
            <tr>
                <td>남은 연차</td>
                <td>{{ state.items.lmtotal - state.items.lmuse }}</td>
            </tr>
            <tr>
                <td>사용 연차</td>
                <td>{{ state.items.lmuse }}</td>
            </tr>
        </table>
    </div>    
    <div class="RequestList">
        <table v-for="(i, idx) in state.request" :key="idx">
            <tr>
                <td>신청일</td>
                <td>{{ lib.formattedTime2(i.day) }}</td>
            </tr>
            <tr>
                <td>신청내용</td>
                <td>{{ i.content }}</td>
            </tr>
            <tr>
                <td>상세내용</td>
                <td>{{ i.detail }}</td>
            </tr>
            <tr>
                <td>처리상태</td>
                <td>{{ i.status }}</td>
            </tr>
            <br>
        </table>
    </div>
</div>
</template>

<script setup>
import store from '@/script/store';
import axios from 'axios';
import lib from '@/script/lib';
import { onMounted, reactive } from 'vue';

const state = reactive({
    items:[],
    employee:[],
    request:[],
})

const load=()=>{
    const empid = store.state.account.id;
    
    axios.get(`/api/employeeInfo/${empid}`).then(({data})=>{
        state.items = data;
        state.employee = data.employee;
    })

    axios.get(`/api/requestList/${empid}`).then(({data})=>{
        state.request = data;
    })
}

onMounted(()=>{ 
    load();
})

</script>

<style scoped>
.container{
    border: 1px solid #98abdf;
    margin: 20px;
    border-radius: 20px;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    padding: 20px;
}
.UserInfo{
    display: flex;
    flex-direction: row;
    width: 45%;
    height: 50%;
}
.RequestList{
    display: flex;
    flex-direction: column;
    width: 45%;
}
table{
    border-spacing: 0;
    text-align: center;
    width: 100%;
}
tr, td {
    border: 1px solid #98abdf;
    padding: 10px;
}
tr > td:nth-child(1){
    background-color: rgba(152,171,223,0.7);
    color : #fff;
}
</style>