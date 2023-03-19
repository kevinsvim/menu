<template>
  <div class="menu-details">
    <div class="demo-image__lazy">
      <el-image style="border-radius: 10px" :src="url" lazy :fit="'fill'"/>
    </div>
    <div class="main_title">
      <span>酸甜可口的菠萝咕咾肉</span>
    </div>
    <div>
      <el-row>
        <el-col :span="2">
          <div class="textStyle"><span>精品</span></div>
        </el-col>
        <el-col :span="4">
          <div class="textStyle" style="background: #FF4C44"><span>独家</span></div>
        </el-col>
      </el-row>
    </div>
    <div class="middle">
      <div class="up_collect">
        <div style="flex: 1; text-align: left; align-items: center; display: flex">
          <span class="up">19.6万</span>
          <span class="upSpan">点赞</span>
        </div>
        <div style="flex: 1; text-align: left; align-items: center; display: flex; margin-left: 40px">
          <span class="up">2500</span>
          <span class="upSpan">收藏</span>
        </div>
        <div style="flex: 5; display: flex; justify-content: right">
          <el-button type="warning">
            <el-icon color="#FFFFF" size="18">
              <StarFilled/>
            </el-icon>
            <span style="margin-top: 3px">收藏</span></el-button>
        </div>
      </div>
      <div style="display: flex; align-items: center; margin-top: 20px">
        <div>
          <el-avatar
            :fit="'fill'"
            src="https://online-en.oss-cn-beijing.aliyuncs.com/canvas66.png"
          />
        </div>
        <div style="flex: 1">
          <span>熊猫半糖</span>
        </div>
        <div style="flex: 1">
          <el-button>
            <el-icon :size="12" :color="'black'">
              <Plus/>
            </el-icon>
            <span style="margin-top: 3px">关注</span>
          </el-button>
        </div>
        <div style="flex: 5"></div>
      </div>
      <div style="margin-top: 40px">
        <span>菠萝咕咾肉是广东的一道特色传统名菜，属于粤菜系，由菠萝和猪肉煮成。它作为欧美人士最熟悉的中国菜之一，最常见于国外的唐人街餐馆。 据悉，咕咾肉始于清朝，当时的广州市有许多外国人都非常喜欢食用中国菜，尤其喜欢酸甜口味的糖醋排骨。但由于外国友人使用刀叉吃饭不习惯吐骨头，广东厨师便运用剔骨的精肉加上调料制成肉圆，入锅油炸至酥脆，淋上糖醋汁，因味道酸甜可口，受到大家的喜爱，便流传开来。</span>
      </div>
    </div>
    <div class="menu_material">
      <div style="text-align: left">
        <span class="child_title">秘制大拌菜的用料</span>
      </div>
      <!-- 材料 -->
      <div>
        <el-table
          ref="singleTableRef"
          :data="tableData"
          highlight-current-row
          style="width: 100%"
          @current-change="handleCurrentChange"
        >
          <el-table-column type="index" width="250"/>
          <el-table-column property="name" label="Material" width="300"/>
          <el-table-column property="value" label="Dosage"/>
        </el-table>
      </div>
    </div>
    <div class="menu_step">
      <div style="text-align: left">
        <span class="child_title">秘制大拌菜的做法</span>
      </div>
      <div style="display: flex; margin-top: 20px; text-align: left" v-for="(item,index) in steps">
        <div style="flex: 1;">
          <el-image class="image_form"
                    :src="'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg'" :fit="'fill'"/>
        </div>
        <div style="flex: 2.5">
          <div>
            <span style="font-weight: bold; font-size: 16px; line-height: 30px">步骤{{index + 1}}</span>
          </div>
          <div>
            <span class="content_text">
              里脊肉切成小块，大约1-1.5厘米左右、用水清洗干净、加入料酒，少许盐腌制10-15分钟
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="cook_skill">
      <div>
        <span class="child_title">菠萝咕咾肉的烹饪技巧</span>
      </div>
      <div>
        <span class="content_text">做菜好吃都有技巧，我的每道菜都有小妙招，大家搜索“豆果”可以直接查看我的菜谱！</span>
      </div>
    </div>
  </div>
</template>

<script>

import { reactive, ref } from 'vue'
import MenuTable from '@/components/detail/MenuTable'
import { StarFilled } from '@element-plus/icons-vue'
import { Plus } from '@element-plus/icons'

export default {
  name: 'MenuDetails',
  components: {
    Plus,
    MenuTable,
    StarFilled
  },
  setup () {
    const url = 'https://menu-api.oss-cn-beijing.aliyuncs.com/dish/home_cooking/gulu.jpeg'

    const tableData = reactive([
      {
        name: '名称',
        value: 'tester',
      },
      {
        name: '创建人2',
        value: '张3',
      },
      {
        name: '创建人',
        value: '张三4',
      }, {
        name: '创建人s',
        value: '张三',
      },

    ])
    let steps = reactive([
      {
        imageUrl: '',
        content: ''
      },
      {

      },
      {

      }
    ])
    return {
      url,
      tableData,
      steps,
    }
  }
}
</script>

<style scoped lang="scss">
.menu-details {
  width: 50%;
  margin-left: 15vw;
  padding-top: 20px;
  padding-bottom: 20px;
}

.demo-image__lazy {
  border-radius: 10px;
  height: 400px;
  margin-bottom: 50px;
}

.demo-image__lazy .el-image {
  display: block;
  min-height: 200px;
  margin-bottom: 10px;
}

.demo-image__lazy .el-image:last-child {
  margin-bottom: 0;
}

.main_title {
  margin-top: 20px;
  margin-bottom: 20px;
  text-align: left;

  span {
    font-weight: bold;
    font-size: 23px;
  }
}

.textStyle {
  width: 50px;
  height: 25px;
  background: #FFB31A;
  border-radius: 5px;
  display: flex;
  justify-content: center;
  align-items: center;

  span {
    color: #FFFFFF;
    font-size: 14px;
  }
}

.middle {
  margin-top: 20px;
  margin-bottom: 20px;

  .up_collect {
    display: flex;

    .up {
      font-size: 24px;
      color: #FFB31A;
    }

    .upSpan {
      font-size: 13px;
      margin-left: 4px;
    }
  }
}

.menu_material {
  margin-top: 60px;
  margin-bottom: 60px;

}

.child_title {
  font-weight: bold;
  font-size: 20px;
}

.image_form {
  width: 200px;
  height: 200px;
  border-radius: 8px;
}
.content_text {
  line-height: 30px;
  font-size: 15px;
}
.cook_skill {
  margin-top: 30px;
  display: flex;
  justify-content: flex-start;
}
</style>
