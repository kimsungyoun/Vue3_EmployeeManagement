<template>
  <footer>
    <div class="footer-container">
      <div class="Info">
        <ul>
          <li>|</li>
          <li><a href="http://www.bnosoft.co.kr/sub01.html">About</a></li>
          <li>|</li>
          <li><a href="http://www.bnosoft.co.kr/sub05.html">Location</a></li>
          <li>|</li>
          <li><a href="/">Privacy Policy</a></li>
          <li>|</li>
          <li><a href="/">Terms of Use</a></li>
          <li>|</li>
        </ul>
      </div>
      <div class="CorpInfo">
        <!-- 
        <ul>
          <li>(08382) 서울시 구로구 디지털로 243, 1005호(구로동, 지하이시티)</li>
          <li>Tel : 02-2224-7303 <span>|</span> Fax : 02-2224-7304 </li>
          <li>사업자등록번호 : 208-87-00610</li>
          <li>COPYRIGHT©2018 ALL RIGHT RESERVED.,LTD</li>
        </ul> 
        -->
        <ul>
          <li>(06999) 서울시 동작구 동작대로 29길 91</li>
          <li>Tel : 010-5238-8636 <span>|</span> Fax : 02-2224-7101</li>
          <li>KIMSUNGYOUN©2023 ALL RIGHT RESERVED.</li>
        </ul>
      </div>
    </div>
  </footer>
</template>

<script setup>

</script>

<style scoped>
.footer-container{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: #98abdf;
  padding: 5px 30px;
  color: #FFFFFF;
}
ul li a {
  cursor: pointer;
  color: #fff;
  text-decoration: none;
}

/* info */
.Info{
  display: flex;
  flex-direction: row;
  align-items: center;
}
.Info > ul{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  padding:0;
}
.Info li{
  margin-right: 10px;
}
/* corpInfo */
.CorpInfo{
  display: flex;
  flex-direction: row;
  align-items: center;
}
.CorpInfo > ul{
  display: flex;
  flex-direction: column;
  padding:0;
  margin-left: 10px;
}

</style>