<template>
<div class="wrapper">
<!-- 页面头部部分 -->
    <div class="header">
        <div class="logo" style="margin-top:0px;margin-left:18px"><img src="../../assets/favicon.png" /></div>
        <!-- 水平一级菜单 -->
        <div class="logo" style="margin-left:-78px">踏星考试系统</div>
        
        <div style="float:left;margin-left:20px">
            <el-menu :default-active="toIndex()" mode="horizontal" @select="handleSelect">
            <template v-for="item in items">
                <el-menu-item :index="item.index" :key="item.index">
                <template slot="title">
                    <span slot="title">{{ item.title }}</span>
                </template>
                </el-menu-item>
            </template>
            </el-menu>
        </div>

        <div class="header-right">
            <div class="header-user-con">
                <!-- 用户头像 -->
                <div class="user-avator"><img src="../../assets/img/001.png" /></div>
                <!-- 用户名下拉菜单 -->
                <el-dropdown class="user-name" trigger="click" @command="handleCommand">
                    <span class="el-dropdown-link">
                        {{ username }}
                        <i class="el-icon-caret-bottom"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <!-- 为了调试方便，这里学生端、教师端和管理员端的名称略有不同 -->
                        <!-- <el-dropdown-item command = "changepass">修改密码</el-dropdown-item> -->
                        <el-dropdown-item command="loginout">退出登录</el-dropdown-item>
                        <el-dropdown-item command="weihu">一键维护</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
    </div>
    
    <!-- 页面左侧二级菜单栏，和主体内容区域部分 -->
    <el-main>
        <router-view></router-view>
    </el-main>

</div>
</template>

<script>
export default {
    data() {
        return {
            items: [    // 水平一级菜单栏的菜单
                 { index: 'Home3', title: '首页' },
                 //为了调试方便，名称与学生端和教师端略有不同
                 { index: 'test1', title: '学生-考试' },
                { index: 'test2', title: '教师-考试管理' },
                { index: 'test3', title: '管理员-系统管理' },
            ]
        }
    },
    computed: {
        username() {
            let username = localStorage.getItem('ms_username');
            return username ? username : this.name;
        }
    },
    methods:{
        // 根据路径绑定到对应的一级菜单，防止页面刷新重新跳回第一个
        toIndex() {
            return this.$route.path.split('/')[1];
        }, 
        // 切换菜单栏
        handleSelect(index) {
            this.$router.push('/' + index);
        },
        // 用户名下拉菜单选择事件
        handleCommand(command) {
            if (command == 'loginout') {
                localStorage.removeItem('ms_username');
                this.$router.push('/login1');
            }
            if (command == 'weihu'){
                this.$router.go(0)
                // localStorage.removeItem('ms_username');
                // this.$router.push('/changepass');
            }
        }
    }
}
</script>

<style scoped>
.wrapper {
    width: 100%;
    height: 100%;
    background: #f0f0f0;
}
.header {
    position: relative;
    box-sizing: border-box;
    width: 100%;
    height: 70px;
    font-size: 22px;
}
.header .logo {
    float: left;
    margin-left: 100px;
    margin-top: 17.5px;
    height: 29px;
    width: 160px;
    vertical-align: middle;
}
/* --------------- 用户头像区域的样式 ---------------- */
.header-right {
    float: right;
    padding-right: 50px;
}
.header-user-con {
    display: flex;
    height: 70px;
    align-items: center;
}
.user-avator {
    margin-left: 20px;
}
.user-avator img {
    display: block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
}
.user-name {
    margin-left: 10px;
}
.el-dropdown-link {
    cursor: pointer;
}
.el-dropdown-menu__item {
    text-align: center;
}
/* --------------- 水平一级菜单栏的样式--------------------- */
.el-menu.el-menu--horizontal {
    border-bottom: none !important;
    float: left;
    margin-left: 50px;
}
.el-menu--horizontal > .el-menu-item.is-active {
    border-bottom: 2px solid #409eff;
    color: #3989fa;
    font-weight: 700;
}
.el-menu--horizontal > .el-menu-item {
    font-size: 16px;
    margin: 0 15px;
    color: black;
}
</style>