<template>
    <div class="wrap">
        <div class="banner" style="width:96%;height:650px;margin-top:25px;margin-left:25px">
            <img class="photo" src="@/assets/img/轮播图2.png" style="width:100%;height:100%">
            <div class="banner-text">欢迎来到踏星考试系统</div>
            <div class="banner-button">
                <el-button type="primary" class="button1" @click="gotoexam()">发布考试</el-button>
                <el-button type="info" class="button2" @click="gotoinvigilate()">我的监考</el-button>
                <el-button type="success" class="button3" @click="gotosetting()">个人设置</el-button>
        </div>
        </div>
       
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
        methods:{
            gotoexam(){
                this.$router.push({
                    path:'/test22/test22-1-1'
                });
            },
            gotoinvigilate(){
                this.$router.push({
                    path:'/test22/test22-2-2'
                });
            },
            gotosetting(){
                this.$router.push({
                    path:'/test22/test22-3-1'
                });
            }
        }
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
