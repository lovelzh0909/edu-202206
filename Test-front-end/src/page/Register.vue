<template>
<div id='building'>
  <div class="lowin  lowin-blue">
    <!--    <div class="lowin-brand">-->
    <!--      <img src="" alt="logo" style="margin-top: 12px">-->
    <!--    </div>-->
    <div class="lowin-wrapper">
      <div class="lowin-box lowin-register">
        <div class="lowin-box-inner">
          <el-form ref="loginForm" :model="loginForm" :rules="rules">
            <div class="lowin-group">
              <el-form-item prop="telephone" style="margin-top:-22px">
                <label>账号/电话号码 </label>
                <el-input ref="userName" v-model="loginForm.phone" class="lowin-input" placeholder="请输入账号或者电话号码"
                          name="userName" tabindex="1" auto-complete="off" maxlength="11" type="tel" style="margin-top:-5px"/>
              </el-form-item>
            </div>
            <div class="lowin-group password-group">
              <el-form-item prop="pass" style="margin-top:-22px">
                <label>密码</label>
                <el-input class="lowin-input" ref="password" v-model="loginForm.password"
                          placeholder="请输入密码" name="password" tabindex="2" auto-complete="off" style="margin-top:-5px"
                          @keyup.enter.native="handleLogin"/>
              </el-form-item>
            </div>
            <div class="lowin-group">
              <el-form-item prop="schoolname" style="margin-top:-22px">
                <label>所属组织机构</label>
                <el-select class="lowin-input" v-model="loginForm.school" placeholder="请选择学校" style="margin-top:-5px;">
                  <el-option label="A大学" value="A大学"></el-option>
                  <el-option label="B大学" value="B大学"></el-option>
                  <el-option label="C大学" value="C大学"></el-option>
                </el-select>
              </el-form-item>
            </div>
            <div class="lowin-group" >
              <el-form-item prop="person" style="margin-top:-22px">
                <label>注册身份</label>
                <el-select class="lowin-input" v-model="loginForm.type" placeholder="请选择注册身份" style="margin-top:-5px">
                  <el-option label="学生" value="student"></el-option>
                  <el-option label="教师" value="teacher"></el-option>
                </el-select>
              </el-form-item>
            </div>
            <div class="lowin-group">
              <el-form-item prop="classroom" style="margin-top:-22px">
                <label>所在班级</label>
                <el-input class="lowin-input" ref="classroom" v-model="loginForm.classroom"
                          placeholder="请输入所在班级" name="classroom" tabindex="2" auto-complete="off" style="margin-top:-5px"/>
              </el-form-item>
            </div>
            <div class="lowin-group">
              <el-form-item prop="pid" style="margin-top:-22px">
                <label>学号/教职工号 </label>
                <el-input ref="userName" v-model="loginForm.id" class="lowin-input" placeholder="请输入学号/教职工号" style="margin-top:-5px"
                          name="userName" tabindex="1" auto-complete="off" maxlength="15"/>
              </el-form-item>
            </div>
            <div class="lowin-group password-group">
              <el-form-item prop="realname" style="margin-top:-22px">
                <label>真实姓名</label>
                <el-input class="lowin-input" ref="name" v-model="loginForm.name"
                          placeholder="请输入真实姓名" name="name" tabindex="2" auto-complete="off" style="margin-top:-5px"
                          @keyup.enter.native="handleLogin" type="text"/>
              </el-form-item>
            </div>
            <el-form-item style="margin-top:-15px">
              <el-button type="primary" style="margin-left: 43%"  @click="handleRegister()">注册</el-button>
            </el-form-item>
            <div class="text-foot" style="margin-left:-5px;margin-top:-15px">
              
              <router-link to="/" class="login-link">
               已有账号? 登录
              </router-link>
            </div>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>

import axios from "axios"
import {registerApi as PopupManager} from "element-ui/src/utils/popup";
import {postRequest} from "@/utils/request";

export default {
  name: 'Home',
  data () {
    const telephone = (rule, value, callback) => {
      if (!Number.isInteger(value)) {
        return callback(new Error('电话号码为数字'))
      }
    };
    const pass = (rule, value, callback) => {
      if (value === '') {
        return callback(new Error('请输入密码'))
      }
    };
    return {
      dialogVisible1:false,
      loginForm: {
        phone: "",
        password: "",
        type: "",
        school:"",
        id:"",
        name:""
      },
      rules:{
        phone: [
          { validator:telephone, trigger: 'blur' }
        ],
        password: [
          { validator:pass, trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择注册身份', trigger: 'change' }
        ]
      }
    }
  },

  methods: {
    handleRegister () {
      let _this=this;
      // console.log('submit!');
      const data={
        phone: this.loginForm.phone,
        classroom: this.loginForm.classroom, 
        password: this.loginForm.password,
        studentId:this.loginForm.id,
        name:this.loginForm.name,
        school:this.loginForm.school,
        role:this.loginForm.type
      }
      postRequest('user/register',data).then(
              function (response) {
                console.log(response);
                _this.$refs.loginForm.validate((valid) => {
                  if (valid) {

                    alert('注册成功');
                    // _this.$router.push({path:'/Login1'});
                    _this.$router.go(-1)
                  } else {
                    console.log('注册失败');
                    return false;
                  }
                })
              },
              function (err) {
                // console.log(err);
                alert('服务器错误!稍后重试')
              }
          );
      // axios.post("http://localhost:8080/user/register", {phone: this.loginForm.phone, classroom: this.loginForm.classroom, password: this.loginForm.password,studentId:this.loginForm.id,name:this.loginForm.name,school:this.loginForm.school,role:this.loginForm.type})//具体phoneNumber和passWord对象跟后端对接口时改
      //     .then(
      //         function (response) {
      //           console.log(response);
      //           _this.$refs.loginForm.validate((valid) => {
      //             if (valid) {

      //               alert('注册成功');
      //               // _this.$router.push({path:'/Login1'});
      //               _this.$router.go(-1)
      //             } else {
      //               console.log('注册失败');
      //               return false;
      //             }
      //           })
      //         },
      //         function (err) {
      //           // console.log(err);
      //           alert('服务器错误!稍后重试')
      //         }
      //     );
    },
  },
  mounted(){
     
  }
}
</script>

<style lang="scss">
.lowin-input {
  .el-input__inner {
    // background-color: #baeeee;
    border: 2px !important;
    width:80%;
    margin-left:30px;
  }
}
</style>

<style scoped>

#building{
  background:url("../assets/img/login.png");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
  
}

/* #building{
  background:url("../assets/img/wallhaven-nmvx21.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
} */
.lowin {
  /* variables */
  --color-primary: #44a0b3;
  --color-grey: rgba(68, 160, 179, .06);
  --color-dark: rgba(68, 160, 179, .5);
  --color-semidark: rgba(68, 160, 179, .5);

  text-align: center;
  margin: 20px 0 0 0;
  font-family: 'Segoe UI';
  font-size: 14px;
}

.lowin .lowin-wrapper {
  -webkit-transition: all 1s;
  -o-transition: all 1s;
  transition: all 1s;
  -webkit-perspective: 1000px;
  perspective: 1000px;
  /* position: relative;  */
  height: 100%;
  width: 46%;
  margin: 0 auto;
}

.lowin.lowin-blue {
  --color-primary: #0081C6;
  --color-grey: rgba(0, 129, 198, .05);
  --color-dark: rgba(0, 129, 198, .7);
  --color-semidark: rgba(0, 129, 198, .45);
}

.lowin a {
  color: var(--color-primary);
  text-decoration: none;
  border-bottom: 1px dashed var(--color-semidark);
  margin-top: -3px;
  padding-bottom: 2px;
}

.lowin * {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

.lowin .lowin-brand {
  overflow: hidden;
  width: 100px;
  height: 100px;
  margin: 0 auto -50px auto;
  border-radius: 50%;
  -webkit-box-shadow: 0 4px 40px rgba(0, 0, 0, .07);
  box-shadow: 0 4px 40px rgba(0, 0, 0, .07);
  padding: 10px;
  background-color: #fff;
  /* z-index: 1; */
  position: relative; 
}

.lowin .lowin-brand img {
  width: 100%;
}

.lowin .lowin-box {
  width: 100%;
  position: absolute;
  left: 0;
}

.lowin .lowin-box-inner {
  background-color: #fff;
  -webkit-box-shadow: 0 7px 25px rgba(0, 0, 0, .08);
  box-shadow: 0 7px 25px rgba(0, 0, 0, .08);
  padding: 60px 25px 25px 25px;
  text-align: left;
  border-radius: 3px;
  height:820px
}
/* .lowin .lowin-box::after {
  content: ' ';
  -webkit-box-shadow: 0 0 25px rgba(0, 0, 0, .1);
  box-shadow: 0 0 25px rgba(0, 0, 0, .1);
  -webkit-transform: translate(0, -92.6%) scale(.88);
  -ms-transform: translate(0, -92.6%) scale(.88);
  transform: translate(0, -92.6%) scale(.88);
  border-radius: 3px;
  position: absolute;
  top: 100%;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #fff;
  z-index: -1;
} */

.lowin .lowin-box.lowin-flip {
  -webkit-transform: rotate3d(0, 1, 0, -180deg);
  transform: rotate3d(0, 1, 0, -180deg);
  display: none;
  opacity: 0;
}

.lowin .lowin-box p {
  color: var(--color-semidark);
  font-weight: 700;
  margin-bottom: 20px;
  text-align: center;
}

.lowin .lowin-box .lowin-group {
  margin-bottom: 30px;
}

.lowin .lowin-box label {
  margin-bottom: 5px;
  display: inline-block;
  width: 100%;
  margin-left: 30px;
  color: #48b7e4;
  font-weight: 700;
}

.lowin .lowin-box label a {
  float: right;
}

.lowin .lowin-box .lowin-input {
  background-color: #fff;
  color: var(--color-dark);
  border: none;
  border-radius: 3px;
  padding: 5px 20px;
  width: 100%;
  outline: 0;
}

.lowin .lowin-box .lowin-btn {
  display: inline-block;
  width: 100%;
  border: none;
  color: #fff;
  padding: 15px;
  border-radius: 3px;
  background-color: var(--color-primary);
  -webkit-box-shadow: 0 2px 7px var(--color-semidark);
  box-shadow: 0 2px 7px var(--color-semidark);
  font-weight: 700;
  outline: 0;
  cursor: pointer;
  -webkit-transition: all .5s;
  -o-transition: all .5s;
  transition: all .5s;
}

.lowin .lowin-box .lowin-btn:active {
  -webkit-box-shadow: none;
  box-shadow: none;
}

.lowin .lowin-box .lowin-btn:hover {
  opacity: .9;
}

.lowin .text-foot {
  text-align: center;
  padding: 10px;
  font-weight: 700;
  margin-top: 20px;
  color: var(--color-semidark);
}

/* animation */
.lowin .lowin-box.lowin-animated {
  -webkit-animation-name: LowinAnimated;
  animation-name: LowinAnimated;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: forwards;
  animation-fill-mode: forwards;
  -webkit-animation-timing-function: ease-in-out;
  animation-timing-function: ease-in-out;
}

.lowin .lowin-box.lowin-animatedback {
  -webkit-animation-name: LowinAnimatedBack;
  animation-name: LowinAnimatedBack;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: forwards;
  animation-fill-mode: forwards;
  -webkit-animation-timing-function: ease-in-out;
  animation-timing-function: ease-in-out;
}

.lowin .lowin-box.lowin-animated-flip {
  -webkit-animation-name: LowinAnimatedFlip;
  animation-name: LowinAnimatedFlip;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: forwards;
  animation-fill-mode: forwards;
  -webkit-animation-timing-function: ease-in-out;
  animation-timing-function: ease-in-out;
}

.lowin .lowin-box.lowin-animated-flipback {
  -webkit-animation-name: LowinAnimatedFlipBack;
  animation-name: LowinAnimatedFlipBack;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: forwards;
  animation-fill-mode: forwards;
  -webkit-animation-timing-function: ease-in-out;
  animation-timing-function: ease-in-out;
}

.lowin .lowin-brand.lowin-animated {
  -webkit-animation-name: LowinBrandAnimated;
  animation-name: LowinBrandAnimated;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: forwards;
  animation-fill-mode: forwards;
  -webkit-animation-timing-function: ease-in-out;
  animation-timing-function: ease-in-out;
}

.lowin .lowin-group.password-group {
  -webkit-transition: all 1s;
  -o-transition: all 1s;
  transition: all 1s;
}

.lowin .lowin-group.password-group.lowin-animated {
  -webkit-animation-name: LowinPasswordAnimated;
  animation-name: LowinPasswordAnimated;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: forwards;
  animation-fill-mode: forwards;
  -webkit-animation-timing-function: ease-in-out;
  animation-timing-function: ease-in-out;
  -webkit-transform-origin: 0 0;
  -ms-transform-origin: 0 0;
  transform-origin: 0 0;
}

.lowin .lowin-group.password-group.lowin-animated-back {
  -webkit-animation-name: LowinPasswordAnimatedBack;
  animation-name: LowinPasswordAnimatedBack;
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: forwards;
  animation-fill-mode: forwards;
  -webkit-animation-timing-function: ease-in-out;
  animation-timing-function: ease-in-out;
  -webkit-transform-origin: 0 0;
  -ms-transform-origin: 0 0;
  transform-origin: 0 0;
}

@media screen and (max-width: 320px) {
  .lowin .lowin-wrapper {
    width: 100%;
  }

  .lowin .lowin-box {
    padding: 0 10px;
  }
}
</style>
