<template>
  <div class="menu-details">
    <div class="demo-image__lazy">
      <el-image style="border-radius: 10px; height: 420px" :src="detail.imageUrl" lazy :fit="'fill'"/>
    </div>
    <div class="main_title">
      <span>{{ detail.name }}</span>
    </div>
    <div>
      <el-row>
        <el-col :span="2">
          <div class="textStyle"><span>精品</span></div>
        </el-col>
        <el-col :span="4" v-if="detail.isSole">
          <div class="textStyle" style="background: #FF4C44"><span>独家</span></div>
        </el-col>
      </el-row>
    </div>
    <div class="middle">
      <div class="up_collect">
        <div style="flex: 1; text-align: left; align-items: center; display: flex">
          <span class="up">{{ detail.upNum }}</span>
          <span class="upSpan">点赞</span>
        </div>
        <div style="flex: 1; text-align: left; align-items: center; display: flex; margin-left: 40px">
          <span class="up" style="margin-top: 3px">{{ detail.collectNum }}</span>
          <span class="upSpan">收藏</span>
        </div>
        <div v-if="!detail.isCollect"  style="flex: 5; display: flex; justify-content: right">
          <el-button type="warning" @click="() => collect(1)">
            <el-icon color="#FFFFF" size="18">
              <StarFilled/>
            </el-icon>
            <span style="margin-top: 3px">收藏</span></el-button>
        </div>
        <div v-if="detail.isCollect"  style="flex: 5; display: flex; justify-content: right">
          <el-button type="warning" @click="() => collect(0)">
            <el-icon color="#FFFFF" size="18">
              <Select/>
            </el-icon>
            <span style="margin-top: 3px">已收藏</span></el-button>
        </div>
      </div>
      <div style="display: flex; align-items: center; margin-top: 20px">
        <div>
          <el-avatar
            :fit="'fill'"
            :src="detail.avatar"
          />
        </div>
        <div style="flex: 1">
          <span>熊猫半糖</span>
        </div>
        <div v-if="!detail.isHiddenFollow && !detail.isFollow" style="flex: 1">
          <el-button @click="() => follow(1)">
            <el-icon :size="12" :color="'black'">
              <Plus/>
            </el-icon>
            <span style="margin-top: 3px">关注</span>
          </el-button>
        </div>
        <div v-if="!detail.isHiddenFollow && detail.isFollow" style="flex: 1">
          <el-button @click="() => follow(0)">
            <el-icon :size="12" :color="'black'">
              <Select/>
            </el-icon>
            <span style="margin-top: 3px">已关注</span>
          </el-button>
        </div>
        <div style="flex: 5"></div>
      </div>
      <div style="margin-top: 40px">
        <span>{{ detail.description }}</span>
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
          :data="detail.materials"
          highlight-current-row
          style="width: 100%"
        >
          <el-table-column type="index" width="250"/>
          <el-table-column property="name" label="Material" width="300"/>
          <el-table-column property="dosage" label="Dosage"/>
        </el-table>
      </div>
    </div>
    <div class="menu_step">
      <div style="text-align: left">
        <span class="child_title">秘制大拌菜的做法</span>
      </div>
      <div style="display: flex; margin-top: 20px; text-align: left" v-for="(item,index) in detail.steps">
        <div style="flex: 1;">
          <el-image class="image_form"
                    :src="item.imageUrl" :fit="'fill'"/>
        </div>
        <div style="flex: 2.5">
          <div>
            <span style="font-weight: bold; font-size: 16px; line-height: 30px">步骤{{ index + 1 }}</span>
          </div>
          <div>
            <span class="content_text">
              {{ item.content }}
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
        <span class="content_text">{{ detail.skill }}</span>
      </div>
    </div>
    <div class="text_left">
      <span class="span_style1">©本菜谱的做法由 <span
        style="color: deepskyblue">{{ detail.publisher }}</span> 编写，未经授权不得转载</span>
    </div>

    <!-- 点赞 -->
    <div style="margin-top: 40px; margin-bottom: 40px">
      <el-button v-if="!detail.isUp" type="warning" plain color="#FFB31A" @click="updateUpState(1)" style="width: 100px">
        <svg t="1679207238822" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="51920" width="20" height="20"><path d="M523.048421 948.170105L200.919579 626.041263a35.031579 35.031579 0 1 1 49.55621-49.55621L523.048421 849.057684l343.363368-343.363368a175.292632 175.292632 0 1 0-247.915789-247.91579l-67.368421 67.368421a35.031579 35.031579 0 0 1-49.556211-49.55621l67.368421-67.368421a245.301895 245.301895 0 0 1 346.893474 346.920421z" fill="#83D2EA" p-id="51921"></path><path d="M519.491368 948.170105L126.571789 555.250526A245.301895 245.301895 0 0 1 473.492211 208.330105l70.790736 70.790737a35.031579 35.031579 0 0 1-49.475368 49.529263l-70.790737-70.790737a175.292632 175.292632 0 1 0-247.915789 247.91579l343.363368 343.363368 272.599579-272.572631a35.031579 35.031579 0 0 1 49.556211 49.55621z" fill="#208BB5" p-id="51922"></path></svg>
        <span style="margin-left: 7px">
            点个赞
        </span>
      </el-button>
      <el-button v-if="detail.isUp" type="warning" plain color="#FFB31A" @click="updateUpState(0)" style="width: 100px">
        <svg t="1679207372833" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="52076" width="20" height="20"><path d="M510.671749 348.792894S340.102978 48.827055 134.243447 254.685563C-97.636714 486.565724 510.671749 913.435858 510.671749 913.435858s616.107079-419.070494 376.428301-658.749272c-194.095603-194.096626-376.428302 94.106308-376.428301 94.106308z" fill="#FF713C" p-id="52077"></path><path d="M510.666632 929.674705c-3.267417 0-6.534833-0.983397-9.326413-2.950192-16.924461-11.872399-414.71121-293.557896-435.220312-529.448394-5.170766-59.482743 13.879102-111.319341 56.643068-154.075121 51.043536-51.043536 104.911398-76.930113 160.095231-76.930114 112.524796 0 196.878996 106.48115 228.475622 153.195078 33.611515-45.214784 122.406864-148.20646 234.04343-148.20646 53.930283 0 105.46603 24.205285 153.210428 71.941496 45.063335 45.063335 64.954361 99.200326 59.133795 160.920016C935.306982 641.685641 536.758893 915.327952 519.80271 926.859589a16.205077 16.205077 0 0 1-9.136078 2.815116zM282.857183 198.75574c-46.25344 0-92.396363 22.682605-137.127124 67.413365-36.149315 36.157501-51.614541 78.120218-47.25321 128.291898 17.575284 202.089671 352.199481 455.119525 412.332023 499.049037 60.434417-42.86732 395.406538-289.147446 414.567947-492.458945 4.933359-52.344159-11.341303-96.465029-49.759288-134.88199-41.431621-41.423435-85.24243-62.424748-130.242319-62.424748-122.041544 0-220.005716 152.203494-220.989114 153.742547-3.045359 4.806469-8.53335 7.883551-14.101159 7.534603a16.257266 16.257266 0 0 1-13.736863-8.184403c-0.902556-1.587148-91.569532-158.081365-213.690893-158.081364z" fill="#885F44" p-id="52078"></path></svg>
        <span style="margin-left: 7px">
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
        <div style="flex: 1; text-align: left" v-if="detail.currentUserAvatar !== ''">
          <el-avatar :size="'default'" :src="detail.currentUserAvatar"></el-avatar>
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
        <el-button type="primary" @click="publishComment" :disabled="commentBtnDisabled">
          发布评论
          <iconfont-svg icon="icon-liuyan" size="25"/>
        </el-button>
      </div>
      <!-- 评论内容 -->
      <div style="margin-top: 20px; display: flex" v-for="comment in detail.comments">
        <div style="flex: 1; text-align: left">
          <el-avatar :size="'default'" :src="comment.imageUrl"></el-avatar>
        </div>
        <div style="flex: 12; text-align: left">
          <div style="margin-top: 8px">
            <span style="font-size: 13px; line-height: 30px">{{ comment.username }}</span>
          </div>
          <div>
            <span style="line-height: 40px; font-size: 15px">{{ comment.content }}</span>
          </div>
          <div style="display: flex">
            <div style="flex: 1; justify-content: flex-start">
              <span style="line-height: 30px; font-size: 13px; color: #999">{{ comment.publishDate }}</span>
            </div>
            <div style="cursor:pointer;">
              <el-button text @click="() => removeComment(comment.id)">
                <span style="font-size: 13px; color: #256DC6;">删除</span>
              </el-button>
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

import {getCurrentInstance, reactive, ref} from 'vue'
import { StarFilled, Check } from '@element-plus/icons-vue'
import {Plus, Select, Star} from '@element-plus/icons'
import MenuFooter from '@/views/footer/FoodFooter'
import NavigationBar from '@/views/pages/nav/NavigationBar'
import { useRoute, useRouter } from 'vue-router'
import resource from "@/api/resource";
import {ElMessage, ElMessageBox} from "element-plus";
import { userStore } from "@/store/user";
import IconfontSvg from "@/components/iconfonts/IconSvg";
import comment from '@/api/comment'
import {MENU_CONSTANT, MENU_EVENT} from "@/utils/nav";
export default {
  name: 'MenuDetails',
  components: {
    IconfontSvg,
    Star,
    MenuFooter,
    Plus,
    StarFilled,
    Comment,
    Check,
    NavigationBar,
    Select
  },
  setup () {
    const route = useRoute()
    const router = useRouter()
    const uStore = userStore();
    /**
     * 详情页面数据
     */
    const detail = reactive({
      // 菜谱id
      id: '',
      // 菜谱名
      name: '',
      // 菜谱主图片
      imageUrl: '',
      // 是否独家
      isSole: false,
      // 菜谱发布日期
      publishDate: '',
      // 点赞数量
      upNum: 0,
      // 收藏数量
      collectNum: 0,
      // 发布者头像
      avatar: '',
      // 当前登录用户是否关注该发布者
      isFollow: false,
      // 菜谱描述
      description: '',
      // 材料
      materials: [],
      // 步骤
      steps: [],
      // 菜谱技巧
      skill: '',
      // 发布者用户名
      publisher: '',
      // 发布者id
      publisherId: '',
      // 当前用户是否点赞该菜谱
      isUp: false,
      // 当前用户是否收藏该菜谱
      isCollect: false,
      // 当前登录用户头像
      currentUserAvatar: '',
      // 当前菜谱的所有评论
      comments: [],
      // 是否隐藏关注
      isHiddenFollow: false,
    })
    // 用户评论内容
    const content = ref('')
    // 发布按钮是否禁止
    const commentBtnDisabled = ref(false)
    // 根据id获取详细信息
    const getDishDetailById = () => {
      let id = route.query.id
      if (id) {
        resource.getDetailByDishId(id).then(res => {
          // 数据赋值
          let data = res.data
          detail.id = data.id
          detail.name = data.name
          detail.imageUrl = data.imageUrl
          detail.isSole = data.isSole
          detail.upNum = data.upNum
          detail.collectNum = data.collectNum
          detail.avatar = data.avatar
          detail.isFollow = data.isFollow
          detail.description = data.description
          detail.materials.push(...data.materials)
          detail.steps.push(...data.steps)
          detail.skill = data.skill
          detail.publisher = data.username
          detail.publishDate = data.publishDate
          detail.isUp = data.isUp
          detail.isCollect = data.isCollect
          detail.publisherId = data.userId
          detail.comments.push(...data.comments)
          detail.currentUserAvatar = uStore.getImageUrl()
          // 判断当前是否是当前登录用户发布的菜谱,如果是则隐藏关注这个功能
          if (uStore.getId() === detail.publisherId) {
            detail.isHiddenFollow = true
          }
        }).catch(error => {
          ElMessage.error('出错了~ 请稍后再试!')
        })
      }
    }
    getDishDetailById()

    // 获取到 全局事件总线
    const { Bus }  = getCurrentInstance().appContext.config.globalProperties
    // 修改导航栏的状态
    const updateSelectState = () => {
      localStorage.setItem('navSelect', MENU_CONSTANT)
      // 通知导航栏组件刷新选中状态
      Bus.emit(MENU_EVENT, { state: true })
    }
    updateSelectState()
    // 收藏与取消收藏
    const collect = (type) => {
      // 判断用户是否登录
      if (uStore.isLogin()) {
        // 先进行页面变化
        detail.isCollect = !detail.isCollect
        resource.collect(type, detail.id, uStore.getId()).then(res => {
        }).catch(error => {
          // 修改失败，则恢复原样
          detail.isCollect = !detail.isCollect
        })
      }
    }
    // 关注与取消关注
    const follow = (type) => {
      detail.isFollow = !detail.isFollow
      resource.follow(detail.publisherId, type).then(res => {

      }).catch(error => {
        detail.isFollow = !detail.isFollow
      })
    }
    // 点赞与取消点赞
    const updateUpState = (status) => {
      detail.isUp = !detail.isUp
      resource.up(status, detail.id).then(res => {
      }).catch(error => {
        detail.isUp = !detail.isUp
      })
    }
    // 发表评论
    const publishComment = () => {
      // 发布按钮禁止
      commentBtnDisabled.value = true
      // 如果没登录，则跳转到登录页面
      if (!uStore.isLogin()) {
        router.push('/')
      } else if (content.value === '') {
        ElMessage.info('您输入的内容为空~')
      } else {
        // 保存评论
        comment.addComment(content.value, detail.id).then(res => {
          content.value = ''
          ElMessage.success('发布成功,审核后显示~')
          commentBtnDisabled.value = false
        }).catch(error => {
          commentBtnDisabled.value = false
        })
      }
    }
    // 移除评论
    const removeComment = (commentId) => {
      // 先移除该评论
      detail.comments.map((element, index) => {
        if (element.id === commentId) {
          detail.comments.splice(index, 1)
        }
      })
      ElMessageBox.confirm(
        '您确定要删除该评论吗?',
        'Warning',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
        // 删除该评论
        comment.deleteCommentById(commentId).then(res => {
        })
      })
    }
    return {
      content,
      detail,
      commentBtnDisabled,
      updateUpState,
      collect,
      follow,
      publishComment,
      removeComment
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
