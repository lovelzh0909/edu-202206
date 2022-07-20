<template>
<div class="content-box">
  <div class="container">
    <p>组织机构管理</p>

    
        <el-popover
            placement="right"
            width="800"
           
            trigger="click">
        <el-button slot="reference" type="primary" icon="el-icon-plus" size="small" style="margin-left:74%" round>添加机构</el-button>
        <h3>请填写组织机构信息:</h3>
        <section class="add">
          <el-form ref="form1" :model="form1" :rules="examInfoRules" label-width="20%" style="width:80%">
            <el-form-item label="机构名称" prop="source" style="margin-top:38px">
              <el-input v-model="form1.school"></el-input>
            </el-form-item>
            <el-form-item label="负责人联系电话" prop="description">
              <el-input v-model="form1.telephone"></el-input>
            </el-form-item>

            
            <el-form-item>
              <el-button type="primary" @click="onSubmit()" size="mini">添 加</el-button>
              <el-button type="info" @click="cancel()" size="mini">重 置</el-button>
              <!-- <el-button @click="returnback()" type="primary" style="margin-left">返回</el-button> -->
            </el-form-item>
          </el-form>
        </section>
      </el-popover>

    <div class="table-container">

  <el-table
    ref="filterTable"
    :data="tableData"
    border
    header-row-class-name="tableHeader"
    style="width: 78%;margin-left:50px">

    <el-table-column fixed="left" label="序号" type="index" width="80"></el-table-column>
    <el-table-column
      prop="school"
      label="机构名称"
      align="center"
      width="180">
    </el-table-column>
    <el-table-column
      prop="telephone"
      label="负责人联系电话"
      align="center"
      width="180">
    </el-table-column>
    <el-table-column
      prop="teachernum"
      label="教师人数"
      align="center"
      width="180">
    </el-table-column>
    <el-table-column
      prop="studentnum"
      label="学生人数"
      align="center"
      width="180">
    </el-table-column>
    
    
    <el-table-column
      fixed="right"
      label="操作"
      align="center"
      width="280">
      <template slot-scope="scope">
        <el-button
          @click="edit(scope.row.phone) "
          type="primary"
          size="mini"
          plain
          >
          修改
        </el-button>
        <el-button
          @click="deleteRecord(scope.row.school) "
          type="danger"
          size="mini"
          plain
          >
          删除
        </el-button>
        
      </template>
    </el-table-column>
  </el-table>
          <!-- 修改对话框-->
        <el-dialog
            title="修改组织机构信息"
            :visible.sync="dialogVisible"
            width="38%"
            :before-close="handleClose">
          <section class="update">
            <el-form :model="form" ref="form" label-width="15%">
              
              <el-form-item label="名称">
                <el-input v-model="form.school" style="width:80%"></el-input>
              </el-form-item>
              
              <el-form-item label="联系电话">
                <el-input v-model="form.studentId" style="width:80%"></el-input>
              </el-form-item>
              
            </el-form>
          </section>
          <span slot="footer" class="dialog-footer" >

        <el-button @click="dialogVisible = false" >取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
      </span>

        </el-dialog>
  </div>
  </div>
</div>
</template>

<script>
import {postRequest} from "@/utils/request";
import {post2Request} from "@/utils/request";
  export default {
    data() {
      return {
        dialogVisible: false,
        //添加机构表单数据初始化
        form1: [{ 
        name: '',
        phone:''
        },],
        //补充的考试信息的表单验证
      examInfoRules: {
        name: [
          {
            required: true,
            message: '请输入组织机构名称',
            trigger: 'blur'
          }
        ],

        phone: [
          {
            required: true,
            message: '请输入负责人联系电话',
            trigger: 'blur'
          }
        ],
        
      },
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          tag: '家'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄',
          tag: '公司'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄',
          tag: '家'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄',
          tag: '公司'
        }],
        form: [{ //表单数据初始化
        role: '',
        name: null,
        phone: null,
        studentId: null,
        classroom: null,
        school: null,
      },],
      }
    },
    mounted(){
        this.getInfo()
    },
    methods: {

      handleClose(done) { //关闭提醒
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          }).catch(_ => {
      });
    },
        //提交添加的组织机构信息
            onSubmit() {
      // let examDate = this.formatTime(this.form.examDate)
      // this.form.examDate = examDate.substr(0,10)

      // this.$axios(`http://47.103.94.131:8089/examManagePaperId`).then(res => {
      //   this.form.paperId = res.data.data.paperId + 1 //实现paperId自增1
        const _this = this
        const data={
        ..._this.form1,
      }
      postRequest('school/save',data).then(res => {
          if (res.data.msg === 'success') {
            this.$message({
              message: '数据添加成功',
              type: 'success'
            })
            // this.form = res.data.data
            this.$router.go(0)
            
          }
        })
        // this.$axios({
        //   url: 'http://localhost:8080/school/save',
        //   method: 'post',
        //   data: {
        //     ..._this.form1,
           
        //   }
        // }).then(res => {
        //   if (res.data.msg === 'success') {
        //     this.$message({
        //       message: '数据添加成功',
        //       type: 'success'
        //     })
        //     // this.form = res.data.data
        //     this.$router.go(0)
            
        //   }
        // })

     },
      //重置按钮
    cancel() {
      this.form1 = {}
    },
        //获取所有用户信息
        getInfo(){
            const _this = this
      post2Request('school/list').then(function (res) {

            _this.tableData = res.data.data
            
            console.log('aaaa')
            
            console.log(_this.tableData)
            if(res.data.msg !='success'){
                alert('服务器错误，请稍后重试')
            }

          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
      //       this.$axios({
      //   url: 'http://localhost:8080/school/list' ,
      //   method: 'post',
      //   headers: {
      //     'Content-Type': 'application/json'
      //   },
        
      // }).then(function (res) {

      //       _this.tableData = res.data.data
            
      //       console.log('aaaa')
            
      //       console.log(_this.tableData)
      //       if(res.data.msg !='success'){
      //           alert('服务器错误，请稍后重试')
      //       }

      //     },
      //     function (err) {
      //       console.log(err);
      //       alert("服务器错误!稍后重试");
      //     })
        },
        
      //编辑信息
      edit(phone) { 
      this.dialogVisible = true
      this.form.phone = phone
      
    },
    //提交修改的信息
      submit() { 
      console.log(...this.form)
      // let form = this.form  
      const _this = this
      this.dialogVisible = false
       const data={
        ..._this.form1,
      }
      postRequest('user/update',data).then(res => {
        if (res.data.code === 200) {
          this.$message({ //成功修改提示
            message: '更新成功',
            type: 'success'
          })
        }
        this.getInfo()
      })
      // this.$axios({
      //   url: 'http://localhost:8080/user/update',
      //   method: 'post',
      //   data: {...this.form}
      // }).then(res => {
      //   if (res.data.code === 200) {
      //     this.$message({ //成功修改提示
      //       message: '更新成功',
      //       type: 'success'
      //     })
      //   }
      //   this.getInfo()
      // })
    },
        //删除该条信息
    deleteRecord(school) {
      this.$confirm("确定删除该记录吗,该操作不可逆！！！", "删除提示", {
        confirmButtonText: '确定删除',
        cancelButtonText: '算了,留着',
        type: 'danger'
      }).then(() => { //确认删除
      const data={
        school:school
      }
      postRequest('school/delete',data).then(res => {
          this.getInfo()
        })
      }).catch(() => {

      })
      //   this.$axios({
      //     url: 'http://localhost:8080/school/delete',
      //     method: 'post',
      //     data: {school:school},
      //   }).then(res => {
      //     this.getInfo()
      //   })
      // }).catch(() => {

      // })
    },
      resetDateFilter() {
        this.$refs.filterTable.clearFilter('date');
      },
      clearFilter() {
        this.$refs.filterTable.clearFilter();
      },
      formatter(row, column) {
        return row.phone;
      },
      filterTag(value, row) {
        return row.tag === value;
      },
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      }
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