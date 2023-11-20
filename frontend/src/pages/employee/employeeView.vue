<template>
    <div class="employeeInfo-container">
        <div class="employeeInfo">
            <table>
                <tr>
                    <td>직원명</td>
                    <td>{{ state.employee.empname }}</td>
                </tr>
                <tr>
                    <td>직책/직급</td>
                    <td>{{ state.employee.emprule }}</td>
                </tr>
                <tr>
                    <td>부서</td>
                    <td>{{ state.employee.empdept }}</td>
                </tr>
                <tr>
                    <td>입사일</td>
                    <td>{{ lib.formattedTime2(state.employee.emphiredate) }}</td>
                </tr>
                <tr>
                    <td>연락처</td>
                    <td>{{ state.employee.empphone }}</td>
                </tr>
                <tr>
                    <td>우편번호</td>
                    <td>{{ state.employee.emppostal }}</td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td>{{ state.employee.empaddr }}</td>
                </tr>
                <tr>
                    <td>상세주소</td>
                    <td>{{ state.employee.empdetail }}</td>
                </tr>
                <tr>
                    <td>총 연차 일수</td>
                    <td>{{ state.items.lmtotal }}</td>
                </tr>
                <tr>
                    <td>사용 연차 일수</td>
                    <td>{{ state.items.lmuse }}</td>
                </tr>
            </table>
        </div>
        <div class="btn-container">
            <input id="modify" value="수정" type="button" @click="modify(state.employee.empid)"/>
            <input id="cancel" value="취소" type="button" @click="cancel()"/>
        </div>
    </div>
</template>

<script setup>
import axios from "axios";
import lib from "@/script/lib";
import { onMounted, reactive } from "vue";
import router from "@/script/router";
import { useRoute } from "vue-router";

const state = reactive({
    items:[],
    employee:[]
})

const load=(empid)=>{    
    axios.get(`/api/employeeInfo/${empid}`).then(({data})=>{
        state.items = data;
        state.employee = data.employee;
    })
}

const modify=(empid)=>{
    router.push({path:`/employeeModify/${empid}`});
}

const cancel=()=>{
    router.push({path:"/employeeList"});
}

onMounted(()=>{
    const route = useRoute();
    const empid = route.params.empid;
    load(empid);
})
</script>

<style scoped>
.employeeInfo-container{
    padding : 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px 0 ;
}
.employeeInfo{
    width: 60%;
}

table{
    border-spacing: 0;
    width: 100%;
}

td:nth-child(2n+1){
    text-align: center;
    width: 25%;
}

tr, td {
    border: 1px solid #98abdf;
    padding : 10px;
}

table > tr:nth-child(1) > td:nth-child(1){
    border-top-left-radius: 15px;
}
table > tr:nth-child(1) > td:nth-child(2){
    border-top-right-radius: 15px;
}
table > tr:nth-child(10) > td:nth-child(1){
    border-bottom-left-radius: 15px;
}
table > tr:nth-child(10) > td:nth-child(2){
    border-bottom-right-radius: 15px;
}

.btn-container{
    margin-top: 20px;
}
#modify{
    margin-right: 10px;
}
</style>