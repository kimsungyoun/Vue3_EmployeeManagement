<template>
    <div>
        <table>
        <tr>
            <td>직원명</td>
            <td>상태</td>
            <td>출근시간</td>
            <td>퇴근시간</td>
            <td>근무시간</td>
        </tr>
        <tr v-for="(i, idx) in items" :key="idx">
            <td>{{i[0].empname}}</td>
            <td v-if="i[1].workstatus == '출근'">
                {{i[1].workstatus}}
            </td>
            <td v-else>
            <a id="reason" @click="reason(i[1].workno)">
                {{i[1].workstatus}}
            </a>
            </td>
            <td>{{i[1].workon}}</td>
            <td>{{i[1].workoff}}</td>
            <td>{{i[1].worktime}}</td>
        </tr>
        </table>
    </div>
</template>

<script setup>

</script>

<style>
table{
  border-spacing: 0;
  width :100%;
}
tr td{
  text-align: center;
  border: 1px solid #98abdf;
}

#reason{
    background-color: #98abdf;
    border: 1px solid #98abdf;
    border-radius: 5px;
    color: #ffffff;
}

#reason:hover{
    cursor: pointer;
}
</style>