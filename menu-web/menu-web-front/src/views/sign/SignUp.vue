<template>
  <el-main>
    <el-form class="register" label-width="80">
      <h4 class="login-text">Welcome to Register</h4>
      <el-form-item label="用户名">
        <el-input type="text" size="large" v-model="registerData.username" placeholder="请设置用户名"
        />
      </el-form-item>
      <el-form-item label="手机号">
        <el-input type="text" size="large" v-model.number="registerData.phone" placeholder="可用于登录和找回密码"
                  maxlength="13"/>
      </el-form-item>
      <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码">
        <el-input type="password" size="large" v-model="registerData.password" placeholder="请设置登录密码"
        />
      </el-form-item>
      <el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;码">
        <el-input type="password" size="large" v-model="secondPassword" placeholder="请再次输入登录密码"
        /></el-form-item>
      <el-form-item label="验证码">
        <el-input v-model="registerData.code" input-style="width: 50%;" size="large" placeholder="请输入验证码"/>
        <el-button v-show="!sendAuthCode" style="margin-left: 30px;" type="primary" :disabled="disabled.codeBtnDisabled"
                   @click="getAuthCode">发送验证码
        </el-button>
        <el-button v-show="sendAuthCode" class="auth_text" style="margin-left: 30px;" type="primary" :disabled="disabled.codeBtnDisabled"
                   @click="getAuthCode"><span class="auth_text_blue">{{ auth_time }} </span>
          秒后重发验证码
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="success" class="submitBtn" round @click.prevent="submit" :loading="registerLoading" :disabled="disabled.submitBtnDisabled">
          注册
        </el-button>
        <hr>
        <p>已经有账号，马上去<span class="to" @click="toLogin">登录</span></p>
      </el-form-item>
    </el-form>
  </el-main>
</template>
<script>
import { useRouter } from 'vue-router/dist/vue-router'
import { ref, reactive } from 'vue'
import member from '@/api/member'
import { ElMessage } from 'element-plus'
import { userTokenStore } from '@/store'
const store = userTokenStore()
export default {
  setup () {

    const router = useRouter()
    // 用户注册信息
    const registerData = reactive({
      username: '',
      phone: '',
      password: '',
      code: ''
    })
    const secondPassword = ref('')
    const registerLoading = ref(false)
    const sendAuthCode = ref(false)
    const auth_time = ref(60)
    // 按钮是否禁止
    const disabled = reactive({
      codeBtnDisabled: false,
      submitBtnDisabled: false
    })
    /**
     * 获取验证码
     */
    const getAuthCode = () => {
      // 将验证码按钮设置为禁止状态
      disabled.codeBtnDisabled = true
      // 设置倒计时秒
      const coderTimer = setInterval(() => {
        auth_time.value--
        if (auth_time.value <= 0) {
          sendAuthCode.value = false
          // 倒计时结束，将其按钮设置为非禁止状态
          disabled.codeBtnDisabled = false
          // 清除定时器
          clearInterval(coderTimer)
        }
      }, 1000)
      // 设置验证码发送状态为已发送
      sendAuthCode.value = true
      member.sendMsm(registerData.phone).then(result => {
      }).catch(error => {
        ElMessage.error('Internal server error')
      })

    }
    /**
     * 用户注册
     */
    const submit = () => {
      // 校验两次密码是否一致
      if (registerData.password !== secondPassword.value) {
        ElMessage.error('两次密码不一致')
        return
      }
      // 校验验证码是否为空
      if (registerData.code === "") {
        ElMessage.error('验证码不能为空')
        return
      }
      registerLoading.value = true
      member.userRegister(registerData).then(result => {
        // 注册成功,获取token
        ElMessage.error('注册成功~')
        console.log("注册结果: ", result)
        store.saveToken(result.data)
        console.log("logStorage存储结果:", window.localStorage.getItem("menu_token_info"))
      }).catch(error => {
        ElMessage.error('注册失败,请稍后再试~')
      })
      registerLoading.value = false
    }

    const toLogin = () => {
      //已经注册过跳转到登入界面
      router.push('/login')
    }

    return {
      registerData,
      registerLoading,
      sendAuthCode,
      auth_time,
      secondPassword,
      disabled,
      getAuthCode,
      submit,
      toLogin,
    }

  }
}
</script>
<style lang="less">
body {
  background-image: url(@/assets/bg.png);
}

.register {
  margin: 80pt auto;
  width: 500px;
  background: #fff;
  box-shadow: 0 0 10px #B4BCCC;
  padding: 30px 30px 0 30px;
  border-radius: 25px;
}

.submitBtn {
  width: 65%;
}

.to {
  color: #FA5555;
  cursor: pointer;
}

.register[data-v-92def6b0] {
  width: 370px;
  min-height: 440px;
}

.login-text {
  text-align: center;
  margin-bottom: 20px;
}
</style>
