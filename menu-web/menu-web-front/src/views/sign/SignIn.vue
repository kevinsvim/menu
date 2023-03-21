<template>
  <div class="full-background">
    <div class="container">
      <!--Data or Content-->
      <div class="box-1">
        <div class="content-holder">
          <h2>Hello!</h2>
          <button class="button-1" @click="register()">Sign up</button>
          <button class="button-2" @click="login()">Login</button>
        </div>
      </div>
      <div class="box-2">
        <div class="login-form-container">
          <h1>Login Form</h1>
          <input type="text" placeholder="Username" class="input-field">
          <br><br>
          <input type="password" placeholder="Password" class="input-field">
          <br><br>
          <button class="login-button" type="button">Login</button>
        </div>
        <div class="signup-form-container">
          <h1>Sign Up Form</h1>
          <input type="text" placeholder="Username" class="input-field">
          <br><br>
          <input type="email" placeholder="Email" class="input-field">
          <br><br>
          <input type="password" placeholder="Password" class="input-field">
          <br><br>
          <button class="signup-button" type="button">Sign Up</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router/dist/vue-router'
import { ref } from 'vue'
export default {
  name: 'Login',
  setup() {
    const router = useRouter()
    const signUp = ref(false)
    function login() {

    }
    function register() {
      router.push('/register')
    }
    return {
      login,
      register
    }
  }
}
</script>

<style scoped>
@import '../../assets/css/sign.css';
</style>
