<template>
  <div>
    <el-container>
      <!--  导航栏 -->
      <el-header>
        <NavigationBar/>
      </el-header>
      <el-main>
        <div style="padding-left: 16vw; padding-right: 16vw; margin-top: 20px; margin-bottom: 20px">
          <el-row style="text-align: left">
            <el-col :span="2">
              <span class="type_title">精选</span>
            </el-col>
            <el-col :span="2">
              <span class="type_title">最新</span>
            </el-col>
            <el-col :span="2">
              <span class="type_title">分类</span>
            </el-col>
            <el-col :span="2">
              <span class="type_title">食材</span>
            </el-col>
            <el-col :span="2">
              <span class="type_title">菜单</span>
            </el-col>
          </el-row>
          <div style="margin-top: 40px">
            <el-row>
              <span style="font-size: 1.3rem; font-weight: bold">热门</span>
            </el-row>
            <el-row>
              <span style="font-size: 1rem; margin-top: 20px; font-weight: bold">家常菜</span>
            </el-row>
            <el-row style="margin-top: 15px;">
                <!--  红烧肉
                      可乐鸡翅
                      糖醋排骨
                      鱼香肉丝
                      红烧排骨
                      宫保鸡丁
                      西红柿炒鸡蛋
                      清蒸鲈鱼
                      酸菜鱼
                      汤            -->
              <el-col :span="3" style="text-align: left">
                <span class="three_title">红烧肉</span>
              </el-col>
              <el-col :span="3" style="text-align: left">
                <span class="three_title">红烧肉</span>
              </el-col>
              <el-col :span="3" style="text-align: left">
                <span class="three_title">红烧肉</span>
              </el-col>
              <el-col :span="3" style="text-align: left">
                <span class="three_title">红烧肉</span>
              </el-col>
              <el-col :span="3" style="text-align: left">
                <span class="three_title">红烧肉</span>
              </el-col>
              <el-col :span="3" style="text-align: left">
                <span class="three_title">红烧肉</span>
              </el-col>
              <el-col :span="3" style="text-align: left">
                <span class="three_title">红烧肉</span>
              </el-col>
              <el-col :span="3" style="text-align: left">
                <span class="three_title">红烧</span>
              </el-col>
              <el-col :span="3" style="text-align: left">
                <span class="three_title">红肉</span>
              </el-col>
              <el-col :span="3" style="text-align: left">
                <span class="three_title">红烧肉</span>
              </el-col>              <el-col :span="3" style="text-align: left">
              <span class="three_title">红肉</span>
            </el-col>

            </el-row>
          </div>
        </div>
      </el-main>
    </el-container>


    <div>

    </div>
  </div>

</template>

<script>
import NavigationBar from '@/views/pages/NavigationBar'

export default {
  name: 'MaterialType',
  components: {
    NavigationBar
  }
}
</script>

<style scoped>
.type_title {
  font-size: 14px;
}
.three_title {
  color: #256DC6;
  line-height: 30px;
}
</style>
