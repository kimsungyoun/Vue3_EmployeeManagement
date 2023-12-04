<template>
<div class="writeRequest-container">
    <h2>요청 페이지</h2>
    <div class="requestform">
        <div class="row">
            <p>신청 일자</p>
            <Datepicker v-model="state.forms.day"/>
        </div>
        <div class="row">
            <p>신청 내용</p>
            <Content v-model="state.forms.content"/>
        </div>
        <div class="row">
            <p>사유 선택</p>
            <Detail v-model="state.forms.detail"/>
        </div>
        <div class="requestBtn">
            <input @click="request()" type="button" value="요청"/>
            <input @click="cancel()" type="button" value="취소"/>
        </div>
    </div>
</div>    
</template>

<script setup>
import Content from "@/components/Request/RequestContent";
import Detail from "@/components/Request/RequestDetail";
import Datepicker from "vue3-datepicker";
import router from "@/script/router";
import axios from "axios";
import store from "@/script/store";
import { reactive } from "vue";

const state = reactive({
    forms:{
        empid:"",
        day:new Date(),
        content:"",
        detail:"",
    }
});

const request = ()=>{
    state.forms.empid = store.state.account.id;
    const result = confirm("요청하시겠습니까?"); 
    if(result){
        console.log(state.forms);
        const args = JSON.parse(JSON.stringify(state.forms));
        axios.post('/api/requestWrite', args).then(()=>{
            alert("요청이 완료되었습니다.")
            router.push({path:"/workList"})
        }).catch(()=>{
            alert("요청 실패")
        })
    }
}

const cancel=()=>{
    router.push({path:"/workList"})
}

</script>

<style scoped>
.writeRequest-container{
    margin: 20px 0;
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
}
h2{
    text-align: center;
}
.requestform{
    display: flex;
    flex-direction: column;
    border: 1px solid #98abdf;
    padding :20px;
    border-radius: 30px;
}

.row{
    display: flex;
    flex-direction: row;
    align-items: center;
    margin: 10px;
}
select{
    width: 65%;
}
p{
    margin-right: 10px;
}
.requestBtn{
    display: flex;
    flex-direction: row;
    justify-content: center;
}

input[type=button]:nth-child(1){
    margin-right: 10px;
}
</style>