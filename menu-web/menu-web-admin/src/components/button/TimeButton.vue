<template>
    <a style="padding: 15px">
      <span>
        {{ title }}
      </span>
    </a>

</template>

<script>
import { ref, reactive } from 'vue'
export default {
  name: "TimeButton",
  props:
    {
      title: {
        type: String,
        required: false,
        default: '流光'
      },
      borderStyle: {
        type: Object,
        required: false,
        default: {
          width: '400px',
          height: '90px',
          borderRadius: '50px'
        }
      }
    },
  setup(props) {
    // 标题
    const title = ref(props.title)
    const borderStyle = reactive(props.borderStyle)
    return {
      title,
      borderStyle
    }
  }
}
</script>

<style scoped lang="scss">
* {
  margin: 0;
  padding: 0;
}

body {
  background-color: black;
}

a {
  cursor: pointer;
  text-decoration: none;
  position: relative;
  /* 水平垂直居中 */
  transform: translate(-50%,-50%);
  font-size: 10px;
  /* 角度渐变 */
  background: linear-gradient(90deg,blue,rgb(4, 171, 255),purple,rgb(223, 9, 235),rgb(163, 97, 237));
  background-size: 400%;
  width: 200px;
  height: 90px;
  line-height: 80px;
  text-align: center;
  color: white;
  /* 大写字母转换 */
  text-transform: uppercase;
  border-radius: 50px;
  z-index: 1;
}

a:hover {
  background-color: white;
  animation: light 5s infinite;
}

@keyframes light {
  100% {
    background-position: -400% 0;
  }
}
a::before {
  content: "";
  position: absolute;
  left: -5px;
  top: -5px;
  right: -5px;
  bottom: -5px;
  /* border: 1px solid white; */
  background: linear-gradient(90deg,blue,rgb(4, 171, 255),purple,rgb(223, 9, 235),rgb(163, 97, 237));
  background-size: 400%;
  //border-radius: 50px;
  /* 背景模糊 */
  filter: blur(20px);
  z-index: -1;
}

/* 伪元素设置动画 */
a:hover::before {
  animation: light 5s infinite;
}
</style>
