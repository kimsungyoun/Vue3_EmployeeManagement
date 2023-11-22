<template>
    <div class="modify-container">
    <div class="modifyform">
        <table>
            <tr>
                <td>직원명</td>
                <td><input type="text" v-model="state.employee.empname"/></td>
            </tr>
            <tr>
                <td>직책/직급</td>
                <td><ruleSelect v-model="state.employee.emprule" :value="state.employee.emprule"/></td>
            </tr>
            <tr>
                <td>부서</td>
                <td><deptSelect v-model="state.employee.empdept" :value="state.employee.empdept"/></td>
            </tr>
            <tr>
                <td>입사일</td>
                <td><input type="text" :value="lib.formattedTime2(state.employee.emphiredate)" disabled/></td>
            </tr>
            <tr>
                <td>생년월일</td>
                <td><input type="text" :value="lib.formattedTime2(state.employee.empbirth)" disabled/></td>
            </tr>
            <tr>
                <td>연락처</td>
                <td><input type="text" v-model="state.employee.empphone"/></td>
            </tr>
            <tr>
                <td>우편번호</td>
                <td><input type="text" v-model="state.employee.emppostal"/></td>
            </tr>
            <tr>
                <td>주소</td>
                <td><input type="text" v-model="state.employee.empaddr"/></td>
            </tr>
            <tr>
                <td>상세주소</td>
                <td><input type="text" v-model="state.employee.empdetail"/></td>
            </tr>
        </table> 
    </div>
    <div class="btn-container">
        <input id="modify" type="button" value="수정" @click="modify()"/>
        <input id="cancel" type="button" value="취소" @click="cancel()"/>
    </div>
    </div>
</template>

<script setup>
import axios from "axios";
import { onMounted, reactive } from "vue";
import { useRoute } from "vue-router";
import lib from "@/script/lib"
import deptSelect from "@/components/deptSelect.vue"
import ruleSelect from "@/components/ruleSelect.vue"
import router from "@/script/router";


const state = reactive({
    employee:[],
})

const load = (empid)=>{
    axios.get(`/api/employeeInfo/${empid}`).then(({data})=>{
        state.employee = data.employee;
    })
}

const modify=()=>{
    const result = confirm("수정하시겠습니까?");
    if(result){
        const args = JSON.parse(JSON.stringify(state.employee));
        args.employee = JSON.stringify(state.employee);

        axios.post(`/api/employeeUpdate`, args).then(()=>{
            router.push({path:`/employeeView/${state.employee.empid}`});
            alert("수정 완료")
        }).catch(()=>{
            alert("수정 실패")
        })
    }else{
        alert("수정 취소")
    }
}

const cancel=()=>{
    const result = confirm("수정을 취소하시겠습니까?");
    if(result){
        history.back();
    }
}

onMounted(()=>{
    const route = useRoute();
    const empid = route.params.empid;
    load(empid);
})
</script>

<style scoped>
.modify-container{
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 20px 0;
}
.modifyform{
    width: 50%;
}

.btn-container{
    display: flex;
    flex-direction: row;
    margin: 10px 0;
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
    padding : 10px 5px;
}
table > tr:nth-child(1) > td:nth-child(1){
    border-top-left-radius: 15px;
}
table > tr:nth-child(1) > td:nth-child(2){
    border-top-right-radius: 15px;
}
table > tr:nth-child(9) > td:nth-child(1){
    border-bottom-left-radius: 15px;
}
table > tr:nth-child(9) > td:nth-child(2){
    border-bottom-right-radius: 15px;
}

input[type="text"]{
    margin: 0;
    width: 100%;
    height: 100%;
    border: 0px;
}

input[disabled]{
    background-color: #fff;
    color: rgba(0, 0, 0, .3);
}

#modify{
    margin-right: 10px;
}
</style>