<template>
  <div class="searchBar" :style="{ width: search_width + 'px' }">
    <div class="icon" @click="search"></div>
    <div class="textInput">
      <input type="text" placeholder="search..." v-model="content">
      <input type="submit" value="go" id="go">
      <div class="clear" @click="clearContent"></div>
    </div>
  </div>
</template>

<script>
import  { ref } from 'vue'
export default {
  setup() {
    const query = ref('')
    const search_width = ref(40)
    const content = ref('')
    function search() {
      search_width.value = search_width.value === 40 ? 300 : 40
    }
    function clearContent() {
      content.value = ''
    }
    return {
      query,
      search_width,
      content,
      search,
      clearContent
    }
  }
};
</script>

<style scoped>
.searchBar {
  height: 40px;
  background-color: #FFF;
  box-shadow: 0 0 10px rgba(0,0,0,.4);
  border-radius: 60px;
  position: relative;
  overflow: hidden;
  transition: .5s;
}
.icon {
  width: 40px;
  height: 40px;
  background-color: #FFF;
  border-radius: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
.icon::before {
  content: '';
  width: 15px;
  height: 15px;
  border-radius: 50%;
  border: 3px solid dodgerblue;
  cursor: pointer;
  position: absolute;
  transform: translate(-4px, -4px);
}
.icon::after {
  content: '';
  width: 3px;
  height: 12px;
  background-color: dodgerblue;
  transform: translate(6px, 6px) rotate(315deg);
}
.textInput {
  width: 320px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: blue;
  position: absolute;
  top: 0;
  left: 60px;
}
.textInput input {
  width: 100%;
  height: 100%;
  border: none;
  outline: none;
  font-size: 16px;
}
.clear {
  width: 15px;
  height: 15px;
  position: absolute;
  right: 43%;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}
.clear::before {
  content: '';
  width: 1px;
  height: 15px;
  background-color: #999;
  transform: rotate(45deg);
  position: absolute;
}
.clear::after {
  content: '';
  width: 1px;
  height: 15px;
  background-color: #999;
  transform: rotate(315deg);
}
.clear:hover {
  background: orange;
}
#go {
  width: 12%;
  height: 60%;
  position: absolute;
  top: 20%;
  right: 28%;
  border-radius: 8px;
  color: #FFF;
  box-shadow: 0 0 2px rgba(0, 0, 0, .4);
  background: linear-gradient(skyblue, deepskyblue);
}
#go:hover {
  cursor: pointer;
  background: #CCC;
}
</style>
