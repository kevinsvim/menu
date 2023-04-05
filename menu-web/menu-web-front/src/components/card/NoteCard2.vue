<template>
  <div class="container">
    <div class="card">
      <div class="imgBx">
        <el-image style="width: 180px; height: 180px; border-radius: 10px" :src="data.imageUrl" :fit="'fill'" />
      </div>
      <div class="contentBx">
        <h2>{{ data.title }}</h2>
        <p style="overflow:hidden; text-overflow:ellipsis; height: 40px;display:block;word-break:keep-all;white-space:nowrap;"><span style="font-size: 15px; color: black">{{ data.intro }}</span></p>
        <a href="#"><span>了解更多</span></a>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";

export default {
  name: "NoteCard2",
  props: ['title', 'intro', 'id', 'image_url'],
  setup(props) {
    const data = reactive({
      title: props.title,
      intro: props.intro,
      id: props.id,
      imageUrl: props.image_url
    })
    return {
      data
    }
  }
}
</script>

<style scoped>
* {
  font-family: '微软雅黑', sans-serif;
  box-sizing: border-box;
}


.container .card {
  width: 250px;
  height: 380px;
  margin-top: 20px;
  padding: 20px 20px;
  background: #ebf5fc;
  border-radius: 40px;
  box-shadow: -6px -6px 20px rgba(255, 255, 255, 1),
  6px 6px 20px rgba(0, 0, 0, .1);
}

.container .card:hover {
  box-shadow: inset -6px -6px 10px rgba(255, 255, 255, 0.5),
  inset 6px 6px 20px rgba(0, 0, 0, 0.05);
}
.container .card .contentBx {
  position: relative;
  text-align: center;
}

.container .card .contentBx h2 {
  color: #32a3b1;
  font-weight: 700;
  font-size: 1.4em;
  letter-spacing: 2px;
}

.container .card .contentBx p {
  color: #32a3b1;
}

.container .card .contentBx a {
  display: inline-block;
  padding: 10px 10px;
  border-radius: 40px;
  color: #32a3b1;
  font-size: 16px;
  text-decoration: none;
  box-shadow: -4px -4px 15px rgba(255, 255, 255, 1),
  4px 4px 15px rgba(0, 0, 0, .1);
}

.container .card .contentBx a:hover {
  box-shadow: inset -4px -4px 10px rgba(255, 255, 255, 0.5),
  inset 4px 4px 10px rgba(0, 0, 0, .1);
}

.container .card .contentBx a:hover span {
  display: block;
  transform: scale(0.98);
}

.container .card:hover .imgBx,
.container .card:hover .contentBx {
  transform: scale(0.98);
}

</style>
