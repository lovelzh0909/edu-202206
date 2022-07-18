<template>
<div class="content-box">
  <div class="container">
    <p>用户角色权限管理</p>

    <div class="table-container">

  <el-table
    ref="filterTable"
    :data="tableData"
    border
    header-row-class-name="tableHeader"
    style="width: 95%">
    <el-table-column
      prop="role"
      label="角色"
      sortable
      align="center"
      width="180"
      column-key="date"
      :filters="[{text: 'admin', value: 'admin'}, {text: 'teacher', value: 'teacher'}, {text: 'student', value: 'student'}]"
      :filter-method="filterHandler"
    >
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      align="center"
      width="180">
    </el-table-column>
    <el-table-column
      prop="phone"
      label="账号/电话号码"
      align="center"
      width="180">
    </el-table-column>
    <el-table-column
      prop="studentId"
      label="学号/工号"
      align="center"
      width="180">
    </el-table-column>
    <el-table-column
      prop="classroom"
      label="所在班级"
      align="center"
      width="180">
    </el-table-column>
    <el-table-column
      prop="school"
      label="所在机构"
      align="center"
      width="180">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      align="center"
      width="250">
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
          @click="deleteRecord(scope.row.phone) "
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
            title="修改角色信息"
            :visible.sync="dialogVisible"
            width="38%"
            :before-close="handleClose">
          <section class="update">
            <el-form :model="form" ref="form" label-width="18%">
              <el-form-item label="角色" style="margin-top:20px">
                <el-input v-model="form.role" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="姓名">
                <el-input v-model="form.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="账号/电话号码">
                    <!-- <span >账号/电话号码：</span> -->
                    
                <el-input v-model="form.phone" style="width:80%" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="学号/工号">
                <el-input v-model="form.studentId" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="所在班级">
                <el-input v-model="form.classroom" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="所在机构">
                <el-input v-model="form.school" style="width:80%"></el-input>
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
import {post1Request} from "@/utils/request";
import {post2Request} from "@/utils/request";
import {getRequest} from "@/utils/request";
  export default {
    data() {
      return {
        dialogVisible: false,
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
        //获取所有用户信息
        getInfo(){
            const _this = this
            getRequest('user/all').then(function (res) {

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
        //     this.$axios({
        // url: 'http://localhost:8080/user/all' ,
        // method: 'get',
        // headers: {
        //   'Content-Type': 'application/json'
        // },
        
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
        ...this.form,
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
    deleteRecord(phone) {
      this.$confirm("确定删除该记录吗,该操作不可逆！！！", "删除提示", {
        confirmButtonText: '确定删除',
        cancelButtonText: '算了,留着',
        type: 'danger'
      }).then(() => { //确认删除
       const data={
        phone:phone,
      }
      postRequest('user/delete',data).then(res => {
          this.getInfo()
        })
      }).catch(() => {

      })
      //   this.$axios({
      //     url: 'http://localhost:8080/user/delete',
      //     method: 'post',
      //     data: {phone:phone},
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