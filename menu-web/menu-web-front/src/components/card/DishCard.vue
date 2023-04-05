<template>
  <div class="card">
    <div class="card-image">
      <el-image
        style="width: 100%; height: 100%"
        :src="data.imageUrl"
        :fit="'fill'"></el-image>
    </div>
    <div class="card-text">
      <span class="date">{{ data.publishDate }}</span>
      <h3 style="overflow:hidden; text-overflow:ellipsis; display:block;word-break:keep-all;white-space:nowrap;">{{ data.title }}</h3>
      <div style="display: flex; align-items: center; justify-items: flex-start">
        <el-avatar :size="15" :src="imageUrl"/>
        <span style="font-size: 13px; color: #999; margin-left: 5px;">by 师傅说的克己复礼</span>
      </div>
    </div>
    <div>
    </div>
    <div class="card-stats">
      <div class="stat">
        <div class="value">{{ data.collectNum }}<sup>m</sup></div>
        <div class="type">收藏</div>
      </div>
      <div class="stat border">
        <div class="value">{{ data.views }}</div>
        <div class="type">浏览</div>
      </div>
      <div class="stat">
        <div class="value">{{ data.commentNum }}</div>
        <div class="type">评论</div>
      </div>
    </div>
  </div>
</template>

<script>
import {time_ago} from '@/utils/date'
import {reactive} from 'vue'

export default {
  name: "DishCard",
  props: ['title', 'imageUrl', 'username', 'collectNum', 'upNum', 'views', 'commentNum', 'evaluate', 'dishId', 'publishDate'],
  setup(props) {
    const data = reactive({})
    data.title = props.title
    data.imageUrl = props.imageUrl
    data.username = props.username
    data.collectNum = props.collectNum
    data.upNum = props.upNum
    data.views = props.views
    data.commentNum = props.commentNum
    data.evaluate = props.evaluate
    data.dishId = props.dishId

    const changeDateDisplayForm = (dateTimeStamp) => {
      data.publishDate = time_ago(dateTimeStamp)
    }
    changeDateDisplayForm(Date.parse(props.publishDate))

    return {
      data
    }
  }
}
</script>

<style scoped>
.card {
  display: grid;
  grid-template-columns: 240px;
  grid-template-rows: 140px 150px 80px;
  grid-template-areas: "image" "text" "stats";

  border-radius: 18px;
  background: white;
  box-shadow: 5px 5px 15px rgba(0,0,0,0.9);
  font-family: roboto;
  text-align: center;
  /*宽度,要和grid-template-columns保持一致*/
  width: 240px;
  transition: 0.5s ease;
  cursor: pointer;
}
.card-image {
  grid-area: image;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  background-size: cover;
  height: 160px;
}

.card-text {
  grid-area: text;
  margin: 25px;
}
.card-text .date {
  color: rgb(255, 7, 110);
  font-size:13px;
}
.card-text p {
  color: grey;
  font-size:15px;
  font-weight: 300;
}
.card-text h2 {
  margin-top:0px;
  font-size:28px;
}
.card-stats {
  grid-area: stats;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr;
  border-bottom-left-radius: 15px;
  border-bottom-right-radius: 15px;
  background: rgb(255, 7, 110);
}
.card-stats .stat {
  padding:10px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  color: white;
}
.card-stats .border {
  border-left: 1px solid rgb(172, 26, 87);
  border-right: 1px solid rgb(172, 26, 87);
}
.card-stats .value{
  font-size:22px;
  font-weight: 500;
}
.card-stats .value sup{
  font-size:12px;
}
.card-stats .type{
  font-size:11px;
  font-weight: 300;
  text-transform: uppercase;
}
.card:hover {
  transform: scale(1.15);
  box-shadow: 5px 5px 15px rgba(0,0,0,0.6);
}

</style>
