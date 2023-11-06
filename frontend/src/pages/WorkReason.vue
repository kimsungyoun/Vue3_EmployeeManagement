<template>
    <div class="Reason-container">
        <h1>2023년 10월 24일</h1>
        <div class="container">
            <label>상태</label>
            <input type="text" id="reason-" value="외근" disabled>
            <br>
            <label>사유 작성</label>
            <textarea></textarea>
        </div>
        <br>
        <input type="button" value="제출"/>
    </div>
</template>

<script setup>

</script>

<style scoped>
.Reason-container{
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20px;
}
.container{
    display: flex;
    flex-direction: column;
    justify-content: center;
    text-align: center;
    width: 60%;
}

label{
    text-align: left;
    font-size: 20px;
}

</style>