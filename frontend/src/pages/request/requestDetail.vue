<template>
<div class="container">
    <h1>업무 처리</h1>
    <div>
        <table>
            <tr>
                <td>신청인</td>
                <td>{{ state.employee.empname }}</td>
            </tr>
            <tr>
                <td>신청날짜</td>
                <td>{{ lib.formattedTime2(state.items.day) }}</td>
            </tr>
            <tr>
                <td>신청사유</td>
                <td>{{ state.items.content }}</td>
            </tr>
            <tr>
                <td>상세내용</td>
                <td>{{ state.items.detail }}</td>
            </tr>
            <tr>
                <td>진행상태</td>
                <td>
                    <RequestStatus v-model="state.items.status" :value="state.items.status"/>
                </td>
            </tr>
        </table>
    </div>
    <div class="btn-container">
        <input id="doneBtn" type="button" @click="done()" value="처리">
        <input id="cancelBtn" type="button" @click="cancel()" value="취소">
    </div>
</div>    
</template>

<script setup>
import axios from "axios";
import lib from "@/script/lib";
import RequestStatus from "@/components/RequestStatus.vue"
import { onMounted, reactive } from "vue";
import { useRoute } from "vue-router";
import router from "@/script/router";

const state = reactive({
    items:[],
    employee:[],
})

const load=(rno)=>{
    axios.get(`/api/requestDetail/${rno}`).then(({data})=>{
        state.items = data;
        state.employee = data.employee;
    })
}

onMounted(()=>{
    const route = useRoute();
    const rno = route.params.rno;
    load(rno);
})

const done=()=>{
    const args = JSON.parse(JSON.stringify(state.items));
    console.log(args);
    axios.post("/api/requestUpdate", args).then(()=>{
        router.push({path:'/admin'});
    })
}

const cancel=()=>{
    history.go(-1);
}

</script>

<style scoped>
.container{
    margin: 20px;
    padding : 20px;
    border: 1px solid #98abdf;
    border-radius: 20px;
    text-align: center;
}

table{
    border-spacing: 0;
    width: 100%;
}
td:nth-child(1){
    background-color: rgba(152,171,223,0.7);
    color: #fff;
}
tr, td {
    border: 1px solid #98abdf;
}

.btn-container{
    margin: 10px 0;
}
#doneBtn{
    margin-right: 10px;
}
</style>