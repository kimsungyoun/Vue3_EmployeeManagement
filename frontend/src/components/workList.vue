<template>
    <div>
        <table>
            <tr>
                <td>직원명</td>
                <td>상태</td>
                <td>출근시간</td>
                <td>퇴근시간</td>
            </tr>
            <tr v-for="(i, idx) in state.items" :key="idx">
                <td>{{i[0].empname}}</td>
                <td><a id="reason" @click="reason(i[1].workno)">{{i[1].workstatus}}</a></td>
                <td>{{i[1].workon}}</td>
                <td>{{i[1].workoff}}</td>
            </tr>
            <tr>
                <td>김과장</td>
                <td>출근</td>
                <td>08:34</td>
                <td>18:05</td>
            </tr>
            <tr>
                <td>김부장</td>
                <td><a id="reason" @click="reason()">외근</a></td>
                <td>-</td>
                <td>-</td>
            </tr>
            <tr>
                <td>김대리</td>
                <td><a id="reason" @click="reason()">반차</a></td>
                <td>09:05</td>
                <td>13:10</td>
            </tr>
            <tr>
                <td>김사원</td>
                <td><a id="reason" @click="reason()">지각</a></td>
                <td>09:05</td>
                <td>18:20</td>
            </tr>
            <tr>
                <td>김인턴</td>
                <td>출근</td>
                <td>08:35</td>
                <td>18:20</td>
            </tr>

        </table>
    </div>
</template>

<script setup>
import router from "@/script/router";
import axios from "axios";
import { onMounted, reactive } from "vue";

const state = reactive({
    items:[]
})

const load=()=>{
    const args = '2023-11-08';
    
    console.log(args);

    axios.post("/api/work", args).then(({data})=>{
        state.items = data;
    })
}

const reason = (workno)=>{
    router.push({path:'/workReason', params:{no: workno}});
};

onMounted(()=>{
    load();
});
</script>

<style>
table{
  border-spacing: 0;
  width :100%;
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