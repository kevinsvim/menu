<template>
  <div class="container">
    <div class="card" style="--clr: #ff3e7f;">
      <div class="imgBox">
        <img src="https://cp1.douguo.com/upload/note/6/d/4/750_6d02f05af99f8c7fe0fb9ec7df7b5864.jpg">
      </div>
      <div class="content">
        <!-- 头像加用户名 -->
        <div style="display: flex; align-items: center">
          <el-avatar :size="40" :src="'https://cp1.douguo.com/upload/note/d/4/2/750_d43fed7ae63f206f6c73bebe35570292.jpg'"></el-avatar>
          <span style="font-weight: bold; font-size: 19px; margin-left: 8px">一页纸工行</span>
        </div>
        <div style="margin-top: 10px">
          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa eius molestias possimus ullam facere fugiateligendi adipisci consequuntur quos expedita.</p>
        </div>
        <a href="#">了解更多</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "NoteCard2"
}
</script>

<style scoped lang="less">
@import url(@/assets/css/google-front.css);

*
{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.container
{
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap:wrap;
  gap: 100px 50px;
  padding: 20px 50px;
}

.container .card
{
  position: relative;
  width: 270px;
  height: 300px;
  background-color: #fff;
  border-radius: 20px;
  box-shadow: 0 35px 80px rgba(0, 0, 0, .15);
  transition: 0.5s;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.container .card:hover{
  height: 400px;
}

.container .card .imgBox
{
  position: absolute;
  top: 20px;
  width: 250px;
  height: 220px;
  border-radius: 12px;
  transition: 0.5s;
  overflow: hidden;
}

.container .card:hover .imgBox
{
  top: -100px;
  scale: 0.75;
  box-shadow: 0 15px 45px rgba(0, 0, 0, .2);
}

.container .card .imgBox img
{
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.container .card .content
{
  position: absolute;
  top: 252px;
  width: 100%;
  padding: 0 30px;
  height: 35px;
  overflow: hidden;
  text-align: center;
  transition: 0.5s;
}

.container .card:hover .content
{
  top:130px;
  height: 250px;
}

.container .card .content h2
{
  font-size: 1.5em;
  font-weight: 700;
  color: var(--clr);
}

.container .card .content p
{
  color: #333;
}

.container .card .content a
{
  position: relative;
  top:15px;
  display: inline-block;
  padding: 12px 25px;
  background-color: var(--clr);
  color: #fff;
  text-decoration: none;
  font-weight: 500;
  border-radius: 8px;
}
</style>
