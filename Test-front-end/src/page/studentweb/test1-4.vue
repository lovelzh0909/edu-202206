<template>
<div class="content-box">
  <div class="container">
    <p>错题集表格</p>
    <el-table
    :data="tableData"
     header-row-class-name="tableHeader"
    border
    style="width: 90%;margin-top:30px;border-radius: 15px">
    <el-table-column
      prop="coursename"
      align="center"
      label="课程名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="id"
      align="center"
      label="错题ID">
    </el-table-column>
    <el-table-column
      prop="type"
      align="center"
      label="题目类型">
    </el-table-column>
    <el-table-column
      prop="stem"
      align="center"
      label="标题"
      width="180">
    </el-table-column>
    <el-table-column
      prop="studentAnswer"
      align="center"
      label="错误答案">
    </el-table-column>
    <el-table-column
      prop="answer"
      align="center"
      label="正确答案">
    </el-table-column>
    
  </el-table>
  </div>
</div>
</template>

<script>
export default {
  name: '',
  data(){
    return{
      tableData: [{
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-08',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-06',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-07',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }]
    }
  },
  mounted(){
    this.getwrongQuestion();
    
  },
  methods:{
      getwrongQuestion() {
      const _this = this;
      // console.log(this.phoneNumber,this.passWord)
      this.$axios({
        url: 'http://localhost:8080/paperJustify/wrongQuestion' ,
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params:{
          phone: localStorage.getItem('ms_username')

        }
      }).then(function (res) {
        _this.tableData = res.data.data
        for(let i=0;i<_this.tableData.length;i++){
          if(_this.tableData[i].quesTypeId ==1){
            _this.tableData[i].type ='单选题'
          }
          else if(_this.tableData[i].quesTypeId ==2){
            _this.tableData[i].type ='多选题'
          }
          else if(_this.tableData[i].quesTypeId ==3){
            _this.tableData[i].type ='填空题'
          }
          else if(_this.tableData[i].quesTypeId ==4){
            _this.tableData[i].type ='简答题'
          }
          else if(_this.tableData[i].quesTypeId ==5){
            _this.tableData[i].type ='判断题'
          }
        }
          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
        },
  },
}
</script>
<style lang="scss" scoped>
::v-deep .tableHeader th {
  // background-color: #e03030;
  color: #373838;
  border-bottom: '1px RGBA(199, 199, 199, 1) solid'
  
}
</style>