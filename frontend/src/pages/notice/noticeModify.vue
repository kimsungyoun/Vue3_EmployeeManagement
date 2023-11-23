<template>
<div class="container">
    <div class="modifyForm">
        <h1>공지사항 수정</h1>
        <div class="row">
            <label>제목</label>
            <input type="text" id="title" v-model="state.forms.title"/>
        </div>
        <div class="row">
            <label>내용</label>
            <textarea id="content" v-model="state.forms.content"></textarea>
        </div>
        
        <div class="btncontainer">
            <input type="button" id="modify" @click="modify()" value="수정"/>
            <input type="button" id="cancel" @click="cancel()" value="취소"/>
        </div>
    </div>        
</div>
</template>

<script setup>
import router from "@/script/router";
import axios from "axios";
import { onMounted, reactive } from "vue";
import { useRoute } from "vue-router";

const state= reactive({
    forms:{
        nno:0,
        empno:0,
        title:"",
        content:"",
    }
})

const load=(nno)=>{
    axios.get(`/api/noticeModify/${nno}`).then(({data})=>{
        state.forms.nno = data.nno;
        state.forms.empno = data.empno;
        state.forms.title = data.title;
        state.forms.content = data.content;
    })
}
const modify=()=>{
    const result = confirm("공지사항을 수정하시겠습니까?");
    if(result){
        const args = JSON.parse(JSON.stringify(state.forms));
        axios.post("/api/noticeUpdate", args).then(()=>{
            router.push({path:'/notice'});
        })
    }
}

const cancel=()=>{
    history.go(-1);
}

onMounted(()=>{
    const route = useRoute();
    const nno = route.params.nno;
    load(nno);
})
</script>

<style scoped>

</style>