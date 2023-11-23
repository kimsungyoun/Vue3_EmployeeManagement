<template>
<div class="container">
    <div class="writeForm">
        <h1>공지사항 작성</h1>
        <div class="row">
            <label>제목</label>
            <input type="text" id="title" v-model="state.forms.title"/>
        </div>
        <div class="row">
            <label>내용</label>
            <textarea id="content" v-model="state.forms.content"></textarea>
        </div>
        
        <div class="btncontainer">
            <input type="button" id="submit" @click="submit()" value="제출"/>
            <input type="button" id="cancel" @click="cancel()" value="취소"/>
        </div>
    </div>        
</div>
</template>

<script setup>
import router from "@/script/router";
import store from "@/script/store";
import axios from "axios";
import { reactive } from "vue";

const state = reactive({
    forms:{
        empno: 0,
        title: "",
        content: "",
    }
})

const submit=()=>{
    state.forms.empno = store.state.account.no;
    const result = confirm("공지사항을 등록하시겠습니까?");
    const args = JSON.parse(JSON.stringify(state.forms)); 
    if(result){
        axios.post("/api/noticeWrite", args).then(()=>{
            alert("공지사항 등록 완료")
            router.push({path:"/notice"});
        }).catch(()=>{
            alert("등록 실패")
        })
    }
}

const cancel=()=>{
    history.go(-1);
}
</script>

<style scoped>
.container{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 50px 0;
}

.writeForm{
    border: 1px solid #98abdf;
    border-radius: 20px;
    width: 800px;
    padding: 50px 100px;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.row{
    display: flex;
    flex-direction: column;
    width: 100%;
}
textarea{
    height: 400px;
    overflow: auto;
}
.btncontainer{
    margin-top: 20px;
}

#submit{
    margin-right: 10px;
}
</style>