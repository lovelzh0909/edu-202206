// 我的题库页面
<template>
  <div id="myExam">
    
    <div class="wrapper" style="margin-left: 5%">
      <ul class="top">
        <li class="order" style="">题库列表</li>
        
        <!-- <el-button size="small"  type="primary" @click="toPathaddquestionbank()" style="margin-left:80%">添加题库</el-button> -->
        <!-- <el-button size="small" plain type="primary" @click="toPathaddquestion()"  style="margin-left:0px">查看题库</el-button> -->
        <el-popover
          placement="right"
          width="1200"
          trigger="click">
          <div class="container" width="50%">
                    <p>题库-创建题库</p>
                    <section class="add">
                    <el-form ref="form" :model="form" :rules="bankInfoRules" label-width="20%" style="width:80%">
                      <el-form-item label="题库名称" prop="coursename">
                        <el-input v-model="form.coursename"></el-input>
                      </el-form-item>
                      <el-form-item label="题库介绍" prop="description">
                        <el-input v-model="form.description"></el-input>
                      </el-form-item>
                     
                      
                      
                      <el-form-item label="备注" prop="tips">
                        <el-input type="textarea" v-model="form.tips"></el-input>
                      </el-form-item>
                      <el-form-item>
                        <el-button type="primary" @click="onSubmit()">立即创建</el-button>
                        <el-button type="text" @click="cancel()">重置</el-button>
                        
                      </el-form-item>
                    </el-form>
                  </section>
                </div>
          <el-button slot="reference" type="primary" icon="el-icon-plus"  size="small" style="margin-left:680px;margin-top:20px">创建题库</el-button>
        </el-popover>
      </ul>
      <ul class="paper" >
        <li class="item" style="border-radius: 15px" v-for="(item,index) in form" :key="index"><el-button icon="el-icon-close" @click="deleteRecord(item.coursename)" circle style="margin-left:300px;margin-top:-60px;"></el-button>
          <h2  @click="toQuestionBank(item.coursename)">{{item.coursename}}
          <el-divider></el-divider>
          <p class="name">介绍：{{item.coursename}}-题库</p>
          </h2>
        </li>
        
      </ul>
      <!-- <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
        
      </div> -->
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  // name: 'myExam'
  data() {
    return {
      //总条数
      total:null,
      //创建题库表单初始化
       form: { //表单数据初始化
        coursename: null,
        description: null,
        institute: null,
        major: null,
        grade: null,
        examDate: null,
        totalTime: null,
        totalScore: null,
        type: null,
        tips: null,
        paperId: null,
      },
      loading: false,
      key: null, //搜索关键字
      allExam: null, //所有考试信息
      pagination: { //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 6 //每页条数
      },
      //补充的考试信息的表单验证
      bankInfoRules: {
        coursename: [
          {
            required: true,
            message: '请输入题库名称',
            trigger: 'blur'
          },
        ],

        description: [
          {
            required: true,
            message: '请输入题库介绍，比如“计算机网络题库一”',
            trigger: 'blur'
          },
        ],
        tips: [
          {
            required: true,
            message: '请输入题库备注，若无则输入“无”',
            trigger: 'blur'
          },
        ],
      }
    }
  },
  props: ['tagInfo'],
  created() {
    this.getCourseInfo()
    this.loading = true
  },
  // watch: {
    
  // },
  methods: {
    //删除题库事件
    deleteRecord(coursename) {


      this.$confirm("确定删除该记录吗,该操作不可逆！！！", "删除提示", {
        confirmButtonText: '确定删除',
        cancelButtonText: '算了,留着',

      }).then(() => { //确认删除
        this.$axios({
          url: 'http://localhost:8080/question/remove/bycoursename',
          method: 'post',

          params: {

            phone: localStorage.getItem('ms_username'),
            coursename
            },

        }).then(res => {
          console.log('coursename')
          console.log('phone')
          this.getCourseInfo()
        })
      }).catch(() => {

      })
    },
    //提交创建的题库
    onSubmit() {
      // for(let i =0;i<this.total;i++){
      //   if(this.form){

      //   }
      // }
      // let examDate = this.formatTime(this.form.examDate)
      // this.form.examDate = examDate.substr(0,10)
      // this.$axios(`http://47.103.94.131:8089/examManagePaperId`).then(res => {
      //   this.form.paperId = res.data.data.paperId + 1 //实现paperId自增1
        axios({
          url: 'http://localhost:8080/question/coursename/save',
          method: 'post',
          params: {
            ...this.form,
            phone: localStorage.getItem('ms_username')
          }
        }).then(res => {
          if(res.data.msg != 'success'){
          alert(res.data.msg)

        }
        else{
          
            this.$message({
              message: '数据添加成功',
              type: 'success'
            })
            this.$router.go(0)
            }
            // this.$router.push({path: '/test2/test2-1-3'})
          
        })
      // })
    },
    cancel() { //取消按钮
      this.form = {}
    },
    //获取当前所有题库信息
    getCourseInfo() { //分页查询所有试卷信息
      const _this = this;
      // this.$axios(`http://localhost:8080/papers/getteacherallpaper/${this.pagination.current}/${this.pagination.size}`).then(res => {
      axios({
        url: `http://localhost:8080/question/getCoursename/`,
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params: {
          phone: localStorage.getItem('ms_username')
        }
      }).then(function(res) {
        
            console.log(res);
            _this.form = res.data.data
            _this.total = res.data.data.length
        
          },)
    },
    // getCourseInfo() {
    //   this.$axios(`http://47.103.94.131:8089/exams/}`).then(res => {
    //     this.pagination = res.data.data
    //     this.loading = false
    //     console.log(this.pagination)
    //   }).catch(error => {
    //     console.log(error)
    //   })
    // },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val
      this.getCourseInfo()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getCourseInfo()
    },
    
    //跳转到试卷详情页
    toQuestionBank(coursename) {
      this.$router.push({path: '/QuestionBank', query: {coursename: coursename}})
      console.log(coursename)
    },
    toPathaddquestionbank(){
      this.$router.push({
        path: '/addquestionbank'
      });
    },
    toPathaddquestion(){
      this.$router.push({
        path: '/getbank'
      });
    }
  }
}
</script>

<style lang="scss" scoped>
.pagination {
  padding: 10px 0px 10px 0px;
  .el-pagination {
    display: flex;
    justify-content: center;
  }
}
.paper {
  h2 {
    cursor: pointer;
    color:rgb(0, 132, 255);
    margin-top:-30px;
  }
}
.paper .item a {
  color: #000;
}
.wrapper .top .order {
  cursor: pointer;
  width:100px;
  margin-left:10px;
}
.wrapper .top .order:hover {
  color: #0195ff;
  border-bottom: 1px solid #0195ff;
}
.wrapper .top .order:visited {
  color: #0195ff;
  border-bottom: 1px solid #0195ff;
}
.item .info i {
  margin-right: 5px;
  color: #0195ff;
}
.item .info span {
  margin-right: 14px;
}
.paper .item {
  width: 380px;
  border-radius: 4px;
  padding: 20px 30px;
  border: 1px solid #eee;
  box-shadow: 0 0 4px 2px rgba(217,222,234,0.3);
  transition: all 0.6s ease;
}
.paper .item:hover {
  box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
  transform: scale(1.03);
}
.paper .item .info {
  font-size: 14px;
  color: #88949b;
}
.paper .item .name {
  font-size: 14px;
  color: #88949b;
}
.paper * {
  margin: 20px 0;
}
.wrapper .paper {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.top .el-icon-search {
  position: absolute;
  right: 10px;
  top: 10px;
}
.top .icon {
  position: relative;
}
.wrapper .top {
  border-bottom: 1px solid #eee;
  margin-bottom: 20px;
  margin-top:-10px;
  margin-left:20px;
}
#myExam .search-li {
  margin-left: auto;
}
.top .search-li {
  margin-left: auto;
}
.top li {
  display: flex;
  align-items: center;
}

.wrapper .top {
  display: flex;
}
.wrapper .top li {
  margin: 20px;
}
#myExam {
  width: 1000px;
  margin: 0 auto;
}
#myExam .title {
  margin: 20px;
}
#myExam .wrapper {
  background-color: #fff;
}
</style>
