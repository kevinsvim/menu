<template>
  <el-form label-width="120px">
    <el-form-item label="信息描述">
      <el-tooltip
          class="box-item"
          effect="dark"
          placement="bottom"
      >
        <template #content> ① 一级分类重复, 二级分类没要求, 三级分类不能重复<br/>② 一级分类不重复, 二级分类不能重复,
          三级分类不能重复
        </template>
        <el-tag type="info">excel模版说明</el-tag>
      </el-tooltip>
      <el-tag>
        <i class="el-icon-download"/>
        <a :href="'/public/test.xlsx'">点击下载模版</a>
      </el-tag>
      <el-tooltip
          class="box-item"
          effect="dark"
          placement="bottom"
      >
        <template #content> ① 一级分类重复, 二级分类没要求, 三级分类不能重复<br/>② 一级分类不重复, 二级分类不能重复,
          三级分类不能重复
        </template>
        <iconfont-svg icon="menu-icon-icon13" size="25"/>
      </el-tooltip>
    </el-form-item>
    <el-form-item label="选择Excel">
      <el-upload
          ref="uploadRef"
          class="upload-demo"
          action="http://localhost:8080/resource/file/parseExcelToDB"
          :auto-upload="false"
          :limit=1
      >
        <template #trigger>
          <el-button type="primary">选择文件</el-button>
        </template>

        <el-button class="ml-3" style="margin-left: 5px" type="success" @click="submitUpload">
          上传到服务器
        </el-button>
        <template #tip>
          <div class="el-upload__tip">
            jpg/png files with a size less than 500kb
          </div>
        </template>
      </el-upload>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import type {UploadInstance} from 'element-plus'
import IconfontSvg from "../../components/iconfonts/IconSvg.vue";

const uploadRef = ref<UploadInstance>()

const submitUpload = () => {

  uploadRef.value!.submit()
}
</script>
<style scoped>

</style>
