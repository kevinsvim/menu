<template>
  <div class="demo-image">
    <!-- 图片 -->
    <el-image style="width: 200px; height: 220px; border-radius: 10px" :src="url" fit="fill"/>
    <!-- 主题 -->
    <div>
      <span class="demonstration">{{ title }}</span>
    </div>
    <!-- 作者 -->
    <div>
      <span>author: &nbsp;</span>
      <span>GinnySun</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MenuItem',
  props: {
    title: {
      type: String,
      default: 'hello'
    }
  },
  setup(props) {
    const url =
      'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
    return {
      url,
      props
    }
  }
}
</script>

<style scoped>
.demo-image .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 20%;
  box-sizing: border-box;
  vertical-align: top;
}
.demo-image .block:last-child {
  border-right: none;
}
.demo-image .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 7px;
  margin-top: 5px;
}
</style>
