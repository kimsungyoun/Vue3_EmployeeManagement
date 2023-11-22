<template>
<div class="container">
    <div class="notice">
        <h1>최신공지</h1>
        <div class="noticeList">
            <table>
                <thead>
                    <tr>
                        <td>번호</td>
                        <td>제목</td>
                        <td>작성일</td>
                        <td>조회수</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>5</td>
                        <td>직장내 장애인 인식개선 교육</td>
                        <td>2021년 04월 15일</td>
                        <td>21</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>직장 내 괴롭힘 상담 및 교육 안내</td>
                        <td>2021년 03월 05일</td>
                        <td>27</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>직장 내 성희롱 예방 교육 안내</td>
                        <td>2021년 03월 12일</td>
                        <td>13</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>근로 기준법 공지 및 안내</td>
                        <td>2021년 03월 05일</td>
                        <td>13</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>공지 게시판 유의사항 안내</td>
                        <td>2021년 02월 15일</td>
                        <td>15</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div>
            <ul>
                <li>1</li>
                <li>2</li>
                <li>3</li>
            </ul>
        </div>
    </div>
</div>
</template>

<script setup>

</script>

<style scoped>
.container{
    padding: 20px;
    
}
.notice{
    border: 1px solid #98abdf;
    border-radius: 20px;
}
h1{
    text-align: center;
    margin: 10px 0 0 0;
}
.noticeList{
    padding : 20px;
}
table{
    border-spacing: 0;
    text-align: center;
    width: 100%;
}

tr, td {
    border: 1px solid #98abdf;
}
ul{
    display: flex;
    flex-direction: row;
    justify-content: center;
    padding: 0;
}
li{
    margin-right: 10px;
}
</style>