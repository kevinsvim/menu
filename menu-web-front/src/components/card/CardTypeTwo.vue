<template>
  <el-space>
    <el-skeleton
      style="width: 240px"
      :loading="loading"
      animated
      :throttle="500"
    >
      <template #default>
        <el-card :body-style="{ padding: '0px', marginBottom: '1px' }">
          <img
            src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            class="image"
          />
          <div style="padding: 14px">
            <span>Delicious hamburger</span>
            <div class="bottom card-header">
              <div class="time">{{ currentDate }}</div>
              <el-button text class="button">operation button</el-button>
            </div>
          </div>
        </el-card>
      </template>
    </el-skeleton>
  </el-space>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'MenuItem2',
  setup () {
    const loading = ref(false)
    const currentDate = new Date().toDateString()
    return {
      loading,
      currentDate
    }
  }

}

</script>
<style scoped>
.image {
  width: 18vw;
  border-radius: 10px;
}
</style>
