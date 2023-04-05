<template>
  <div class="custom-tree-container">
    <el-card shadow="hover">
      <el-input
          v-model="filterText"
          style="height: 50px"
          placeholder="Filter keyword"
      >
        <template #prefix>
          <iconfont-svg icon="menu-icon-sousuo" size="40"></iconfont-svg>
        </template>
      </el-input>
    </el-card>
    <!-- 按钮(添加一级分类) -->
    <div class="margin_top_10">
      <TimeButton
          :title="'添加一级分类'"
          @click="() => addCategory(1, null)"
      />
    </div>
    <el-row :gutter="20">
      <!-- 树形结构 -->
      <el-col :span="18">
        <el-card shadow="always" style="margin-top: 20px;">
          <el-scrollbar height="60vh">
            <el-tree
                ref="treeRef"
                :data="dataSource"
                node-key="id"
                default-expand-all
                :filter-node-method="filterNode"
                defaultProps
                class="category_tree margin_top_20"
            >
              <template #default="{ node, data }">
            <span class="custom-tree-node">
              <el-button link type="primary"><span style="font-size: 17px; line-height: 30px">
                  {{ data.name }}
              </span></el-button>
              <span>
                <a @click="addCategory(2, data)" v-if="node.level !== 3">
                  <svg t="1679626649357" class="icon" viewBox="0 0 1024 1024" version="1.1"
                       xmlns="http://www.w3.org/2000/svg"
                       p-id="12261" width="20" height="20"><path
                      d="M1022.293364 470.409918a511.990827 511.990827 0 1 0-551.883445 551.883446 513.484133 513.484133 0 0 0 551.883445-551.883446zM736.00516 565.341551H618.673929a53.332378 53.332378 0 0 0-53.332378 53.332378v117.331231a53.332378 53.332378 0 0 1-106.664755 0V618.673929a53.332378 53.332378 0 0 0-53.332378-53.332378h-117.331231a53.332378 53.332378 0 0 1 0-106.664756H405.344418a53.332378 53.332378 0 0 0 53.332378-53.332377v-117.331232a53.332378 53.332378 0 0 1 106.664755 0V405.344418a53.332378 53.332378 0 0 0 53.332378 53.332377h117.331231a53.332378 53.332378 0 0 1 0 106.664756z"
                      fill="#4A9FE1" p-id="12262"></path></svg>
                </a>
                <a style="margin-left: 8px" @click="remove(node, data)" v-if="node.childNodes.length === 0">
                  <svg t="1679626742913" class="icon" viewBox="0 0 1024 1024" version="1.1"
                       xmlns="http://www.w3.org/2000/svg" p-id="17748" width="20" height="20"><path
                      d="M0 0h1024v1024H0V0z"
                      fill="#202425"
                      opacity=".01"
                      p-id="17749"></path><path
                      d="M136.533333 273.066667a68.266667 68.266667 0 0 1 68.266667-68.266667h614.4a68.266667 68.266667 0 0 1 68.266667 68.266667v580.266666a136.533333 136.533333 0 0 1-136.533334 136.533334H273.066667a136.533333 136.533333 0 0 1-136.533334-136.533334V273.066667z"
                      fill="#FF7744" p-id="17750"></path><path
                      d="M409.6 34.133333a34.133333 34.133333 0 0 0-34.133333 34.133334 34.133333 34.133333 0 0 1-34.133334 34.133333H136.533333a68.266667 68.266667 0 0 0-68.266666 68.266667v68.266666a68.266667 68.266667 0 0 0 68.266666 68.266667h750.933334a68.266667 68.266667 0 0 0 68.266666-68.266667V170.666667a68.266667 68.266667 0 0 0-68.266666-68.266667h-204.8a34.133333 34.133333 0 0 1-34.133334-34.133333 34.133333 34.133333 0 0 0-34.133333-34.133334h-204.8z"
                      fill="#FFAA44" p-id="17751"></path><path
                      d="M375.466667 460.8a51.2 51.2 0 0 1 51.2 51.2v273.066667a51.2 51.2 0 0 1-102.4 0v-273.066667a51.2 51.2 0 0 1 51.2-51.2z m273.066666 0a51.2 51.2 0 0 1 51.2 51.2v273.066667a51.2 51.2 0 0 1-102.4 0v-273.066667a51.2 51.2 0 0 1 51.2-51.2z"
                      fill="#FFFFFF" p-id="17752"></path></svg>
                </a>
                <a v-else style="margin-left: 27px"></a>
              </span>
            </span>
              </template>
            </el-tree>
          </el-scrollbar>

        </el-card>
      </el-col>

      <!-- 提示     -->
      <el-col :span="6">
        <el-tooltip
            class="box-item"
            effect="dark"
            content="分类最多支持三级~"
            placement="top-start"
        >
          <span class="category_tip">
            <iconfont-svg icon="menu-icon-tishi" size="40"></iconfont-svg>
          </span>
        </el-tooltip>
      </el-col>
    </el-row>
    <!-- 分类对话框 -->
    <!-- 添加分类框 可拖拽、垂直居中、内容居中-->
    <div v-loading="loading.isCategorySaveLoading">
      <el-dialog v-model="categoryDialogVisible"
                 width="40%"
                 center
                 draggable
                 align-center
                 :show-close=false
                 style="padding-bottom: 20px"
      >
        <!--  标题、提示 -->
        <template #header>
          <div style="text-align: left">
            <el-tooltip
                class="box-item"
                effect="dark"
                content="您可以重新选择想要挂载的父分类(一级分类除外)"
                placement="top-start"
            >
              <iconfont-svg icon="menu-icon-tishi1" size="30"></iconfont-svg>
            </el-tooltip>
          </div>
          <div class="container_layout">
            <iconfont-svg icon="menu-icon-_fenleiguanli" size="30"></iconfont-svg>
            <span class="category_dialog_title">添加菜谱分类</span>
          </div>
        </template>
        <div class="category_content_body">
          <!-- 新增分类挂载的层级-->
          <div class="mounted_level" v-if="isAddOneCategory">
            <div style="flex: 1">
              <span class="tip_title">挂载的分类层级： </span>
            </div>
            <div style="flex: 3">
              <el-cascader :props="categoryOptionProps" :options="dataSource" v-model="typeId" clearable>
                <template #default="{ node, data }">
                  <span>{{ data.name }}</span>
                  <span v-if="!node.isLeaf"> ({{ data.children.length }}) </span>
                </template>
              </el-cascader>
            </div>
          </div>
          <!-- 新增的分类名 -->
          <div class="category_name">
            <div style="flex: 1">
              <span class="tip_title">请输入分类名：</span>
            </div>
            <div style="flex: 3">
              <el-input
                  v-model="categoryForm.name"
                  placeholder="Please Input"
                  style="height: 40px">
                <template #suffix>
                  <iconfont-svg icon="menu-icon-name-color" size="30"></iconfont-svg>
                </template>
              </el-input>
            </div>
          </div>
        </div>
        <template #footer>
        <span class="dialog-footer">
          <el-button
              @click="cancelSaveCategory"
              size="large"
          >
            取消</el-button>
          <el-button
              type="primary"
              @click="saveNewCategory"
              size="large"
          >
            保存
          </el-button>
        </span>
        </template>
      </el-dialog>
    </div>

  </div>
</template>

<script>
import {ref, watch, reactive} from 'vue'
import resource from '../../api/resource.js'
import TimeButton from "@/components/button/TimeButton.vue";
import IconfontSvg from "@/components/iconfonts/IconSvg.vue";
import {ElMessage, ElMessageBox} from "element-plus";
import {ElLoading} from 'element-plus'

export default {
  name: 'Update',
  components: {
    IconfontSvg,
    TimeButton
  },
  setup() {
    // 树结够名称转换
    const defaultProps = {
      children: 'children',
      label: 'name',
    }
    // 页面渲染之前调用，获取树结构数据
    const dataSource = ref([])
    const getTreeList = () => {
      resource.getTreeCategory().then(res => {
        dataSource.value.push(...res.data)
      })
    }
    getTreeList()

    // 移除某个节点
    const remove = (node, data) => {
      ElMessageBox.confirm(
          '您确定要移除该分类吗?',
          'Warning', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          }
      ).then(() => {
        // 开启loading状态
        const loadingInstance = ElLoading.service(loadingOptions);
        resource.removeCategory(node.level, data.id).then(res => {
          if (res.data) {
            removeNode(node, data)
            loadingInstance.close()
            ElMessage({
              type: 'success',
              message: '移除成功~',
            })
          } else {
            ElMessage({
              type: 'warning',
              message: '该分类下存在菜谱,无法移除!',
            })
          }
        }).catch(error => {
          ElMessage({
            type: 'error',
            message: '移除失败,请稍后再试!',
          })
        })
      })
    }
    const removeNode = (node, data) => {
      const parent = node.parent
      const children = parent.data.children || parent.data
      const index = children.findIndex((d) => d.id === data.id)
      children.splice(index, 1)
      dataSource.value = [...dataSource.value]
    }
    // 过滤查询
    const filterText = ref('')
    const treeRef = ref()

    // 监听输入框的值，进行实时的树结构数据过滤
    watch(filterText, (val) => {
      treeRef.value.filter(val)
    })
    // 过滤出符合的数据
    const filterNode = (value, data) => {
      if (!value) return true
      return data.name.toLowerCase().indexOf(value) !== -1
    }
    // 添加分类
    let typeId = ref([])
    const categoryForm = reactive({})
    const categoryDialogVisible = ref(false)
    const isAddOneCategory = ref(false)
    // const dialogEchoCategory = ref([])
    const addCategory = (type, data) => {
      // 表示添加的是一级分类，需要隐藏弹出框的级联选择器
      if (type === 1) {
        isAddOneCategory.value = false
      } else {
        isAddOneCategory.value = true
        // 数据回显
        // 如果parentId == 0则为回显一级分类数据，否者二级
        if (data.parentId === 0) {
          typeId.value = [data.id]
        } else {
          typeId.value = [data.parentId, data.id]
        }
      }
      categoryDialogVisible.value = true
    }

    // 保存新的分类
    const saveNewCategory = () => {
      // 开启加载
      const loadingInstance = ElLoading.service(loadingOptions);
      // 1.判断父分类id是否存在
      // 2.如果存在则是追加分类，否者是添加一级分类
      console.log(typeId)
      if (typeId.value.length === 0) {
        // 表明添加的是一级分类,设置父分类id
        categoryForm.parentId = 0
        // 设置层级
        categoryForm.typeLevel = 1
      } else {
        // 表明是二级三级分类
        categoryForm.parentId = typeId.value[typeId.value.length - 1]
        categoryForm.typeLevel = typeId.value.length + 1
      }
      // 保存分类
      resource.saveCategory(categoryForm).then(res => {
        refreshScope()
        loadingInstance.close()
        categoryDialogVisible.value = false
        ElMessage.success('分类添加成功')
      }).catch(error => {
        loadingInstance.close()
        ElMessage.error('分类添加失败,', error.message)
      })
    }
    // 刷新页面
    const refreshScope = () => {
      dataSource.value = []
      getTreeList()
    }
    // 取消点开的分类对话框
    const cancelSaveCategory = () => {
      // 关闭弹框
      categoryDialogVisible.value = false
      // 数据置空
      categoryForm.name = ''
      typeId.value = []
    }
    // 级联选择器转换对象
    const categoryOptionProps = {
      value: 'id',
      label: 'name',
      children: 'children',
      checkStrictly: true,
    }
    // 正在加载按钮
    const loading = reactive({
      isCategorySaveLoading: false
    })
    // loading加载配置项
    const loadingOptions = {
      text: '正在加载...',
      lock: true,
      background: 'rgba(0, 0, 0, 0.7)',
    }
    return {
      defaultProps,
      filterNode,
      remove,
      dataSource,
      filterText,
      treeRef,
      categoryDialogVisible,
      categoryForm,
      categoryOptionProps,
      addCategory,
      isAddOneCategory,
      saveNewCategory,
      typeId,
      loading,
      cancelSaveCategory
    }
  }
}


</script>

<style scoped lang="less">
.tip_title {
  letter-spacing: 1px;
}

.category_content_body {
  margin: 20px 30px;

  .mounted_level {
    display: flex;
    justify-content: flex-start;
    align-items: center;
  }

  .category_name {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    margin-top: 20px
  }
}

// 垂直居中三件套
.container_layout {
  display: flex;
  justify-content: center;
  align-items: center;
}

// 修改级联选择框的高度
:deep(.el-cascader .el-input) {
  height: 40px;
}

.category_dialog_title {
  font-size: 18px;
  font-weight: bold;
}

:deep(.el-card__body) {
  padding: 0 !important;
}

:deep(.el-tree-node__content) {
  height: 35px;
}

.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}

/* 设置树节点的字体大小和行高 */
.el-tree-node__label {
  font-size: 14px;
  line-height: 1.5;
}

.category_tip:hover {
  -webkit-animation: Glow 0.5s ease infinite alternate; //兼容Chrome
  animation: Glow 0.5s ease infinite alternate;
}

.category_tip {
  cursor: pointer;
  position: absolute;
  margin-top: 10vh;
  margin-left: 10vw;
  display: inline-block;
  width: 40px;
  height: 80px;
  color: deepskyblue;
  text-align: left;
  background: radial-gradient(currentColor 2px, transparent 8px);
  animation: spin 6s linear infinite reverse;
}

.category_tip::before,
  //.category_tip::after,
.category_tip > iconfont-svg {
  content: "";
  position: absolute;
  width: 5px;
  height: 5px;
  background-color: currentColor;
  box-shadow: 0 6px 0 -.5px, 0 12px 0 -1px, 0 18px 0 -1.5px, 0 24px 0 -2px;
  border-radius: 50%;
  offset-path: path("M20 0 L5.858 30L34.142 30Z");
  offset-rotate: auto 90deg;
  animation: motion 1.5s linear infinite, shadow .5s linear infinite alternate;
}

.category_tip::before {
  animation-delay: -.5s;
}

//.category_tip::after {
//  animation-delay: -1s;
//}

@keyframes motion {
  100% {
    offset-distance: 100%;
  }
}

@keyframes shadow {
  50% {
    box-shadow: 0 6px 0 -.5px, 0 12px 0 -1px, 0 18px 0 -1.5px, 0 24px 0 -2px;
  }
  0%, 100% {
    box-shadow: 0 0 0 -.5px, 0 0 0 -1px, 0 0 0 -1.5px, 0 0 0 -2px;
  }
}

@keyframes spin {
  100% {
    transform: rotate(360deg);
  }
}
</style>
<style>
@import "@/assets/css/common.css";
</style>
