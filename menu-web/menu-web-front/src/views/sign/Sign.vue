<template>
  <article class="test">
    <div class="container" :class="{'sign-up-active' : signUp}">
      <div class="overlay-container">
        <div class="overlay">
          <div class="overlay-left">
            <h2>Welcome Back!</h2>
            <p>Please login with your personal info</p>
            <button class="invert" id="signIn" @click="signUp = !signUp">Sign In</button>
          </div>
          <div class="overlay-right">
            <h2>Hello, Friend!</h2>
            <p>Please enter your personal details</p>
            <button class="invert" id="signUp" @click="signUp = !signUp">Sign Up</button>
          </div>
        </div>
      </div>
      <form class="sign-up">
        <h2>Create login</h2>
        <div>Use your email for registration</div>
        <input type="text" v-model="registerData.username" placeholder="Username"/>
        <input type="text" v-model.number="registerData.phone" maxlength="13" placeholder="Phone"/>
        <input type="password" v-model="registerData.password" placeholder="Password"/>
        <el-input
          style="width: 265px;height: 35px;"
          v-model="registerData.code" placeholder="验证码">
          <template #suffix>
            <el-button
              @click="getAuthCode"
              type="primary"
              style="height: 30px; width: 30px"
              :disabled="disabled.codeBtnDisabled"
              v-show="!sendAuthCode"
            >
              <span style="font-size: 13px">获取验证码</span></el-button>
            <el-button
              type="primary"
              style="height: 30px; width: 30px"
              v-show="sendAuthCode"
            >
              <span style="font-size: 12px">
                {{ auth_time }}秒后重发
              </span>
            </el-button>
          </template>
        </el-input>
        <button>Sign Up</button>
      </form>
      <form class="sign-in">
        <h2>Sign In</h2>
        <div>Use your account</div>
        <input type="text" v-model="loginForm.account" placeholder="Email/Phone"/>
        <input type="password" v-model="loginForm.password" placeholder="Password"/>
        <a href="#">Forgot your password?</a>
        <button @click="member_login">Sign In</button>
      </form>
    </div>
  </article>
</template>

<script>
import member from "@/api/member";
import {ElMessage} from "element-plus";
import { useRouter } from 'vue-router/dist/vue-router'
import { ref, reactive } from 'vue'
import { tokenStore } from '@/store/token'
import { userStore } from '@/store/user'
import sign from "@/api/sign";
export default {
  name: 'Sign',
  components: {},
  setup() {
    const router = useRouter()
    const signUp = ref(false)
    /**************************  登录  ******************************/
    const tStore = tokenStore()
    const uStore = userStore()
      // 登录信息
    const loginForm = reactive({
        type: 0,
        account: '',
        password: '',
      })
    const member_login = () => {
      sign.login(loginForm).then(res => {
        // 保存token到存储库和localstorage
        tStore.saveToken(res.data.token)

        // 存储用户信息
        const user = {
          id: res.data.id,
          username: res.data.username,
          imageUrl: res.data.image_url
        }
        uStore.saveUser(user)
        // 跳转到主页面
        router.push('/')
      }).catch(error => {
        ElMessage.error('登录失败, 请稍后再试!')
      })
    }
    /**************************  注册  ******************************/
    // 用户注册信息
    const registerData = reactive({
      username: '',
      phone: '',
      password: '',
      code: ''
    })
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
      // 校验验证码是否为空
      if (registerData.code === "") {
        ElMessage.error('验证码不能为空')
        return
      }
      registerLoading.value = true
      member.userRegister(registerData).then(result => {
        // 注册成功,获取token
        ElMessage.success('注册成功~')
        console.log("注册结果: ", result)
        // store.saveToken(result.data)
        console.log("logStorage存储结果:", window.localStorage.getItem("menu_token_info"))
      }).catch(error => {
        ElMessage.error('注册失败,请稍后再试~')
      })
      registerLoading.value = false
    }

    const toLogin = () => {
      //已经注册过跳转到登入界面

    }

    return {
      signUp,
      registerData,
      registerLoading,
      sendAuthCode,
      auth_time,
      disabled,
      loginForm,
      getAuthCode,
      submit,
      toLogin,
      member_login
    }
  }
}
</script>

<style lang="scss" scoped>
.test {
  font-family: Tahoma;
  font-size: 1.6rem;
  color: #222;
  background-color: #092525;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

:deep(.el-input__wrapper) {
  border-radius: 18px !important;
}

.container {
  position: relative;
  width: 768px;
  height: 480px;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 15px 30px rgba(0, 0, 0, .2),
  0 10px 10px rgba(0, 0, 0, .2);
  background: linear-gradient(to bottom, #efefef, #ccc);

  .overlay-container {
    position: absolute;
    top: 0;
    left: 50%;
    width: 50%;
    height: 100%;
    overflow: hidden;
    transition: transform .5s ease-in-out;
    z-index: 100;
  }

  .overlay {
    position: relative;
    left: -100%;
    height: 100%;
    width: 200%;
    background: linear-gradient(to bottom right, #7FD625, #009345);
    color: #fff;
    transform: translateX(0);
    transition: transform .5s ease-in-out;
  }

  @mixin overlays($property) {
    position: absolute;
    top: 0;
    display: flex;
    align-items: center;
    justify-content: space-around;
    flex-direction: column;
    padding: 70px 40px;
    width: calc(50% - 80px);
    height: calc(100% - 140px);
    text-align: center;
    transform: translateX($property);
    transition: transform .5s ease-in-out;
  }

  .overlay-left {
    @include overlays(-20%);
  }

  .overlay-right {
    @include overlays(0);
    right: 0;
  }
}

h2 {
  margin: 0;
}

p {
  margin: 20px 0 30px;
}

a {
  color: #222;
  text-decoration: none;
  margin: 15px 0;
  font-size: 1rem;
}

button {
  border-radius: 20px;
  border: 1px solid #009345;
  background-color: #009345;
  color: #fff;
  font-size: 1rem;
  font-weight: bold;
  padding: 10px 40px;
  letter-spacing: 1px;
  text-transform: uppercase;
  cursor: pointer;
  transition: transform .1s ease-in;

  &:active {
    transform: scale(.9);
  }

  &:focus {
    outline: none;
  }
}

button.invert {
  background-color: transparent;
  border-color: #fff;
}

form {
  position: absolute;
  top: 0;
  display: flex;
  align-items: center;
  justify-content: space-around;
  flex-direction: column;
  padding: 90px 60px;
  width: calc(50% - 120px);
  height: calc(100% - 180px);
  text-align: center;
  background: linear-gradient(to bottom, #efefef, #ccc);
  transition: all .5s ease-in-out;

  div {
    font-size: 1rem;
  }

  input {
    background-color: #eee;
    border: none;
    padding: 8px 15px;
    margin: 6px 0;
    width: calc(100% - 30px);
    border-radius: 15px;
    border-bottom: 1px solid #ddd;
    box-shadow: inset 0 1px 2px rgba(0, 0, 0, .4),
    0 -1px 1px #fff,
    0 1px 0 #fff;
    overflow: hidden;

    &:focus {
      outline: none;
      background-color: #fff;
    }
  }
}

.sign-in {
  left: 0;
  z-index: 2;
}

.sign-up {
  left: 0;
  z-index: 1;
  opacity: 0;
}

.sign-up-active {
  .sign-in {
    transform: translateX(100%);
  }

  .sign-up {
    transform: translateX(100%);
    opacity: 1;
    z-index: 5;
    animation: show .5s;
  }

  .overlay-container {
    transform: translateX(-100%);
  }

  .overlay {
    transform: translateX(50%);
  }

  .overlay-left {
    transform: translateX(0);
  }

  .overlay-right {
    transform: translateX(20%);
  }
}

@keyframes show {
  0% {
    opacity: 0;
    z-index: 1;
  }
  49% {
    opacity: 0;
    z-index: 1;
  }
  50% {
    opacity: 1;
    z-index: 10;
  }
}
</style>
