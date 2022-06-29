
<template>
    <div id='building'>
        <div class="crumbs" style="margin-top:-18px;margin-left:46%">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-warning"></i> 首页提示</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container" style="border-radius: 10px;margin-top:-8px">
            
            <h1 align="center">学生端介绍</h1>
            <p  style="margin-left:28%">1. 学生端首页顶部有两个一级菜单：“首页”，“学生-考试”</p>
            <p style="margin-left:28%">2. 在首页部分会介绍学生端的功能与注意事项</p>
            <p style="margin-left:28%">3. 在“学生-考试”界面会有学生的考试列表、考试通知、个人信息、错题集、成绩查询和修改密码五个子页面</p>

            <p style="margin-top:50px;margin-left:46%">点击这里可以<router-link to="/login1" class="logout">退出登录</router-link></p>
        </div>

    </div>
</template>
<style scoped>
#building{
  background:url("../assets/img/Blue Moon.jpg");
  width:78%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
  border-radius: 10px;
  margin-top:10px;
  margin-left:10%
}
</style>