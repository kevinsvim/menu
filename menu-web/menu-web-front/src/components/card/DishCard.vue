<template>
  <section class="slider">
    <div class="card">
      <div class="card-content">
        <el-image style="width: 270px; height: 250px" :src="imageUrl" :fit="'fill'" />
        <h1 class="card-title">
          <span>
            {{ title }}
          </span>
        </h1>
        <div class="card-body">
          <div class="card-star">
            <span class="rating-value">4.5</span>
            <span class="star">&#9733;</span>
          </div>
          <p class="card-price">
            <span>by {{ username }}</span>
          </p>
        </div>
        <div class="card-footer">
          <button class="btn btn-success">Make Now</button>
          <button class="btn btn-border">Details</button>
        </div>
      </div>
    </div>
  </section>
</template>

<script>

export default {
  name: "DishCard",
  props: ['imageUrl', 'title', 'username'],
  setup(props, ctx) {
    const imageUrl = props.imageUrl
    const title = props.title
    const username = props.username
    const currentDate = new Date().toDateString()
    return {
      title,
      imageUrl,
      currentDate,
      username
    }
  }
}
</script>

<!--<style>-->
<!--:root {-->
<!--  &#45;&#45;primary: #18253c;-->
<!--  /* &#45;&#45;primary: #fff; */-->
<!--  &#45;&#45;secondary: #00dd53;-->
<!--  &#45;&#45;bg: #0e1523;-->
<!--  /* &#45;&#45;bg: #f5f5f5; */-->
<!--}-->

<!--* {-->
<!--  margin: 0;-->
<!--  padding: 0;-->
<!--  box-sizing: border-box;-->
<!--}-->

<!--.slider {-->
<!--  /*background-color: var(&#45;&#45;bg);*/-->
<!--  padding: 1rem;-->

<!--  display: grid;-->
<!--  grid-template-columns: repeat(4, 1fr);-->
<!--  place-items: center;-->
<!--  grid-gap: 2rem;-->

<!--  font: normal 1rem sans-serif;-->
<!--}-->

<!--.card {-->
<!--  position: relative;-->
<!--  width: 17rem;-->
<!--  height: 23rem;-->
<!--  background-color:var(&#45;&#45;primary);-->
<!--  color: #fff;-->
<!--  border-radius: 1rem;-->
<!--  border: .1rem solid transparent;-->
<!--  overflow: hidden;-->
<!--  box-shadow: 0 .2rem .6rem #0003;-->
<!--  transition: .5s ease-in-out;-->
<!--}-->

<!--.card:hover {-->
<!--  border: .1rem solid var(&#45;&#45;secondary);-->
<!--  transform: scale(1.05);-->
<!--}-->

<!--.card::before, .card::after {-->
<!--  position: absolute;-->
<!--  content: "";-->
<!--  background-color: var(&#45;&#45;secondary);-->
<!--  border-radius: 50%;-->
<!--  transition: .5s ease-in-out;-->
<!--  z-index: 100;-->
<!--}-->

<!--.card::before {-->
<!--  top: -6%;-->
<!--  right: -6%;-->
<!--  width: 9rem;-->
<!--  height: 9rem;-->
<!--}-->

<!--.card::after {-->
<!--  bottom: -6%;-->
<!--  left: -6%;-->
<!--  width: 5rem;-->
<!--  height: 5rem;-->
<!--}-->

<!--.card:hover::before {-->
<!--  transform: scale(1.8);-->
<!--}-->

<!--.card:hover::after {-->
<!--  transform: scale(0);-->
<!--}-->

<!--.card-content {-->
<!--  position: absolute;-->
<!--  top:0;-->
<!--  left:0;-->
<!--  width: 100%;-->
<!--  height: 100%;-->

<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  justify-content: space-around;-->
<!--  align-items: center;-->
<!--  z-index: 200;-->
<!--}-->

<!--.card-img,-->
<!--.card-title {-->
<!--  transform: translateY(4rem);-->
<!--  transition: .5s ease-in-out;-->
<!--}-->

<!--.card-img {-->
<!--  width: auto;-->
<!--  height: 9.5rem;-->
<!--}-->

<!--.card-title {-->
<!--  margin-top: 10px;-->
<!--  font-size: 1rem;-->
<!--  text-transform: capitalize;-->
<!--  transition-delay: .1s;-->
<!--}-->

<!--.card-body,-->
<!--.card-footer {-->
<!--  transform: translateY(8rem);-->
<!--  transition: .5s ease-in-out;-->
<!--}-->

<!--.card-body {-->
<!--  width: 100%;-->

<!--  display: flex;-->
<!--  justify-content: space-around;-->
<!--  align-items: center;-->

<!--  transition-delay: .2s;-->
<!--}-->

<!--.card:hover .card-img,-->
<!--.card:hover .card-title,-->
<!--.card:hover .card-body,-->
<!--.card:hover .card-footer {-->
<!--  transform: translateY(0);-->
<!--}-->

<!--.card-footer {-->
<!--  transition-delay: .3s;-->
<!--}-->

<!--.star {-->
<!--  font-size: 1.4rem;-->
<!--  color: gold;-->
<!--}-->

<!--.card-price {-->
<!--  font: bolder 0.7rem sans-serif;-->
<!--}-->

<!--.btn {-->
<!--  padding: 1rem 2rem;-->
<!--  background-color: unset;-->
<!--  border: none;-->
<!--  outline: none;-->
<!--  border-radius: 2rem;-->
<!--  color: #fff;-->
<!--  cursor: pointer;-->
<!--}-->

<!--.btn-success {-->
<!--  background-color: var(&#45;&#45;secondary);-->
<!--}-->

<!--.btn-border {-->
<!--  border: .1rem solid var(&#45;&#45;secondary);-->
<!--}-->

<!--</style>-->
