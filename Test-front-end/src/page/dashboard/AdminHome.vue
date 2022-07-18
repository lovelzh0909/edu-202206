<template>
    <div class="wrap">
        <!-- <div id="building"></div> -->
        <!-- <div class="header" style="width:100%">
            <div class="logo1" style="margin-top:10px;margin-left:18px;width: 50px;" ><img src="@/assets/img/favicon.png" /></div>
            <div class="logo2" style="margin-left:100px;margin-top: -55px;width: 200px;"><b><font size="6">踏星考试系统</font></b></div>
            <div class="logo3" style="margin-left:138px;margin-top: 0px;width: 1px;"><small>www.taxing.com</small></div>
            <div class="Navigation" style="margin-top:-67px;margin-left:350px;width: 50%;">
                <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                    <el-menu-item index="1">首页</el-menu-item>
                    <el-menu-item index="2">学生-考试</el-menu-item>
                    <el-menu-item index="3">教师-考试管理</el-menu-item>
                    <el-menu-item index="4">管理员-系统管理</el-menu-item>
                </el-menu>
            </div> -->
            <!-- 用户头像 -->
            <!-- <div class="user-avator" style="margin-left: 85%;margin-top: -50px;width:50px"><el-avatar icon="el-icon-user-solid"></el-avatar></div>
            
            <div class="notification" style="width:10px;margin-left:83%;margin-top: -32px;"><i class="el-icon-bell"></i></div>
            
            <div class="yonghuxialacaidan" style="margin-left:1340px;margin-top:-22px;width: 70px;">
                <el-dropdown>
                    <span>
                        用户名<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>  
        </div> -->
        <!-- <div id="building"></div> -->
        <div class="banner" style="width:96%;height:650px;margin-top:25px;margin-left:25px">
            <img class="photo" src="@/assets/img/背景图.jpg" style="width:100%;height:100%">
            <div class="banner-text">欢迎来到踏星考试系统</div>
            <div class="banner-button">
                <el-button type="primary" class="button1" @click="gotomodel">组织机构</el-button>
                <el-button type="info" class="button2">角色权限</el-button>
                <el-button type="success" class="button3">操作日志</el-button>
        </div>
        </div>
        <!-- <div class="img-box1" style="margin-top:30px;margin-left:150px;width:1200px">
            <el-carousel height=500px>
                <el-carousel-item v-for="item in imgList" :key="item.id">
                    <img :src="item.url" width="100%" height="500" alt="">
                </el-carousel-item>
            </el-carousel>
        </div> -->
        <!-- <div class="pictures" style="margin-top:5px">
            <img :src="imgURL1" class="fabukaoshi">
            <img :src="imgURL2" class="wodejiankao">
            <img :src="imgURL3" class="gerenshezhi">
        </div>
        <div class="words">
            <el-link href="https://element.eleme.io" target="primary" type="primary" class="exam">发布考试</el-link>
            <el-link type="primary" class="watch">我的监考</el-link>
            <el-link type="primary" class="settlement">个人设置</el-link>
        </div> -->
        <div class="footer">CopyRight@踏星工作室 </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                imgList: [
                {
                id: '1',
                url: require('@/assets/img/首页名字.jpg')
                },
                {
                id: '2', 
                url: require('@/assets/img/轮播图2.png')
                },
                {
                id: '3',
                url: require('@/assets/img/轮播图1.jpg')
                }
            ],
                imgURL1:require('@/assets/img/在线考试.svg'),
                imgURL2:require('@/assets/img/我的监考.png'),
                imgURL3:require('@/assets/img/个人设置.svg')
            }
  },
}

</script>

<style scoped>
 .img-box1{
    width: 90%;
    height: 100%;
    margin-left: 5%;
 }
 .pictures{
    margin-top: 1%;
 }
 .fabukaoshi{
    width:6%;
    height: 6%;
    margin-left: 23%;
 }
 .wodejiankao{
    width:8%;
    height: 8%;
    margin-left: 17%;
 }
 .gerenshezhi{
    width:6%;
    height: 6%;
    margin-left: 17%;
 }
 .exam{
    font-size: 20px;
    margin-left: 23%;
 } 
 .watch{
    font-size: 20px;
    margin-left: 18.5%;
 }
 .settlement{
    font-size: 20px;
    margin-left: 19%;
 }
 .footer{
    margin-top: 1%;
    text-align: center;
 }
/* #building{
    background:url("@/assets/img/轮播图2.png");
    margin-top: 30px;
    margin-left: 0px;
    width:110%;			
    height:50%;			
    position:absolute;
    background-size:100% 100%;
} */
.banner{
    position: relative;
}
.banner-text{
    position: absolute;
    z-index:5;
    left:220px;
    top:50px;
    color: aliceblue;
    font-size: 40px;
    font-weight: 600;
    margin-top: 200px;
}
.banner-button{
  position: absolute;
  left: 900px;
  margin-top: -400px;
  display: flex;
}
.button1{
   font-weight: 700;
  height: 50px;
  width: 120px;
}
.button2{
  margin-left: 70px;
  font-weight: 700;
  height: 50px;
  width: 120px;
}
.button3{
  margin-left: 70px;
  font-weight: 700;
  height: 50px;
  width: 120px;
}
</style>
