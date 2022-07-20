<template>
<div class="container" style="margin-left:15%;width:60%">
    <div class="box">
        <div>
            <h2 class="title" style="margin-left:-50px">账户信息</h2>
            <ul class="mes-list">

                <li>
                    <span class="label">用户ID</span>
                    <span class="mes">{{ mes.phone }}</span>
                </li>
            </ul>
        </div>
        <div  style="margin-top:30px">
            <h2 class="title" style="margin-left:-50px">个人信息</h2>
            <ul class="mes-list">
                <li>
                    <span class="label">身份类型</span>
                    <span class="mes">{{ mes.role }}</span>
                </li>
                <li>
                    <span class="label">学校</span>
                    <span class="mes">{{ mes.school }}</span>
                </li>
                <li>
                    <span class="label">学号</span>
                    <span class="mes">{{ mes.studentId}}</span>
                </li>
                <li>
                    <span class="label">班级</span>
                    <span class="mes">{{ mes.classroom}}</span>
                </li>
                <li>
                    <span class="label">真实姓名</span>
                    <span class="mes">{{ mes.name }}</span>
                </li>

            </ul>

           
        </div>
    </div>
</div>
</template>

<script>
import axios from "axios";
import {post1Request} from "@/utils/request";

export default {
    name: '',

    data() {
        return {
            radio: 1,
            mes: {
                userName: '张三',
                phone: '12648768',
                role: '教师',
                school: '武汉理工大学',
                studentId: '087657645',
                name: '张大炮',
                gender: '男',
                qualification: '本科',
                grade: '大三'
            }
        };
    },
    methods: {
        async getMes() {
             const data={
        phone: localStorage.getItem('ms_username')
      }
      post1Request('user/information',data).then( 
        function (res) {
                if (res.status === 200) {
              let  role  = res.data.data.role;
                switch (role) {
                    case 'student': {
                        role = '学生';
                        break;
                    }
                    case 'teacher': {
                        role = '老师';
                        break;
                    }
                    case 'admin': {
                        role = '管理员';
                        break;
                    }
                }
              res.data.data.role = role;
              this.mes = res.data.data;
                }})
        //   let res = await axios({
        //     url: "http://localhost:8080/user/information",
        //     method: 'post',
        //     headers: {
        //       'Content-Type': 'application/json'
        //     },
        //     params: {
        //       phone: localStorage.getItem('ms_username')

        //     }
        //   });

        //     if (res.status === 200) {
        //       let  role  = res.data.data.role;
        //         switch (role) {
        //             case 'student': {
        //                 role = '学生';
        //                 break;
        //             }
        //             case 'teacher': {
        //                 role = '老师';
        //                 break;
        //             }
        //             case 'admin': {
        //                 role = '管理员';
        //                 break;
        //             }
        //         }
        //       res.data.data.role = role;
        //       this.mes = res.data.data;
        //     }
        }
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
    color: rgb(179, 175, 175);
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