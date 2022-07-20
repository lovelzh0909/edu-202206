<template>
<div class="content-box">
  <div class="container">
  <el-container>

    <el-main>
      
    
      <!--设置考试信息-->
      <el-card v-show="curStep === 2">
        <!-- <el-radio-group v-model="examAuthority" size="medium">
          <el-radio :label="1" border>完全公开</el-radio>
          <el-radio :label="2" border>需要密码</el-radio>
        </el-radio-group>

        <el-alert style="margin-top: 8px"
                  :title="examAuthority === 1? '开放考试，无需密码即可进行考试！' : '输入正确密码方能进入考试！'"
                  type="warning">
        </el-alert>

        <el-input style="margin-top: 15px;width: 20%" v-model="examPassword" v-show="examAuthority === 2"
                  type="password" show-password placeholder="输入考试密码"></el-input> -->
        <el-form :model="examInfo" :rules="examInfoRules" ref="examInfoForm" label-width="200px">
          <el-form-item label="考试名称：" prop="coursename" style="margin-top:20px;width:50%">
            <el-input v-model="examInfo.coursename"></el-input>
          </el-form-item>

          <el-form-item label="考试类型：" prop="testtype" style="margin-top:20px;width:50%">
          <el-select v-model="examInfo.testtype" placeholder="请选择考试类型">
            <el-option
              v-for="item in options"
              :key="item.type"
              :label="item.label"
              :value="item.label">
            </el-option>
          </el-select>
          </el-form-item>
          

          <el-form-item label="及格分数：" prop="passScore" style="margin-top:20px">
            <el-input-number v-model="examInfo.passScore" :min="1" :max="240"></el-input-number>
          </el-form-item>

          <el-form-item label="考试时长(分钟)：" prop="timelast">
            <el-input-number v-model="examInfo.timelast" :min="1" :max="240"></el-input-number>
          </el-form-item>

          <div class="block">
            <span class="demonstration" style="margin-left: 100px">考试发布时间：</span>
            <el-date-picker
              v-model="examInfo.testtime"
              type="datetime"
              format="yyyy-MM-dd HH:mm"
              value-format="yyyy-MM-dd HH:mm"
              style="margin-left: 0px"
              placeholder="选择日期时间">
            </el-date-picker>
          </div>

          
          <!-- <div class="block">
            <span class="demonstration" style="margin-left: 100px">考试截止时间：</span>
            <el-date-picker
              v-model="examInfo.endtime"
              type="datetime"
              format="yyyy-MM-dd HH:mm"
              value-format="yyyy-MM-dd HH:mm"
              style="margin-left: 0px"
              placeholder="选择日期时间">
            </el-date-picker>
          </div> -->
          <!--vextable显示考场号。监考老师和每个考场的考生选择 -->
          <vxe-toolbar>
          <template #buttons>
            <h4 style="margin-left:0px;margin-top:25px" >考场划分：(注：最多不能超过四个考场)</h4>
            <vxe-button icon="el-icon-plus" @click="insertEvent(-1)" status="primary">新增</vxe-button>
            
            <!-- <vxe-button @click="insertEvent(-1)">新增</vxe-button> -->
            <vxe-button @click="$refs.xTable.removeCheckboxRow()" icon="el-icon-delete" status="danger">删除选中</vxe-button>
            <!-- <vxe-button @click="getSelectionEvent">获取选中</vxe-button>
            <vxe-button @click="getInsertEvent">获取新增</vxe-button> -->
            <!-- <vxe-button @click="saveEvent" status="primary">保存</vxe-button> -->
          </template>
        </vxe-toolbar>

        <vxe-table
          border
          show-overflow
          keep-source
          ref="xTable"
          max-height="400"
          style="margin-top:15px"
          :data="tableData"
          :edit-config="{trigger: 'click', mode: 'cell', icon: 'fa fa-pencil', showStatus: true}">
          <vxe-column type="checkbox" width="60"></vxe-column>
          <vxe-column type="seq" align="center" title="序号" width="60"></vxe-column>
          <vxe-column type="seq" align="center" title="考场序号" >
          
          </vxe-column>
          <!-- <vxe-column field="examtea" title="监考老师" :edit-render="{autofocus: '.vxe-input--inner',defaultValue: '张三'}">
            <template #edit="{ row }">
              <vxe-input v-model="row.examtea" type="text"></vxe-input>
            </template>
          </vxe-column> -->
          <vxe-column field="paperId" align="center" title="请选择试卷" :edit-render="{}">
            <template #default="{ row }">
              <span>{{ formatTest(row.test) }}</span>
            </template>
            <template #edit="{ row }">
              <vxe-select
              v-model="row.test"
              clearable 
              transfer 
              @change="testChangeEvent(row.test)"
              >
                <vxe-option 
                v-for="item in testList" 
                :key="item.paperId" 
                :value="item.paperId" 
                :label="item.source" 
                
                ></vxe-option>
              </vxe-select>
            </template>
          </vxe-column>
          <vxe-column field="studentphone" align="center" title="选择考试发布对象">
            <template  #default="{ row }">
              <!-- <vxe-input v-model="row.choice" type="date" transfer></vxe-input> -->           
            <el-popover
                placement="right"
                width="1000"
                trigger="click">
                
                <el-transfer
                    filterable
                    :filter-method="filterMethod"
                     :titles="['全选-学生列表', '全选-考生列表']"
                    filter-placeholder="请输入学生姓名进行搜索"
                    v-model="row.examstudent"
                    @change="handle(row.examstudent)"

                    :data="leftdata">
                    
                </el-transfer>
            <el-button slot="reference" type="primary" style="margin-left:0px;margin-top:0px" icon="el-icon-plus" @click="goto(row)">选择考生</el-button>
            
          </el-popover>
          </template>
          </vxe-column>
          
        </vxe-table>
          
          
          <el-button @click="returnback()" type="primary" size="small" style="margin-top:30px">返回</el-button>
          <el-button style="float:right;margin-top: 10px" v-show="curStep === 2" type="primary" @click="submit()">提交</el-button>
        </el-form>
      
      </el-card>
    </el-main>

  </el-container>
  </div>
</div>
</template>;



<script>
import Axios from 'axios';
import {postRequest} from "@/utils/request";
import {post1Request} from "@/utils/request";
import {post2Request} from "@/utils/request";
import VXETable from 'vxe-table'


  export default {
    // inject: ['reload'],//引入刷新
    name: 'AddExam',
    data () {
      const generateData = _ => {
        const leftdata = [];
        const _this = this
        const students= [];
        // const studentsphone= [];
        const data={
        teacherphone: localStorage.getItem('ms_username')
      }
      post1Request('teacherrelclass/getClass/byTeacher/',data).then(function (res) {
            console.log('aaaaa');
            console.log(res.data.data);
            _this.classname = res.data.data
            let i =0;
            
            for(i=0;i<res.data.data.length;i++){
              //  classname.push(res.data.data[i].classroom)
               _this.classname[i]=res.data.data[i].classroom
            }
            console.log(_this.classname)
            console.log('aaaaa');
            // this.testList = res.data.data.records;
          }).then(function(){
            let t = []
            _this.classname.forEach(function (classroom){
              t.push(classroom)
              
            })
      //   Axios({
      //   url: 'http://localhost:8080/teacherrelclass/getClass/byTeacher/',
      //   method: 'post',
      //   headers: {
      //     'Content-Type': 'application/json'
      //   },
      //   params:{
      //     teacherphone: localStorage.getItem('ms_username')

      //   }
      // }).then(function (res) {
      //       console.log('aaaaa');
      //       console.log(res.data.data);
      //       _this.classname = res.data.data
      //       let i =0;
            
      //       for(i=0;i<res.data.data.length;i++){
      //         //  classname.push(res.data.data[i].classroom)
      //          _this.classname[i]=res.data.data[i].classroom
      //       }
      //       console.log(_this.classname)
      //       console.log('aaaaa');
      //       // this.testList = res.data.data.records;
      //     }).then(function(){
      //       let t = []
      //       _this.classname.forEach(function (classroom){
      //         t.push(classroom)
              
      //       })
            // t = JSON.stringify(t)
           
             const data= t
      postRequest('teacherrelclass/getStudent/byclass',data).then(function(res1){
              console.log('fffff')
              console.log(res1)
              console.log('fffff')
              console.log(students)
              for(let i=0;i<res1.data.data.length;i++){
              students.push(res1.data.data[i].name)
              _this.studentsphone.push(res1.data.data[i].phone)
              }
              
              console.log(_this.studentsphone)
              const pinyin = []
              students.forEach(function (name){
                pinyin.push(name)
              })
              console.log('aaaa'+pinyin)
              
              students.forEach((name, phone) => {
          leftdata.push({
            label: name,
            key: phone,
            phone:phone,
            pinyin: pinyin[phone],
            disabled:false
          });
        });
            })
            console.log('66666'),
            function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          };
          })
        //     Axios({
        //       // dataq = qs.stringify(classname),
        //       url: 'http://localhost:8080/teacherrelclass/getStudent/byclass',
        //       method: 'post',
        //       headers: {
        //         'Content-Type': 'application/json'
        //       },
        //        data: t  
        //     }).then(function(res1){
        //       console.log('fffff')
        //       console.log(res1)
        //       console.log('fffff')
        //       console.log(students)
        //       for(let i=0;i<res1.data.data.length;i++){
        //       students.push(res1.data.data[i].name)
        //       _this.studentsphone.push(res1.data.data[i].phone)
        //       }
              
        //       console.log(_this.studentsphone)
        //       const pinyin = []
        //       students.forEach(function (name){
        //         pinyin.push(name)
        //       })
        //       console.log('aaaa'+pinyin)
              
        //       students.forEach((name, phone) => {
        //   leftdata.push({
        //     label: name,
        //     key: phone,
        //     phone:phone,
        //     pinyin: pinyin[phone],
        //     disabled:false
        //   });
        // });
        //     })
        //     console.log('66666'),
        //     function (err) {
        //     console.log(err);
        //     alert("服务器错误!稍后重试");
        //   };
        //   })
        return leftdata;
      };
      return {
        // 表格数据
        data1:[],
        data2:[],
        data3:[],
        data4:[],
        flag:0,
        tableData: [
              ],
        paperId:'',
        studentphone:[],
        newRow1:[],
        newRow2:{},
        //所有试卷
         testList: [
               
              ],    
        //该教师的所有班级名称
        classname:[],
        form : {
          classname:[],
        },
        students:[],
        studentsphone:[],

        //穿梭框参数
        leftdata: generateData(),
        examstudent: [],
        filterMethod(query, item) {
          return item.pinyin.indexOf(query) > -1;
        },

        //考试类型
        options: [{
          type: '选项1',
          label: '线上'
        }, {
          type: '选项2',
          label: '线下'
        }],
          // value: '',

       
        
    
        //当前的步骤
        curStep: 2,
        //组卷模式
        makeModel: 2,
        //添加考试题目信息(makeModel = 1的时候)
        addExamQuestion: [],
        //所有题库信息
        allBank: [],
        //添加考试题目信息(makeModel = 2 的时候)
        addExamQuestion2: [],
        //所有题目的对话框
        showQuestionDialog: false,
        //对话框中题目表格的加载
        loading: true,
        //所有题目的信息
        questionInfo: [],
        //所有题目的对话框中表格被选中
        selectedTable: [],
        //所有题目总数
        total: 0,
        //考试权限(1公开, 2密码)
        examAuthority: 1,
        //考试密码(权限为2时的密码)
        examPassword: '',
        //补充的考试信息
        examInfo: {
          'examId': '',
          'examDesc': '',
          'testtype':'',
          'passScore': 60,
          'timelast': 120,
          'testtime': '',
          'endtime': ''
        },
        //补充的考试信息的表单验证
        examInfoRules: {
          coursename: [
            {
              required: true,
              message: '请输入考试名称',
              trigger: 'blur'
            },
          ],
          testtype: [
            {
              required: true,
              message: '请输入考试类型',
              trigger: 'blur'
            },
          ],
          
          passScore: [
            {
              required: true,
              message: '请输入通过分数',
              trigger: 'blur'
            }
          ],
          timelast: [
            {
              required: true,
              message: '请输入考试时长',
              trigger: 'blur'
            }
          ],
        },
        value1: '',
        value2: '',
      }
    },
    props: ['tagInfo'],
    mounted(){
      console.log('bbb'+this.leftdata.length)
      // this.generateData.students.push('ni')
      console.log('aaa')
      // console.log(this.generateData.students)
      console.log('11111111111')
      console.log(this.tableData)
    },
    created () {
      // //一创建就改变头部的面包屑
      // this.$emit('giveChildChangeBreakInfo', '添加考试', '添加考试')
      // this.createTagsInParent()
      // this.getBankInfo()
      this.getList(),
      //选择试卷
      this.$nextTick(() => {
              // this.updateTestList()
            })

      console.log(this.value)
    },
    methods: {
      testChangeEvent(t){
        console.log(t)
        this.paperId=t
        // this.newRow1[this.flag]=this.newRow2
      console.log('aaa')
      // console.log(this.paperId)
      },
      //选择试卷的方法
      formatTest (value) {
              const item = this.testList.find(item => item.paperId === value)
              return item ? item.source : value
            },
      //得到可选择试卷的方法
      getList(){
        const _this = this;
      // console.log(this.phoneNumber,this.passWord)
      const data={
        phone: localStorage.getItem('ms_username')
      }
      post1Request(`papers/getteacherallpaper/${1}/${1}`,data).then(function (res) {

            console.log(res);
            _this.testList = res.data.data.records;

          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
      // this.$axios({
      //   url: `http://localhost:8080/papers/getteacherallpaper/${1}/${1}`,
      //   method: 'post',
      //   headers: {
      //     'Content-Type': 'application/json'
      //   },
      //   params:{
      //     phone: localStorage.getItem('ms_username')

      //   }
      // }).then(function (res) {

      //       console.log(res);
      //       _this.testList = res.data.data.records;

      //     },
      //     function (err) {
      //       console.log(err);
      //       alert("服务器错误!稍后重试");
      //     })
      },
      //得到穿梭框左边值的方法
      getLeftdata(){

      },


      //发送穿梭框右边值的方法
      handle(examstudent){
        const num=[]
        this.flag= this.flag+1
        // examstudent.forEach(function(i){
        //   console.log(i)
        //   num.push(i)
        // },

        // )
       
        let i=0
        let n = 0
        // for(i in examstudent){
        //   console.log(this.students1[n])
        //   n++
        // }
        console.log('999999999999999999999')
        let d = []
        // console.log(examstudent)
        let data=[this.data1,this.data2,this.data3,this.data4]
        let dataa=[]
        
        for(i;i<examstudent.length;i++){
          // this.leftdata.splice(examstudent[i],1)
          // n=n+1
          // examstudent[i]=examstudent[i]-n
          this.leftdata[examstudent[i]].disabled = true
          // console.log(examstudent)
          console.log(this.leftdata[examstudent[i]].label)
          console.log(this.studentsphone[i])
          d.push(this.studentsphone[i])
        }
        console.log(d)
        for(n;n<d.length;n++)
        {
        data[this.flag].push(d[n])
        }
        console.log(data[this.flag])
        this.studentphone=data[this.flag]
        // this.examInfo = JSON.stringify(this.examInfo)
        this.newRow2.studentphone=this.studentphone
        this.newRow2.paperId=this.paperId
        this.newRow2.coursename = this.examInfo.coursename
        this.newRow2.testtype = this.examInfo.testtype
        this.newRow2.passScore = this.examInfo.passScore
        this.newRow2.timelast = this.examInfo.timelast
        this.newRow2.testtime = this.examInfo.testtime
        this.newRow2.endtime = this.examInfo.endtime
        let phone = localStorage.getItem('ms_username')
        this.newRow2.teacherphone = phone
              console.log('aaa')
              this.newRow1[this.flag-1]=this.newRow2
      console.log('aaa')
      console.log(this.newRow1)
      this.newRow2={}
      // console.log(this.paperId)
            // this.tableData[this.flag].push(this.newRow1)
            // this.tableData[this.flag-1].studentphone=this.studentphone
            // this.tableData[this.flag-1].paperId=this.paperId
                  console.log( this.tableData)
      
      },
      //提交考试submit方法
      submit(){
        console.log('bbbccc')
        console.log(this.newRow1)
        // this.examInfo = JSON.stringify(this.examInfo)
        // this.newRow1.push (this.examInfo)
        
        let dataa = this.newRow1
        
        // let examInfo = JSON.stringify(this.examInfo)
        console.log('aaa')
        console.log(dataa)
        console.log(this.examInfo)
        let data1 =[]
        // for(let i =0;i<dataa.length;i++){
          // dataa[i].push(examInfo)
          data1 = dataa[0]
          // data1.push(examInfo)
        // }
        // console.log(data1)
        console.log('aaa')
        const data=dataa
        postRequest('test/saveall',data).then(res => {
          if(res.data.msg !== 'success') {
            alert(res.data.msg)
            }
          else 
          {
            this.$message({
              message: '数据添加成功',
              type: 'success'
            })
            
          }
          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
      //   Axios({
      //   url: 'http://localhost:8080/test/saveall',
      //   method: 'post',
      //   headers: {
      //     'Content-Type': 'application/json'
      //   },
      //   // params: 
      //   //   this.studentsphone
      //   data: dataa
          

          
      // }).then(res => {
      //     if(res.data.msg !== 'success') {
      //       alert(res.data.msg)
      //       }
      //     else 
      //     {alert('提交成功')
      //       // this.$router.go(-1)
      //       // this.$router.push({path: '/test2/test2-1-3'})
          
      //     this.$router.go(0)}
      //     },
      //     function (err) {
      //       console.log(err);
      //       alert("服务器错误!稍后重试");
      //     })
      console.log(this.newRow1)
      },


      // table表格中方法
      
            async insertEvent (row) {
              const $table = this.$refs.xTable
              const record = {
              }
              const { row: newRow } = await $table.insertAt(record, row)
              console.log('aaa')
//               let newRow = {
// paperId : this.paperId,
// studentphone : this.studentphone
//       }
//       console.log('aaa')
//       // console.log(this.paperId)
//       this.tableData.push(newRow)

              await $table.setActiveCell(newRow)
            },
            // getInsertEvent () {
            //   const $table = this.$refs.xTable
            //   const insertRecords = $table.getInsertRecords()
            //   VXETable.modal.alert(insertRecords.length)
            // },
            // getSelectionEvent () {
            //   const $table = this.$refs.xTable
            //   const selectRecords = $table.getCheckboxRecords()
            //   VXETable.modal.alert(selectRecords.length)
            // },
            // saveEvent () {
            //   const $table = this.$refs.xTable
            //   const { insertRecords, removeRecords, updateRecords } = $table.getRecordset()
            //   VXETable.modal.alert(`insertRecords=${insertRecords.length} removeRecords=${removeRecords.length} updateRecords=${updateRecords.length}`)
            // },
            async removeEvent (row) {
              const type = await VXETable.modal.confirm('您确定要删除该数据?')
              const $table = this.$refs.xTable
              if (type === 'confirm') {
                $table.remove(row)
              }
            },
      //向父组件中添加头部的tags标签
      createTagsInParent () {
        let flag = false
        this.tagInfo.map(item => {
          //如果tags全部符合
          if (item.name === '添加考试' && item.url === this.$route.path) {
            flag = true
          } else if (item.name === '添加考试' && item.url !== this.$route.path) {
            this.$emit('updateTagInfo', '添加考试', this.$route.path)
            flag = true
          }
        })
        if (!flag) this.$emit('giveChildAddTag', '添加考试', this.$route.path)
      },
      //获取所有的题库信息
      getBankInfo () {
        this.$http.get(this.API.getBankHaveQuestionSumByType, {
          params: {
            'pageNo': 1,
            'pageSize': 9999
          }
        }).then((resp) => {
          if (resp.data.code === 200) {
            this.allBank = resp.data.data.data
          } else {
            this.$notify({
              title: 'Tips',
              message: resp.data.message,
              type: 'error',
              duration: 2000
            })
          }
        })
      },
      //删除当前需要去除的题库
      delBank (bankId) {
        this.addExamQuestion.forEach((item, index) => {
          if (item.bankId === bankId) this.addExamQuestion.splice(index, 1)
        })
      },
      //添加题库组卷中的题库
      addBank () {
        this.addExamQuestion.push(
          {
            'bankName': 'sad asdS',
            'singleScore': 1,
            'multipleScore': 1,
            'judgeScore': 1,
            'shortScore': 1
          })
      },
      //自由组卷时添加试题
      showAddDialog () {
        this.showQuestionDialog = true
        this.getQuestionInfo()
      },
      //自由组卷时删除试题
      delQuestion (questionId) {
        this.addExamQuestion2.forEach((item, index) => {
          if (item.questionId === questionId) this.addExamQuestion2.splice(index, 1)
        })
      },
      //题目类型变化
      typeChange (val) {
        this.queryInfo.questionType = val
        this.getQuestionInfo()
      },
      //题库变化
      bankChange (val) {
        this.queryInfo.questionBank = val
        this.getQuestionInfo()
      },
      //获取题目信息
      getQuestionInfo () {
        this.$http.get(this.API.getQuestion, { params: this.queryInfo }).then((resp) => {
          if (resp.data.code === 200) {
            this.questionInfo = resp.data.data.data;
            this.total = resp.data.data.total;
            this.loading = false
          } else {
            this.$notify({
              title: 'Tips',
              message: '获取题库信息失败',
              type: 'error',
              duration: 2000
            })
          }
        })
      },
      //处理表格被选中
      handleTableSectionChange (val) {
        this.selectedTable = val
      },
      //分页页面大小改变
      handleSizeChange (val) {
        this.queryInfo.pageSize = val
        this.getQuestionInfo()
      },
      //分页插件的页数
      handleCurrentChange (val) {
        this.queryInfo.pageNo = val
        this.getQuestionInfo()
      },
      //自由组卷中选中的题目添加进去
      addQuToFree () {
        this.selectedTable.forEach(item => {
          if (!this.addExamQuestion2.some(i2 => {
            return i2.questionId === item.id
          })) {//不存在有当前题目
            this.addExamQuestion2.push({
              'questionId': item.id,
              'questionContent': item.quContent,
              'questionType': item.quType,
              'score': 1
            })
          }
        })
        this.showQuestionDialog = false
      },
      //组卷模式变化
      makeModelChange () {
        this.$confirm('此操作将丢失当前组卷数据, 是否继续?', 'Tips', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.makeModel === 1 ? this.addExamQuestion2 = [] : this.addExamQuestion = []
        }).catch(() => {
        })
      },
      //添加考试
      addExam () {
        this.$refs['examInfoForm'].validate((valid) => {
          if (valid && (this.addExamQuestion.length !== 0 || this.addExamQuestion2.length !== 0)) {
            //构造数据对象(考试信息)
            let exam = this.examInfo
            exam.totalScore = this.sumTotalScore
            exam.status = 1
            //权限id设置
            exam.type = this.examAuthority
            if (this.examAuthority === 2) {//考试密码
              if (this.examPassword === '') {// 当前用户选择了需要密码权限,但是密码为空
                this.$message.error('当前权限为需要密码,但是密码为空');
                return false;
              }
              exam.password = this.examPassword
            }
            //题库组卷模式
            if (this.makeModel === 1 && !this.addExamQuestion.some(item => item.bankId === '')) {
              console.log(this.addExamQuestion)
              let bankNames = []
              this.addExamQuestion.forEach(item => bankNames.push(item.bankName))
              exam.bankNames = bankNames.join(',')
              exam.singleScore = this.addExamQuestion[0].singleScore
              exam.multipleScore = this.addExamQuestion[0].multipleScore
              exam.judgeScore = this.addExamQuestion[0].judgeScore
              exam.shortScore = this.addExamQuestion[0].shortScore
              this.$http.post(this.API.addExamByBank, exam).then((resp) => {
                if (resp.data.code === 200) this.$router.push('/examManage')
              })
            } else if (this.makeModel === 2) {//自由组卷模式
              //题目id数组
              let questionIds = []
              //题目成绩数组
              let scores = []
              this.addExamQuestion2.forEach(item => {
                questionIds.push(item.questionId)
                scores.push(item.score)
              })
              exam.questionIds = questionIds.join(',')
              exam.scores = scores.join(',')
              console.log(exam)
              this.$http.post(this.API.addExamByQuestionList, exam).then((resp) => {
                if (resp.data.code === 200) this.$router.push('/examManage')
              })
            } else {
              this.$message.error('请检查考试规则设置是否完整')
            }
          } else {
            this.$message.error('请检查考试规则设置是否完整')
            return false
          }
        })
      },
      //返回题库列表
        returnback(){
          this.$router.push({path:'/test2/test2-1-4'})
        },
    },
    computed: {
      //计算总分
      sumTotalScore () {
        if (this.makeModel === 2) {
          let score = 0
          this.addExamQuestion2.forEach(item => {
            score += parseInt(item.score)
          })
          return score
        }
      }
    }
  }
</script>

<style scoped lang="scss">
  .el-container {
    width: 100%;
    height: 100%;
  }

  // .el-container {
  //   animation: leftMoveIn .7s ease-in;
  // }

  @keyframes leftMoveIn {
    0% {
      transform: translateX(-100%);
      opacity: 0;
    }
    100% {
      transform: translateX(0%);
      opacity: 1;
    }
  }


  /deep/ .el-table thead {
    color: rgb(85, 85, 85) !important;
  }

  /*表格的头部样式*/
  /deep/ .has-gutter tr th {
    background: rgb(242, 243, 244);
    color: rgb(85, 85, 85);
    font-weight: bold;
    line-height: 32px;
  }

  .el-table {
    box-shadow: 0 0 1px 1px gainsboro;
  }
</style>
