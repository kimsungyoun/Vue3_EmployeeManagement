<template>
<div class="container">
    <ul>
        <li>이용자명 : {{ state.employee.empname }}</li>
        <li>부서 : {{ state.employee.empdept }}</li>
        <li>직책/직급 : {{ state.employee.emprule }}</li>
        <li>남은 연차 : {{ state.items.lmtotal - state.items.lmuse }}</li>
        <li>사용 연차 : {{ state.items.lmuse }}</li>
    </ul>
    <ul v-for="(i, idx) in state.request" :key="idx">
        <li>신청일 : {{ lib.formattedTime2(i.day) }}</li>
        <li>신청내용 : {{ i.content }}</li>
        <li>상세내용 : {{ i.detail }}</li>
        <li>처리상태 : {{ i.status }}</li>
    </ul>
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

</style>