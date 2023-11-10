<template>
  <footer>
    <div class="footer-container">
      <div class="Info">
        <ul>
          <li><a href="http://www.bnosoft.co.kr/sub01.html">회사소개</a></li>
          <li>|</li>
          <li><a href="http://www.bnosoft.co.kr/sub03.html">회사실적</a></li>
          <li>|</li>
          <li><a href="http://www.bnosoft.co.kr/sub05.html">회사위치</a></li>
          <li>|</li>
          <li><a href="/">개인정보보호정책</a></li>
          <li>|</li>
          <li><a href="/">이용약관</a></li>
        </ul>
      </div>
      <div class="CorpInfo">
        (08382)  서울시 구로구 디지털로 243, 1005호(구로동, 지하이시티)
        <br>
        Tel : 02-2224-7303 ㅣ Fax : 02-2224-7304 | 사업자등록번호 : 208-87-00610 
        <br>        
        COPYRIGHT©2018 ALL RIGHT RESERVED.,LTD
      </div>
    </div>
      
  </footer>
</template>

<script setup>

</script>

<style scoped>
footer{
  position: relative;
  transform: translateY(0%);
  /* background-color: #98abdf;
  padding: 15px;
  color: #FFFFFF; */
}
.footer-container{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: #8293c4;
  padding: 15px 40px;
  color: #FFFFFF;
}
ul li a {
  cursor: pointer;
  color: #fff;
  text-decoration: none;
}
.Info{
  display: flex;
  flex-direction: row;
  align-items: center;
}

ul , li{
  display: flex;
  direction: ltr;
  padding : 0;
  color: #FFF;
  margin-right: 15px;
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>