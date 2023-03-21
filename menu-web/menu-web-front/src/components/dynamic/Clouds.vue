<template>
  <div class="box">
    <div ref="vantaRef" style="width: 100%; height: 300px"></div>
    <div class="banner">
      <h1>抽奖活动</h1>
      <h6>
        &ensp;&ensp;3.28&ensp;&ensp;&ensp;4.15&ensp;&ensp;&ensp;&ensp;5.21&ensp;&ensp;&ensp;&ensp;&ensp;6.19
      </h6>
      <p>活动时间：待定</p>
      <p>组织单位：待定</p>
    </div>
  </div>
</template>

<script>
import * as THREE from "three";
import CLOUDS from "vanta/src/vanta.clouds";

export default {
  data() {
    return {};
  },
  mounted() {
    this.vantaEffect = CLOUDS({
      el: this.$refs.vantaRef,
      THREE: THREE,
    });
    VANTA.CLOUDS({
      el: this.$refs.vantaRef,
      /*以下为样式配置*/
      mouseControls: true,
      touchControls: true,
      gyroControls: false,
      minHeight: 300.0,
      minWidth: 200.0,
      skyColor: 0x26b9f2,
      cloudColor: 0xb7c9e8,
      cloudShadowColor: 0x2f3a48,
      sunColor: 0x5c452f,
      sunGlareColor: 0xd4a798,
      sunlightColor: 0xdc9c59,
      speed: 1.2,
    });
  },
  beforeDestroy() {
    if (this.vantaEffect) {
      this.vantaEffect.destroy();
    }
  },
};
</script>

<style lang="less" scoped>
.box {
  position: relative;
  .banner {
    z-index: 999;
    position: absolute;
    top: 30%;
    left: 10%;
    color: #fff;
  }
}
h1 {
  font-size: 66px;
}
p {
  margin-top: 60px;
  font-size: 18px;
}
</style>
