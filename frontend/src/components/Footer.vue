<template>
  <footer>
      <div>
        <ul>
          <li><a href="/">회사소개</a></li>
          <li>|</li>
          <li><a href="/">가이드</a></li>
          <li>|</li>
          <li><a href="/">개인정보보호정책</a></li>
          <li>|</li>
          <li><a href="/">이용약관</a></li>
        </ul>
      </div>
  </footer>
</template>

<script setup>

</script>

<style scoped>
footer{
  background-color: #98abdf;
  padding: 15px;
  color: #FFFFFF;
}
ul li a {
  cursor: pointer;
  color: #fff;
  text-decoration: none;
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