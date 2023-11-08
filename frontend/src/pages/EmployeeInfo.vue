<template>
  <h1>{{state.form.name}}님의 정보</h1>
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
              <deptSelect id="dept" v-model="state.form.dept" :value="state.form.dept"/>
            </td>
          </tr>
          <tr>
            <td>직책</td>
            <td>
              <ruleSelect id="rule" v-model="state.form.rule" :value="state.form.rule"/>
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

    <div class="user-detail" v-if="state.items">
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
          <input type="text" :value='state.items.leaveManagement.lmuse' disabled/>
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