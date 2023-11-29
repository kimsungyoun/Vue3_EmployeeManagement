<template>
<div id="container">
  <div class="container">
    <h2 v-if="$store.state.account.position!='a'" >회원가입</h2>
    <h2 v-else>직원등록</h2>
    <div class="EmployeeAddContainer">
      <label>아이디</label>
      <div id="idbox">
        <input id="id" type="text" placeholder="사용하실 아이디를 입력하세요" v-model="state.forms.empid"/>
        <input id="checkBtn" type="button" @click="checkIdDuplication()" value="아이디 중복 체크"/>
      </div> 
      <label>비밀번호</label>
      <input id="password" type="password" placeholder="비밀번호를 입력하세요" v-model="state.forms.password"/>
      <label>이름</label>
      <input id="name" type="text" placeholder="홍길동" v-model="state.forms.empname"/>
      <label>핸드폰</label>
      <input id="phone" type="text" placeholder="010-1234-5678" v-model="state.forms.empphone"/>
      <label>생년월일</label>
      <datePicker v-model="state.forms.empbirth" :typeable="true" :clearable="true" auto-apply/>    
      
      <label>부서</label>
      <deptSelect v-model="state.forms.empdept"/>

      <label>직책</label>
      <ruleSelect v-model="state.forms.emprule"/>

      <label>우편 주소</label> 
      <div class="postal">
        <input type="text" v-model="state.forms.emppostal" id="sample6_postcode" placeholder="우편번호">
        <input id="postalBtn" type="button" @click="showApi()" value="우편번호 찾기">
      </div>
      <label>주소</label>
      <input type="text" v-model="state.forms.empaddr" id="sample6_address" placeholder="주소">
      <label>상세 주소</label>
      <input type="text" v-model="state.forms.empdetail" id="sample6_detailAddress" placeholder="상세 주소">
      <label>참고 항목</label>
      <input type="text" v-model="state.forms.extra" id="sample6_extraAddress" placeholder="참고 항목">
    </div>
    <div id="btn-container">
      <input v-if="$store.state.account.position!='a'" id="submitBtn" type="button" @click="submit()" value="가입"/>
      <input v-else id="submitBtn" type="button" @click="submit()" value="등록"/>
      <input id="cancelBtn" type="button" @click="cancel()" value="취소"/>
    </div>
  </div>
</div> 
</template>

<script setup>
import {reactive, onMounted} from "vue";
import router from "@/script/router";
import axios from "axios";
import deptSelect from "@/components/DeptSelect";
import ruleSelect from "@/components/RuleSelect";
import datePicker from "vue3-datepicker";
import store from "@/script/store";

const state = reactive({
  forms: {
    empid: "",
    password: "",
    empname: "",
    empphone: "",
    empdept: "",
    emprule: "",
    emppostal: "",
    empaddr: "",
    empdetail: "",
    extra:"",
    empbirth:new Date("2000-01-01"),
  },
});

// 아이디 중복 체크
const checkIdDuplication = ()=>{
  axios.get(`/api/checkIdDuplication/${state.forms.empid}`).then((response) => {
    if (response.data == 1) {
      alert("이미 사용중인 아이디입니다.");
    } else if (response.data == 0){
      alert("사용 가능한 아이디입니다.");
    }
  }).catch(() => {
    alert("아이디 중복 체크 실패");
  });
}

const submit = () => {
  const result = confirm("등록하시겠습니까?");
  const args = JSON.parse(JSON.stringify(state.forms));
  if(result){
    axios.post("/api/employee/add", args).then(() => {
      alert("등록 성공");
      if(store.state.account.position !='a'){
        router.push({ path: "/" });
      }else{
        router.push({ path: "/employeeList" });
      }
      
    }).catch(() => {
      alert("등록 실패");
    });
  }
};

const cancel = () => {
  const result = confirm("취소하시겠습니까?");
  if(result){
    if(store.state.account.position !='a'){
        router.push({ path: "/" });
      }else{
        router.push({ path: "/employeeList" });
      }
  }
};

// 검색 API
const loadDaumPostcodeScript = () => {
  const script = document.createElement('script');
  script.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js';
  script.async = true;
  document.body.appendChild(script);
}

// 우편주소 창 띄우기
const showApi = () => {
  new window.daum.Postcode({
    oncomplete: function(data) {
      state.forms.emppostal = data.zonecode;
      state.forms.empaddr = data.roadAddress || data.jibunAddress;
      state.forms.empdetail = '';
      state.forms.extra = '';

      var addr = '';
      var extraAddr = ''; 

      if (data.userSelectedType === 'R') {
        addr = data.roadAddress;
      } else {
        addr = data.jibunAddress;
      }

      if(data.userSelectedType === 'R'){
        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
          extraAddr += data.bname;
        }
        if(data.buildingName !== '' && data.apartment === 'Y'){
          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        if(extraAddr !== ''){
          extraAddr = ' (' + extraAddr + ')';
        }
        document.getElementById("sample6_extraAddress").value = extraAddr;
        } else { 
          document.getElementById("sample6_extraAddress").value = '';
        }

        document.getElementById('sample6_postcode').value = data.zonecode;
        document.getElementById("sample6_address").value = addr;
        document.getElementById("sample6_extraAddress").value = extraAddr;        

        document.getElementById("sample6_detailAddress").focus();
      } 
    }).open();
};

onMounted(() => {
    loadDaumPostcodeScript();
});

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
  border: 1px solid #98abdf;
  padding :40px;
  border-radius: 20px;
}
label{
  margin-top:5px;
  margin-bottom: 5px;
}

select, input[type=text], #password{
  height: 25px;
  width: 100%;
}

#idbox{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

#checkBtn, #postalBtn, #cancelBtn{
  margin-left: 10px;
}

.postal{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

#btn-container{
  margin-top:10px
}
</style>