<template>
  <div class="detail_home">
    <el-row>
      <el-col :span="16">
        <el-carousel
          ref="noteCarouselRef"
          :interval="3000"
          arrow="always"
          height="60vh"
          @change="handleChange"
          style="width: 46vw; border-radius: 10px"
        >
          <el-carousel-item v-for="(url, index) in noteDetail.images" :key="index">
            <el-image
              style="width: 100%; height: 100%"
              :src="url"
              :fit="'cover'"></el-image>
          </el-carousel-item>
        </el-carousel>
        <el-row justify="center" style="margin-top: 20px">
          <el-col :span="3" v-for="(url, index) in noteDetail.images" :key="index">
            <el-image
              @click="() => handleClickImg(index)"
              :style="sizeStyle[index]"
              style="border-radius: 10px"
              autoplay
              interval
              :src="url"
              :fit="'cover'"
            ></el-image>
          </el-col>
        </el-row>
        <div style="margin-top: 30px; text-align: left">
          <span style="font-size: 1.5rem; font-weight: bold">
            {{ noteDetail.title }}
          </span>
        </div>
        <div style="margin-top: 20px; margin-bottom: 30px; display: flex; align-items: center;">
          <div style="flex: 1; text-align: left">
            <el-avatar :size="40"
                       :src="'https://cp1.douguo.com/upload/note/d/4/2/750_d43fed7ae63f206f6c73bebe35570292.jpg'"/>
          </div>
          <div style="flex: 2; text-align: left">
            <span style="font-size: 13px;">{{ noteDetail.publisherName }}</span>
          </div>
          <div style="flex: 2;">
            <el-button v-if="!noteDetail.isFollow" @click="() => follow(1)">
              <iconfont-svg icon="icon-jiahao"/>
              <span>关注</span>
            </el-button>
            <el-button v-else @click="() => follow(0)">
              <iconfont-svg icon="icon-yiguanzhu"/>
              <span>已关注</span>
            </el-button>

          </div>
          <div style="flex: 4"></div>
          <div style="flex: 2">
            <el-button style="border-color: rgb(255, 179, 26)" v-if="!noteDetail.isUp" @click="() => updateUpState(1)">
              <iconfont-svg icon="icon-dianzan1" size="20"/>
              <span style="color: rgb(255, 179, 26); margin-left: 2px">{{ noteDetail.upNum }}</span>
            </el-button>
            <el-button style="border-color: rgb(255, 179, 26)" v-else @click="() => updateUpState(0)">
              <iconfont-svg icon="icon-dianzan" size="20"/>
              <span style="color: rgb(255, 179, 26); margin-left: 2px">{{ noteDetail.upNum }}</span>
            </el-button>
          </div>
          <div style="flex: 2">
            <el-button type="warning" color="rgb(255, 179, 26)" v-if="!noteDetail.isCollect" @click="() => collect(1)">
              <iconfont-svg icon="icon-jiaxingshoucang" size="18"/>
              <span style="margin-left: 2px; color: #FFFFFF">Collect</span>
            </el-button>
            <el-button type="warning" color="rgb(255, 179, 26)" v-else @click="() => collect(0)">
              <iconfont-svg icon="icon-jiaxingshoucangtianchong" size="18"/>
              <span style="margin-left: 2px; color: #FFFFFF">Collect</span>
            </el-button>
          </div>
        </div>
        <div style="text-align: left">
          <span>
            {{ noteDetail.intro }}
          </span>
        </div>
        <div style="margin-top: 15px; text-align: left">
          <span style="color: #999; font-size: 13px;">
            创建时间:{{ noteDetail.publisherDate }} · 北京市
          </span>
        </div>
        <!-- 评论 -->
        <div class="comment">
          <div class="text_left">
            <span class="child_title">评论</span>
          </div>
          <div style="margin-top: 20px; display: flex;">
            <div style="flex: 1; text-align: left" v-if="noteDetail.currentUserAvatar !== ''">
              <el-avatar :size="'default'" :src="noteDetail.currentUserAvatar"></el-avatar>
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
          <div style="margin-top: 20px; display: flex" v-for="comment in noteDetail.comments">
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
                <el-divider/>
              </div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
  <MenuFooter/>
</template>

<script>
import {getCurrentInstance, ref, reactive} from 'vue'
import {HOME_CONSTANT, MENU_CONSTANT, NOTE_EVENT, NOTE_CONSTANT} from "@/utils/nav";
import {useRouter, useRoute} from "vue-router/dist/vue-router";
import {Plus, Star, Search} from '@element-plus/icons-vue'
import IconfontSvg from "@/components/iconfonts/IconSvg";
import noteApi from '@/api/note'
import resourceApi from '@/api/resource'
import {ElMessage, ElMessageBox} from "element-plus";
import {userStore} from "@/store/user";
import noteCommentApi from '@/api/noteComment'
import MenuFooter from '@/views/footer/FoodFooter'

export default {
  name: "NoteDetail",
  components: {
    IconfontSvg,
    Plus,
    Star,
    Search,
    MenuFooter
  },
  setup() {
    const router = useRouter()
    const routes = useRoute()
    const uStore = userStore();
    const noteDetail = reactive({
      id: '',
      isFollow: false,
      isUp: false,
      isCollect: false,
      currentUserAvatar: '',
      images: [],
      comments: [],
      upNum: 0,
      avatar: '',
      title: '',
      publisherId: '',
      publisherName: '',
      publisherDate: '',
      intro: ''
    })
    const commentBtnDisabled = ref(false)
    const content = ref('')
    // 获取到 全局事件总线
    const {Bus} = getCurrentInstance().appContext.config.globalProperties

    const noteCarouselRef = ref();
    const sizeStyle = reactive([])
    const selectStateIndex = ref(0)
    const initNoteDetail = () => {
      noteApi.getNoteDetail(noteDetail.id).then(res => {
        console.log(res)
        let data = res.data
        noteDetail.isFollow = data.isFollow
        noteDetail.isUp = data.isUp
        noteDetail.isCollect = data.isCollect
        noteDetail.upNum = data.upNum
        noteDetail.images.push(...data.images.split(','))
        noteDetail.comments.push(...data.noteComments)
        noteDetail.isFollow = data.isFollow
        noteDetail.avatar = data.imageUrl
        noteDetail.title = data.title
        noteDetail.publisherId = data.publishId
        noteDetail.publisherName = data.username
        noteDetail.publisherDate = data.publisherDate
        noteDetail.intro = data.intro
      })
    }
    if (routes.query.id) {
      noteDetail.id = routes.query.id
      initNoteDetail()

      sizeStyle.push({width: 90 + 'px', height: 90 + 'px'})
      for (let i = 1; i < noteDetail.images.length; i++) {
        sizeStyle.push({width: 70 + 'px', height: 70 + 'px'})
      }
      // 查询详情数据
    } else {
      // 重定向到404
      router.push('/404')
    }
    // 修改导航栏的状态
    const updateSelectState = () => {
      localStorage.setItem(HOME_CONSTANT, 'unselect')
      localStorage.setItem(MENU_CONSTANT, 'unselect')
      localStorage.setItem(NOTE_CONSTANT, 'select')

      // 通知导航栏组件刷新选中状态
      Bus.emit(NOTE_EVENT, {state: true})
    }
    updateSelectState()

    // 处理点击图片时将其放大
    const handleClickImg = (index) => {
      // 将选中的图片放大
      sizeStyle[index] = {width: 90 + 'px', height: 90 + 'px'}
      // 将上一个放到的图片恢复
      sizeStyle[selectStateIndex.value] = {width: 70 + 'px', height: 70 + 'px'}
      // 记录当前放大的图片索引
      selectStateIndex.value = index
      noteCarouselRef.value.setActiveItem(index)
    }
    const handleChange = (activeIndex, originIndex) => {
      selectStateIndex.value = originIndex
      handleClickImg(activeIndex)
    }
    // 关注与取消关注
    const follow = (type) => {
      noteDetail.isFollow = !noteDetail.isFollow
      console.log(noteDetail.publisherId)
      resourceApi.follow(noteDetail.publisherId, type).then(res => {
      }).catch(error => {
        noteDetail.isFollow = !noteDetail.isFollow
      })
    }
    // 点赞与取消点赞
    const updateUpState = (status) => {
      console.log('状态:', status)
      noteDetail.isUp = !noteDetail.isUp
      resourceApi.noteUp(status, noteDetail.id).then(res => {
      }).catch(error => {
        noteDetail.isUp = !noteDetail.isUp
      })
    }
    // 收藏与取消收藏
    const collect = (type) => {
      // 判断用户是否登录
      if (uStore.isLogin()) {
        // 先进行页面变化
        noteDetail.isCollect = !noteDetail.isCollect
        resourceApi.noteCollect(type, noteDetail.id, uStore.getId()).then(res => {
        }).catch(error => {
          // 修改失败，则恢复原样
          noteDetail.isCollect = !noteDetail.isCollect
        })
      }
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
        noteCommentApi.addComment(content.value, noteDetail.id).then(res => {
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
      ElMessageBox.confirm(
        '您确定要删除该评论吗?',
        'Warning',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
        // 先移除该评论
        noteDetail.comments.map((element, index) => {
          if (element.id === commentId) {
            noteDetail.comments.splice(index, 1)
          }
        })
        // 删除该评论
        noteCommentApi.deleteCommentById(commentId).then(res => {
        })
      })
    }
    return {
      noteCarouselRef,
      noteDetail,
      sizeStyle,
      content,
      commentBtnDisabled,
      handleClickImg,
      handleChange,
      follow,
      updateUpState,
      collect,
      publishComment,
      removeComment
    }
  }
}
</script>

<style scoped lang="less">
.comment {
  margin-top: 30px;
}

.text_left {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.child_title {
  font-weight: bold;
  font-size: 20px;
}

.detail_home {
  margin: 10px 15vw 40px;
}

:deep(.el-carousel__arrow ) {
  background-color: #F1F7FA !important;
  height: 60px !important;
  width: 60px !important;
}

:deep(.el-icon) {
  font-size: 40px !important;
  color: #475669 !important;
}
</style>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
