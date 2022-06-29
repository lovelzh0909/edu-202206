// 点击题库后的详细信息
<template>
  <div id="msg">
    <div class="title">
      <span>题库列表</span>
      <span>/ {{ examData.coursename}}</span>
    </div>
    <div class="wrapper">
      <ul class="top">
        <li class="example">{{ examData.coursename}}</li>
        <!-- <li><i class="iconfont icon-pen-"></i></li> -->
        <!-- <li><i class="iconfont icon-share"></i></li> -->
        <!-- <li class="right">
          <div>
            <span class="count">总共</span>
            <span class="score">{{topicCount[0]+topicCount[1]+topicCount[2]}}</span><span>题</span>
          </div>
        </li> -->
        <ul class="bottom" style="margin-left:75%">
        <li class="right"><el-button  icon="el-icon-plus" type="medium" round @click="add(examData.paperId,examData.coursename)">增加题目</el-button></li>
      </ul>
      </ul>
      
      
    </div>
    <div class="content">

    <div class="exam">
    <p>
          <vxe-radio-group v-model="selectTab">
            <vxe-radio-button label="tab1" content="单选题"></vxe-radio-button>
            <vxe-radio-button label="tab2" content="多选题"></vxe-radio-button>
            <vxe-radio-button label="tab5" content="判断题"></vxe-radio-button>
            <vxe-radio-button label="tab3" content="填空题"></vxe-radio-button>
            <vxe-radio-button label="tab4" content="简答题"></vxe-radio-button>
          </vxe-radio-group>
        </p>

        <div v-show="selectTab === 'tab1'">
          <vxe-table
            border
            :sync-resize="selectTab"
            :data="tableData1">
            <vxe-column type="seq" width="60"></vxe-column>
            <vxe-column field="id" sortable title="题目id"></vxe-column>
            <vxe-column field="difficulty" title="难度系数" ></vxe-column>
            <vxe-column field="pointId" title="所属章节" ></vxe-column>
            <vxe-column field="stem" title="标题"></vxe-column>
            <vxe-column field="choiceA" title="答案A" show-overflow></vxe-column>
            <vxe-column field="choiceB" title="答案B" show-overflow></vxe-column>
            <vxe-column field="choiceC" title="答案C" show-overflow></vxe-column>
            <vxe-column field="choiceD" title="答案D" show-overflow></vxe-column>
            <!-- <vxe-column field="choiceE" title="答案E" show-overflow></vxe-column>
            <vxe-column field="choiceF" title="答案F" show-overflow></vxe-column>
            <vxe-column field="choiceG" title="答案G" show-overflow></vxe-column> -->
            <vxe-column field="answer" title="正确答案" show-overflow></vxe-column>
            <vxe-column field="jiexi" title="答案解析" show-overflow></vxe-column>
          </vxe-table>
          <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination1.current"
          :page-sizes="[6, 10]"
          :page-size="pagination1.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination1.total"
          class="page"
          style="margin-left:0%"
        ></el-pagination>
        </div>

        <div v-show="selectTab === 'tab2'">
          <vxe-table
            border
            :sync-resize="selectTab"
            :data="tableData2">
            <vxe-column type="seq" width="60"></vxe-column>
            <vxe-column field="id" sortable title="题目id"></vxe-column>
            <vxe-column field="difficulty" title="难度系数" ></vxe-column>
            <vxe-column field="pointId" title="所属章节" ></vxe-column>
            <vxe-column field="stem" title="标题"></vxe-column>
            <vxe-column field="choiceA" title="答案A" show-overflow></vxe-column>
            <vxe-column field="choiceB" title="答案B" show-overflow></vxe-column>
            <vxe-column field="choiceC" title="答案C" show-overflow></vxe-column>
            <vxe-column field="choiceD" title="答案D" show-overflow></vxe-column>
            <!-- <vxe-column field="choiceE" title="答案E" show-overflow></vxe-column>
            <vxe-column field="choiceF" title="答案F" show-overflow></vxe-column>
            <vxe-column field="choiceG" title="答案G" show-overflow></vxe-column> -->
            <vxe-column field="answer" title="正确答案" show-overflow></vxe-column>
            <vxe-column field="jiexi" title="答案解析" show-overflow></vxe-column>
          </vxe-table>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagination2.current"
            :page-sizes="[6, 10]"
            :page-size="pagination2.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination2.total"
            class="page"
            style="margin-left:0%"
          ></el-pagination>
        </div>

        <div v-show="selectTab === 'tab5'">
          <vxe-table
            border
            :sync-resize="selectTab"
            :data="tableData5">
            <vxe-column type="seq" width="60"></vxe-column>
            <vxe-column field="id" sortable title="题目id"></vxe-column>
            <vxe-column field="difficulty" title="难度系数" ></vxe-column>
            <vxe-column field="pointId" title="所属章节" ></vxe-column>
            <vxe-column field="stem" title="标题"></vxe-column>
            <vxe-column field="answer" title="正确答案" show-overflow></vxe-column>
            <vxe-column field="jiexi" title="答案解析" show-overflow></vxe-column>
          </vxe-table>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagination5.current"
            :page-sizes="[6, 10]"
            :page-size="pagination5.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination5.total"
            class="page"
            style="margin-left:0%"
          ></el-pagination>
        </div>
        <div v-show="selectTab === 'tab3'">
          <vxe-table
            border
            :sync-resize="selectTab"
            :data="tableData3">
            <vxe-column type="seq" width="60"></vxe-column>
            <vxe-column field="id" sortable title="题目id"></vxe-column>
            <vxe-column field="difficulty" title="难度系数" ></vxe-column>
            <vxe-column field="pointId" title="所属章节" ></vxe-column>
            <vxe-column field="stem" title="标题"></vxe-column>
            <vxe-column field="answer" title="正确答案" show-overflow></vxe-column>
            <vxe-column field="jiexi" title="答案解析" show-overflow></vxe-column>
          </vxe-table>
          <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination3.current"
          :page-sizes="[6, 10]"
          :page-size="pagination3.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination3.total"
          class="page"
          style="margin-left:0%"
        ></el-pagination>
        </div>
        <div v-show="selectTab === 'tab4'">
          <vxe-table
            border
            :sync-resize="selectTab"
            :data="tableData4">
             <vxe-column type="seq" width="60"></vxe-column>
            <vxe-column field="id" sortable title="题目id"></vxe-column>
            <vxe-column field="difficulty" title="难度系数" ></vxe-column>
            <vxe-column field="pointId" title="所属章节" ></vxe-column>
            <vxe-column field="stem" title="标题"></vxe-column>
            <vxe-column field="answer" title="正确答案" show-overflow></vxe-column>
            <vxe-column field="jiexi" title="答案解析" show-overflow></vxe-column>
          </vxe-table>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagination4.current"
            :page-sizes="[6, 10]"
            :page-size="pagination4.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination4.total"
            class="page"
            style="margin-left:0%"
          ></el-pagination>
        </div>
        </div>
    
    <el-button @click="returnback()" type="primary" size="small">返回</el-button>

  </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //题库测试表格数据
      selectTab: 'tab1',
              tableData1: [
                { id: 10001, name: 'Test1', role: 'Develop', sex: '0', age: 28, amount: 888, address: 'test abc' },
                { id: 10002, name: 'Test2', role: 'Test', sex: '1', age: 22, amount: 666, address: 'Guangzhou' },
                { id: 10003, name: 'Test3', role: 'PM', sex: '1', age: 32, amount: 89, address: 'Shanghai' },
                { id: 10004, name: 'Test4', role: 'Designer', sex: '0', age: 23, amount: 1000, address: 'test abc' },
                { id: 10005, name: 'Test5', role: 'Develop', sex: '0', age: 30, amount: 999, address: 'Shanghai' }
              ],
              tableData2:[],
              tableData3:[],
              tableData4:[],
              tableData5:[],
      dialogVisible: false, //对话框属性
      activeName: '0',  //默认打开序号
      topicCount: [],//每种类型题目的总数
      score: [],  //每种类型分数的总数
      examData: { //考试信息
         coursename : this.$route.query.coursename //获取路由传递过来的试卷编号
         //coursename: null,
        // totalScore: null,
      },
      topic: {  //试卷信息

      },
      pagination1: {
        //分页后的考试信息
        quesTypeId:1,
        current: 1, //当前页
        total: null, //记录条数
        size: 10 //每页条数
      },
      pagination2: {
        //分页后的考试信息
        quesTypeId:2,
        current: 1, //当前页
        total: null, //记录条数
        size: 10 //每页条数
      },
      pagination3: {
        //分页后的考试信息
        quesTypeId:3,
        current: 1, //当前页
        total: null, //记录条数
        size: 10 //每页条数
      },
      pagination4: {
        //分页后的考试信息
        quesTypeId:4,
        current: 1, //当前页
        total: null, //记录条数
        size: 10 //每页条数
      },
      pagination5: {
        //分页后的考试信息
        quesTypeId:5,
        current: 1, //当前页
        total: null, //记录条数
        size: 10 //每页条数
      },
    }
  },
  created() {
    this.getQuestionInfo1();
    this.getQuestionInfo2();
    this.getQuestionInfo3();
    this.getQuestionInfo4();
    this.getQuestionInfo5();
  },
  mounted() {
    this.init()
    this.getQuestionInfo();
  },
  methods: {
    
    //初始化页面数据
    // init() {
    //   let coursename = this.$route.query.coursename //获取路由传递过来的试卷编号
    //   this.$axios(`http://47.103.94.131:8089/exam/${coursename}`).then(res => {  //通过coursename请求试卷详细信息
    //     res.data.data.examDate = res.data.data.examDate.substr(0,10)
    //     this.examData = { ...res.data.data}
    //     let paperId = this.examData.paperId
    //     this.$axios(`http://47.103.94.131:8089/paper/${paperId}`).then(res => {  //通过paperId获取试题题目信息
    //       this.topic = {...res.data}
    //       let keys = Object.keys(this.topic) //对象转数组
    //       keys.forEach(e => {
    //         let data = this.topic[e]
    //         this.topicCount.push(data.length)
    //         let currentScore = 0 
    //         for(let i = 0; i< data.length; i++) { //循环每种题型,计算出总分
    //           currentScore += data[i].score
    //         }
    //         this.score.push(currentScore) //把每种题型总分存入score
    //       })
    //     })
    //   })
    // },
    toAnswer(id) {
      this.$router.push({path:"/answer",query:{coursename: id}})
    },
    getQuestionInfo1() {
      let coursename = this.$route.query.coursename //获取路由传递过来的试卷编号
      console.log('asd asd ')
      const _this = this;
      //分页查询所有试卷信息
      this.$axios({
        url: `http://localhost:8080/question/get/bycourse/${this.pagination1.quesTypeId}/${this.pagination1.current}/${this.pagination1.size}`,
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params: {
          phone: localStorage.getItem('ms_username'),
          coursename
        }
      }).then(function(res) {
            console.log(res);
            _this.tableData1 = res.data.data.records
          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
    },
    getQuestionInfo2() {
      let coursename = this.$route.query.coursename //获取路由传递过来的试卷编号
      console.log('asd asd ')
      const _this = this;
      //分页查询所有试卷信息
      this.$axios({
        url: `http://localhost:8080/question/get/bycourse/${this.pagination2.quesTypeId}/${this.pagination2.current}/${this.pagination2.size}`,
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params: {
          phone: localStorage.getItem('ms_username'),
          coursename
        }
      }).then(function(res) {
            console.log(res);
            _this.tableData2 = res.data.data.records
          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
    },
    getQuestionInfo3() {
      let coursename = this.$route.query.coursename //获取路由传递过来的试卷编号
      console.log('asd asd ')
      const _this = this;
      //分页查询所有试卷信息
      this.$axios({
        url: `http://localhost:8080/question/get/bycourse/${this.pagination3.quesTypeId}/${this.pagination3.current}/${this.pagination3.size}`,
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params: {
          phone: localStorage.getItem('ms_username'),
          coursename
        }
      }).then(function(res) {
            console.log(res);
            _this.tableData3 = res.data.data.records
          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
    },
    getQuestionInfo4() {
      let coursename = this.$route.query.coursename //获取路由传递过来的试卷编号
      console.log('asd asd ')
      const _this = this;
      //分页查询所有试卷信息
      this.$axios({
        url: `http://localhost:8080/question/get/bycourse/${this.pagination4.quesTypeId}/${this.pagination4.current}/${this.pagination4.size}`,
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params: {
          phone: localStorage.getItem('ms_username'),
          coursename
        }
      }).then(function(res) {
            console.log(res);
            _this.tableData4 = res.data.data.records
          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
    },
    getQuestionInfo5() {
      let coursename = this.$route.query.coursename //获取路由传递过来的试卷编号
      console.log('asd asd ')
      const _this = this;
      //分页查询所有试卷信息
      this.$axios({
        url: `http://localhost:8080/question/get/bycourse/${this.pagination5.quesTypeId}/${this.pagination5.current}/${this.pagination5.size}`,
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params: {
          phone: localStorage.getItem('ms_username'),
          coursename
        }
      }).then(function(res) {
            console.log(res);
            _this.tableData5 = res.data.data.records
          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getQuestionInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getQuestionInfo();
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 == 0) {
        return "warning-row";
      } else {
        return "success-row";
      }
    },
    add(paperId,coursename) { //增加题目
      this.$router.push({path:'/addquestion',query: {paperId: paperId,subject:coursename}})
    },
    //返回题库列表
    returnback(){
      this.$router.go(-1)
    },
  }
}
</script>

<style lang="scss" scoped>
.exam {
  padding: 0px 0px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit {
    margin-left: 0px;
  }
  .el-table tr {
    background-color: #DD5862 !important;
  }
}
  .el-table .warning-row {
    background: #000 !important;
    
  }

  .el-table .success-row {
    background: #DD5862;
  }
.bottom {
  .right{
    .el-button{
      color: #409EFF;
      border-color: #c6e2ff;
      background-color: #ecf5ff;
    }
  }
}
.right {
  margin-left: auto;
}
.inner .contenti .question {
  margin-left: 40px;
  color: #9a9a9a;
  font-size: 14px;
}
.content .inner .titlei {
  margin-left: 20px;
  font-size: 16px;
  color: #88949b;
  font-weight: bold;
}
.content .title .time {
  font-size: 16px;
  margin-left: 420px;
  color: #999;
}
.content .stitle {
  background-color: #0195ff;
}
.content .title span {
  margin-right: 10px;
}
#msg .content .title {
  font-size: 20px;
  margin: 0px;
  display: flex;
  align-items: center;
}
.content {
  margin-top: 20px;
  background-color: #fff;
}
.content .header {
  padding: 10px 30px;
}
.wrapper .info {
  margin: 20px 0px 0px 20px;
  border-top: 1px solid #eee;
  padding: 20px 0px 10px 0px;
}
.wrapper .info a {
  color: #88949b;
  font-size: 14px;
}
.wrapper .info a:hover {
  color: #0195ff;
}
.wrapper .bottom .btn {
  cursor: pointer;
  padding: 5px 10px;
  border: 1px solid #88949b;
  border-radius: 4px;
} 
.wrapper .bottom {
  display: flex;
  margin-left: 20px;
  color: #999;
  font-size: 14px;
  align-items: center;
}
.wrapper .bottom li {
  margin-right: 14px;
}
#msg {
  background-color: #eee;
  width: 90%;
  margin: 0 auto;
}
#msg .title {
  margin: 20px;
}
#msg .wrapper {
  background-color: #fff;
  padding: 10px;
}
.wrapper .top {
  display: flex;
  margin: 20px;
  align-items: center;
}
.wrapper .top .right {
  margin-left: auto;
}
.wrapper .top .example {
  color: #333;
  font-size: 22px;
  font-weight: 700;
}
.wrapper .top li i {
  margin-left: 20px;
  color: #88949b;
}
.wrapper .right .count {
  margin-right: 60px;
  color: #fff;
  padding: 4px 10px;
  background-color: #24afff;
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
  border: 1px solid #56befa;
}
.wrapper .right .score {
  position: absolute;
  left: 53px;
  top: -5px;
  padding: 1px 12px;
  font-size: 20px;
  color: #061a25;
  border: 1px dashed #2bacf7;
  border-left: none;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  font-weight: bold;
}
.wrapper .right div {
  position: relative;
}
</style>
