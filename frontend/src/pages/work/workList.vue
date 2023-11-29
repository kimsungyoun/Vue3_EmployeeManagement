<template>
<div class="work-container">
    <h1>{{ lib.formattedTime2(state.form.day) }}</h1>
    <div class="selectDay">
        <datepicker v-model="pickedDay" :typeable="true" :clearable="true" auto-apply/>
        <input id="searchbtn" type="button" @click="search()" value="조회"/>
    </div>
    <div class="worklist">
        <table>
            <thead>
                <tr>
                    <td>직원명</td>
                    <td>근무상태</td>
                    <td>근무일자</td>
                    <td>출근시간</td>
                    <td>퇴근시간</td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(i, idx) in state.items" :key="idx">
                    <td>{{ i.employee.empname }}</td>
                    <td>{{ i.workstatus }}</td>
                    <td>{{ lib.formattedTime2(i.workday) }}</td>
                    <td v-if="i.workstatus == '연차'||i.workstatus == '외근'">-</td>
                    <td v-else>{{ i.workon }}</td>
                    <td v-if="i.workoff != null">{{ i.workoff }}</td>
                    <td v-else>-</td>
                </tr>
            </tbody>
        </table>

    </div>
</div>    
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue';
import axios from 'axios';
import datepicker from "vue3-datepicker";
import lib from "@/script/lib";

const state = reactive({
    items:[],
    form:{
        day : ""
    }
})

const pickedDay = ref(new Date());

const load=()=>{
    state.form.day = lib.formattedTime(pickedDay.value);
    axios.post('/api/workList', state.form.day).then(({data})=>{
        state.items = data
    })
}

const search =()=>{
    state.form.day = lib.formattedTime(pickedDay.value);
    if(state.form.day != ""){
        axios.post('/api/workList', state.form.day).then(({data})=>{
            state.items = data;
        }).catch(()=>{
            alert("입력 에러");
        })
    }else{
        load();
    }
}

onMounted(()=>{
    load();
})
</script>

<style scoped>
h1{
    text-align: center;
}
.selectDay{
    display: flex;
    flex-direction: row;
    justify-content: center;
}
.worklist{
    margin: 20px 0;
    padding: 10px;
}

table{
    border-spacing: 0;
    text-align: center;
    width: 100%;
}
thead td{
    color :#fff;
    background-color: rgba(152,171,223,0.7);;
}
tr, td {
    border: 1px solid #98abdf;
}

</style>