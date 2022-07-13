<template>
  <div class="content-box">
    <div class="container">
      <p>监考场次通知列表</p>
      <div class="test-div">
        <el-table
            :data="tableData"
            :span-method="objectSpanMethod"
            height="400"
            border
            style="width: 125%; margin-top: 0px; ">
          <!--          <el-table-column-->
          <!--              prop="id"-->
          <!--              label="序号"-->
          <!--              width="80">-->
          <!--          </el-table-column>-->
          <el-table-column
              prop="coursename"
              label="课程名称"
              width="155">
          </el-table-column>
          <el-table-column
              prop="testtime"
              label="考试时间"
              width="200">
          </el-table-column>
          <el-table-column
              prop="testId"
              label="考试编号"
              width="140">
          </el-table-column>
          <el-table-column
              prop="invigilator"
              label="监考老师"
              width="140">
          </el-table-column>
          <el-table-column
              prop="testtype"
              label="考试类型"
              width="140">
          </el-table-column>
          <el-table-column
              prop="status"
              label="考试状态"
              width="140">
          </el-table-column>
<!--          <el-table-column-->
<!--              prop="status2"-->
<!--              type="html"-->
<!--              label="批阅状况"-->
<!--              width="140">-->
<!--          </el-table-column>-->
          
          <el-table-column label="操作" width="180">
            <template slot-scope="scope">
              <div v-if="scope.row.teststatus == 2 || scope.row.teststatus == 3">
              <el-button size="medium" type="primary" plain icon="el-icon-d-arrow-right" @click="Invigilate(scope.row.testId)">进入监考</el-button>
              </div>
              <div v-if="scope.row.teststatus == 1">
              <el-button type="primary" disabled >考试未开始</el-button>
              </div>
            </template>

          </el-table-column>
          <el-table-column
              prop="note"
              label="备注"
              width="140">
          </el-table-column>
        </el-table>
      </div>
      <!-- <el-button size="small" plain type="primary" @click="toPath()">跳转到页面：考试列表</el-button> -->
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {postRequest} from "../utils/request";

export default {
  data() {
    return {

      tableData: [{
        id: '1',
        name: '离散数学',
        time: '周一',
        kaochanghao: '001',
        examtea: '张三',
        type:'线上',
        beizhu: '无',
        status1: '已结束',

        status2: '已批阅'
      }, {
        id: '1',
        name: '离散数学',
        time: '周一',
        kaochanghao: '002',
        examtea: '李四',
        type:'线上',
        beizhu: '无',
        status1: '已结束',
        end: '',
        status2: '待批阅'
      }, {
        id: '1',
        name: '离散数学',
        time: '周一',
        kaochanghao: '003',
        examtea: '王五',
        type:'线上',
        beizhu: '无',
        status1: '已结束',
        end: '',
        status2: '待批阅'
      }, {
        id: '2',
        name: '大学物理',
        time: '周二',
        kaochanghao: '001',
        examtea: '狗蛋',
        type:'线上',
        beizhu: '无',
        status1: '未开始',
        end: '',
        status2: '待批阅'
      }, {
        id: '2',
        name: '大学物理',
        time: '周二',
        kaochanghao: '002',
        examtea: '翠花',
        type:'线上',
        beizhu: '无',
        status1: '未开始',
        end: '',
        status2: '待批阅'
      }]
    };
  },
  methods: {
    getTestlist() {
      const _this = this;
      // console.log(this.phoneNumber,this.passWord)
      const data={
       invigilatorId: localStorage.getItem('ms_username')
      }
      postRequest(`/test/getTeachertest/${1}/${8}`,data).then(
              function (res) {
                console.log(res);
                _this.tableData = res.data.data.records;
                for(let i=0;i<_this.tableData.length;i++){
              // _this.student.push("teststatus:")
              if(_this.tableData[i].teststatus == 1){
                _this.tableData[i].status = '未开始'
              }
              else if(_this.tableData[i].teststatus == 2){
                _this.tableData[i].status = '已开始'
              }
              else _this.tableData[i].status = '已结束'
            }
              },
              function (err) {
                console.log(err);
                alert("服务器错误!稍后重试");
              }
          );
      // axios({
      //   url: `http://localhost:8080/test/getTeachertest/${1}/${8}` ,
      //   method: 'post',
      //   headers: {
      //     'Content-Type': 'application/json'
      //   },
      //   data:{
      //     invigilatorId: localStorage.getItem('ms_username')

      //   }
      // })

      //     .then(
      //         function (res) {
      //           console.log(res);
      //           _this.tableData = res.data.data.records;
      //           for(let i=0;i<_this.tableData.length;i++){
      //         // _this.student.push("teststatus:")
      //         if(_this.tableData[i].teststatus == 1){
      //           _this.tableData[i].status = '未开始'
      //         }
      //         else if(_this.tableData[i].teststatus == 2){
      //           _this.tableData[i].status = '已开始'
      //         }
      //         else _this.tableData[i].status = '已结束'
      //       }
      //         },
      //         function (err) {
      //           console.log(err);
      //           alert("服务器错误!稍后重试");
      //         }
      //     );
    },


    Invigilate(testId){
      this.$router.push({path:'/test22-2-2-1',query: {testId: testId}})
    }
  },
  mounted() {
    this.getTestlist();
  },

};
</script>

<style>
</style>