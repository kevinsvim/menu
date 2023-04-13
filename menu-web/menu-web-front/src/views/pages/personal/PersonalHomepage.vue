<template>
  <div class="person_home">
    <!-- 个人信息   -->
    <div>
      <div style="display: flex; align-items: center">
        <div style="flex: 1">
          <el-avatar :size="130" :src="personInfo.imageUrl" />
        </div>
        <div style="text-align: left; margin-left: 15px; flex: 4">
          <div style="display: flex; justify-content: space-between; align-items: center">
            <div>
              <span style="font-size: 20px; font-weight: bold; line-height: 40px">{{ personInfo.nickname || personInfo.username }} </span>
            </div>
            <div style="display: flex; align-items: center; margin-right: 5vw">
              <el-button plain>
                <iconfont-svg v-if="personInfo.sex === 1" icon="icon-jiahao"/>
                <iconfont-svg v-else icon="icon-nan"/>
                <span>关注</span>
              </el-button>
            </div>
          </div>
          <div style="display: flex;">
            <iconfont-svg icon="icon-nv" size="20"/>
            <span class="person_sub_title">{{ personInfo.address }}</span>
          </div>
          <div>
            <span class="person_sub_title" style="line-height: 30px">
              经验值： {{ personInfo.exp }}
            </span>
          </div>
          <div>
            <span class="person_sub_title">
              {{ personInfo.intro }}
            </span>
          </div>
        </div>
        <div style="flex: 3">
          <div style="height: 62px; width: 320px; background-color: rgb(241, 247, 250)">
            <el-row>
              <el-col :span="5" class="text_center">
                <div>
                  <div>
                    <span>{{ personInfo.follows }}</span>
                  </div>
                  <div>
                    <span>关注</span>
                  </div>
                </div>
              </el-col>
              <el-divider direction="vertical" style="height: 62px"/>
              <el-col :span="5" class="text_center">
                <div>
                  <div>
                    <span>{{ personInfo.fans }}</span>
                  </div>
                  <div>
                    <span>粉丝</span>
                  </div>
                </div>
              </el-col>
              <el-divider direction="vertical" style="height: 62px"/>
              <el-col :span="5" class="text_center">
                <div>
                  <div>
                    <span>{{ menu.records.length }}</span>
                  </div>
                  <div>
                    <span>菜谱</span>
                  </div>
                </div>
              </el-col>
              <el-divider direction="vertical" style="height: 62px"/>
              <el-col :span="4" class="text_center">
                <div>
                  <div>
                    <span>{{ note.records.length }}</span>
                  </div>
                  <div>
                    <span>笔记</span>
                  </div>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>
      </div>
    </div>
    <div style="margin-top: 40px">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-divider/>
        <el-tab-pane label="概览" name="overview">
          <div style="margin-top: 20px; text-align: left">
            <div>
              <div>
                <h3>发布的菜谱<span style="color: #999; font-weight: normal;">（{{ menu.records.length }}）</span></h3>
              </div>
              <div style="margin-top: 20px">
                <el-row :gutter="30">
                  <el-col :span="8" v-for="(item,index) in menu.records">
                    <MenuCard
                      v-show="index < 3"
                      style="height: 35vh"
                      :image-url="item.imageUrl"
                      :border-var="8"
                      :menu-id="item.id"
                      :width="324"
                      :height="182"
                      :collect-num="item.collectNum"
                      :view-num="item.viewNum"
                    />
                  </el-col>
                </el-row>
              </div>
            </div>
            <div style="margin-top: 40px">
              <div>
                <h3>发布的笔记<span style="color: #999; font-weight: normal">（{{ note.records.length }}）</span></h3>
              </div>
              <div>
                <el-row :gutter="30">
                  <el-col :span="6" v-for="(item, index) in note.records" style="margin-top: 20px">
                    <NoteCard
                      v-show="index < 8"
                      :image-url="item.imageUrls.split(',')[0]"
                      :up-num="item.upNum"
                      :note-id="item.id"
                      @click="toNoteDetail(item.id)"
                    />
                  </el-col>
                </el-row>
              </div>
            </div>
            <div style="margin-top: 40px">
              <div>
                <h3>收藏的菜谱<span style="color: #999; font-weight: normal">（{{ collectMenu.records.length }}）</span></h3>
              </div>
              <div style="margin-top: 20px">
                <el-row :gutter="30">
                  <el-col :span="8" v-for="(item, index) in collectMenu.records">
                    <MenuCard
                      v-show="index < 3"
                      style="height: 35vh"
                      :image-url="item.imageUrl"
                      :border-var="8"
                      :width="324"
                      :height="182"
                      :menu-id="item.id"
                      :collect-num="item.collectNum"
                      :view-num="item.viewNum"
                    />
                  </el-col>
                </el-row>
              </div>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="菜谱" name="menu">
          <div>
            <el-row :gutter="30">
              <el-col :span="8" v-for="item in menu.records">
                <MenuCard
                  style="height: 35vh"
                  :image-url="item.imageUrl"
                  :border-var="8"
                  :width="324"
                  :height="182"
                  :collect-num="item.collectNum"
                  :view-num="item.viewNum"
                  :menu-id="item.id"
                />
              </el-col>
            </el-row>
          </div>
          <div style="margin-top: 40px; margin-bottom: 50px; display: flex; justify-content: center;">
            <el-pagination
              v-model:current-page="menu.currentPage"
              v-model:page-size="menu.pageSize"
              :disabled=false
              :background=true
              layout="prev, pager, next, jumper"
              :total="menu.total"
              @size-change="handleMenuSizeChange"
              @current-change="handleMenuCurrentChange"
            />
          </div>
        </el-tab-pane>
        <el-tab-pane label="笔记" name="note">
          <div>
            <el-row :gutter="30">
              <el-col :span="6" v-for="item in note.records" style="margin-top: 20px">
                <NoteCard
                  :image-url="item.imageUrls.split(',')[0]"
                  :up-num="item.upNum"
                  @click="() => toNoteDetail(item.id)"
                />
              </el-col>
            </el-row>
          </div>
          <div style="margin-top: 40px; margin-bottom: 50px; display: flex; justify-content: center;">
            <el-pagination
              v-model:current-page="note.currentPage"
              v-model:page-size="note.pageSize"
              :disabled=false
              :background=true
              layout="prev, pager, next, jumper"
              :total="note.total"
              @size-change="handleNoteSizeChange"
              @current-change="handleNoteCurrentChange"
            />
          </div>
        </el-tab-pane>
        <el-tab-pane label="收藏" name="collect">
          <el-tabs v-model="activeTwoName" class="demo-tabs" @tab-click="handleCollectClick">
            <el-tab-pane label="菜谱" name="collectMenu">
              <div>
                <el-row :gutter="30">
                  <el-col :span="8" v-for="item in collectMenu.records">
                    <MenuCard
                      style="height: 35vh"
                      :image-url="item.imageUrl"
                      :border-var="8"
                      :width="324"
                      :height="182"
                      :collect-num="item.collectNum"
                      :view-num="item.viewNum"
                      :menu-id="item.id"
                    />
                  </el-col>
                </el-row>
              </div>
              <div style="margin-top: 40px; margin-bottom: 50px; display: flex; justify-content: center;">
                <el-pagination
                  v-model:current-page="collectMenu.currentPage"
                  v-model:page-size="collectMenu.pageSize"
                  :disabled=false
                  :background=true
                  layout="prev, pager, next, jumper"
                  :total="collectMenu.total"
                  @size-change="handleCollectMenuSizeChange"
                  @current-change="handleCollectMenuCurrentChange"
                />
              </div>
            </el-tab-pane>
            <el-tab-pane label="笔记" name="collectNote">
              <div>
                <el-row :gutter="30">
                  <el-col :span="6" v-for="item in collectNote.records" style="margin-top: 20px">
                    <NoteCard
                      :image-url="item.imageUrls.split(',')[0]"
                      :up-num="item.upNum"
                      @click="() => toNoteDetail(item.id)"
                    />
                  </el-col>
                </el-row>
              </div>
              <div style="margin-top: 40px; margin-bottom: 50px; display: flex; justify-content: center;">
                <el-pagination
                  v-model:current-page="collectNote.currentPage"
                  v-model:page-size="collectNote.pageSize"
                  :disabled=false
                  :background=true
                  layout="prev, pager, next, jumper"
                  :total="collectNote.total"
                  @size-change="handleCollectNoteSizeChange"
                  @current-change="handleCollectNoteCurrentChange"
                />
              </div>
            </el-tab-pane>
          </el-tabs>

        </el-tab-pane>
      </el-tabs>
    </div>
    <div style="text-align: left; margin-top: 30px">
      <span style="font-size: 13px;">你当前的位置：美食吃坊 > 个人中心</span>
    </div>
  </div>
  <FoodFooter/>
</template>

<script>
import {ref, reactive, getCurrentInstance} from "vue";
import IconfontSvg from "@/components/iconfonts/IconSvg";
import MenuCard from "@/components/card/MenuCard";
import NoteCard from "@/components/card/NoteCard";
import FoodFooter from "@/views/footer/FoodFooter";
import { useRoute, useRouter } from "vue-router/dist/vue-router";
import dish from "@/api/dish";
import {
  COLLECT_MENU_SELECT,
  OVERVIEW_SELECT,
  PERSONAL_COLLECT_CONSTANT,
  PERSONAL_CONSTANT, PERSONAL_EVENT
} from "@/utils/nav";

export default {
  name: "PersonalHomepage",
  components: {
    NoteCard,
    IconfontSvg,
    MenuCard,
    FoodFooter
  },
  setup() {
    const router = useRouter()
    const routes = useRoute()
    const memberId = routes.query.id
    const activeName = ref('overview')
    const activeTwoName = ref('collectMenu')
    // 存储选中状态
    const init = () => {
      let selectComponent = localStorage.getItem(PERSONAL_CONSTANT)
      let collectSelectComponent = localStorage.getItem(PERSONAL_COLLECT_CONSTANT)
      if (selectComponent === null) {
        localStorage.setItem(PERSONAL_CONSTANT, OVERVIEW_SELECT)
      } else {
        activeName.value = selectComponent
        if (collectSelectComponent == null) {
          localStorage.setItem(PERSONAL_COLLECT_CONSTANT, COLLECT_MENU_SELECT)
        } else {
          activeTwoName.value = collectSelectComponent
        }
      }
    }
    init()
    // 菜谱分页数据
    const menu = reactive({
      currentPage: 1,
      pageSize: 9,
      total: 0,
      records: []
    })
    // 日记分页数据
    const note = reactive({
      currentPage: 1,
      pageSize: 9,
      total: 0,
      records: []
    })
    // 收藏菜谱分页数据
    const collectMenu = reactive({
      currentPage: 1,
      pageSize: 9,
      total: 0,
      records: []
    })
    // 收藏笔记分页数据
    const collectNote = reactive({
      currentPage: 1,
      pageSize: 9,
      total: 0,
      records: []
    })
    // 个人信息
    const personInfo = reactive({
      id: '',
      nickname: '',
      username: '',
      imageUrl: '',
      intro: '',
      sex: 1,
      address: '',
      exp: 100,
      fans: 0,
      follows: 0
    })
    // 获取个人信息
    const getPersonalPage = () => {
      let personalDto = {
        userId: memberId,
        menuCurrentPage: 1,
        menuPageSize: 15,
        menuTotal: 0,
        noteCurrentPage: 1,
        notePageSize: 20,
        noteTotal: 0,
        collectMenuCurrentPage: 1,
        collectMenuPageSize: 12,
        collectMenuTotal: 0,
        collectNoteCurrentPage: 1,
        collectNotePageSize: 16,
        collectNoteTotal: 0
      }
      // 取消导航栏的选中状态
      // 获取到 全局事件总线
      const { Bus }  = getCurrentInstance().appContext.config.globalProperties
      // 修改导航栏的状态
      const updateSelectState = () => {
        // 通知导航栏组件移除选中状态
        Bus.emit(PERSONAL_EVENT, { state: true })
      }
      updateSelectState()
      dish.getPersonalInfo(personalDto).then(res => {
        // 个人信息数据赋值
        let person = res.data.memberInfoVo
        personInfo.id = person.id
        personInfo.nickname = person.nickname
        personInfo.username = person.username
        personInfo.imageUrl = person.imageUrl
        personInfo.intro = person.intro
        personInfo.sex = person.sex
        personInfo.address = person.address
        personInfo.exp = person.exp
        personInfo.fans = person.fans
        personInfo.follows = person.follows
        // 菜谱数据赋值
        let dishPage = res.data.personalDishPageVo
        menu.currentPage = dishPage.currentPage
        menu.total = dishPage.total
        menu.records.push(...dishPage.personalDishVos)
        // 日记数据赋值
        let notePage = res.data.personalNotePageVo
        note.currentPage = notePage.currentPage
        note.total = notePage.total
        note.records.push(...notePage.personalNoteVos)
        // 收藏赋值
        let dishCollectPage = res.data.personalCollectVo.personalDishCollectVo
        let noteCollectPage = res.data.personalCollectVo.personalNoteCollectVo
        collectMenu.currentPage = dishCollectPage.currentPage
        collectMenu.total = dishCollectPage.total
        collectMenu.records.push(...dishCollectPage.personalDishVos)

        collectNote.currentPage = noteCollectPage.currentPage
        collectNote.total = noteCollectPage.total
        collectNote.records.push(...noteCollectPage.personalNoteVos)
      })
    }
    getPersonalPage()
    const handleMenuSizeChange = (val) => {
      console.log(`${val} items per page`)
    }
    // 菜谱当前页发生变化
    const handleMenuCurrentChange = (val) => {
      menu.currentPage = val

    }
    const handleNoteSizeChange = (val) => {
      console.log(`${val} items per page`)
    }
    // 菜谱当前页发生变化
    const handleNoteCurrentChange = (val) => {
      note.currentPage = val
    }
    const handleCollectMenuSizeChange = (val) => {
      console.log(`${val} items per page`)
    }
    // 菜谱收藏当前页发生变化
    const handleCollectMenuCurrentChange = (val) => {
      collectMenu.currentPage = val
    }
    const handleCollectNoteSizeChange = (val) => {
      console.log(`${val} items per page`)
    }
    // 菜谱当前页发生变化
    const handleCollectNoteCurrentChange = (val) => {
      collectNote.currentPage = val
    }
    // 点击tab
    const handleClick = (tab, event) => {
      localStorage.setItem(PERSONAL_CONSTANT, tab.paneName)
    }
    // 点击collect中的tab
    const handleCollectClick = (tab, event) => {
      localStorage.setItem(PERSONAL_COLLECT_CONSTANT, tab.paneName)
    }
    const toNoteDetail = (id) => {
      let data = router.resolve({path: '/noteDetail', query: {id: id}})
      open(data.href, '_blank')
    }
    return {
      activeName,
      activeTwoName,
      menu,
      note,
      collectMenu,
      collectNote,
      personInfo,
      toNoteDetail,
      handleClick,
      handleCollectClick,
      handleMenuSizeChange,
      handleMenuCurrentChange,
      handleNoteSizeChange,
      handleNoteCurrentChange,
      handleCollectMenuSizeChange,
      handleCollectMenuCurrentChange,
      handleCollectNoteSizeChange,
      handleCollectNoteCurrentChange
    }
  }
}
</script>

<style scoped lang="less">
.person_home {
  margin: 20px 18vw;
}
.person_sub_title {
  font-size: 12px;
  line-height: 20px;
  color: #666;
}
.text_center {
  display: flex;
  justify-content: center;
  align-items: center;
  span {
    font-size: 12px; line-height: 15px;
  }
}
// 去掉tab标签页的底部分割线
:deep(.el-tabs__nav-wrap::after) {
  height: 0 !important;
}
</style>
