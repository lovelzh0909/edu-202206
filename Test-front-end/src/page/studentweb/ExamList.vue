
<template>
  <div class="content-box">
    <div class="container">
      <p>考试列表</p>
      <div class="test-div">
        <el-table

            :data="pagination.records.slice((pagination.current-1)*pagination.size,pagination.current*pagination.size)"
            height="500"
            border
            header-row-class-name="tableHeader"
            style="width: 125%;top:0px;border-radius: 10px">
            
          <el-table-column
              prop="createdate"
              align="center"
              sortable
              label="日期"
              format="yyyy-MM-dd HH:mm"
              value-format="yyyy-MM-dd HH:mm"
              width="150">
          </el-table-column>
          <el-table-column
              prop="coursename"
              align="center"
              label="考试名称"
              width="150">
          </el-table-column>
          <el-table-column
              prop="testtime"
              label="考试时间"
              align="center"
              width="180">
          </el-table-column>
          <el-table-column
              prop="timelast"
              label="持续时间（min）"
              align="center"
              width="150">
          </el-table-column>
          <el-table-column
              prop="roomId"
              label="考场号"
              align="center"
              width="150"
              >
          </el-table-column>
          <el-table-column
              prop="invigilator"
              label="监考老师"
              align="center"
              width="150">
          </el-table-column>
          <el-table-column
              prop="note"
              label="备注"
              align="center"
              width="150"
              placeholder="输入考试密码">
          </el-table-column>
          <el-table-column
              prop="status"
              align="center"
              label="状态"
              width="150">
          </el-table-column>
          <el-table-column label="操作" width="150" align="center">
            <template slot-scope="scope" >
              <div v-if="scope.row.teststatus == 2">
              <el-button  type="primary"  @click="gototest(scope.row.testId)">进入考试</el-button>
               <el-dialog
                  title="提示"
                  :visible.sync="dialogVisible"
                  width="50%"
              >
                <h2 align="center" style="margin-top:-20px">你确定现在进入考试吗？</h2>
                <li style="margin-top:20px">  1.进入考试之后将进入全屏模式</li>
                <li>  2.进入考试之后你只有三次切屏机会，每次切屏时长超过5秒或者切屏次数超过3次系统将自动交卷。</li> 
                 <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button  type="primary"  @click="toPath()">确定</el-button>
        </span>
             </el-dialog> 
             </div>
             <div v-if="scope.row.teststatus == 1">
                <el-button  type="primary" disabled >等待开始</el-button>
             </div>
             <div v-if="scope.row.teststatus == 3">
                <el-button  type="primary" disabled >等待批阅</el-button>
             </div>
             <div v-if="scope.row.teststatus == 4">
                <el-button  type="primary" disabled >阅卷结束</el-button>
             </div>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="block">

      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-size="pagination.size"
          class="page"
          layout="total, prev, pager, next, jumper"
          :total="pagination.total"
      style="margin-left: 35%">
      </el-pagination>
    </div>
      <!-- <el-button size="small" plain type="primary" @click="toPath()">跳转到页面：考试列表</el-button> -->
    </div>
    
  </div>
</template>

<script>
import axios from "axios";
import * as jQuery from "xe-utils";
import {post1Request} from "@/utils/request";
export default {
  name: '',
  data() {
    return {
      testId:null,
      dialogVisible: false,
      phone:null,
      pagination: {
        total: 0,
        current:1,
        size:6,
        records:[]

      },
      //学生考试状态集合
      student:[
        {teststatus:'未开始'},
      ],
      
    }
  },
  methods: {
    gototest(testId){
      this.dialogVisible = true
      console.log(testId)
      this.testId= testId

    },

    //分页
    handleSizeChange(val) {
      const _this = this;
      _this.pagination.size = val
      

    },
    handleCurrentChange(val) {
      const _this = this;
      _this.pagination.current = val
      
    },
    toPath(){
      console.log('1111111111')
      
    
      
      
      console.log(this.testId)
      this.$router.push({
        path:'/exam-paper',query: {testId: this.testId}
      });
      // console.log(testId)
    },
    getTest() {
      const _this = this;
      // console.log(this.phoneNumber,this.passWord)
      const data={
        phone: localStorage.getItem('ms_username')
      }
      post1Request(`test/getStudenttest/${1}/${6}`,data).then(function (res) {

            console.log(res);
            _this.pagination = res.data.data;
            console.log(_this.pagination.records[0].teststatus)
            console.log(_this.student)
            _this.pagination.total = _this.pagination.records.length
            for(let i=0;i<_this.pagination.records.length;i++){
              // _this.student.push("teststatus:")
              if(_this.pagination.records[i].teststatus == 1){
                _this.pagination.records[i].status = '未开始'
              }
              else if(_this.pagination.records[i].teststatus == 2){
                _this.pagination.records[i].status = '已开始'
              }
              else if(_this.pagination.records[i].teststatus == 3){
                _this.pagination.records[i].status = '已结束'
              }
              else if(_this.pagination.records[i].teststatus == 4){
                _this.pagination.records[i].status = '已出成绩'
              }
            }
            
            console.log('aaaa')
            
            console.log(_this.pagination.records)


          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
      // axios({
      //   url: `http://localhost:8080/test/getStudenttest/${1}/${6}` ,
      //   method: 'post',
      //   headers: {
      //     'Content-Type': 'application/json'
      //   },
      //   params:{
      //     phone: localStorage.getItem('ms_username')

      //   }
      // }).then(function (res) {

      //       console.log(res);
      //       _this.pagination = res.data.data;
      //       console.log(_this.pagination.records[0].teststatus)
      //       console.log(_this.student)
      //       _this.pagination.total = _this.pagination.records.length
      //       for(let i=0;i<_this.pagination.records.length;i++){
      //         // _this.student.push("teststatus:")
      //         if(_this.pagination.records[i].teststatus == 1){
      //           _this.pagination.records[i].status = '未开始'
      //         }
      //         else if(_this.pagination.records[i].teststatus == 2){
      //           _this.pagination.records[i].status = '已开始'
      //         }
      //         else if(_this.pagination.records[i].teststatus == 3){
      //           _this.pagination.records[i].status = '已结束'
      //         }
      //         else if(_this.pagination.records[i].teststatus == 4){
      //           _this.pagination.records[i].status = '已出成绩'
      //         }
      //       }
            
      //       console.log('aaaa')
            
      //       console.log(_this.pagination.records)


      //     },
      //     function (err) {
      //       console.log(err);
      //       alert("服务器错误!稍后重试");
      //     })
     
    },

  },
  mounted() {
    this.getTest();
  }


}
</script>

<style lang="scss" scoped>
::v-deep .tableHeader th {
  // background-color: #e03030;
  color: #373838;
  border-bottom: '1px RGBA(199, 199, 199, 1) solid'
  
}
</style>
