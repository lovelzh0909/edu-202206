<template>
  <div class="content-box">
    <div class="container">
      <p>考试列表</p>
      <div class="test-div">
        <el-table
            :data="pagination.records.slice((pagination.current-1)*pagination.size,pagination.current*pagination.size)"
            :span-method="objectSpanMethod"
            height="400"
            border
            
            style="width: 125%; margin-top: 0px;border-radius: 10px "
            header-row-class-name="tableHeader">

<!--          <el-table-column-->
<!--              prop="id"-->
<!--              label="序号"-->
<!--              width="80">-->
<!--          </el-table-column>-->
          <el-table-column
              prop="coursename"
              label="考试名称"
              align="center"
              width="155">
          </el-table-column>
          <el-table-column
              prop="testtime"
              label="考试时间"
              align="center"
              width="200">
          </el-table-column>
          <el-table-column
              prop="roomId"
              label="考场号"
              align="center"
              width="120">
          </el-table-column>
          <el-table-column
              prop="invigilator"
              label="监考老师"
              align="center"
              width="120">
          </el-table-column>
          <el-table-column
              prop="testtype"
              label="考试类型"
              align="center"
              width="120">
          </el-table-column>
          <el-table-column
              prop="status1"
              label="考试状态"
              align="center"
              width="120">
          </el-table-column>
          <el-table-column
              prop="status2"
              align="center"
              label="批阅状况"
              width="120">
          </el-table-column>

          <el-table-column label="操作" align="center" width="250">
            <template slot-scope="scope">
              <div v-if="scope.row.teststatus == 3">
                    <el-button size="small"  type="primary" icon="el-icon-document-checked" @click="toPathyuejuan(scope.row.testId)" round>批阅</el-button>
                    <el-button size="small" plain type="danger" icon="el-icon-delete-solid" round @click="deleteRecord(scope.row.testId)">删除</el-button>
              <!-- <el-button size="small" plain type="primary" @click="toPathlook()">查看结果</el-button> -->
              </div >
              <div v-if="scope.row.teststatus == 1 || scope.row.teststatus == 2">
                    <el-button  size="small" type="primary" disabled  round>暂时无法批阅</el-button>
                    <el-button size="small" plain type="danger" icon="el-icon-delete-solid" round @click="deleteRecord(scope.row.testId)">删除</el-button>
              </div>
              <div v-if="scope.row.teststatus == 4">
                    <el-button size="small"  type="primary" disabled round>暂时无法批阅</el-button>
                    <el-button size="small" plain type="danger" icon="el-icon-delete-solid" round @click="deleteRecord(scope.row.testId)">删除</el-button>
              </div>
              
                    
              
            </template>

          </el-table-column>
          <el-table-column
              prop="note"
              label="备注"
              width="180">
          </el-table-column>
        </el-table>
      </div>
      <!-- <el-button size="small" plain type="primary" @click="toPath()">跳转到页面：考试列表</el-button> -->
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
    </div>
    
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      testId:null,
      pagination: {
        total: 0,
        current:1,
        size:2,
        records:[]

      },

      
    };
  },
  methods: {
    //分页
    handleSizeChange(val) {
      const _this = this;
      _this.pagination.size = val
      

    },
    handleCurrentChange(val) {
      const _this = this;
      _this.pagination.current = val
      
    },

    //删除考试按钮
      deleteRecord(testId) {
      this.$confirm("确定删除该记录吗,该操作不可逆！！！", "删除提示", {
        confirmButtonText: '确定删除',
        cancelButtonText: '算了,留着',
        type: 'danger'
      }).then(() => { //确认删除
        this.$axios({
          url: 'http://localhost:8080/test/remove',
          method: 'post',
          params: {id:testId},
        }).then(res => {
          this.getTestlist()
        })
      }).catch(() => {

      })
    },
    getTestlist() {
      const _this = this;
      // console.log(this.phoneNumber,this.passWord)
      axios
          .post(`http://localhost:8080/test/getTeachertest/distribute/${1}/${6}`, {
            //.post("https://10.135.242.126:8080/user/logon", {
            //user: JSON.stringify({
            teacherphone: localStorage.getItem('ms_username')
            //}),
          }) //具体phoneNumber和passWord对象跟后端对接口时改
          .then(
              function (res) {
                console.log(res);
                _this.pagination = res.data.data;
                console.log(_this.pagination);
                for(let i=0;i<_this.pagination.records.length;i++){
              if(_this.pagination.records[i].note ==null)
              _this.pagination.records[i].note = '无'
              if(_this.pagination.records[i].teststatus == 1){
                _this.pagination.records[i].status1 = '未开始'
                _this.pagination.records[i].status2 = '考试未结束'
              }
              else if(_this.pagination.records[i].teststatus == 2){
                _this.pagination.records[i].status1 = '考试中'
                _this.pagination.records[i].status2 = '考试未结束'
              }
              else if(_this.pagination.records[i].teststatus == 3){
                _this.pagination.records[i].status1 = '已结束'
                _this.pagination.records[i].status2 = '待批阅'
              }
              else if(_this.pagination.records[i].teststatus == 4){
                _this.pagination.records[i].status1 = '已结束'
                _this.pagination.records[i].status2 = '已批阅完成'
              }
               
            }
            console.log(_this.pagination.records);
              },
              function (err) {
                console.log(err);
                alert("服务器错误!稍后重试");
              }
          );
    },

    // //合并行或者列的方法，返回数组[rowspan，colsapn]
    // arraySpanMethod({ row, column, rowIndex, columnIndex }) {
    //   if (rowIndex % 2 === 0) {
    //     if (columnIndex === 0) {
    //       return [1, 2];
    //     } else if (columnIndex === 1) {
    //       return [0, 0];
    //     }
    //   }
    // },
    // //合并行或者列的方法，返回键名为[rowspan，colsapn]的对象
    // objectSpanMethod({ row, column, rowIndex, columnIndex }) {
    //   if (columnIndex === 0 || columnIndex ===1) {
    //     if (rowIndex % 3 === 0) {
    //       return {
    //         rowspan: 3,
    //         colspan: 1
    //       };

    //     } else {
    //       return {
    //         rowspan: 0,
    //         colspan: 0
    //       };
    //     }
    //   }
    // },
    handleEdit(index, row) {
      console.log(index, row);
    },

    //判断是否阅卷
    ifyuejaun(){
      if(status1 === 1){

      }
    },
    toPathyuejuan(testId){
      this.testId= testId
      this.$router.push({
        path: '/yuejuan-paper',query: {testId: this.testId}
      });
    },
    toPathlook(){
      this.$router.push({
        path: '/look-paper'
      });
    },
  },
  mounted() {
    this.getTestlist();
  },

};
</script>

<style lang="scss" scoped>
::v-deep .tableHeader th {
  // background-color: #e03030;
  color: #373838;
  border-bottom: '1px RGBA(199, 199, 199, 1) solid'
  
}
</style>