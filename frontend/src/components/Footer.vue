<template>
  <footer>
    <div class="footer-container">
      <div class="Info">
        <ul>
          <li><a href="http://www.bnosoft.co.kr/sub01.html">회사소개</a></li>
          <li>|</li>
          <li><a href="http://www.bnosoft.co.kr/sub05.html">회사위치</a></li>
          <li>|</li>
          <li><a href="/">개인정보보호정책</a></li>
          <li>|</li>
          <li><a href="/">이용약관</a></li>
        </ul>
      </div>
      <div class="CorpInfo">
        (06999)  서울시 동작구 동작대로 (사당, 사당우성아파트)
        <br>
        Tel : 010-5238-8636 
        <br>        
        KIMSUNGYOUN © ALL RIGHT RESERVED.
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