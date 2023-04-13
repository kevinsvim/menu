<template>
  <div>
    <div>
      <el-image
        :style="{ width: width + 'px', height: height + 'px', borderRadius: borderVar + 'px'}"
        :src="imageUrl"
        :fit="'fill'"
        @click="toDishDetail"
      />
    </div>
    <div>
      <span @click="toDishDetail" class="cookName">绿豆糕</span>
    </div>
    <div>
    <span>
      <iconfont-svg icon="icon-yanjing1" size="14"/>
      <span style="font-size: 12px; height: 12px; margin-left: 2px">{{ viewNum }}</span>
    </span>
      <span style="margin-left: 10px">
      <iconfont-svg icon="icon-shoucang" size="14"/>
      <span style="font-size: 12px; height: 12px; margin-left: 2px">{{ collectNum }}</span>
    </span>
    </div>

  </div>
</template>

<script>
import IconfontSvg from "@/components/iconfonts/IconSvg";
import {ref} from 'vue'
import {useRouter} from "vue-router/dist/vue-router";

export default {
  name: "MenuCard",
  props: ['imageUrl', 'borderVar', 'width', 'height', 'viewNum', 'collectNum', 'menuId'],
  components: {
    IconfontSvg

  },
  setup(props) {
    const router = useRouter()
    const toDishDetail = () => {
      let data = router.resolve({path: '/detail', query: {id: props.menuId}})
      open(data.href, '_blank')
    }
    return {
      toDishDetail
    }
  }
}
</script>

<style scoped lang="less">
.cookName {
  display: block;
  font-size: 15px;
  height: 35px;
  line-height: 35px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
  cursor: pointer;

}

.cookName:hover {
  color: rgb(255, 170, 0)
}
</style>
