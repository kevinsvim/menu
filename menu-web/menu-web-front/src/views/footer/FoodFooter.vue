<template>
  <footer class="footer">
    <div class="container">
      <div class="row">
        <div class="footer-col">
          <h4>company</h4>
          <ul>
            <li><a href="#">about us</a></li>
            <li><a href="#">our services</a></li>
            <li><a href="#">privacy policy</a></li>
            <li><a href="#">affiliate program</a></li>
          </ul>
        </div>
        <div class="footer-col">
          <h4>get help</h4>
          <ul>
            <li><a href="#">FAQ</a></li>
            <li><a href="#">shipping</a></li>
            <li><a href="#">returns</a></li>
            <li><a href="#">payment options</a></li>
          </ul>
        </div>
        <div class="footer-col">
          <h4>online shop</h4>
          <ul>
            <li><a href="#">watch</a></li>
            <li><a href="#">bag</a></li>
            <li><a href="#">shoes</a></li>
            <li><a href="#">dress</a></li>
          </ul>
        </div>
        <div class="footer-col">
          <h4>follow us</h4>
          <div class="social-links">
            <a href="#">
              <iconfont-svg size="40" icon="icon-google"/>
            </a>
            <a href="#">
              <iconfont-svg size="40" icon="icon-github"/>
            </a>
            <a href="#">
              <iconfont-svg size="40" icon="icon-weixin"/>
            </a>
            <a href="#">
              <iconfont-svg size="40" icon="icon-dingding"/>
            </a>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
import IconfontSvg from "@/components/iconfonts/IconSvg";
export default {
  components: {IconfontSvg},
  data: () =>({
    icons:[
      'mdi-facebook',
      'mdi-twitter',
      'mdi-linkedin',
      'mdi-instagram'
    ]
  })
}
</script>

<style>
@import url(@/assets/css/style.css);
</style>
