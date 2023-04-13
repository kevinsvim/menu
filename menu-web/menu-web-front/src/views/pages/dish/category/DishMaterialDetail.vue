<template>
  <div class="material_home">
    <div class="intro">
      <div>
        <span class="title">{{ ingredient.name }}</span>
      </div>
      <div>
        <span class="other_variable">别名：{{ ingredient.alias }}</span>
      </div>
      <div>
        <span class="other_variable">热量：{{ ingredient.caloric }}</span>
      </div>
      <div>
        <span class="other_variable">适宜人群：{{ ingredient.suitable }}</span>
      </div>
      <div style="display: flex; align-items: center">
        <span class="other_variable">它的图片:</span>
        <el-image class="image" :src="ingredient.imageUrl" :fit="'fill'"/>
      </div>
    </div>
    <el-divider/>
    <div style="margin-top: 20px">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="营养功效" name="effect">
          <div class="effect">
            <!-- 介绍 -->
            <div>
              <div>
                <span class="sub_title">{{ ingredient.name }}介绍</span>
              </div>
              <div>
                <p>
                  {{ ingredient.intro }}
                </p>
              </div>
            </div>
            <el-divider/>
            <!-- 营养成分 -->
            <div style="margin-top: 20px">
              <div>
                <span class="sub_title">{{ ingredient.name }}营养成分(营养素含量/100克)</span>
              </div>
              <div style="margin-top: 20px">
                <el-table
                  ref="singleTableRef"
                  :data="ingredient.nutritional"
                  highlight-current-row
                  style="width: 100%"
                >
                  <el-table-column type="index" width="250"/>
                  <el-table-column property="name" label="Material" width="300"/>
                  <el-table-column property="dosage" label="Dosage"/>
                </el-table>
              </div>
            </div>
            <el-divider/>
            <!-- 功效与作用 -->
            <div style="margin-top: 20px">
              <div>
                <span class="sub_title">{{ ingredient.name }}的功效与作用</span>
              </div>
              <div>
                <p>
                  {{ ingredient.effect }}
                </p>
              </div>
            </div>
            <el-divider/>
            <!-- 营养价值 -->
            <div style="margin-top: 20px">
              <div>
                <span class="sub_title">{{ ingredient.name }}的功效与作用</span>
              </div>
              <div>
                <p>
                  {{ ingredient.nutritiveValue }}
                </p>
              </div>
            </div>
            <el-divider/>
            <!-- 挑选方式 -->
            <div style="margin-top: 20px">
              <div>
                <span class="sub_title">如何挑选{{ ingredient.name }}</span>
              </div>
              <div>
                <p>
                  {{ ingredient.pick }}
                </p>
              </div>
            </div>
            <el-divider/>
            <!-- 存储方式 -->
            <div style="margin-top: 20px">
              <div>
                <span class="sub_title">{{ ingredient.name }}的存储方式</span>
              </div>
              <div>
                <p>
                  {{ ingredient.storage }}
                </p>
              </div>
            </div>
            <el-divider/>
          </div>
          <div>
            <span style="font-size: 12px;">
              你当前的位置：豆果美食 > 菜谱 > 食材 > 营养功效
            </span>
          </div>
        </el-tab-pane>
        <el-tab-pane label="食物相克" name="restrictionFood">
          <div style="margin-top: 30px">
            <div>
              <div style="display: flex; align-items: center">
                <iconfont-svg icon="icon-jinggao" size="30"/>
                <span style="color: red; font-size: 20px; margin-left: 4px">{{ ingredient.name }}不能和什么一起吃?</span>
              </div>
              <div>
              <span>
                {{ ingredient.restrictionFood }}
              </span>
              </div>
            </div>
            <el-divider/>
            <div>
              <div>
                <span class="sub_title">禁忌人群</span>
              </div>
              <div>
                <p>
                  {{ ingredient.tabooPopulation }}
                </p>
              </div>
            </div>
          </div>
          <el-divider/>
          <div>
            <span style="font-size: 12px;">
              你当前的位置：豆果美食 > 菜谱 > 食材 > 食物相克
            </span>
          </div>
        </el-tab-pane>
        <el-tab-pane label="食物搭配" name="foodMatching">
          <div style="margin-top: 30px">
            <div style="display: flex; align-items: center">
              <iconfont-svg icon="icon-duihao2" size="30"/>
              <span style="color: green; font-size: 20px; margin-left: 4px">{{ ingredient.name }}和什么一起吃好?</span>
            </div>
            <el-divider/>
            <div>
              <span>
                {{ ingredient.foodMatching }}
              </span>
            </div>
          </div>
          <el-divider/>
          <div>
            <span style="font-size: 12px;">
              你当前的位置：豆果美食 > 菜谱 > 食材 > 食物搭配
            </span>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

  </div>
  <FoodFooter/>
</template>

<script>
import {useRoute} from "vue-router/dist/vue-router";
import ingredientApi from "@/api/ingredient";
import {ref, reactive, onMounted, getCurrentInstance} from "vue";
import FoodFooter from "@/views/footer/FoodFooter";
import IconfontSvg from "@/components/iconfonts/IconSvg";
import {MENU_CONSTANT, MENU_EVENT} from "@/utils/nav";

export default {
  name: "DishMaterialDetail",
  components: {
    IconfontSvg,
    FoodFooter,
  },
  setup() {
    const routes = useRoute()
    const id = routes.query.id
    const activeName = ref('effect')
    const ingredient = reactive({
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
      nutritional: [],
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
    // 通知状态栏发生变更
    const {Bus} = getCurrentInstance().appContext.config.globalProperties
    localStorage.setItem('navSelect', MENU_CONSTANT)
    Bus.emit(MENU_EVENT, {state: true})

    onMounted(() => {
      // 设置默认选中值
      let select = localStorage.getItem("ingredientSelect")
      if (select) {
        activeName.value = select
      } else {
        localStorage.setItem("ingredientSelect", "effect")
      }
    })
    // 判断路由参数获取数据
    const getIngredient = () => {
      console.log('id:', id)
      if (id) {
        ingredientApi.getIngredientById(id).then(res => {
          console.log(res.data)
          let data = res.data
          ingredient.id = data.id
          ingredient.name = data.name
          ingredient.alias = data.alias
          ingredient.caloric = data.caloric
          ingredient.doing = data.doing
          ingredient.effect = data.effect
          ingredient.foodMatching = data.foodMatching
          ingredient.imageUrl = data.imageUrl
          ingredient.intro = data.intro
          let nutritionArr = data.nutritional.split('@')
          let nameArr = nutritionArr[0].split(',')
          let dosageArr = nutritionArr[1].split(',')
          for (let i = 0; i < nameArr.length; i++) {
            ingredient.nutritional.push({name: nameArr[i], dosage: dosageArr[i]})
          }
          ingredient.nutritiveValue = data.nutritiveValue
          ingredient.pick = data.pick
          ingredient.restrictionFood = data.restrictionFood
          ingredient.storage = data.storage
          ingredient.suitable = data.suitable
          ingredient.tabooPopulation = data.tabooPopulation

        })
      }
    }
    // 点击tab切换
    const handleClick = (tab, event) => {
      localStorage.setItem("ingredientSelect", tab.paneName)
    }
    getIngredient()
    return {
      ingredient,
      activeName,
      handleClick
    }
  }
}
</script>

<style scoped lang="less">
.material_home {
  padding: 20px 40vw 20px 15vw;
  text-align: left;

  .intro {
    .title {
      font-size: 20px;
      font-weight: bold;
      line-height: 35px;
    }

    .other_variable {
      font-size: 12px;
      line-height: 25px;
    }

    .image {
      width: 100px;
      height: 100px;
      border-radius: 10px;
      margin-top: 10px;
      margin-left: 10px;
    }
  }
}
.effect {
  margin-top: 15px;
}
.sub_title {
  font-size: 20px;
  font-weight: bold;
  line-height: 35px;
}
// 去掉tab标签页的底部分割线
:deep(.el-tabs__nav-wrap::after) {
  height: 0 !important;
}
</style>
