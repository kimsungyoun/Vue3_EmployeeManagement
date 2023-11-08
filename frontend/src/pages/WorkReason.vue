<template>
    <div class="Reason-container">
        <h1>2023년 10월 24일</h1>
        <div class="text-container">
            <label>상태</label>
            <input type="text" id="reason-" :value="state.form.status" disabled>
            <br>
            <label>사유 작성</label>
            <textarea v-model="reason"></textarea>
        </div>
        <div class="upload-container">
            <span>제출 자료</span>
            <fileList/>
        </div>
        <div class="btn-container">
            <input type="button" @click="submit()" value="제출"/>
            <input type="button" @click="cancel()" value="취소"/>
        </div>
        
    </div>
</template>

<script setup>
import fileList from "@/components/fileList.vue";
import router from "@/script/router";
import axios from "axios";
import { onMounted, reactive } from "vue";

const state =reactive({
    items:[],
    form:{
        workno:"",
        reason:"",
        status:""
    }
})

const load=()=>{
    const no = router.params.no;
    console.log(no);
    state.form.workno = no;

    axios.post("/api/workreason", no).then(({data})=>{
        state.form.status = data;
    })
}

const submit=()=>{
    const result = confirm("제출하시겠습니까?");
    if(result){
        // 요청 DB에 제출
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
.btn-container{
    margin: 10px 0;
}

input[type="button"]{
    margin-right: 10px;
    padding: 10px;
}
</style>