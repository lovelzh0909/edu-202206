
<template>
    <div>
        <div class="crumbs" style="margin-top:3px;margin-left:48%" >
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-warning"></i> 首页提示</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container" style="border-radius: 10px" >
            <h1 align="center">管理员端首页介绍</h1>
            <p style="margin-left:28%">1. 管理员端首页顶部有四个一级菜单：“首页”，“学生-考试”，“教师-考试管理”和“管理员-系统管理”</p>
            <p style="margin-left:28%">2. 管理员可以进入学生端和教师端的任何界面并分别模拟学生端和教师端的操作</p>
            <p style="margin-left:28%">3. 管理员身份还有额外的“系统管理”界面，在此界面中，管理员可以对系统绑定的组织机构和系统所有的用户进行一系列操作</p>
            <p style="margin-top:50px;margin-left:46%">点击这里可以<router-link to="/login1" class="logout">退出登录</router-link></p>
        </div><div id='building'>
</div>
    </div>
</template>
<style scoped>
#building{
  background:url("../assets/img/StarAndMoon.jpg");
  width:97.5%;
  height:80%;
  position:fixed;
  background-size:100% 100%;
  border-radius: 10px
  
}
</style>