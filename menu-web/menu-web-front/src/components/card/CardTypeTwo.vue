<template>
  <el-space>
    <el-skeleton
      style="width: 240px"
      :loading="loading"
      animated
      :throttle="500"
    >
      <template #default>
        <el-card
          :body-style="{ padding: '0px', marginBottom: '1px' }"
          shadow="hover"
        >
          <el-image
            style="width: 250px;
            height: 220px"
            :src="imageUrl"
            :fit="'fill'"
          />
          <div style="padding: 14px">
            <span>{{ title }}</span>
            <div class="bottom card-header">
              <div class="time">{{ currentDate }}</div>
              <el-button text class="button">查看详情</el-button>
            </div>
          </div>
        </el-card>
      </template>
    </el-skeleton>
  </el-space>
</template>

<script>
import {ref} from 'vue'

export default {
  name: 'MenuItem2',
  props: ['imageUrl', 'title'],
  setup(props, ctx) {
    const imageUrl = props.imageUrl
    const title = props.title
    const loading = ref(false)
    const currentDate = new Date().toDateString()
    return {
      loading,
      title,
      imageUrl,
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
