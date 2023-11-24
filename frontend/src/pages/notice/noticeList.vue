<template>
<div class="container">
    <div class="notice">
        <h1>최신공지</h1>
        <div class="writebtn" v-if="$store.state.account.position == 'a'">
            <input type="button" @click="noticeWrite()" value="글작성"/>
        </div>
        <div class="noticeList">
            <table>
                <thead>
                    <tr>
                        <td>번호</td>
                        <td>제목</td>
                        <td>작성일</td>
                        <td>조회수</td>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(i, idx) in state.items" :key="idx">
                        <td>{{ i.nno }}</td>
                        <td><a @click="detail(i.nno)">{{ i.title }}</a></td>
                        <td>{{ lib.formattedTime2(i.writedate) }}</td>
                        <td>{{ i.hit }}</td>
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
</div>
</template>

<script setup>
import lib from "@/script/lib";
import router from "@/script/router";
import axios from "axios";
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
    const pageSize = 5;
    const url = `/api/noticeList?page=${state.currentPage}&size=${pageSize}`;

    axios.get(url).then(({data})=>{
        state.items = data.content;
        state.totalPages = data.totalPages;
    })
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

const noticeWrite = ()=>{
    router.push({path:'/noticeWrite'});
}

const detail=(nno)=>{
    router.push({path:`/noticeDetail/${nno}`});
}

onMounted(()=>{
    load();
})
</script>

<style scoped>
.container{
    padding: 20px;
    
}
.notice{
    border: 1px solid #98abdf;
    border-radius: 20px;
}
h1{
    text-align: center;
    margin: 10px 0 0 0;
}
.writebtn{
    padding: 0 20px;
    text-align: right;
}

.noticeList{
    padding : 20px;
}
table{
    border-spacing: 0;
    text-align: center;
    width: 100%;
}
thead td{
    color: #fff;
    background-color: rgba(152,171,223,0.7);
}
tr, td {
    border: 1px solid #98abdf;
}
td{
    width: 25%;
}
ul{
    display: flex;
    flex-direction: row;
    justify-content: center;
    padding: 0;
}
li{
    margin-right: 10px;
}

.page{
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin-bottom: 10px;
}
.page > input[type=button]{
    margin-right: 5px;
}
</style>