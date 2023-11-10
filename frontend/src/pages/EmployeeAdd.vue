<template>
<div id="container">
  <div class="container">
    <h2>직원 등록</h2>

    <div class="EmployeeAddContainer">
      <label>아이디</label>
      <div id="idbox">
        <input id="id" type="text" v-model="state.forms.id"/>
        <input id="checkBtn" type="button" @click="checkIdDuplication()" value="아이디 중복 체크"/>
      </div>
      
      <label>비밀번호</label>
      <input id="password" type="password" v-model="state.forms.password"/>

      <label>이름</label>
      <input id="name" type="text" v-model="state.forms.name"/>

      <label>핸드폰</label>
      <input id="phone" type="text" placeholder="010-0000-0000" v-model="state.forms.phone"/>

      <label>생년월일</label>
      <input id="birth" type="text" placeholder="1999-01-01" v-model="state.forms.birth"/>

      <label>부서</label>
      <deptSelect v-model="state.forms.dept"/>

      <label>직책</label>
      <ruleSelect v-model="state.forms.rule"/>

      <label>우편 주소</label> 
      <div class="postal">
        <input type="text" v-model="state.forms.postal" id="sample6_postcode" placeholder="우편번호">
        <input id="postalBtn" type="button" @click="showApi()" value="우편번호 찾기">
      </div>

      <label>주소</label>
      <input type="text" v-model="state.forms.address" id="sample6_address" placeholder="주소">

      <label>상세 주소</label>
      <input type="text" v-model="state.forms.detail" id="sample6_detailAddress" placeholder="상세주소">

      <label>참고 항목</label>
      <input type="text" v-model="state.forms.extra" id="sample6_extraAddress" placeholder="참고항목">
    </div>
    <div id="btn-container">
      <input id="submitBtn" type="button" @click="submit()" value="등록"/>
      <input id="cancelBtn" type="button" @click="cancel()" value="취소"/>
    </div>
  </div>
</div> 
</template>

<script setup>
import {reactive, onMounted} from "vue";
import router from "@/script/router";
import axios from "axios";
import deptSelect from "@/components/deptSelect.vue";
import ruleSelect from "@/components/ruleSelect.vue";

const state = reactive({
  forms: {
    id: "",
    password: "",
    name: "",
    birth: "",
    phone: "",
    dept: "",
    rule: "",
    postal: "",
    address: "",
    detail: "",
    extra:"",
  },
});
// 아이디 중복 체크
const checkIdDuplication =()=>{
  axios.get("/api/checkIdDuplication/" + state.forms.id).then((response) => {
      if (response.data==1) {
        alert("아이디 중복입니다.");
      } else if (response.data == 0){
        alert("아이디 사용 가능합니다.");
      }
    })
    .catch(() => {
      alert("아이디 중복 체크 실패");
    });
}

// 직원 등록
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

// 직원 등록 취소
const cancel = () => {
  router.push({ path: "/employee" });
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
      state.forms.postal = data.zonecode;
      state.forms.address = data.roadAddress || data.jibunAddress;
      state.forms.detail = '';
      state.forms.extra = '';

      var addr = ''; // 주소 변수
      var extraAddr = ''; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
        addr = data.roadAddress;
      } else { // 사용자가 지번 주소를 선택했을 경우(J)
        addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if(data.userSelectedType === 'R'){
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if(data.buildingName !== '' && data.apartment === 'Y'){
          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if(extraAddr !== ''){
          extraAddr = ' (' + extraAddr + ')';
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.getElementById("sample6_extraAddress").value = extraAddr;
        } else { 
          document.getElementById("sample6_extraAddress").value = '';
        }

        // 우편번호와 주소 정보를 해당 필드에 넣는다.
        document.getElementById('sample6_postcode').value = data.zonecode;
        document.getElementById("sample6_address").value = addr;
        document.getElementById("sample6_extraAddress").value = extraAddr;        
        // 커서를 상세주소 필드로 이동한다.
        document.getElementById("sample6_detailAddress").focus();
      } 
    }).open();
};

onMounted(() => {
    loadDaumPostcodeScript();
  });

</script> 

<style scoped>
/* 공통 */
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
input[type=button]{
  padding: 5px 10px;
}
/* 크기 고정 */
select, input[type="text"], #password{
  height: 25px;
  width: 100%;
}

/* 아이디 입력 칸 */
#idbox{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

#checkBtn, #postalBtn, #cancelBtn{
  margin-left: 10px;
}

/* 우편주소 */
.postal{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

/* 하단 버튼 */
#btn-container{
  margin-top:10px
}
</style>