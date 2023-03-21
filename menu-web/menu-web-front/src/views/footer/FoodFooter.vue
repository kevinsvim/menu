<template>
  <div id="footer">
    <div class="dginfo">
      <div class="dgintro">
        <div class="logo2">
          <img class="wb100" src="https://cp1.douguo.com//static/nweb/images/logo3.png?20191218" alt="豆果美食logo">
        </div>
        <div class="datainfo">
          <p><span>500万+</span>美食菜谱；<span>2000万+</span>互动内容；<span>3000+</span>美食课堂；<span>5000万+</span>用户每天都有新分享</p>
        </div>
        <div class="threbl">
          <h3>扫二维码，下载美食吃坊手机应用：</h3>
          <ul>
            <li>
              <a href="javascript:void(0);" class="emwx">
                <img width="92" height="92" src="https://cp1.douguo.com//static/nweb/images/xcx.jpg?2001" alt="豆果美食">
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'MenuFooter'
}
</script>

<style scoped>
#footer {
  width: 100%;
  margin-top: 30px;
  background: #f8f8f8;
}
.dginfo {
  background: #f2f2f2;
}
.dgintro {
  width: 1000px;
  position: relative;
  height: 188px;
  margin: 0 auto;
  padding-top: 94px;
}
.dgintro .logo2 {
  width: 147px;
  left: 0;
  top: 35px;
  position: absolute;
}
.dgintro .threbl {
  width: 312px;
  position: absolute;
  top: 16px;
  left: 690px;
}
.emwx, .emdgms {
  width: 92px;
  height: 92px;
  display: block;
  margin-bottom: 8px;
}
</style>
