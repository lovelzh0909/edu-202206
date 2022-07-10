
<template>
    <div>
        <div class="crumbs" style="margin-top:-10px;margin-left:46%">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-warning"></i> 首页提示</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container" style="border-radius: 10px">
            <h1 align="center">教师端首页介绍</h1>
            <p style="margin-left:28%">1. 教师端首页顶部有两个个一级菜单：“首页”，“教师-考试管理”</p>
            <p style="margin-left:28%">2. 在“教师-考试管理界面”下又分为“发布考试”，“我的监考”，“个人设置”三个子菜单</p>
            <p style="margin-left:28%">3. 在“发布考试”菜单中，教师的大体操作流程为：创建题库--添加题目--创建试卷--组卷--发布考试</p>
            <p style="margin-left:28%">4. 在“我的监考”菜单中，有监考任务的教师将会看到监考场次通知列表，当监考开始后，教师可以进入该场考试的监考页面，查看监考信息</p>
            <p style="margin-left:28%">5. 在“个人设置”菜单中，教师可以看到自己的个人信息和修改密码</p>

            <p style="margin-top:20px;margin-left:46%">点击这里可以<router-link to="/login1" class="logout">退出登录</router-link></p>
        </div><div id='building1'><div id='building2'></div>

    </div>
     </div>
</template>

<style scoped>
#building1{

  width:49%;
  height:50%;
  position:fixed;
  background-size:100% 100%;
  border-radius: 10px;
  margin-top:10px;
  
}
#building2{

  width:48%;
  height:50%;
  position:fixed;
  background-size:100% 100%;
  border-radius: 10px;
  /* margin-top:10px; */
  margin-left:50%
}
</style>