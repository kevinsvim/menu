<template>
  <div>
    <el-table
        :data="pageIngredients.records"
        style="width: 100%"
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}"
    >
      <el-table-column fixed prop="createTime" label="创建日期" width="150"/>
      <el-table-column prop="name" label="食材名" width="120"/>
      <el-table-column prop="alias" label="别名" width="120">
        <template #default="scope">
          <el-tooltip
              class="box-item"
              effect="dark"
              content="Top Center prompts info"
              placement="top">
            <template #content>
              {{ scope.row.alias }}
            </template>
            <div class="text_overflow">
              <span style="font-size: 13px">
            {{ scope.row.alias }}
          </span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="nutritiveValue" label="营养价值" width="160">
        <template #default="scope">
          <el-tooltip
              class="box-item"
              effect="dark"
              content="Top Center prompts info"
              placement="top">
            <template #content>
              {{ scope.row.nutritiveValue }}
            </template>
            <div class="text_overflow">
              <span style="font-size: 13px">
            {{ scope.row.nutritiveValue }}
          </span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="city" label="图片" width="160">
        <template #default="scope">
          <el-avatar shape="square" :size="70" :fit="'fill'" :src="scope.row.imageUrl" />
        </template>
      </el-table-column>
      <el-table-column prop="caloric" label="卡路里" width="120">
        <template #default="scope">
          <span style="font-size: 13px">
            {{ scope.row.caloric }}
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="介绍" width="160">
        <template #default="scope">
          <el-tooltip
              class="box-item"
              effect="dark"
              content="Top Center prompts info"
              placement="top">
            <template #content>
              {{ scope.row.intro }}
            </template>
            <div class="text_overflow">
              <span style="font-size: 13px">
            {{ scope.row.intro }}
          </span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="doing" label="做法" width="160">
        <template #default="scope">
          <el-tooltip
              class="box-item"
              effect="dark"
              content="Top Center prompts info"
              placement="top">
            <template #content>
              {{ scope.row.doing }}
            </template>
            <div class="text_overflow">
              <span style="font-size: 13px">
            {{ scope.row.doing }}
          </span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column  label="有效作用" width="160">
        <template #default="scope">
          <el-tooltip
              class="box-item"
              effect="dark"
              content="Top Center prompts info"
              placement="top">
            <template #content>
              {{ scope.row.effect }}
            </template>
            <div class="text_overflow">
              <span style="font-size: 13px">
                {{ scope.row.effect }}
              </span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column  label="食物搭配" width="160">
        <template #default="scope">
          <el-tooltip
              class="box-item"
              effect="dark"
              content="Top Center prompts info"
              placement="top">
            <template #content>
              {{ scope.row.foodMatching }}
            </template>
            <div class="text_overflow">
              <span style="font-size: 13px">
            {{ scope.row.foodMatching }}
          </span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column  label="营养含量" width="160">
        <template #default="scope">
          <el-tooltip
              class="box-item"
              effect="dark"
              content="Top Center prompts info"
              placement="top">
            <template #content>
              {{ scope.row.nutritional }}
            </template>
            <div class="text_overflow">
              <span style="font-size: 13px">
            {{ scope.row.nutritional }}
          </span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column  label="挑选方式" width="160">
        <template #default="scope">
          <el-tooltip
              class="box-item"
              effect="dark"
              content="Top Center prompts info"
              placement="top">
            <template #content>
              {{ scope.row.pick }}
            </template>
            <div class="text_overflow">
              <span style="font-size: 13px">
            {{ scope.row.pick }}
          </span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column  label="存储方式" width="160">
        <template #default="scope">
          <el-tooltip
              class="box-item"
              effect="dark"
              content="Top Center prompts info"
              placement="top">
            <template #content>
              {{ scope.row.storage }}
            </template>
            <div class="text_overflow">
              <span style="font-size: 13px">
            {{ scope.row.storage }}
          </span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="功能" width="120">
        <template #default="scope">
          <el-popconfirm
              width="220"
              confirm-button-text="确认删除"
              cancel-button-text="取消"
              icon-color="#626AEF"
              title="删除之后将无法恢复,您确定要删除吗"
              :onConfirm="() => deleteIngredient(scope.row.id)"
          >
            <template #reference>
              <el-button link type="primary" size="small"
              >删除
              </el-button>
            </template>
          </el-popconfirm>
          <el-button link type="primary" size="small" @click="() => editIngredient(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 40px; margin-bottom: 50px">
      <el-pagination
          v-model:current-page="pageIngredients.currentPage"
          v-model:page-size="pageIngredients.pageSize"
          :disabled=false
          :background=true
          layout="prev, pager, next, jumper"
          :total="pageIngredients.total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>
  </div>
  <!-- 编辑弹出框 -->
  <el-dialog v-model="editIngredientDialogVisible" title="修改食材" width="60%" center>
    <el-scrollbar height="60vh">
      <el-form :model="editIngredientRow" label-width="120px">
        <el-form-item label="食材名">
          <el-input v-model="editIngredientRow.name" />
        </el-form-item>
        <el-form-item label="别名">
          <el-input v-model="editIngredientRow.alias" />
        </el-form-item>
        <el-form-item label="卡路里">
          <el-input v-model="editIngredientRow.caloric" />
        </el-form-item>
        <el-form-item label="做法">
          <el-input v-model="editIngredientRow.doing" type="textarea" rows="5"/>
        </el-form-item>
        <el-form-item label="功效与作用">
          <el-input v-model="editIngredientRow.effect" type="textarea" rows="5"/>
        </el-form-item>
        <el-form-item label="食物搭配">
          <el-input v-model="editIngredientRow.foodMatching" type="textarea" rows="5"/>
        </el-form-item>
        <el-form-item label="图片">
          <el-upload
              v-model:file-list="fileList"
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
                jpg/png files with a size less than 1MB
              </div>
            </template>
          </el-upload>
        </el-form-item>
        <el-form-item label="食材介绍">
          <el-input v-model="editIngredientRow.intro" type="textarea" rows="5"/>
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
        <el-form-item label="营养价值">
          <el-input v-model="editIngredientRow.nutritiveValue" type="textarea" rows="5"/>
        </el-form-item>
        <el-form-item label="挑选方式">
          <el-input v-model="editIngredientRow.pick" type="textarea" rows="5"/>
        </el-form-item>
        <el-form-item label="相克食物">
          <el-input v-model="editIngredientRow.restrictionFood" type="textarea" rows="5"/>
        </el-form-item>
        <el-form-item label="存储方式">
          <el-input v-model="editIngredientRow.storage" type="textarea" rows="5"/>
        </el-form-item>
        <el-form-item label="适宜人群">
          <el-input v-model="editIngredientRow.suitable" type="textarea" rows="5"/>
        </el-form-item>
        <el-form-item label="禁忌人群">
          <el-input v-model="editIngredientRow.tabooPopulation" type="textarea" rows="5"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveEditIngredient">保存</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="editIngredientDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveEditIngredient">
          保存
        </el-button>
      </span>
      </template>
    </el-scrollbar>

  </el-dialog>
</template>

<script>
import { ref, reactive } from "vue";
import dayjs from "dayjs";
import IconfontSvg from "@/components/iconfonts/IconSvg.vue";
import ingredientApi from "@/api/ingredient.js";
import oss from "@/api/oss.js";
import {ElMessage} from "element-plus";

export default {
  name: "List",
  components: {
    IconfontSvg
  },
  setup() {
    const pageIngredients = reactive({
      currentPage: 1,
      pageSize: 5,
      total: 0,
      records: []
    })
    const fileList = reactive([])
    // 编辑回显数据
    let editIngredientRow = reactive({
      id: '',
      // 名字
      name: '',
      // 别名
      alias: '',
      // 卡路里
      caloric: '',
      // 做法
      doing: '',
      // 功效与作用
      effect: '',
      // 食物搭配
      foodMatching: '',
      // 食材图片
      imageUrl: '',
      // 食材介绍
      intro: '',
      // 营养含量
      nutritional: '',
      // 营养价值
      nutritiveValue: '',
      // 如何挑选
      pick: '',
      // 相克食物
      restrictionFood: '',
      // 存储方式
      storage: '',
      // 适宜人群
      suitable: '',
      // 禁忌人群
      tabooPopulation: ''
    })
    // 营养成分临时表
    const nutritional = reactive([])
    // 编辑食材列数据回显方法
    const editIngredient = (row) => {
      fileList.push({
        name: row.name + '.jpeg',
        url: row.imageUrl
      })
      editIngredientRow.id = row.id
      editIngredientRow.name = row.name
      editIngredientRow.alias = row.alias
      editIngredientRow.caloric = row.caloric
      editIngredientRow.doing = row.doing
      editIngredientRow.effect = row.effect
      editIngredientRow.foodMatching = row.foodMatching
      editIngredientRow.imageUrl = row.imageUrl
      editIngredientRow.intro = row.intro
      let nutritionArr = row.nutritional.split('@')
      let nameArr = nutritionArr[0].split(',')
      let dosageArr = nutritionArr[1].split(',')
      for (let i = 0; i < nameArr.length; i++) {
        nutritional.push({name: nameArr[i], dosage: dosageArr[i] })
      }
      editIngredientRow.nutritiveValue = row.nutritiveValue
      editIngredientRow.pick = row.pick
      editIngredientRow.restrictionFood = row.restrictionFood
      editIngredientRow.storage = row.storage
      editIngredientRow.suitable = row.suitable
      editIngredientRow.tabooPopulation = row.tabooPopulation
      editIngredientDialogVisible.value = true
    }
    // 保存编辑的食材数据
    const saveEditIngredient = () => {
      editIngredientRow.nutritional = nutritional.flatMap(item => item.name).join(',') + '@' + nutritional.flatMap(item => item.dosage).join(',')
      ingredientApi.updateIngredient(editIngredientRow).then(res => {
        ElMessage.success('修改成功')
        editIngredientDialogVisible.value = false
        getPageIngredients()
      })
    }
    // 删除某一行食材数据
    const deleteIngredient = (id) => {
      console.log(id)
      ingredientApi.removeIngredientById(id).then(res => {
        ElMessage.success('移除成功')
        getPageIngredients()
      })
    }
    // 对话框弹出控制变量
    const editIngredientDialogVisible = ref(false)
    // 移除文件触发的方法
    const removeSignalImage = (file, fileList) => {
      oss.removeImage(editIngredientRow.imageUrl).then(result => {
      })
    }
    // 图片上传成功触发的方法
    const signalUploadSuccess = (data, fileList) => {
      editIngredientRow.imageUrl = data.data
      fileList[0].url = data.data
    }
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
    // 获取所有的食材分页数据
    const getPageIngredients = () => {
      ingredientApi.getIngredients(pageIngredients.currentPage, pageIngredients.pageSize).then(res => {
        pageIngredients.records = []
        let data = res.data
        pageIngredients.currentPage = data.current
        pageIngredients.total = data.total
        // 将日期进行格式化
        data.records.map(item => {
          item.createTime = dayjs(item.createTime).format('YYYY-MM-DD')
          pageIngredients.records.push(item)
        })
      })
    }
    const handleSizeChange = (val) => {
      console.log(`${val} items per page`)
    }
    const handleCurrentChange = (val) => {
      pageIngredients.currentPage = val
      getPageIngredients()
    }
    getPageIngredients()
    return {
      pageIngredients,
      editIngredientDialogVisible,
      editIngredientRow,
      fileList,
      nutritional,
      deleteIngredient,
      onAddItem,
      deleteRow,
      signalUploadSuccess,
      removeSignalImage,
      saveEditIngredient,
      handleCurrentChange,
      handleSizeChange,
      editIngredient
    }
  }
}
</script>

<style scoped lang="less">
// 文字超出两行截断显示
.text_overflow {
  display: inline-block;
  white-space: nowrap;
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
