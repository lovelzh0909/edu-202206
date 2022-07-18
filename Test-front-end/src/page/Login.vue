<template>
<div id='building'>

  <div class="lowin lowin-blue">
    <div class="lowin-wrapper">
      <div class="lowin-box">
        <div class="lowin-box-inner">
          <form>
            <p>用户登录</p>
            <div class="lowin-group">
              <label>用户名 </label>
              <input
                  v-model.trim="phoneNumber"
                  class="lowin-userinput"
                  placeholder="手机号"
                  name="userName"
                  type="text"
                  maxlength="11"
              />
              <!-- maxlength设置最大长度 -->
            </div>
            <div class="lowin-group password-group">
              <label>密码 <a href="#" class="forgot-link"></a></label>
              <input
                  v-model.trim="passWord"
                  class="lowin-userinput"
                  placeholder="密码"
                  name="password"
                  type="password"
                  maxlength=" "
                  minlength=" "
                  auto-complete="on"
              />
            </div>
            <button class="lowin-but login-btn" @click.prevent="login">
              登录
            </button>
            <div class="text-foot">
              
              <a href="http://localhost:8081/#/register" class="register-link">还没有账号?注册</a>
            </div>
            <div class="otherway-login"></div>
          </form>
        </div>
      </div>
    </div>
  </div>
   </div>
</template>


<script>
import  cryptoJSObj  from  '../utils/asc.js'
import axios from "axios";
import {postRequest} from "../utils/request";
import  {loginTosystem}  from "../api/login.js";


export default {
  name: "Login",
  data() {
    return {
      
      phoneNumber: "",
      passWord: "",
    };
  },
  methods: {
    login() {
      const _this=this;
      const data={
        phone: this.phoneNumber,
       password: cryptoJSObj.encryptFunc(this.passWord, '123456789GFEDCBA')  //密码加密
      }
      postRequest('user/logon',data).then(
              function (res) {
                console.log(res);
                localStorage.setItem('ms_userrole',res.data.data);
                localStorage.setItem('ms_username',_this.phoneNumber);
                //根据后端返回的内容做出处理
                // var commonReturn = JSON.parse(res.data.commonReturn);
                if (res.data.status === 1) {

                  if(res.data.data === 'admin'){
                    // this.$router.push('/');

                    return _this.$router.push('/Whole3');
                  }
                  else if(res.data.data === 'teacher'){
                    // this.$router.push('/');
                    return _this.$router.push('/Whole2');
                  }
                  else if(res.data.data === 'student'){

                    return _this.$router.push('/Whole1');
                  }else alert("错误!")

                } else {
                  alert("手机号或密码错误!");
                }
              },
              // function (err) {
              //   // console.log(err);
              //   alert("服务器错误!稍后重试");
              // }
          );
      // loginTosystem(data).then(
      //         function (res) {
      //           console.log(res);
      //           localStorage.setItem('ms_userrole',res.data.data);
      //           localStorage.setItem('ms_username',_this.phoneNumber);
      //           //根据后端返回的内容做出处理
      //           // var commonReturn = JSON.parse(res.data.commonReturn);
      //           if (res.data.status === 1) {

      //             if(res.data.data === 'admin'){
      //               // this.$router.push('/');

      //               return _this.$router.push('/Whole3');
      //             }
      //             else if(res.data.data === 'teacher'){
      //               // this.$router.push('/');
      //               return _this.$router.push('/Whole2');
      //             }
      //             else if(res.data.data === 'student'){

      //               return _this.$router.push('/Whole1');
      //             }else alert("错误!")

      //           } else {
      //             alert("手机号或密码错误!");
      //           }
      //         },
      //         function (err) {
      //           // console.log(err);
      //           alert("服务器错误!稍后重试");
      //         }
      //     );
    //   const _this = this;
    //   // console.log(this.phoneNumber,this.passWord)
    //   axios.post("http://localhost:8080/user/logon", {
    //         //.post("https://10.135.242.126:8080/user/logon", {
    //         //user: JSON.stringify({
    //         phone: this.phoneNumber,
    //         password: cryptoJSObj.encryptFunc(this.passWord, '123456789GFEDCBA')  //密码加密
    //         //}),
    //       }) //具体phoneNumber和passWord对象跟后端对接口时改
    //       .then(
    //           function (res) {

    //             console.log(res);
    //             localStorage.setItem('ms_userrole',res.data.data);
    //             localStorage.setItem('ms_username',_this.phoneNumber);
    //             //根据后端返回的内容做出处理
    //             // var commonReturn = JSON.parse(res.data.commonReturn);
    //             if (res.data.status === 1) {

    //               if(res.data.data === 'admin'){
    //                 // this.$router.push('/');

    //                 return _this.$router.push('/Whole3');
    //               }
    //               else if(res.data.data === 'teacher'){
    //                 // this.$router.push('/');
    //                 return _this.$router.push('/Whole2');
    //               }
    //               else if(res.data.data === 'student'){

    //                 return _this.$router.push('/Whole1');
    //               }else alert("错误!")

    //             } else {
    //               alert("手机号或密码错误!");
    //             }
    //           },
    //           function (err) {
    //             // console.log(err);
    //             alert("服务器错误!稍后重试");
    //           }
    //       );
    },

  },
};
</script>

<style scoped>
.bgBackground{
width:100%;
height:150;
}

#building{
  background:url("../assets/img/wallhaven-nmvx21.jpg");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}
.lowin {
  /* variables */
  --color-primary: #44a0b3;
  --color-grey: rgba(68, 160, 179, 0.06);
  --color-dark: rgba(68, 160, 179, 0.5);
  --color-semidark: rgba(68, 160, 179, 0.5);

  text-align: center;
  margin: 60px auto;
  font-family: "Segoe UI";
  font-size: 14px;
}

.lowin .lowin-wrapper {
  -webkit-transition: all 1s;
  -o-transition: all 1s;
  transition: all 1s;
  -webkit-perspective: 1000px;
  perspective: 1000px;
  /* position: relative; */
  height: 100%;
  width: 540px;
  margin: 0 auto;
}

.lowin.lowin-blue {
  --color-primary: #0081c6;
  --color-grey: rgba(0, 129, 198, 0.05);
  --color-dark: rgba(0, 129, 198, 0.7);
  --color-semidark: rgba(0, 129, 198, 0.45);
}

.lowin a {
  color: #fcfcfc;
  text-decoration: none;
  /* border-bottom: 1px dashed var(--color-semidark); */
  margin-top: -3px;
  padding-bottom: 2px;
}

.lowin * {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

.lowin .lowin-box {
  width: 540px;
  margin: 50px auto;
  /* position: absolute; */
  /* left: 0; */
}

.lowin .lowin-box-inner {
  background-color: rgba(26, 58, 100, 0.788);
  -webkit-box-shadow: 0 7px 25px rgba(0, 0, 0, 0.08);
  box-shadow: 0 7px 25px rgba(0, 0, 0, 0.08);
  padding: 100px 90px;
  text-align:center;
  border-radius: 3px;
  height: 520px;
  width: 480px;
  margin: 50px, auto;
  margin-left: 0%;
  margin-top: 200px;
  ;border-radius: 20px
}

.lowin .lowin-box p {
  color: #fcfcfc;
  font-weight: 700;
  margin-bottom: 20px;
  text-align: center;
  font-size: 20px;
  margin-top:-42px
}

.lowin .lowin-box .lowin-group {
  margin-bottom: 30px;
}

.lowin .lowin-box label {
  margin-bottom: 5px;
  display: inline-block;
  width: 100%;
  color: #fcfcfc;
  font-weight: 700;
}

.lowin .lowin-box label a {
  float: right;
}

.lowin .lowin-box .lowin-but {
  display: inline-block;
  width: 100%;
  height: 48px;
  border: none;
  color: #fff;
  padding: 15px;
  border-radius: 24px;
  background-color: var(--color-primary);
  -webkit-box-shadow: 0 2px 7px var(--color-semidark);
  box-shadow: 0 2px 7px var(--color-semidark);
  font-weight: 700;
  outline: 0;
  cursor: pointer;
  -webkit-transition: all 0.5s;
  -o-transition: all 0.5s;
  transition: all 0.5s;
}

.lowin .lowin-box .lowin-but:active {
  -webkit-box-shadow: none;
  box-shadow: none;
}

.lowin .lowin-box .lowin-but:hover {
  opacity: 0.9;
}

.lowin .text-foot {
  text-align: center;
  padding: 10px;
  font-weight: 700;
  margin-top: 20px;
  color: #fcfcfc;
}

.lowin-userinput {
  border-radius: 24px;
  border: rgb(224, 224, 224) solid 1px;
  height: 48px;
  width: 340px;
  margin-left: -20px;
  padding: 0px 20px;
  font-size: 12px;
}

.lowin .lowin-userinput:focus {
  border: rgb(129, 176, 207) solid 1px;
  outline: none;
}

.lowin .otherway-login {
  margin-top: 20px;
  text-align: center;
}

.lowin .character-color {
  color: rgb(181, 192, 192);
}

/* @media screen and (max-width: 320px) {
    .lowin .lowin-wrapper {
      width: 100%;
    }
    .lowin .lowin-box {
      padding: 0 10px;
    }
  } */
</style>
