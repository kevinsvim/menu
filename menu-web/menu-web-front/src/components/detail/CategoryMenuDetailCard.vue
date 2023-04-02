<template>
<div>
  <el-row :gutter="20">
    <el-col :span="6">
      <el-image @click="toDetail" style="width: 200px; height: 150px; border-radius: 10px" :src="imageUrl" :fit="'fill'" />
    </el-col>
    <el-col :span="12" style="height: 150px">
      <div>
        <span style="font-weight: bold; font-size: 19px; line-height: 40px">#{{ menuName }}</span>
      </div>
      <div>
        <span style="line-height: 35px">点赞人数: {{ upNum }}</span>
      </div>
      <div style="line-height: 35px">
        <span>收藏人数: {{ collectNum }}</span>
      </div>
      <div>
        <el-avatar style="vertical-align: middle; margin-right: 4px" :size="20" :src="avatar" />
        <span style="line-height: 35px">By {{ username }}</span>
      </div>
    </el-col>
    <el-col :span="6">
    </el-col>
  </el-row>
</div>
</template>

<script>
import { ref } from 'vue'
import { useRouter } from "vue-router/dist/vue-router";

export default {
  name: "CategoryMenuDetailCard",
  props: ['menuName', 'upNum', 'collectNum', 'username', 'avatar', 'imageUrl', 'menuId'],
  components: {

  },

  setup(props, ctx) {

    const menuName = props.menuName
    const upNum = props.upNum
    const collectNum = props.collectNum
    const username = props.username
    const imageUrl = props.imageUrl
    const avatar = props.avatar
    const menuId = props.menuId
    const value = ref(3.7)
    const router = useRouter()
    const toDetail = () => {
      let data = router.resolve({path: '/detail', query: {id: menuId}})
      window.open(data.href, '_blank')
    }
    return {
      avatar,
      value,
      menuName,
      upNum,
      collectNum,
      username,
      imageUrl,
      toDetail
    }
  }
}
</script>

<style scoped>

</style>
