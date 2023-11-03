<template>
  <div class="container">
    <h2>직원 등록</h2>

    <div class="EmployeeAddContainer">
      <label>아이디</label>
      <input type="text" v-model="state.forms.id"/>

      <label>이름</label>
      <input type="text" v-model="state.forms.name"/>

      <label>핸드폰</label>
      <input type="text" placeholder="010-0000-0000" v-model="state.forms.phone"/>

      <label>생년월일</label>
      <input type="text" placeholder="1999-01-01" v-model="state.forms.birth"/>

      <label>부서</label>
      <select v-model="state.forms.dept">
        <!-- 부서 관련 테이블에서 리스트가 출력 되도록 하기 -->
        <option value="개발팀">개발팀</option>
        <option value="인사팀">인사팀</option>
        <option value="경영팀">경영팀</option>
        <option value="지원팀">지원팀</option>
        <option value="영업팀">영업팀</option>
      </select>

      <label>직위</label>
      <select v-model="state.forms.rule">
        <!-- 직위 관련 테이블에서 리스트가 출력 되도록 하기 -->
        <option value="인턴">인턴</option>
        <option value="사원">사원</option>
        <option value="대리">대리</option>
        <option value="과장">과장</option>
        <option value="부장">부장</option>
        <option value="팀장">팀장</option>
        <option value="사장">사장</option>
      </select>

      <label>우편 주소</label>
      <input type="text" v-model="state.forms.postal"/>

      <label>주소</label>
      <input type="text" v-model="state.forms.address"/>

      <label>상세 주소</label>
      <input type="text" v-model="state.forms.detail"/>
    </div>
    <div id="btn-container">
      <input type="button" @click="submit()" value="등록"/>
      <input type="button" @click="cancel()" value="취소"/>
    </div>

  </div>
</template>

<script>
import {reactive} from "vue";
import router from "@/script/router";
import axios from "axios";

export default {
  setup(){
    const state = reactive({
      forms:{
        id:"",
        name:"",
        birth:"",
        postal:"",
        address:"",
        detail:"",
        phone:"",
        dept:"",
        rule:""
      }
    })

    const submit=()=>{
      const args = JSON.parse(JSON.stringify(state.forms));
      axios.post('/api/employee/add', args).then(()=>{
        alert("등록 성공")
        router.push({path:"/employee"})
      }).catch(()=>{
        alert("등록 실패");
        router.push({path:"/employee/add"})
      })
    }

    return {state, submit};
  },
  methods:{
    cancel(){
      router.push({path:"/employee"});
    }
  }
}
</script>

<style scoped>
.container{
  display: flex;
  flex-direction: column ;
  justify-content: center;
  align-items: center;
  margin-bottom: 30px;
}

.EmployeeAddContainer{
  display: flex;
  flex-direction: column;
  width: 400px;
  justify-content: center;
}

label{
  margin-top:5px;
  margin-bottom: 5px;
}

#btn-container{
  margin-top:10px
}
input[type=button]{
  margin-right: 10px;
  padding: 5px 10px;
}
</style>