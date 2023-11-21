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
                <tbody>
                    <tr v-for="(i, idx) in state.items" :key="idx">
                        <td v-if="i.status == '진행중'">{{ i.no }}</td>
                        <td v-if="i.status == '진행중'">{{ i.employee.empname }}</td>
                        <td v-if="i.status == '진행중'">{{ lib.formattedTime2(i.day) }}</td>
                        <td v-if="i.status == '진행중'">{{ i.content }}</td>
                        <td v-if="i.status == '진행중'"><a>{{ i.status }}</a></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="page" v-if="state.totalPages">        
            <button v-for="pageNumber in state.totalPages" :key="pageNumber" @click="changePage(pageNumber)">
                {{ pageNumber }}
            </button>
        </div>
    </div>
    <div class="doneRequest-list">
        <h2>처리목록</h2>
        <div>
            <table>
                <thead>
                    <tr>
                        <td>번호</td>
                        <td>신청자</td>
                        <td>신청일</td>
                        <td>요청내용</td>
                        <td>처리상태</td>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(i, idx) in state.items" :key="idx">
                        <div v-if="i.status != '진행중'">
                            <td>{{ i.no }}</td>
                            <td>{{ i }}</td>
                            <td>{{ i.day }}</td>
                            <td>{{ i.content }}</td>
                            <td><a>{{ i.status }}</a></td>
                        </div>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>    
</template>

<script setup>
import axios from "axios";
import lib from "@/script/lib";
import { onMounted, reactive } from "vue";

const state = reactive({
    items:[],
    currentPage:0,
    totalPages:0,
})

const load=()=>{
    fetchData();
}

const fetchData=()=>{
    const pageSize = 10;
    const url = `/api/request?page=${state.currentPage}&size=${pageSize}`;

    axios.get(url).then(({data})=>{
        console.log(data);
        state.items = data.content;
        state.totalPages = data.totalPages;
    })
}

const changePage = (pageNumber) => {
  state.currentPage = pageNumber-1;
  fetchData();
};

onMounted(()=>{
    load();
})
</script>

<style scoped>
.admin-container{
    margin: 20px 0;
}

.request-list{
    padding: 10px;
    border: 1px solid #98abdf;
    margin: 10px;
    border-radius: 15px;
}
.doneRequest-list{
    padding: 10px;
    border: 1px solid #98abdf;
    margin: 10px;
    border-radius: 15px;
}
.page{
    text-align: center;
    margin: 10px 0;
}
table{
    border-spacing: 0;
    text-align: center;
    width: 100%;
}

tr, td {
    border: 1px solid #98abdf;
}
</style>