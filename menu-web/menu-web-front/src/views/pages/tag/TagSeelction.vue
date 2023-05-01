<template>
  <div class="tag-selection" v-loading="tagLoading">
    <div class="tag-header">
      <h2>请选择您感兴趣的标签</h2>
      <p>您至多可以选择七个标签，我们将根据您的选择为您推荐内容</p>
    </div>
    <el-checkbox-group v-model="selectedTags" :min="0" :max="7">
      <el-checkbox v-for="tag in tagGroups" :key="tag.id" :label="tag.id" border style="margin-top: 20px">
        <el-tag
          :key="tag.id"
          class="mx-1"
          effect="dark"
          round
        >
          {{ tag.name }}
        </el-tag>

        </el-checkbox>
    </el-checkbox-group>
    <div class="tag-footer">
      <button class="btn" @click="jump">跳过</button>
      <button class="btn" @click="submit">完成</button>
    </div>
  </div>
</template>

<script>
import {ref, reactive} from "vue";
import tagApi from '@/api/tag'
import { useRouter } from "vue-router/dist/vue-router";
export default {
  name: "TagSelection",
  setup() {
    const router = useRouter()
    const tagGroups = reactive([])
    const tagLoading = ref(true)
    const selectedTags = ref([])
    // 初始化获取标签数据
    const initTags = () => {
      tagApi.getTags().then(res => {
        // 取出name为'其他'的分类
        let arr = res.data.filter(x => x.name !== '其他')
        tagGroups.push(...arr)
        tagLoading.value = false
      })
    }
    initTags()
    // 提交
    const submit = () => {
      // 将数据进行保存
      tagLoading.value = true
      tagApi.saveTags(selectedTags.value).then(res => {
        tagLoading.value = false
        // 调转到主页
        router.push("/")
      }).catch(error => {
        tagLoading.value = false
      })
    }
    return {
      tagGroups,
      tagLoading,
      selectedTags,
      submit
    }
  }
};
</script>

<style scoped>
.tag-selection {
  width: 50%;
  margin: 5% auto;
}

.tag-header {
  text-align: center;
  margin-bottom: 20px;
}

.tag-group h3 {
  font-size: 18px;
  margin-bottom: 10px;
}

.tag-footer {
  text-align: center;
  margin-top: 20px;
}

.btn {
  padding: 10px 20px;
  border: none;
  background-color: #e74c3c;
  color: #fff;
  cursor: pointer;
  transition: all 0.3s ease;
}

.btn:hover {
  background-color: #c0392b;
}
</style>
