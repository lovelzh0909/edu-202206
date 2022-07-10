<template>
<div class="content-box">
    <div class="container">
      <p>成绩管理表格</p>
      
        <allStudentsGrade></allStudentsGrade>
        <el-button type="primary"  @click="toupload()" style="margin-left:81.5%;margin-top:-30px" >导入线下成绩</el-button>
        <p>课程成绩统计图</p>
        <BarAnalys style="margin-top:-450px"></BarAnalys>
        
     
    </div>
</div>
</template>

<script>
import allStudentsGrade from '@/components/gradeManage/allStudentsGrade.vue' 
// import PieAnalys from '@/components/gradeManage/PieAnalys.vue';
// import LineAnalys from '@/components/gradeManage/LineAnalys.vue';
import BarAnalys from '@/components/gradeManage/BarAnalys.vue';
export default {
    name: '',
    data(){
      return{
        // //图表类型 0：饼图 1：折线图  2：柱状图
        // type:0,
        // eChartsTypes:['饼图分析','条形图分析','折线图分析'],
      }
    },
    components: {
        allStudentsGrade,
        // PieAnalys,
        // LineAnalys,
        BarAnalys,
    },
    methods:{
        onChangeType(index){
            this.type=index
        },
        toupload(){
            this.$router.push({path: '/upload'})
        }
        // ,query: {paperId: paperId}
    }
};
</script>

<style lang="scss" scoped>
.content-box {
  height: 2000px;
  width:1200px
}
.container {
    // margin-left: 250px;
    height: 1100px;
  width:1200px;
    // height: calc(100vh - 90px);
    background-color: #f9f9f9;
    .change-type{
        margin: 10px 50px;
        height: 28px;
        display: flex;
        justify-content: end;
        // margin-top: 0px;
        button{
            margin: 0 10px;
            padding: 3px;
            border: #909399 solid 1px;
            border-radius: 4px;
            color: #909399;
            font-family: 黑体;
            background-color: #f9f9f9;
        }
        button:active{
            background-color: rgb(181, 187, 187);
        }
        .active{
            background-color: rgb(221, 226, 226);
        }
    }
}
</style>