<template>
  <h1>직원 정보</h1>
  <div class="employee-info">
    <div class="user-info">
      <img src="@/assets/bno.png" alt="프로필 사진"/>
      <div v-if="state.items.employee">
        <input type="hidden" v-model='state.form.id'>
        <table>
          <tr>
            <td>직원명</td>
            <td><input id="name" type="text" v-model="state.form.name"/></td>
          </tr>
          <tr>
            <td>부서</td>
            <td>
              <select id="dept" v-model="state.form.dept">
                <option :value="state.items.employee.empdept">{{state.form.dept}}</option>
                <option v-if="state.items.employee.empdept !== '개발팀'" value="개발팀">개발팀</option>
                <option v-if="state.items.employee.empdept !== '인사팀'" value="인사팀">인사팀</option>
                <option v-if="state.items.employee.empdept !== '경영팀'" value="경영팀">경영팀</option>
                <option v-if="state.items.employee.empdept !== '지원팀'" value="지원팀">지원팀</option>
                <option v-if="state.items.employee.empdept !== '영업팀'" value="영업팀">영업팀</option>
              </select>
            </td>
          </tr>
          <tr>
            <td>직위</td>
            <td>
              <select id="rule" v-model="state.form.rule">
                <option :value="state.items.employee.emprule">{{state.form.rule}}</option>
                <option v-if="state.items.employee.emprule !=='인턴'" value="인턴">인턴</option>
                <option v-if="state.items.employee.emprule !=='사원'" value="사원">사원</option>
                <option v-if="state.items.employee.emprule !=='대리'" value="대리">대리</option>
                <option v-if="state.items.employee.emprule !=='과장'" value="과장">과장</option>
                <option v-if="state.items.employee.emprule !=='부장'" value="부장">부장</option>
                <option v-if="state.items.employee.emprule !=='팀장'" value="팀장">팀장</option>
                <option v-if="state.items.employee.emprule !=='사장'" value="사장">사장</option>
              </select>

            </td>
          </tr>
          <tr>
            <td>입사일</td>
            <td>
              <input id="hiredate" type="text" :value='lib.formattedTime(state.items.employee.emphiredate)' disabled/>
            </td>
          </tr>
        </table>
      </div>
      <input id="deleteBtn" @click="removeEmp(state.items.employee.empid)" type="button" value="삭제"/>
    </div>

    <div class="user-detail" v-if="state.items.employee">
      <div class="row">
        <label>연락처</label>
        <input type="text" v-model="state.form.phone"/>
      </div>
      <div class="row" >
        <label>우편 번호</label>
        <input type="text" v-model="state.form.postal"/>
      </div>
      <div class="row">
        <label>주소</label>
        <input type="text" v-model="state.form.address"/>
      </div>
      <div class="row" >
        <label>상세 주소</label>
        <input type="text" v-model="state.form.detail"/>
      </div>

      <div class="leaveManagement" v-if="state.items.leaveManagement">
        <div class="lm_row">
          <label>총 연차 일수</label>
          <input type="text" :value='state.items.leaveManagement.lmtotal' disabled/>
        </div>
        <div class="lm_row">
          <label>사용 연차 일수</label>
          <input type="text" :value='state.items.leaveManagement.lmremain' disabled/>
        </div>
      </div>
      <div class="btn">
        <input id="saveBtn" @click="update()" type="button" value="수정"/>
        <input id="cancelBtn" @click="$router.go(-1)" type="button" value="취소"/>
      </div>
    </div>

  </div>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import lib from "@/script/lib";
import {useRoute} from "vue-router";
import router from "@/script/router";

export default {
  computed: {
    lib() {
      return lib
    }
  },

  setup(){
    const state = reactive({
      items:[],
      form:{
        id:"",
        name:"",
        phone:"",
        postal:"",
        address:"",
        detail:"",
        dept:"",
        rule:"",
      }
    })

    const EmployeeInfo = (empid)=>{
      axios.get(`/api/employeeInfo/${empid}`).then(({data})=>{
        state.items = data;

        state.form.id = state.items.employee.empid;
        state.form.name = state.items.employee.empname;
        state.form.phone = state.items.employee.empphone;
        state.form.postal = state.items.employee.emppostal;
        state.form.address = state.items.employee.empaddr;
        state.form.detail = state.items.employee.empdetail;
        state.form.dept = state.items.employee.empdept;
        state.form.rule = state.items.employee.emprule;
      })
    }

    const route = useRoute();

    const empid = route.params.empid;

    EmployeeInfo(empid);

    const update = ()=>{
      const args = JSON.parse(JSON.stringify(state.form));
      console.log(args);
      args.items = JSON.stringify(state.form);

      axios.post(`/api/employeeUpdate`, args).then(()=>{
          router.push({path:`/employee`});
          alert("회원정보 수정 완료")
      }).catch(error =>{
        alert("회원정보 수정 실패 >> " + error)
      })
    }

    const removeEmp = (empid)=>{
      const result = window.confirm("정말 삭제하시겠습니까?");

      if(result){
        axios.delete(`/api/employee/delete/${empid}`).then(()=>{
          router.push({path:"/employee"});

          alert("삭제 완료");
        }).catch(()=> {
          alert("삭제 실패");
        })
      }else{
        alert("삭제 취소")
        router.push({path:`/employeeInfo/${empid}`})
      }
    }

    return {state, update, removeEmp};
  }
}
</script>

<style scoped>

.employee-info{
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 40px;
  margin-bottom: 40px;
}

.user-info{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 400px;
  font-size: 20px;
  border-radius: 20px;
  border: 1px solid #98abdf;
  margin-right: 10px;
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
}

#deleteBtn{
  background-color: #cc3300;
  border-radius: 10px;
  border: 1px solid #cc3300;
  padding: 10px 30px;
  color: #FFFFFF;
}

#saveBtn{
  margin-right: 10px;
  background-color: #98abdf;
  border-radius: 10px;
  border: 1px solid #98abdf;
  padding: 10px 30px;
  color: #FFFFFF;
}

#cancelBtn{
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
</style>