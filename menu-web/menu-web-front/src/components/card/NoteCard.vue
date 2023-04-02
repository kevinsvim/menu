<template>
  <div class="product-card">
    <div class="logo-cart">
      <img src="@/assets/images/logo.jpg" alt="logo">
      <i class='bx bx-shopping-bag'></i>
    </div>
    <div class="main-images">
      <img id="blue" class="blue active" src="https://cp1.douguo.com/upload/caiku/1/0/1/yuan_10019c27eca6084eb07b3bcecfd01691.jpg" alt="blue">
      <img id="pink" class="pink" src="@/assets/images/pink.png" alt="blue">
      <img id="yellow" class="yellow" src="@/assets/images/yellow.png" alt="blue">
    </div>
    <div class="shoe-details">
      <span class="shoe_name">ADDIDAS GAZE ZX</span>
      <p>Lorem ipsum dolor sit lorenm i amet, consectetur adipisicing elit. Eum, ea, ducimus!</p>
      <div class="stars">
        <i class='bx bxs-star' ></i>
        <i class='bx bxs-star' ></i>
        <i class='bx bxs-star' ></i>
        <i class='bx bxs-star' ></i>
        <i class='bx bx-star' ></i>
      </div>
    </div>
    <div class="color-price">
      <div @click="handlerColor" class="color-option">
        <span class="color">Colour:</span>
        <div class="circles">
          <span class="circle blue active"  id="blue"></span>
          <span class="circle pink " id="pink"></span>
          <span class="circle yellow " id="yellow"></span>
        </div>
      </div>
      <div class="price">
        <span class="price_num">$09.00</span>
        <span class="price_letter">Nine dollar only</span>
      </div>
    </div>
    <div class="button">
      <div class="button-layer"></div>
      <button>Add To Cart</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "NoteCard",
  setup() {

    const handlerColor = (e) => {
      let target = e.target;
      let circle = document.querySelector(".color-option");
      if(target.classList.contains("circle")){
        circle.querySelector(".active").classList.remove("active");
        target.classList.add("active");
        document.querySelector(".main-images .active").classList.remove("active");
        document.querySelector(`.main-images .${target.id}`).classList.add("active");
      }
    }
    return {
      handlerColor
    }
  }
}
</script>

<style scoped>
/* Google Fonts Poppins */
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap');

*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

body{
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: linear-gradient(135deg, #43CBFF 10%, #9708CC 100%);
}
.product-card {
  position: relative;
  max-width: 355px;
  width: 100%;
  border-radius: 25px;
  padding: 20px 30px 30px 30px;
  background: #fff;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
  z-index: 3;
  overflow: hidden;
}
.product-card .logo-cart{
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.product-card .logo-cart img{
  height: 60px;
  width: 60px;
  object-fit: cover;
}
.product-card .logo-cart i{
  font-size: 27px;
  color: #707070;
  cursor: pointer;
  transition: color 0.3s ease;
}
.product-card .logo-cart i:hover{
  color: #333;
}
.product-card .main-images{
  position: relative;
  height: 210px;
}
.product-card .main-images img{
  position: absolute;
  height: 300px;
  width: 300px;
  object-fit: cover;
  transform: rotate(18deg);
  left: 12px;
  top: -40px;
  z-index: -1;
  opacity: 0;
  transition: opacity 0.5s ease;
}
.product-card .main-images img.active{
  opacity: 1;
}
.product-card .shoe-details .shoe_name{
  font-size: 24px;
  font-weight: 500;
  color: #161616;
}
.product-card .shoe-details p{
  font-size: 12px;
  font-weight: 400;
  color: #333;
  text-align: justify;
}
.product-card .shoe-details .stars i{
  margin: 0 -1px;
  color: #333;
}
.product-card .color-price .color-option{
  display: flex;
  align-items: center;
}
.color-price{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}
.color-price .color-option .color{
  font-size: 18px;
  font-weight: 500;
  color: #333;
  margin-right: 8px;
}
.color-option  .circles{
  display: flex;
}
.color-option  .circles .circle{
  height: 18px;
  width: 18px;
  background: #0071C7;
  border-radius: 50%;
  margin: 0 4px;
  cursor: pointer;
  transition: all 0.4s ease;
}
.color-option  .circles .circle.blue.active{
  box-shadow: 0 0 0 2px #fff,
  0 0 0 4px #0071C7;
}
.color-option  .circles .circle.pink{
  background: #FA1795;
}
.color-option  .circles .circle.pink.active{
  box-shadow: 0 0 0 2px #fff,
  0 0 0 4px #FA1795;
}
.color-option  .circles .circle.yellow{
  background: #F5DA00;
}
.color-option  .circles .circle.yellow.active{
  box-shadow: 0 0 0 2px #fff,
  0 0 0 4px #F5DA00;
}
.color-price .price{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.color-price .price .price_num{
  font-size: 25px;
  font-weight: 600;
  color: #707070;
}
.color-price .price .price_letter{
  font-size: 10px;
  font-weight: 600;
  margin-top: -4px;
  color: #707070;
}
.product-card .button{
  position: relative;
  height: 50px;
  width: 100%;
  border-radius: 25px;
  margin-top: 30px;
  overflow: hidden;
}
.product-card .button .button-layer{
  position: absolute;
  height: 100%;
  width: 300%;
  left: -100%;
  background-image: linear-gradient(135deg,#9708CC, #43CBFF,#9708CC, #43CBFF );
  transition: all 0.4s ease;
  border-radius: 25PX;
}
.product-card .button:hover .button-layer{
  left: 0;
}
.product-card .button button{
  position: relative;
  height: 100%;
  width: 100%;
  background: none;
  outline: none;
  border: none;
  font-size: 18px;
  font-weight: 600;
  letter-spacing: 1px;
  color: #fff;
}

/*  */

</style>
