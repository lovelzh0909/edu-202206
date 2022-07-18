<template>
  <div class="body">
    <div class="content">
      <div class="title3">其他通知</div>
      <div class="notice">
        <el-card class="box-card">
          <!-- <div v-for="o in list1" :key="o" class="text item">
            {{o + " : " + notice_detail[o-1] }}
          </div> -->
          暂无
        </el-card>
      </div>
      <div class="title1">临近考试通知</div>
      <div class="close">
        <!--      <el-card class="close_card">-->
        <!--        <div slot="header" class="clearfix">-->
        <!--          <span>临近的考试</span>-->
        <!--          <el-button style="float:right; padding: 3px 0" type="text">我记住了</el-button>-->
        <!--        </div>-->
        <!--        <div v-for="o in 2" :key="o" class="text item">-->
        <!--          {{o+":"+test_course[o-1]+"还剩"+close_time[o-1]+"天"+"  临近考试！"}}-->
        <!--        </div>-->
        <!--      </el-card>-->
        <el-collapse accordian v-model="activeNames" @change="handleChange">
          <el-collapse-item v-for="o in list2" :key="o" class="close_item" :name="o">
            <template slot="title">
              <div class="title_style">
                {{test_course[o-1]+"还剩"+close_time[o-1]+"天"+"  临近考试！"}}
              </div>
            </template>
            <div style="display: flex">
              <div>
                {{"考试时间"+" : "+ date1[o-1]}}
                <el-link type="primary" :underline="false" class="operation" @click="test_detail()">查看详情</el-link>
              </div>
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
      <!-- <div class="title2">即将考试通知</div>
      <div class="ready">
        <el-table
            :data="tableData"
            style="width: 100%"
            border
            max-height="250">
          <el-table-column
              fixed
              prop="testtime"
              label="考试时间"
              width="170"></el-table-column>
          <el-table-column
              prop="name"
              label="考生姓名"
              width="140"></el-table-column>
          <el-table-column
              prop="studentId"
              label="考生学号"
              width="170"></el-table-column>
          <el-table-column
              prop="coursename"
              label="考试科目"
              width="170"></el-table-column>
          <el-table-column
              prop="invigilator"
              label="监考老师"
              width="140"></el-table-column>
          <el-table-column
              prop="testteacher"
              label="任课老师"
              width="140"></el-table-column>
          <el-table-column
              prop="timelast"
              label="考试时长"
              width="140"></el-table-column>
          <el-table-column
              fixed="right"
              label="操作"
              width="140">
            <template slot-scope="scope">
              <el-button
                  @click="gototest()"
                  type="text"
                  size="medium">
                进入考试
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div> -->
      <!-- <div class="pagination-container">
        <el-pagination
            background
            layout="total, prev, pager, next, jumper"
            :current-page.sync="currentPage"
            :page-size="pageSize"
            :total="totalPages">
        </el-pagination>
      </div> -->
    </div>
  </div>
</template>


<script>
import axios from "axios";
// import { mapMutations, mapState } from 'vuex'
// import registerApi from '@/api/register'
export default {
  name: 'Home',
  methods:{
    gototest(){
      this.$router.push('/')
    },
    test_detail(){
      this.$router.push('test11-1')
    },
    handleChange(val){
      console.log(val);
    }
  },
  mounted() {
    // axios({
    //       url: "http://localhost:8080/notice",
    //       method: 'post',
    //       headers: {
    //         'Content-Type': 'application/json'
    //       },
    //       params: {
    //         phone: localStorage.getItem('ms_username')

    //       }
    //     }
    // ).then(res => {
    //   let count = 0;
    //   this.list1= [];
    //   console.log(res.data)
    //   res.data.data.forEach(item => {
    //     console.log(item)
    //     console.log(item.notice)
    //     this.notice_detail.push(item.notice);
    //     count =count +1;
    //     this.list1.push(count);
    //   });
    //   // this.notice_detail.push(result.data);
    // });
    axios({
          url: "http://localhost:8080/notice/closenotice",
          method: 'post',
          headers: {
            'Content-Type': 'application/json'
          },
          params: {
            phone: localStorage.getItem('ms_username')

          }
        }
    ).then(res => {
      if(res.data.msg !='success'){
            alert(res.data.msg)
          }
          else{
      console.log(res)
      let count = 0;
      this.list2= [];
      const result = res.data;
      result.data.forEach(item => {
        this.test_course.push(item.coursename);
        this.close_time.push(item.note);
        this.date1.push(item.testtime);
        count =count +1;
        this.list2.push(count);
      });}
      // this.notice_detail.push(result.data);
    });

  //     axios({
  //             url: "http://localhost:8080/notice/getStudentNotice",
  //             method: 'post',
  //             headers: {
  //               'Content-Type': 'application/json'
  //             },
  //             params: {
  //               phone: localStorage.getItem('ms_username')

  // }
  // }
  //   )
  //       .then(response => {
  //         if(res.data.msg !='success'){
  //           alert(res.data.msg)
  //         }
  //         else{
  //         console.log(response);
  //         response.data.data.records.forEach(item=>{
  //           this.tableData.push(item)
  //           this.totalPages = this.totalPages+1
  //         })
  //         console.log(this.tableData);}
  //       })
  },
  data () {
    return {
      // tableData:[]
      tableData:[],
      list: null,
      listLoading: true,
      test_course:[],
      close_time:[],
      activeNames:['1'],
      date1:[],
      // notice_detail:["离散数学由线下考试更改为线上考试，考试时间待定"],
      notice_detail:[],
      list1:[],
      list2:[],
      pageSize:6,
      currentPage:1,
      totalPages:0
    }
  }
}
// this.$http({
//   url: this.$http.adornUrl('后台返回连接地址'),
//   method: 'get',
//   params: this.$http.adornParams({
//     'keyword': '男',
//     'page': 1,
//     'limit': 10
//   })
// }).then(({data}) => {
//   this.tableData = data.page.list    // 注意这里，后台返回的json格式不一样写的就不一样
//   console.log(JSON.stringify(data))
// }).catch(function (error) {
//   console.log(error)
// })
</script>


<style>
.ready{
  width: 1190px;
  margin-top: 20px;

}
.body{
  position: absolute;
  left: 250px;
  right: 0;
  top: 70px;
  bottom: 0;
  padding: 10px 20px;
  -webkit-transition: left .3s ease-in-out;
  transition: left .3s ease-in-out;
  background: #f0f0f0;
}
.content{
  padding: 20px;
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
}
.pagination-container{
  margin-left: 870px;
}
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}
.title1{
  border-left:solid 10px #2d8cf0;
  padding-left: 5px;
  margin-top: 40px;
}
.title2{
  border-left:solid 10px #2d8cf0;
  padding-left: 5px;
  margin-top: 40px;
}
.close{
  margin-top: 20px;
}
.title_style{
  font-weight: 600;
}
.operation{
  margin-left: 930px;
  font-size: 14px;
}
.title3{
  border-left:solid 10px #2d8cf0;
  padding-left: 5px;
}
.notice{
  margin-top: 20px;
  color: black !important;
}
</style>

