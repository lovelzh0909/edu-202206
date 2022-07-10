<template>
<div class="container" style="margin-left:15%;width:60%">
    <div class="box">
        <div>
            <h2 class="title" style="margin-left:-50px">确认信息</h2>
            <ul class="mes-list">

                <li>
                    <span class="label">用户ID</span>
                    <span class="mes">{{ mes.phone }}</span>
                </li>
                <li>
                    <span class="label">学号</span>
                    <span class="mes">{{ mes.studentId }}</span>
                </li>
                <li>
                    <span class="label">真实姓名</span>
                    <span class="mes">{{ mes.name }}</span>
                </li>
            </ul>
        </div>
        <div style="margin-top:30px">
            <h2 class="title" style="margin-left:-50px">更换密码</h2>
            <el-form>
                <el-form-item label="请输入原始密码：" >
                    <el-input placeholder="请输入密码" v-model="input1" show-password style="width:300px;margin-left:42px"></el-input>
                </el-form-item>
                <el-form-item label="请输入要修改成的密码：" >
                    <el-input placeholder="请输入密码" v-model="input2" show-password style="width:300px"></el-input>
                </el-form-item>
            </el-form>

            
        </div>
        <el-button type="primary" style="margin-left:600px" @click="Submit()">提交修改</el-button>
    </div>
</div>
</template>

<script>
import axios from "axios";

export default {
    name: '',

    data() {
        return {
            radio: 1,
            mes: {
                userName: '张三',
                phone: '12648768',
                role: '学生',
                school: '武汉理工大学',
                studentId: '087657645',
                name: '张大炮',
                gender: '男',
                qualification: '本科',
                grade: '大三'
            },
            input1:'',
            input2:'',
        };
    },
    methods: {
        async getMes() {

            let res = await axios({
              url: "http://localhost:8080/user/information",
              method: 'post',
              headers: {
                'Content-Type': 'application/json'
              },
              params: {
                phone: localStorage.getItem('ms_username')

              }
            });

            if (res.status === 200) {
                
                this.mes = res.data.data;
                console.log(this.mes)
            }
        },

        Submit() {
            console.log(this.input1)
            if(this.input1 != this.mes.password){
                alert('请确认输入的原始密码正确')
            }
else{
      const _this = this;
      // console.log(this.phoneNumber,this.passWord)
      axios({
        url: 'http://localhost:8080/user/update',
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        data:{
          phone: localStorage.getItem('ms_username'),
          password: this.input2
        }
      }).then(function (res) { 
            console.log('aaaa')
            // this.getMes();
            alert('密码修改成功')
            _this.$router.go(0)
        


          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
}
    },
    },

    mounted() {
        this.getMes();
    }
};
</script>

<style>
.box {
    margin-left: 50px;
}

.title {
    padding: 10px;
    color: rgb(43, 39, 39);
    font-size: 12px;
    font-weight: normal;
}

.mes-list {
    background-color: #fff;
    padding: 0 18px;
    border-radius: 5px;
    overflow: hidden;
}

.mes-list li {
    list-style-type: none;
    border-bottom: rgb(241, 238, 238) solid 1px;
    /* text-align: center; */
    line-height: 50px;
    position: relative;
}

.mes-list li:last-child {
    border-bottom: none;
}

.mes {
    position: absolute;
    left: 120px;
}
</style>