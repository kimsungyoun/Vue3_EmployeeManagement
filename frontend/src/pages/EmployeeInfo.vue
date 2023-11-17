<template>
<div id="container">
  <div class="employee-info">
    <div class="user-info">
      <div>
        <input id="id" type="hidden" :value="$route.params.empid"/>
        <h1>{{ state.forms.name }}님의 정보</h1>
        <table>
          <tr>
            <td>직원명</td>
            <td><input id="name" type="text" v-model="state.forms.name"/></td>
          </tr>
          <tr>
            <td>부서</td>
            <td>
              <deptSelect id="dept" v-model="state.forms.dept" :value="state.forms.dept"/></td>
          </tr>
          <tr>
            <td>직책</td>
            <td><ruleSelect id="rule" v-model="state.forms.rule" :value="state.forms.rule"/></td>
          </tr>
          <tr>
            <td>입사일</td>
            <td><input id="hiredate" type="text" :value='lib.formattedTime2(state.forms.hiredate)' disabled/></td>
          </tr>
        </table>
      </div>
    </div>

    <div class="user-detail">
      <div class="row">
        <label>연락처</label>
        <input type="text" v-model="state.forms.phone"/>
      </div>
      <div class="row" >
        <label>우편 번호</label>
        <input type="text" v-model="state.forms.postal"/>
      </div>
      <div class="row">
        <label>주소</label>
        <input type="text" v-model="state.forms.address"/>
      </div>
      <div class="row" >
        <label>상세 주소</label>
        <input type="text" v-model="state.forms.detail"/>
      </div>
      <div class="leaveManagement">
        <div class="lm_row">
          <label>총 연차 일수</label>
          <input type="text" :value='state.items.lmtotal' disabled/>
        </div>
        <div class="lm_row">
          <label>사용 연차 일수</label>
          <input type="text" :value='state.items.lmuse' disabled/>
        </div>
      </div>
    </div>
    </div>
    <div class="btn">
      <input id="deleteBtn" @click="removeEmp(state.items.employee.empid)" type="button" value="삭제"/>
      <input id="saveBtn" @click="update()" type="button" value="수정"/>
      <input id="cancelBtn" @click="$router.go(-1)" type="button" value="취소"/>
    </div>
</div>
</template>

<script setup>
import lib from "@/script/lib";
import axios from "axios";
import deptSelect from "@/components/deptSelect.vue";
import ruleSelect from "@/components/ruleSelect.vue";
import router from "@/script/router";
import { onMounted, reactive } from "vue";

const state = reactive({
  items:[],
  forms:{
    id:"",
    name: "",
    phone: "",
    postal: "",
    address: "",
    detail: "",
    dept: "",
    rule: "",
    hiredate:"",
  }
})

const load=()=>{
  const empid = document.getElementById("id").value;

  axios.get(`/api/employeeInfo/${empid}`).then(({data})=>{
    state.items = data;
    state.forms.id = state.items.employee.empid;
    state.forms.name = state.items.employee.empname;
    state.forms.phone = state.items.employee.empphone;
    state.forms.postal = state.items.employee.emppostal;
    state.forms.address = state.items.employee.empaddr;
    state.forms.detail = state.items.employee.empdetail;
    state.forms.dept = state.items.employee.empdept;
    state.forms.rule = state.items.employee.emprule;
    state.forms.hiredate = state.items.employee.emphiredate;
  })
}

const update=()=>{
  const result = confirm("수정?");
  if(result){
    const args = JSON.parse(JSON.stringify(state.forms));
    args.items = JSON.stringify(state.forms);
    //console.log(args);

    axios.post('/api/employeeUpdate', args).then(()=>{
      router.push({path:'/employee'})
      alert("수정 완료");
    }).catch(error=>{
      alert("수정 실패 >> "+error);
    })
  }else{
    alert("수정 취소")
  }
}

const removeEmp=(empid)=>{
  const result = confirm("삭제?");
  if(result){
    axios.delete(`/api/employee/delete/${empid}`).then(()=>{
      router.push({path:"/employee"});
      alert("삭제 완료");
    }).catch((error)=>{
      alert("삭제 실패 >> " + error);
    })
  }else{
    alert("삭제 취소");
  }
}

onMounted(()=>{
  load();
})
</script> 

<style scoped>
.employee-info{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.user-info{
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 20px;
  border-radius: 20px;
  border: 1px solid #98abdf;
  margin-bottom: 10px;
  padding : 20px 50px;
  justify-content: space-around;

}

.user-detail{
  width: 400px;
  border-radius: 20px;
  border: 1px solid #98abdf;
  padding: 20px;
}
img{
  width: 150px;
  height: 150px;
  border-radius: 30px;
}
table{
  border-spacing: 0;
  margin-top: 10px;
}
tr td{
  text-align: center;
  border: 1px solid #98abdf;
  padding: 5px;
  border-radius: 10px;
}
.row{
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  font-size: 20px;
}
.lm_row{
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
  font-size: 20px;
  align-items: center;
}

.lm_row > input{
  width: 50%;
  text-align: center;
  font-size: 20px;
}

label{
  margin-right: 10px;
}

input{
  font-size: 20px;
  margin-top: 5px;
}

.leaveManagement{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.btn{
  display: flex;
  flex-direction: row;
  justify-content: center;
  padding: 10px;
}

#deleteBtn{
  background-color: #cc3300;
  border-radius: 10px;
  border: 1px solid #cc3300;
  padding: 10px 30px;
  color: #FFFFFF;
}

#saveBtn, #cancelBtn{
  background-color: #98abdf;
  border-radius: 10px;
  border: 1px solid #98abdf;
  padding: 10px 30px;
  color: #FFFFFF;
}

#rule, #dept{
  text-align: center;
  border: 1px solid #98abdf;
  border-radius: 10px;
  background-color: #FFFFFF;
  padding : 5px;
}
option{
  text-align: center;
}

#name, #hiredate{
  text-align: center;
  border: 0;
  background-color: #FFFFFF;
}

input[type=text]{
  padding : 5px;
  border: 1px solid #98abdf;
  border-radius: 10px;
}
input[type=button]{
  margin-right:10px;
}
</style>