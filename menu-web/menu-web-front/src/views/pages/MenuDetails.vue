<template>
    <NavigationBar/>
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
          <span class="up" style="margin-top: 3px">2500</span>
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
            <span style="font-weight: bold; font-size: 16px; line-height: 30px">步骤{{ index + 1 }}</span>
          </div>
          <div>
            <span class="content_text">
              里脊肉切成小块，大约1-1.5厘米左右、用水清洗干净、加入料酒，少许盐腌制10-15分钟
            </span>
          </div>
        </div>
      </div>
    </div>
    <!-- 烹饪技巧 -->
    <div class="cook_skill">
      <div>
        <span class="child_title">菠萝咕咾肉的烹饪技巧</span>
      </div>
      <div style="margin-top: 30px; margin-bottom: 30px">
        <span class="content_text">做菜好吃都有技巧，我的每道菜都有小妙招，大家搜索“豆果”可以直接查看我的菜谱！</span>
      </div>
    </div>

    <div class="text_left">
      <span class="span_style1">©本菜谱的做法由 <span
        style="color: deepskyblue">厨房笔记-fang</span> 编写，未经授权不得转载</span>
    </div>

    <!-- 点赞 -->
    <div style="margin-top: 40px; margin-bottom: 40px">
      <el-button type="warning" plain color="#FFB31A" @click="updateUpState(true)" style="width: 100px">
        <svg v-show="!isUp" t="1679207238822" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="51920" width="20" height="20"><path d="M523.048421 948.170105L200.919579 626.041263a35.031579 35.031579 0 1 1 49.55621-49.55621L523.048421 849.057684l343.363368-343.363368a175.292632 175.292632 0 1 0-247.915789-247.91579l-67.368421 67.368421a35.031579 35.031579 0 0 1-49.556211-49.55621l67.368421-67.368421a245.301895 245.301895 0 0 1 346.893474 346.920421z" fill="#83D2EA" p-id="51921"></path><path d="M519.491368 948.170105L126.571789 555.250526A245.301895 245.301895 0 0 1 473.492211 208.330105l70.790736 70.790737a35.031579 35.031579 0 0 1-49.475368 49.529263l-70.790737-70.790737a175.292632 175.292632 0 1 0-247.915789 247.91579l343.363368 343.363368 272.599579-272.572631a35.031579 35.031579 0 0 1 49.556211 49.55621z" fill="#208BB5" p-id="51922"></path></svg>
        <svg v-show="isUp" t="1679207372833" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="52076" width="20" height="20"><path d="M510.671749 348.792894S340.102978 48.827055 134.243447 254.685563C-97.636714 486.565724 510.671749 913.435858 510.671749 913.435858s616.107079-419.070494 376.428301-658.749272c-194.095603-194.096626-376.428302 94.106308-376.428301 94.106308z" fill="#FF713C" p-id="52077"></path><path d="M510.666632 929.674705c-3.267417 0-6.534833-0.983397-9.326413-2.950192-16.924461-11.872399-414.71121-293.557896-435.220312-529.448394-5.170766-59.482743 13.879102-111.319341 56.643068-154.075121 51.043536-51.043536 104.911398-76.930113 160.095231-76.930114 112.524796 0 196.878996 106.48115 228.475622 153.195078 33.611515-45.214784 122.406864-148.20646 234.04343-148.20646 53.930283 0 105.46603 24.205285 153.210428 71.941496 45.063335 45.063335 64.954361 99.200326 59.133795 160.920016C935.306982 641.685641 536.758893 915.327952 519.80271 926.859589a16.205077 16.205077 0 0 1-9.136078 2.815116zM282.857183 198.75574c-46.25344 0-92.396363 22.682605-137.127124 67.413365-36.149315 36.157501-51.614541 78.120218-47.25321 128.291898 17.575284 202.089671 352.199481 455.119525 412.332023 499.049037 60.434417-42.86732 395.406538-289.147446 414.567947-492.458945 4.933359-52.344159-11.341303-96.465029-49.759288-134.88199-41.431621-41.423435-85.24243-62.424748-130.242319-62.424748-122.041544 0-220.005716 152.203494-220.989114 153.742547-3.045359 4.806469-8.53335 7.883551-14.101159 7.534603a16.257266 16.257266 0 0 1-13.736863-8.184403c-0.902556-1.587148-91.569532-158.081365-213.690893-158.081364z" fill="#885F44" p-id="52078"></path></svg>
        <span v-show="!isUp"  style="margin-left: 7px">
            点个赞
        </span>
        <span v-show="isUp" style="margin-left: 7px">
            已点赞
        </span>
      </el-button>
    </div>
    <!-- 当前位置 -->
    <div class="text_left">
      <span class="span_style1">你当前的位置：美食吃坊 > 巨巨巨好吃✅这个做法我怎么没早点发现</span>
    </div>
    <!-- 评论 -->
    <div class="comment">
      <div class="text_left">
        <span class="child_title">评论</span>
      </div>
      <div style="margin-top: 20px; display: flex;">
        <div style="flex: 1; text-align: left">
          <el-avatar :size="'default'" :src="url"></el-avatar>
        </div>
        <div style="flex: 10; text-align: left">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="content"
            maxlength="150"
            show-word-limit
            rows="6"
          >
          </el-input>
        </div>
      </div>
      <div style="margin-top: 10px; text-align: right">
        <el-button type="primary">
          发布评论
          <svg style="margin-left: 3px" t="1679217923062" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2304" width="20" height="20"><path d="M896 85.333333C299.008 85.333333 184.661333 605.013333 137.344 882.688l-2.346667 13.866667a36.138667 36.138667 0 0 0 35.626667 42.112h8.917333a42.666667 42.666667 0 0 0 41.344-32.128c1.877333-7.381333 3.584-13.525333 5.034667-18.304 34.218667-110.378667 101.12-171.776 200.746667-184.234667 170.666667-21.333333 298.666667-170.666667 341.333333-298.666667l-64-42.666666 42.666667-42.666667c42.666667-42.666667 85.504-106.666667 149.333333-234.666667z" fill="#F64E54" p-id="2305"></path></svg>
        </el-button>
      </div>
      <!-- 评论内容 -->
      <div style="margin-top: 20px; display: flex">
        <div style="flex: 1; text-align: left">
          <el-avatar :size="'default'" :src="url"></el-avatar>
        </div>
        <div style="flex: 12; text-align: left">
          <div style="margin-top: 8px">
            <span style="font-size: 13px; line-height: 30px">李耀英</span>
          </div>
          <div>
            <span style="line-height: 40px; font-size: 15px">这得买多少鱿鱼呀,没做过</span>
          </div>
          <div style="display: flex">
            <div style="flex: 1; justify-content: flex-start">
              <span style="line-height: 30px; font-size: 13px; color: #999">1天前&nbsp;&nbsp;&nbsp;河南省</span>
            </div>
            <div style="cursor:pointer;">
              <span style="font-size: 13px; color: #256DC6;">删除</span>
            </div>
          </div>
          <div>
            <el-divider />
          </div>
        </div>
      </div>
      <div style="margin-top: 20px; display: flex">
        <div style="flex: 1; text-align: left">
          <el-avatar :size="'default'" :src="url"></el-avatar>
        </div>
        <div style="flex: 12; text-align: left">
          <div style="margin-top: 8px">
            <span style="font-size: 13px; line-height: 30px">李耀英</span>
          </div>
          <div>
            <span style="line-height: 40px; font-size: 15px">这得买多少鱿鱼呀,没做过</span>
          </div>
          <div style="display: flex">
            <div style="flex: 1; justify-content: flex-start">
              <span style="line-height: 30px; font-size: 13px; color: #999">1天前&nbsp;&nbsp;&nbsp;河南省</span>
            </div>
            <div style="cursor:pointer;">
              <span style="font-size: 13px; color: #256DC6;">删除</span>
            </div>
          </div>
          <div>
            <el-divider />
          </div>
        </div>
      </div>
    </div>
  </div>
   <MenuFooter/>
</template>

<script>

import { reactive, ref } from 'vue'
import { StarFilled, Check } from '@element-plus/icons-vue'
import { Plus, Star } from '@element-plus/icons'
import MenuFooter from '@/views/footer/FoodFooter'
import NavigationBar from '@/views/pages/NavigationBar'

export default {
  name: 'MenuDetails',
  components: {
    Star,
    MenuFooter,
    Plus,
    StarFilled,
    Comment,
    Check,
    NavigationBar
  },
  setup () {
    const url = 'https://menu-api.oss-cn-beijing.aliyuncs.com/dish/home_cooking/gulu.jpeg'
    const isUp = ref(false)
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
      {},
      {}
    ])
    const content = ref('')
    // 修改点赞状态
    const updateUpState = (status) => {
      isUp.value = !isUp.value
    }
    return {
      url,
      tableData,
      steps,
      isUp,
      updateUpState,
      content
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
  flex-flow: wrap;
}
.span_style1 {
  font-size: 13px;
  line-height: 30px;
}
.text_left {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.comment {
  margin-top: 30px;
}
</style>
