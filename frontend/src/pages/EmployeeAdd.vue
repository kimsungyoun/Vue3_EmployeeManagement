<template>
  <div class="container">
    <h2>직원 등록</h2>

    <div class="EmployeeAddContainer">
      <label>아이디</label>
      <input id="row" type="text" v-model="state.forms.id"/>

      <label>비밀번호</label>
      <input id="row" type="password" v-model="state.forms.password"/>

      <label>이름</label>
      <input id="row" type="text" v-model="state.forms.name"/>

      <label>핸드폰</label>
      <input id="row" type="text" placeholder="010-0000-0000" v-model="state.forms.phone"/>

      <label>생년월일</label>
      <input id="row" type="text" placeholder="1999-01-01" v-model="state.forms.birth"/>

      <label>부서</label>
      <deptSelect v-model="state.forms.dept"/>

      <label>직위</label>
      <ruleSelect v-model="state.forms.rule"/>

      <label>우편 주소</label> 
      <div id="postal">
        <input type="text" v-model="state.forms.postal" id="sample6_postcode" placeholder="우편번호">
        <input type="button" value="우편번호 찾기">
      </div>

      <label>주소</label>
      <input type="text" v-model="state.forms.address" id="sample6_address" placeholder="주소">

      <label>상세 주소</label>
      <input type="text" v-model="state.forms.detail" id="sample6_detailAddress" placeholder="상세주소">
      <!-- <input type="text" id="sample6_extraAddress" placeholder="참고항목"> -->

      <!-- 
      <label>우편 주소</label>
      <input type="text" v-model="state.forms.postal"/>

      <label>주소</label>
      <input type="text" v-model="state.forms.address"/>

      <label>상세 주소</label>
      <input type="text" v-model="state.forms.detail"/> 
      -->

    </div>
    <div id="btn-container">
      <input type="button" @click="submit()" value="등록"/>
      <input type="button" @click="cancel()" value="취소"/>
    </div>
  </div>
</template>


<script setup>
import {reactive} from "vue";
import router from "@/script/router";
import axios from "axios";
import deptSelect from "@/components/deptSelect.vue";
import ruleSelect from "@/components/ruleSelect.vue";

const state = reactive({
  forms: {
    id: "",
    name: "",
    birth: "",
    postal: "",
    address: "",
    detail: "",
    phone: "",
    dept: "",
    rule: "",
    password: "",
  },
});

const submit = () => {
  const args = JSON.parse(JSON.stringify(state.forms));
  axios
    .post("/api/employee/add", args)
    .then(() => {
      alert("등록 성공");
      router.push({ path: "/employee" });
    })
    .catch(() => {
      alert("등록 실패");
      router.push({ path: "/employee/add" });
    });
};

const cancel = () => {
  router.push({ path: "/employee" });
};

</script> 


<!-- <script>
import {reactive} from "vue";
import axios from "axios";
import lib from "@/script/lib";
import DeptSelect from "@/components/deptSelect.vue";
import RuleSelect from "@/components/ruleSelect.vue";

export default {
  components: {RuleSelect, DeptSelect},
  computed: {
    lib() {
      return lib
    }
  },data() {
    return {
      empid: null,
      state: reactive({
        items: [],
        form: {
          id: "",
          name: "",
          phone: "",
          postal: "",
          address: "",
          detail: "",
          dept: "",
          rule: "",
        }
      }),
    };
  },
  mounted() {
    this.empid = this.$route.params.empid;
    this.employeeInfo(this.empid);
  },
  methods: {
    employeeInfo(empid) {
      axios.get(`/api/employeeInfo/${empid}`).then(({ data }) => {
        this.state.items = data;
        console.log(this.state.items);
        const employee = this.state.items.employee;
        this.state.form.id = employee.empid;
        this.state.form.name = employee.empname;
        this.state.form.phone = employee.empphone;
        this.state.form.postal = employee.emppostal;
        this.state.form.address = employee.empaddr;
        this.state.form.detail = employee.empdetail;
        this.state.form.dept = employee.empdept;
        this.state.form.rule = employee.emprule;
      });
    },
    update() {
      const args = JSON.parse(JSON.stringify(this.state.form));
      console.log(args);
      args.items = JSON.stringify(this.state.form);

      axios.post(`/api/employeeUpdate`, args).then(() => {
        this.$router.push({ path: `/employee` });
        alert("회원정보 수정 완료");
      }).catch(error => {
        alert("회원정보 수정 실패 >> " + error);
      });
    },
    removeEmp(empid) {
      const result = window.confirm("정말 삭제하시겠습니까?");

      if (result) {
        axios.delete(`/api/employee/delete/${empid}`).then(() => {
          this.$router.push({ path: "/employee" });
          alert("삭제 완료");
        }).catch(() => {
          alert("삭제 실패");
        });
      } else {
        alert("삭제 취소");
        this.$router.push({ path: `/employeeInfo/${empid}` });
      }
    },
  }
}
</script> -->


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

#postal{
  display: flex;
  flex-direction: row;
  width: 100%;
}

#sample6_postcode{
  margin-right:10px;
}

select:nth-child(12), select:nth-child(14){
  height: 25px;
}
#row{
  height: 25px;
}
#sample6_address{
  height: 25px;
}
#sample6_detailAddress{
  height: 25px;
}
</style>