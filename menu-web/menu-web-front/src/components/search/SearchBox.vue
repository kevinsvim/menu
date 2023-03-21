<template>
  <div class="search bar1">
    <form>
      <input type="text" placeholder="请输入搜索的内容...">
      <button type="submit">
        <svg t="1679302506304" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="4720" width="30" height="30">
          <path d="M511.6928 512m-432.3328 0a432.3328 432.3328 0 1 0 864.6656 0 432.3328 432.3328 0 1 0-864.6656 0Z"
                fill="#8D8DFF" p-id="4721"></path>
          <path
            d="M286.72 417.8432c-85.0432 0-160.4096 41.3696-207.104 105.0624 4.5568 182.7328 122.368 337.3056 285.952 396.032 103.2192-33.28 177.92-130.048 177.92-244.3776 0-141.7728-114.944-256.7168-256.768-256.7168z"
            fill="#A6A6FF" p-id="4722"></path>
          <path
            d="M733.4912 778.9568a40.96 40.96 0 0 1-28.0576-12.8512l-66.7648-70.9632c-15.5136-16.4864-14.6944-42.3936 1.792-57.9072 16.4864-15.5136 42.3936-14.6944 57.9072 1.792l66.7648 70.9632c15.5136 16.4864 14.6944 42.3936-1.792 57.9072a40.5504 40.5504 0 0 1-29.8496 11.0592z"
            fill="#D7D7FF" p-id="4723"></path>
          <path
            d="M500.9408 757.0432c-146.1248 0-265.0112-118.8864-265.0112-265.0112S354.816 227.0208 500.9408 227.0208 765.952 345.9072 765.952 492.032s-118.8864 265.0112-265.0112 265.0112z m0-448.1024c-100.9664 0-183.0912 82.1248-183.0912 183.0912s82.1248 183.0912 183.0912 183.0912 183.0912-82.1248 183.0912-183.0912-82.176-183.0912-183.0912-183.0912z"
            fill="#FFFFFF" p-id="4724"></path>
        </svg>
      </button>
    </form>
  </div>
</template>

<script>
import { Search } from '@element-plus/icons'

export default {
  name: 'SearchBox',
  components: {
    Search
  }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

body {
  margin: 0;
  padding: 0;
  background: #494A5F;
  font-weight: 500;
  font-family: "Microsoft YaHei", "宋体", "Segoe UI", "Lucida Grande", Helvetica, Arial, sans-serif, FreeSans, Arimo;
}


form {
  position: relative;
  width: 300px;
  margin: 0 auto;
}

input, button {
  border: none;
  outline: none;
}

input {
  width: 100%;
  height: 42px;
  padding-left: 13px;
  padding-right: 46px;
}

button {
  height: 42px;
  width: 42px;
  cursor: pointer;
  position: absolute;
}

.bar1 input {
  border: 2px solid #7BA7AB;
  border-radius: 5px;
  color: #9E9C9C;
}

.bar1 button {
  top: 0;
  right: 0;
  background: #7BA7AB;
  border-radius: 0 5px 5px 0;
}

</style>
