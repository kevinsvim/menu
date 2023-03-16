<template>
  <el-carousel :interval="4000" type="card" height="300px">
    <el-carousel-item v-for="(banner, index) in bannerList" :key="index">
      <h3 text="2xl" justify="center"><div style="width: 100%; height: 100%">
        <el-image :src="banner"/>
      </div> </h3>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
import { ElMessage } from 'element-plus'
import banner from '@/api/banner'
import { ref} from 'vue'
export default {
  name: 'banner',
  setup() {
    const bannerList = ref([])
    /**
     * 获取banner列表
     */
    const getBannerList = () => {
      banner.getBannerList().then(result => {
        bannerList.value = result.data
      }).catch(error => {
        ElMessage.error('Internal server error')
      })
    }
    getBannerList()
    return {
      bannerList
    }
  }
}
</script>

<style scoped>

</style>
