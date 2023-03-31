<template>
  <div class="menu-upload">
    <!-- 输入菜谱名 -->
    <div class="menu-input">
      <el-input
        v-model="publishMenu.name"
        :show-file-list="true"
        placeholder="菜谱名称"
        clearable
        maxlength="15"
        minlength="1"
        show-word-limit
        input-style="height: 40px"
      />
    </div>
    <!-- 菜谱主图片上传 -->
    <div>
      <el-upload
        class="upload"
        drag
        :limit=1
        :action="url"
        multiple
        :before-remove="removeSignalImage"
        :on-success="signalUploadSuccess"
      >
        <el-icon class="el-icon--upload">
          <upload-filled/>
        </el-icon>
        <div class="el-upload__text">
          添加菜谱成品图 <em>click to upload</em>
        </div>
        <template #tip>
          <div class="el-upload__tip">
            支持上传的图片格式JPG/PNG/JPEG/GIF/WEBP
          </div>
        </template>
      </el-upload>
    </div>
    <!-- 烹饪工艺、难度、等级、时间、口味、 -->
    <div class="cook_skill">
      <el-row :gutter="10">
        <el-col :span="3" class="skill__item">
          <span>烹饪难度:</span>
        </el-col>
        <el-col :span="9">
          <el-select v-model.number="publishMenu.productionLevelId" class="m-2" placeholder="Select" size="large">
            <el-option
              v-for="item in productionLevels"
              :key="item.id"
              :label="item.levelName"
              :value="item.id"
            />
          </el-select>
        </el-col>
        <el-col :span="3" class="skill__item">
          <span>烹饪时间:</span>
        </el-col>
        <el-col :span="9">
          <el-select v-model.number="publishMenu.productionTimeId" class="m-2" placeholder="Select" size="large">
            <el-option
              v-for="item in productionTimes"
              :key="item.id"
              :label="item.timeName"
              :value="item.id"
            />
          </el-select>
        </el-col>
      </el-row>
      <el-row :gutter="10" style="margin-top: 20px">
        <el-col :span="3" class="skill__item">
          <span>口味:</span>
        </el-col>
        <el-col :span="9">
          <el-select v-model.number="publishMenu.flavorId" class="m-2" placeholder="Select" size="large">
            <el-option
              v-for="item in flavors"
              :key="item.id"
              :label="item.name"
              :value="item.id"
              placeholder="请选择"
            />
          </el-select>
        </el-col>
        <el-col :span="3" class="skill__item">
          <span>烹饪工艺:</span>
        </el-col>
        <el-col :span="9">
          <el-select v-model.number="publishMenu.cookTechnologyId" class="m-2" placeholder="Select" size="large">
            <el-option
              v-for="item in cookTechnologies"
              :key="item.id"
              :label="item.name"
              :value="item.id"
              placeholder="请选择"
            />
          </el-select>
        </el-col>
      </el-row>
      <el-row :gutter="10" style="margin-top: 20px">
        <el-col :span="3" class="skill__item">
          <span>厨具:</span>
        </el-col>
        <el-col :span="9">
          <el-select
            v-model="publishMenu.kitchenwareIds"
            multiple
            collapse-tags
            collapse-tags-tooltip
            :max-collapse-tags="3"
            placeholder="请选择"
            style="width: 228px"
          >
            <el-option
              v-for="item in kitchenwares"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-col>
        <el-col :span="3" class="skill__item">
          <span>所属分类:</span>
        </el-col>
        <el-col :span="9">
          <el-cascader
            clearable
            :options="categoryTree"
            :show-all-levels="false"
            :props="categoryOptionProps"
            v-model="categoryId"
          />
        </el-col>
      </el-row>

    </div>
    <!-- 描述 -->
    <div>
      <el-input
        v-model="publishMenu.description"
        :rows="2"
        type="textarea"
        placeholder="这道菜背后的故事~(选填)"
        input-style="height: 150px; margin-top: 20px; border-radius: 5px; margin-bottom: 20px"
      />
    </div>
    <!-- 分割线    -->
    <el-divider>
      <el-icon>
        <star-filled/>
      </el-icon>
    </el-divider>
    <!-- 食材清单 -->
    <div class="material">
      <div class="material-content"><span class="material-content-title">食材清单</span></div>
      <div class="material-body">
        <el-row :gutter="20">
          <el-col :span="12" class="material-body-span">食材（如五花肉）</el-col>
          <el-col :span="10" class="material-body-span">用量（如600g）</el-col>
        </el-row>
        <div>
          <el-table :data="publishMenu.materialMenu" style="width: 100%" max-height="450">
            <el-table-column prop="name" width="390">
              <template #default="scope">
                <el-input
                  v-model="publishMenu.materialMenu[scope.$index].name"
                  placeholder="菜谱食材"
                  clearable
                  maxlength="30"
                  minlength="1"
                  show-word-limit
                  input-style="height: 40px"
                />
              </template>
            </el-table-column>
            <el-table-column prop="name" width="200">
              <template #default="scope">
                <el-input
                  v-model="publishMenu.materialMenu[scope.$index].dosage"
                  placeholder="菜谱用量"
                  clearable
                  maxlength="10"
                  minlength="1"
                  show-word-limit
                  input-style="height: 40px"
                />
              </template>
            </el-table-column>

            <el-table-column fixed="right" width="120">
              <template #default="scope">
                <el-button
                  type="warning"
                  round
                  @click.prevent="deleteRow(scope.$index)"
                >
                  Remove
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-button type="primary" plain class="mt-4" style="width: 100%" @click="onAddItem"
          >增加一栏
          </el-button
          >
        </div>
      </div>
    </div>
    <!-- 分割线    -->
    <el-divider>
      <el-icon>
        <star-filled/>
      </el-icon>
    </el-divider>
    <!-- 步骤 -->
    <div class="step">
      <div class="step-content">
        <span class="step-title">步骤</span>
        <span class="step-tip">(支持上传图片格式有jpg、jpeg、png、gif、webp)</span>
      </div>
      <div style="text-align: left">
        <el-table :data="publishMenu.stepMenu" style="width: 100%" max-height="600">
          <el-table-column prop="index" width="35" type="index">
            <template #default="scope">
              <span style="font-size: 25px;">{{ number[scope.$index] }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="imageUrl" width="200">
            <template #default="scope">
              <el-upload
                class="avatar-uploader"
                :action="url"
                :show-file-list="false"
                :on-success="response => handleStepImageSuccess(response, file, scope.$index)"
                :before-upload="beforeAvatarUpload"
                style="height: 200px"
              >
                <img v-if="publishMenu.stepMenu[scope.$index].imageUrl"
                     :src="publishMenu.stepMenu[scope.$index].imageUrl" class="avatar"/>
                <el-icon v-else class="avatar-uploader-icon">
                  <Plus/>
                </el-icon>
              </el-upload>
            </template>
          </el-table-column>
          <el-table-column prop="content" width="405">
            <template #default="scope">
              <el-input
                v-model="publishMenu.stepMenu[scope.$index].content"
                :rows="7"
                type="textarea"
                placeholder="步骤介绍"
              />
            </template>
          </el-table-column>

          <el-table-column fixed="right" width="100">
            <template #default="scope">
              <el-button
                type="warning"
                round
                input-style="height: 300px"
                @click.prevent="deleteRow2(scope.$index)"
              >
                Remove
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-button type="primary" plain class="mt-4" style="width: 100%" @click="addStepData"
        >增加一栏
        </el-button
        >
      </div>

    </div>
    <!-- 分割线    -->
    <el-divider>
      <el-icon>
        <star-filled/>
      </el-icon>
    </el-divider>
    <!-- 小窍门 -->
    <div class="step">
      <div class="step-content">
        <span class="step-title">小窍门</span>
      </div>
      <el-input
        v-model="publishMenu.skill"
        :rows="7"
        type="textarea"
        placeholder="Please input"
      />
    </div>
    <el-checkbox v-model="publishMenu.isSole" label="独家发布" size="large" border class="sole"/>
    <div style="margin-top: 30px">
      <el-button type="success" style="width: 100px; height: 40px" @click="publish">发布</el-button>
    </div>
  </div>
  <FoodFooter/>
</template>

<script>
import {UploadFilled, Plus, StarFilled} from '@element-plus/icons-vue'
import {reactive, ref} from 'vue'
import {ElMessage} from 'element-plus'
import FoodFooter from '@/views/footer/FoodFooter'
import request from '@/utils/http'
import oss from '@/api/oss'
import {number} from '@/utils/numberUtils'
import resource from '@/api/resource'
import router from '@/router'

export default {
  name: 'publish',
  components: {
    UploadFilled,
    StarFilled,
    Plus,
    FoodFooter
  },
  setup() {
    // 请求封装数据
    const publishMenu = reactive({
      name: '',
      imageUrl: '',
      productionLevelId: 0,
      productionTimeId: 0,
      flavorId: 0,
      cookTechnologyId: 0,
      typeId: 0,
      kitchenwareIds: [],
      description: '',
      materialMenu: [
        {
          name: '',
          dosage: ''
        }
      ],
      stepMenu: [
        {
          rank: '',
          imageUrl: '',
          content: ''
        }
      ],
      skill: '',
      isSole: false
    })
    const categoryId = ref([])
    // 保存数据
    const publish = () => {
      publishMenu.typeId = categoryId.value[2]
      console.log('发布的数据为:', publishMenu)
      resource.savePublishData(publishMenu).then(result => {
        ElMessage.success('发布成功')
        // 跳转到登录页面
        router.push('/')

      }).catch(error => {
        ElMessage.error('发布失败,请稍后再试~')
      })
    }
    const url = request.defaults.baseURL + '/oss/upload/avatar'
    /*************************   组件渲染之前执行    ***********************/
      // 烹饪等级
    let productionLevels = reactive([])
    // 烹饪时间
    let productionTimes = reactive([])
    // 口味
    let flavors = reactive([])
    // 烹饪工艺
    let cookTechnologies = [reactive([])]
    // 厨具
    let kitchenwares = reactive([])
    // 发布数据回显方法
    const getPublishDataEcho = () => {
      resource.getPublishDataEcho().then(result => {
        // 数据赋值
        Array.prototype.push.apply(productionLevels, result.data.productionLevels)
        Array.prototype.push.apply(productionTimes, result.data.productionTimes)
        Array.prototype.push.apply(flavors, result.data.flavors)
        Array.prototype.push.apply(cookTechnologies, result.data.cookTechnologies)
        Array.prototype.push.apply(kitchenwares, result.data.kitchenwares)
      }).catch(error => {
        ElMessage.error('Inner server error')
      })
    }
    getPublishDataEcho()

    // 获取所有的分类
    const categoryTree = reactive([])
    const getTreeCategory = () => {
      resource.getTreeCategory(2).then(res => {
        categoryTree.push(...res.data)
      })
    }
    getTreeCategory()
    // 级联数据名转换
    const categoryOptionProps = {
      value: 'id',
      label: 'name',
      children: 'children'
    }
    /*************************   主图片的上传与移除    ***********************/

      // 移除文件触发的方法
    const removeSignalImage = (file, fileList) => {
        oss.removeImage(publishMenu.imageUrl).then(result => {
          console.log(result)
          ElMessage.success('图片移除成功~')
        })
      }
    // 图片上传成功触发的方法
    const signalUploadSuccess = (data, fileList) => {
      publishMenu.imageUrl = data.data
    }
    /***************************   食材清单   ****************************/
    const deleteRow = (index) => {
      publishMenu.materialMenu.splice(index, 1)
    }

    const onAddItem = () => {
      publishMenu.materialMenu.push({
        name: '',
        dosage: ''
      })
    }
    /***************************   步骤   ****************************/

    const deleteRow2 = (index) => {
      publishMenu.stepMenu.splice(index, 1)
    }
    const addStepData = () => {
      publishMenu.stepMenu.push({
        imageUrl: '',
        content: ''
      })
    }
    // 图片上传成功执行的方法
    const handleStepImageSuccess = (response, file, index) => {
      // 在指定索引位置进行数据替换
      publishMenu.stepMenu[index].imageUrl = response.data
      publishMenu.stepMenu[index].rank = index + 1
    }

    const beforeAvatarUpload = (rawFile) => {
      if (rawFile.type !== 'image/jpeg') {
        ElMessage.error('Avatar picture must be JPG format!')
        return false
      } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('Avatar picture size can not exceed 2MB!')
        return false
      }
      return true
    }

    return {
      url,
      number,
      productionLevels,
      productionTimes,
      flavors,
      cookTechnologies,
      kitchenwares,
      publishMenu,
      categoryTree,
      categoryOptionProps,
      categoryId,
      onAddItem,
      deleteRow,
      handleStepImageSuccess,
      beforeAvatarUpload,
      addStepData,
      deleteRow2,
      removeSignalImage,
      signalUploadSuccess,
      publish,
    }
  }

}
</script>

<style scoped lang="scss">

.upload {
  background-color: #e5f2ff;
  margin-top: 30px;
  border-radius: 15px;
}

.menu-upload {
  width: 50%;
  margin-left: 15vw;

  .menu-input {
    margin-top: 20px;

  }

  .cook_skill {
    margin-top: 30px;
    text-align: left;

    :deep(.el-input) {
      height: 40px;
    }

    .skill__item {
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
}

/* 穿透upload背景色 */
:deep(.el-upload-dragger) {
  background-color: #f0f0f5 !important;
}

.material {
  margin-top: 20px;
  margin-bottom: 20px;

  .material-content {
    display: flex;
    justify-content: left;

    .material-content-title {
      font-weight: bold;
      font-size: 20px;
    }
  }

  .material-body {
    min-height: 100px;
    background-color: #F1F7FA;
    border-radius: 8px;
    margin-top: 10px;
    padding: 20px 15px;

    .material-body-span {
      display: flex;
      justify-content: left;
      margin-bottom: 10px;
      margin-left: 18px;
    }
  }
}

.step {
  .step-content {
    display: flex;
    justify-content: left;
    margin-bottom: 10px;

    .step-title {
      font-weight: bold;
      font-size: 20px;
    }

    .step-tip {
      font-size: 13px;
      margin-left: 10px;
      margin-top: 6px;
    }
  }

}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.sole {
  margin-top: 20px;
  display: flex;
  justify-content: left;
  width: 100px;
}
</style>
<style>
html {

  height: 100%;

}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
