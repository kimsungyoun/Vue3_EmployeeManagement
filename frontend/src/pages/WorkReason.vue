<template>
    <div class="Reason-container">
        <h1>{{lib.formattedTime2(state.form.day)}}</h1>
        <input type="hidden" :value="$route.params.workno" id="workno"/>
        <div class="text-container">
            <label>상태</label>
            <input type="text" id="reason-" v-model="state.form.status" disabled>
            <br>
            <label>사유 작성</label>
            <textarea v-model="state.form.reason"></textarea>
        </div>
        <div class="upload-container">
            <span>제출 자료</span>
            <input type="file"/>
        </div>
        <div class="btn">
            <input type="button" @click="submit()" value="제출"/>
            <input type="button" @click="cancel()" value="취소"/>
        </div>
    </div>
</template>

<script setup>
import router from "@/script/router";
import axios from "axios";
import lib from "@/script/lib";
import { onMounted, reactive } from "vue";

const state = reactive({
    items:[],
    form:{
        no:"",
        day:"",
        reason:"",
        status:"",
    }
})

const load=()=>{
    const workno = document.getElementById("workno").value;
    axios.get(`/api/workreason/${workno}`).then(({data})=>{
        state.items = data;
        state.form.no = state.items.workno;
        state.form.day = lib.formattedTime(state.items.workday);
        state.form.status = state.items.workstatus;
        state.form.reason = "";
        console.log(state.form);
    })
}

const submit=()=>{
    const result = confirm("제출 하시겠습니까?");
    if(result){
        // 요청 DB에 제출
        
        alert("제출이 완료되었습니다.")
        router.push({path:"/work"})
    }else{
        alert("제출을 취소하셨습니다.")
    }
}

const cancel=()=>{
    const result = confirm("제출을 취소하시겠습니까?");
    if(result){
        router.push({path:"/work"})
    }
}

onMounted(()=>{
    load();
})
</script>

<style scoped>
.Reason-container{
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20px;
    width: 60%;
    margin:auto;
}
.text-container{
    display: flex;
    flex-direction: column;
    justify-content: center;
    text-align: center;
    width: 100%;
}

textarea{
    height: 400px;
    overflow: auto;
}

label{
    text-align: left;
}
.upload-container{
    display:flex;
    flex-direction: column;
    margin: 10px 0;
    width:100%;
}
.btn{
    margin-bottom: 10px;
}

input[type="button"]{
    margin-right: 10px;
    padding: 10px;
}
</style>