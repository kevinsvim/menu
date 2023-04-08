<template>
  <div>
    <el-steps :active="active">
      <el-step>
        <template #title>
          <span style="font-weight: bold">步骤①</span>
        </template>
        <template #icon>
          <iconfont-svg icon="menu-icon-bianji1" size="30"/>
        </template>
      </el-step>
      <el-step>
        <template #title>
          <span style="font-weight: bold">步骤②</span>
        </template>
        <template #icon>
          <iconfont-svg icon="menu-icon-bianji1" size="30"/>
        </template>
      </el-step>
      <el-step>
        <template #title>
          <span style="font-weight: bold">步骤③</span>
        </template>
        <template #icon>
          <iconfont-svg icon="menu-icon-bianji1" size="30"/>
        </template>
      </el-step>
    </el-steps>
    <div v-show="canShowStep.step1">
      <div style="height: 450px; width: 100%; background-color: #F1F7FA; margin-top: 30px">
        <el-scrollbar height="100%" style="padding: 15px 40px">
          <div style="margin-top: 20px; margin-bottom: 20px">
            <el-row align="middle">
              <el-col :span="2">
                <span>食材名</span>
              </el-col>
              <el-col :span="6">
                <el-input type="text" v-model="ingredient.name" style="height: 40px"/>
              </el-col>
            </el-row>
          </div>
          <div>
            <el-row align="middle">
              <el-col :span="2">
                <span>食材别名</span>
              </el-col>
              <el-col :span="6">
                <el-input type="text" v-model="ingredient.alias" style="height: 40px"/>
              </el-col>
            </el-row>
          </div>
          <div style="margin-top: 20px; margin-bottom: 20px">
            <el-row align="middle">
              <el-col :span="2">
                <span>热量</span>
              </el-col>
              <el-col :span="6">
                <el-input type="text" v-model="ingredient.caloric" style="height: 40px"
                          placeholder="例如:77大卡(每一百克)"/>
              </el-col>
            </el-row>
          </div>
          <div style="margin-top: 20px; margin-bottom: 20px">
            <el-row align="middle">
              <el-col :span="2">
                <span>适宜人群</span>
              </el-col>
              <el-col :span="6">
                <el-input type="text" v-model="ingredient.suitable" style="height: 40px"
                          placeholder="例如:一般人均可食用。脾胃气虚者、癌症、高血压、动脉硬化患者适宜。"/>
              </el-col>
            </el-row>
          </div>
          <div style="margin-top: 20px; margin-bottom: 20px">
            <el-row align="middle">
              <el-col :span="2">
                <span>食材图片</span>
              </el-col>
              <el-col :span="6">
                <el-upload
                    class="upload-demo"
                    action="http://localhost:8080/oss/upload/avatar"
                    :on-success="signalUploadSuccess"
                    :on-remove="removeSignalImage"
                    list-type="picture"
                    :limit="1"
                >
                  <el-button type="primary">Click to upload</el-button>
                  <template #tip>
                    <div class="el-upload__tip">
                      仅支持JPG/PNG,并且大小低于1MB
                    </div>
                  </template>
                </el-upload>
              </el-col>
            </el-row>
          </div>
        </el-scrollbar>
        <div>
          <el-button type="primary" @click.prevent="() => toStep(2)">
            Next Page
            <iconfont-svg icon="menu-icon-xiayibu" size="16" style="margin-left: 4px"/>
          </el-button>
        </div>
      </div>

    </div>
    <div v-show="canShowStep.step2">
      <div style="height: 450px; width: 100%; background-color: #F1F7FA; margin-top: 30px">
        <el-scrollbar height="100%" style="padding: 15px 40px">
          <el-form :model="ingredient" label-width="120px">
            <el-form-item label="食材介绍">
              <el-input v-model="ingredient.intro" type="textarea"/>
            </el-form-item>
            <el-form-item label="营养成分">
              <div>
                <el-table :data="nutritional" style="width: 100%" max-height="450">
                  <el-table-column prop="name" width="390">
                    <template #default="scope">
                      <el-input
                          v-model="nutritional[scope.$index].name"
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
                          v-model="nutritional[scope.$index].dosage"
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
            </el-form-item>
            <el-form-item label="功效与作用">
              <el-input v-model="ingredient.effect" type="textarea"/>
            </el-form-item>
            <el-form-item label="营养价值">
              <el-input v-model="ingredient.nutritiveValue" type="textarea"/>
            </el-form-item>
            <el-form-item label="做法">
              <el-input v-model="ingredient.doing" type="textarea"/>
            </el-form-item>
            <el-form-item label="如何挑选">
              <el-input v-model="ingredient.pick" type="textarea"/>
            </el-form-item>
            <el-form-item label="存储方式">
              <el-input v-model="ingredient.storage" type="textarea"/>
            </el-form-item>
          </el-form>
        </el-scrollbar>
        <div>
          <el-button type="primary" @click.prevent="() => toStep(1)">
            Last Page
            <iconfont-svg icon="menu-icon-shangyibu" size="16" style="margin-left: 4px"/>
          </el-button>
          <el-button type="primary" @click.prevent="() => toStep(3)">
            Next Page
            <iconfont-svg icon="menu-icon-xiayibu" size="16" style="margin-left: 4px"/>
          </el-button>
        </div>
      </div>
    </div>
    <div v-show="canShowStep.step3">
      <div style="height: 450px; width: 100%; background-color: #F1F7FA; margin-top: 30px">
        <el-scrollbar height="100%" style="padding: 15px 40px">
          <el-form :model="ingredient" label-width="120px">
            <el-form-item label="相克食物">
              <el-input v-model="ingredient.restrictionFood" type="textarea"/>
            </el-form-item>
            <el-form-item label="禁忌人群">
              <el-input v-model="ingredient.tabooPopulation" type="textarea"/>
            </el-form-item>
            <el-form-item label="搭配食物">
              <el-input v-model="ingredient.foodMatching" type="textarea"/>
            </el-form-item>
          </el-form>
        </el-scrollbar>
        <div style="display: flex">
          <el-button type="primary" @click.prevent="() => toStep(2)">
            Last Page
            <iconfont-svg icon="menu-icon-shangyibu" size="16" style="margin-left: 4px"/>
          </el-button>
          <div @click="saveIngredient" class="tag-item current"><span>保存</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {ref, reactive} from "vue";
import IconfontSvg from "@/components/iconfonts/IconSvg.vue";
import {ElMessage} from "element-plus";
import ingredientApi from '@/api/ingredient.js'
import oss from "@/api/oss.js";
export default {
  name: "Increase",
  components: {IconfontSvg},
  setup() {
    const ingredient = reactive({
      // 食材名
      name: '',
      // 别名
      alias: '',
      // 热量
      caloric: '',
      // 适宜人群
      suitable: '',
      // 食材图片
      imageUrl: '',
      // 食材介绍
      intro: '',
      // 营养成分
      nutritional: '',
      // 功效与作用
      effect: '',
      // 营养价值
      nutritiveValue: '',
      // 做法
      doing: '',
      // 如何挑选
      pick: '',
      // 存储方式
      storage: '',
      // 相克食物
      restrictionFood: '',
      // 禁忌人群
      tabooPopulation: '',
      // 搭配食物
      foodMatching: '',
    })
    // 营养成分临时表
    const nutritional = reactive([
      {
        name: '',
        dosage: ''
      }
    ])
    // 当前到哪一步
    const active = ref(1)
    //  步骤显示
    const canShowStep = reactive({
      step1: true,
      step2: false,
      step3: false
    })
    // 增加一栏
    const onAddItem = () => {
      nutritional.push({
        name: '',
        dosage: ''
      })
    }
    // 删除一栏
    const deleteRow = (index) => {
      nutritional.splice(index, 1)
    }
    // 点击下一步
    const toStep = (step) => {
      switch (step) {
        case 1:
          canShowStep.step1 = true
          canShowStep.step2 = false
          active.value = 1
          break
        case 2:
          if (ingredient.name === '' || ingredient.alias === '' || ingredient.caloric === '' || ingredient.suitable === '' || ingredient.imageUrl === '') {
            ElMessage.warning('存在字段为空!')
          } else {
            canShowStep.step1 = false
            canShowStep.step3 = false
            canShowStep.step2 = true
            active.value = 2
          }
          break
        case 3:
          if (ingredient.intro === '' || nutritional.length < 1 || ingredient.effect === '' || ingredient.nutritiveValue === '' || ingredient.doing === ''
          || ingredient.pick === '' || ingredient.storage === '') {
            ElMessage.warning('存在字段为空!')
          } else {
            canShowStep.step2 = false
            canShowStep.step3 = true
            active.value = 3
          }
          break
        default:
          break
      }
    }
    // 保存材料
    const saveIngredient = () => {
      ingredient.nutritional = nutritional.flatMap(item => item.name).join(',') + '@' + nutritional.flatMap(item => item.dosage).join(',')
      ingredientApi.saveIngredient(ingredient).then(res => {
        ElMessage.success('添加成功!')
      })
    }

    // 移除文件触发的方法
    const removeSignalImage = (file, fileList) => {
      oss.removeImage(ingredient.imageUrl).then(result => {
        ingredient.imageUrl = ''
      })
    }
    // 图片上传成功触发的方法
    const signalUploadSuccess = (data, fileList) => {
      ingredient.imageUrl = data.data
    }
    return {
      ingredient,
      canShowStep,
      active,
      nutritional,
      removeSignalImage,
      signalUploadSuccess,
      saveIngredient,
      deleteRow,
      onAddItem,
      toStep
    }
  }
}
</script>

<style scoped>
.current {
  text-align: center;
  width: 70px;
  height: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  color: #7f7f7f;
  margin-left: 10vw;
  border-radius: 999px;
}
.tag-item:hover {
  background: salmon linear-gradient(315deg, seagreen 0, coral 100%) center center;
}
.tag-item {
  background: #4A54FF linear-gradient(315deg, #6772FF 0, #00F9E5 100%) center center;
  background-size: 104% 104%;
  color: #fff;
}
</style>
