<template>
<div class="container">
    <div class="detailForm">
        <h1>공지</h1>
        <table>
            <tr>
                <td>작성자</td>
                <td>{{ state.employee.empname }}</td>
                <td>작성일</td>
                <td>{{ lib.formattedTime2(state.items.writedate) }}</td>
            </tr>
            <tr>
                <td>제목</td>
                <td colspan="3">{{ state.items.title }}</td>
            </tr>
            <tr>
                <td>내용</td>
                <td colspan="3">{{ state.items.content }}</td>
            </tr>
        </table>
    </div>
    <div class="btncontainer">       
        <input v-if="$store.state.account.position == 'a'&& state.items.empno == $store.state.account.no" type="button" id="modify" @click="modify(state.items.nno)" value="수정"/>
        <input type="button" id="list" @click="list()" value="목록"/>
    </div>
</div>
</template>

<script setup>
import axios from "axios";
import lib from "@/script/lib";
import { onMounted, reactive } from "vue";
import { useRoute } from "vue-router";
import router from "@/script/router";

const state = reactive({
    items:[],
    employee:[]
})

const load=(nno)=>{
    axios.get(`/api/noticeView/${nno}`).then(({data})=>{
        state.items = data;
        state.employee = data.employee;
    })
}
const modify = (nno)=>{
    router.push({path:`/noticeModify/${nno}`});
}
const list = ()=>{
    history.go(-1);
}
onMounted(()=>{
    const route = useRoute();
    const nno = route.params.nno;
    load(nno);
})
</script>

<style scoped>
.container{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 30px 0 20px 0;
}
h1{
    text-align: center;
    margin: 0 0 10px 0;
}
.detailForm{
    display: flex;
    flex-direction: column;
    padding: 30px;
    border: 1px solid #98abdf;
    width: 60%;
    border-radius: 20px;
}

table{
    border-spacing: 0;
    width: 100%;
}
tr, td {
    border: 1px solid #98abdf;
    padding : 5px;
}
tr:nth-child(1) > td{
    width: 25%;    
}
tr > td:nth-child(1), tr:nth-child(1) > td{
    text-align: center;
}
tr > td:nth-child(1), tr:nth-child(1) > td:nth-child(3){
    background-color: rgba(152,171,223,0.7);
}

.btncontainer{
    margin-top: 10px;
}
#modify{
    margin-right: 10px;
}
</style>