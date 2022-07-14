//查询所有试卷
<template>
  <div class="content-box">
    <div class="container">
      <li class="order" style="">试卷列表</li>
      
        <!-- <el-button @click="toPathadd()" type="primary" size="small" style="margin-left:88%" icon="el-icon-plus" >创建试卷</el-button> -->
        
        <el-popover
            placement="right"
            width="1200"
            trigger="click">
      <el-button slot="reference" type="primary" icon="el-icon-plus" size="small" style="margin-left:88%" round>创建试卷</el-button>
        <p>试卷-创建试卷</p>
        <section class="add">
          <el-form ref="form" :model="form" :rules="examInfoRules" label-width="20%" style="width:80%">
            <el-form-item label="试卷名称" prop="source">
              <el-input v-model="form.source"></el-input>
            </el-form-item>
            <el-form-item label="试卷介绍" prop="description">
              <el-input v-model="form.description"></el-input>
            </el-form-item>

            <el-form-item label="持续时间" prop="totalTime">
              <el-input v-model="form.totalTime"></el-input>
            </el-form-item>
            <el-form-item label="总分" prop="totalScore">
              <el-input v-model="form.totalScore"></el-input>
            </el-form-item>

            <el-form-item label="备注" prop="note">
              <el-input type="textarea" v-model="form.note"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit()">立即创建</el-button>
              <el-button type="text" @click="cancel()">重置</el-button>
              <!-- <el-button @click="returnback()" type="primary" style="margin-left">返回</el-button> -->
            </el-form-item>
          </el-form>
        </section>
      </el-popover>
   

      <div class="paper">
        <el-table  header-row-class-name="tableHeader" :data="pagination.records.slice((pagination.current-1)*pagination.size,pagination.current*pagination.size)" border style="margin-top:10px;margin-left:10px;border-radius: 10px">
          <el-table-column fixed="left" label="序号" align="center" type="index" width="80"></el-table-column>
          <el-table-column prop="paperId" sortable align="center" label="试卷id" width="90"></el-table-column>
          <el-table-column prop="source" label="试卷名称" align="center" width="150"></el-table-column>
          <el-table-column prop="description" label="试卷介绍" align="center" width="150"></el-table-column>
          <!-- <el-table-column prop="institute" label="所属学院" width="120"></el-table-column>
          <el-table-column prop="major" label="所属专业" width="120"></el-table-column>
          <el-table-column prop="grade" label="年级" width="100"></el-table-column> -->
          <!-- <el-table-column prop="examDate" label="考试日期" width="120"></el-table-column> -->
          <el-table-column prop="totalTime" label="持续时间(min)" align="center" width="120"></el-table-column>
          <el-table-column prop="createTime" sortable label="创建时间" align="center" width="160"></el-table-column>
          <el-table-column prop="totalScore" label="总分" align="center" width="120"></el-table-column>
          <!-- <el-table-column prop="type" label="试卷类型" width="120"></el-table-column> -->
          <el-table-column prop="note" label="备注" align="center" width="360"></el-table-column>
          <el-table-column fixed="right" label="操作"  width="180" align="center">
            <template slot-scope="scope">
              <el-button @click="toPathtestpaper(scope.row.paperId,scope.row.totalScore)" type="primary" size="small" circle>组卷</el-button>
              <!-- <el-button @click="edit(scope.row.paperId)" type="success" size="small" circle>修改</el-button>
              <el-button @click="copy(scope.row.paperId)" type="info" size="small" circle>复制</el-button>
              <el-button @click="toLook(scope.row.paperId)" type="warning" size="small" circle>预览</el-button> -->
              <el-button @click="deleteRecord(scope.row.paperId)" type="danger" size="small" circle>删除</el-button>
            </template>
          </el-table-column>
        </el-table>
    

        <!-- 编辑对话框-->
        <el-dialog
            title="编辑试卷信息"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
          <section class="update">
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="试卷名称">
                <el-input v-model="form.source"></el-input>
              </el-form-item>
              <el-form-item label="试卷介绍">
                <el-input v-model="form.description"></el-input>
              </el-form-item>
              <!-- <el-form-item label="所属学院">
                <el-input v-model="form.institute"></el-input>
              </el-form-item>
              <el-form-item label="所属专业">
                <el-input v-model="form.major"></el-input>
              </el-form-item>
              <el-form-item label="年级">
                <el-input v-model="form.grade"></el-input>
              </el-form-item>
              <el-form-item label="考试日期">
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="选择日期" v-model="form.examDate" style="width: 100%;"></el-date-picker>
                </el-col>
              </el-form-item> -->
              <el-form-item label="持续时间">
                <el-input v-model="form.totalTime"></el-input>
              </el-form-item>
              <el-form-item label="总分">
                <el-input v-model="form.totalScore"></el-input>
              </el-form-item>
              <!-- <el-form-item label="试卷类型">
                <el-input v-model="form.type"></el-input>
              </el-form-item> -->
              <el-form-item label="备注">
                <el-input type="textarea" v-model="form.note"></el-input>
              </el-form-item>
            </el-form>
          </section>
          <span slot="footer" class="dialog-footer">

        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
      </span>

        </el-dialog>
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
    </div>
    
  </div>
</template>

<script>

import axios from "axios";

export default {
  data() {
    return {
      // form: {}, //保存点击以后当前试卷的信息
      pagination: {
        total: 0,
        current:1,
        size:2,
        records:[]

      },
      dialogVisible: false,
      form: [{ //表单数据初始化
        source: '',
        description: null,



        examDate: null,
        totalTime: null,
        totalScore: null,
        note: null,
        paperId: null,
        phone: localStorage.getItem('ms_username')

      },],

      form1: [],
      //补充的考试信息的表单验证
      examInfoRules: {
        source: [
          {
            required: true,
            message: '请输入试卷名称',
            trigger: 'blur'
          }
        ],

        description: [
          {
            required: true,
            message: '请输入试卷介绍，比如“计算机网络期末考试”',
            trigger: 'blur'
          }
        ],
        totalTime: [
          {
            required: true,
            message: '请输入试卷持续时长',
            trigger: 'blur'
          }
        ],
        totalScore: [
          {
            required: true,
            message: '请输入试卷总分',
            trigger: 'blur'
          }
        ],
        note: [
          {
            required: true,
            message: '请输入备注，若无则输入“无”',
            trigger: 'blur'
          }
        ],
      }
    }
  },
  created() {
    this.getExamInfo()
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


    onSubmit() {
      // let examDate = this.formatTime(this.form.examDate)
      // this.form.examDate = examDate.substr(0,10)

      // this.$axios(`http://47.103.94.131:8089/examManagePaperId`).then(res => {
      //   this.form.paperId = res.data.data.paperId + 1 //实现paperId自增1
        const _this = this
        this.$axios({
          url: 'http://localhost:8080/papers/save',
          method: 'post',
          data: {
            ..._this.form,
            phone: localStorage.getItem('ms_username')
          }
        }).then(res => {
          if (res.data.msg === 'success') {
            this.$message({
              message: '数据添加成功',
              type: 'success'
            })
            this.form = res.data.data
            this.$router.go(0),
            this.$router.push({path: '/test2/test2-1-4'})
          }
        })

     },
    cancel() { //取消按钮
      this.form = {}
    },

    //复制试卷
    copy(paperId){
       this.$axios({
        url: 'http://localhost:8080/papers/copy',
        method: 'post',
        params:{paperId:paperId}
      }).then(res => {
        if (res.data.msg === 'success') {
          alert('复制成功，即将刷新页面')
          this.$router.go(0)
        }
        
      })
    },
    edit(paperId) { //编辑试卷
      this.dialogVisible = true
      localStorage.setItem('paperId',paperId);
      
    },
    //跳转至预览界面
    toLook(paperId){
      this.$router.push({path: '/look-paper',query: {paperId: paperId}})

    },
    toPathtestpaper(paperId,totalScore) {
      this.$router.push({
        path:'/testpaper',
        query: {
          paperId: paperId,
          totalScore:totalScore
          },
        
      });
      console.log(localStorage.getItem('paperId'))
    },
    handleClose(done) { //关闭提醒
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          }).catch(_ => {
      });
    },

    submit() { //提交修改后的试卷信息

      this.dialogVisible = false
      this.$axios({
        url: `http://localhost:8080/papers/update/${localStorage.getItem('paperId')}`,
        method: 'post',
        data: {
          phone: localStorage.getItem('ms_username'),
          ...this.form,

        }
      }).then(res => {
        if (res.data.code === 200) {
          this.$message({ //成功修改提示
            message: '更新成功',
            type: 'success'
          })
        }
        this.getExamInfo()
      })
    },
    deleteRecord(paperId) {
      this.$confirm("确定删除该记录吗,该操作不可逆！！！", "删除提示", {
        confirmButtonText: '确定删除',
        cancelButtonText: '算了,留着',
        type: 'danger'
      }).then(() => { //确认删除
        this.$axios({
          url: 'http://localhost:8080/papers/remove',
          method: 'post',
          params: {paperId},
        }).then(res => {
          this.getExamInfo()
        })
      }).catch(() => {

      })
    },
    getExamInfo() { //分页查询所有试卷信息
      const _this = this;
      // this.$axios(`http://localhost:8080/papers/getteacherallpaper/${this.pagination.current}/${this.pagination.size}`).then(res => {
      axios({
        url: `http://localhost:8080/papers/getteacherallpaper/${this.pagination.current}/${this.pagination.size}`,
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params: {
          phone: localStorage.getItem('ms_username')
        }
      }).then(function(res) {
            console.log(res);
            _this.pagination = res.data.data
            console.log(_this.pagination.records);
            _this.pagination.total = _this.pagination.records.length
          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
    },
    

  },
};
</script>
<style lang="scss" scoped>
::v-deep .tableHeader th {
  // background-color: #e03030;
  color: #373838;
  border-bottom: '1px RGBA(199, 199, 199, 1) solid'
  
}
.exam {
  padding: 0px 40px;

  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .edit {
    margin-left: 20px;
  }
}

.add {
  padding: 0px 40px;
  width: 1200px;
}

</style>
