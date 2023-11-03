<template>
  <div class="container">
    <h2>직원 등록</h2>

    <div class="EmployeeAddContainer">
      <label>아이디</label>
      <input type="text" v-model="state.forms.id"/>

      <label>비밀번호</label>
      <input type="password" v-model="state.forms.password"/>

      <label>이름</label>
      <input type="text" v-model="state.forms.name"/>

      <label>핸드폰</label>
      <input type="text" placeholder="010-0000-0000" v-model="state.forms.phone"/>

      <label>생년월일</label>
      <input type="text" placeholder="1999-01-01" v-model="state.forms.birth"/>

      <label>부서</label>
      <deptSelect v-model="state.forms.dept"/>

      <label>직위</label>
      <ruleSelect v-model="state.forms.rule"/>

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
import deptSelect from "@/components/deptSelect.vue";
import ruleSelect from "@/components/ruleSelect.vue"

export default {
  components:{
    deptSelect,
    ruleSelect
  },
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
        rule:"",
        password:""
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