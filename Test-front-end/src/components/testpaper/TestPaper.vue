<template>
  <el-container>
    <el-main>
      <!--设置试题信息-->
      <el-card v-show="curStep === 1" align="center">

        <el-radio-group v-model="makeModel"
                        @change="makeModelChange"
                        size="medium">
          
          <el-radio :label="2" border>手动组卷</el-radio>
          <el-radio :label="1" border>随机组卷</el-radio>
          <el-radio :label="3" border>智能组卷</el-radio>
        </el-radio-group>

        <span style="float: right;color: red;font-weight: bold" v-if="makeModel ===2">
          {{ '试卷总分：' + sumTotalScore }}</span>

        <span style="float: right;color: red;font-weight: bold" v-if="makeModel ===1||makeModel ===3">
          {{ '试卷总分：' + sumTotalScore2 }}</span>
        <!-- 手动组卷内容-->
        <div v-show="makeModel === 2">
          <el-card>
            <h1>题目列表</h1>
            <el-button type="primary" icon="el-icon-plus" size="small" @click="showAddDialog">添加试题</el-button>

            <el-table :data="addExamQuestion2" border style="margin-top: 10px">

              <el-table-column
                type="index"
                label="序号"
                width="50">
              </el-table-column>
              
              <el-table-column align="center"
                         label="题目类型">
          <template slot-scope="scope">
            <span v-if="scope.row.quesTypeId === 1">单选题</span>
                  <span v-else-if="scope.row.quesTypeId === 2">多选题</span>
                  <span v-else-if="scope.row.quesTypeId === 3">填空题</span>
                  <span v-else-if="scope.row.quesTypeId === 4">简答题</span>
                  <span v-else-if="scope.row.quesTypeId === 5">判断题</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="题目id">
          <template slot-scope="scope">
            <span class="quContent">{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        
        <el-table-column  align="center" label="题目主干">
          <template slot-scope="scope">
            
            <span class="quContent">{{ scope.row.stem }}</span>
          </template>
        </el-table-column>

        
        <el-table-column align="center"  label="选项A">
          <template slot-scope="scope">
            <div v-if="scope.row.quesTypeId === 1||scope.row.quesTypeId === 2">
            <span class="quContent">{{ scope.row.choiceA }}</span></div>
          </template>
        </el-table-column>
        <el-table-column align="center" label="选项B">
          <template slot-scope="scope">
            <div v-if="scope.row.quesTypeId === 1||scope.row.quesTypeId === 2">
            <span class="quContent">{{ scope.row.choiceB }}</span></div>
          </template>
        </el-table-column>
        <el-table-column align="center" label="选项C">
          <template slot-scope="scope">
            <div v-if="scope.row.quesTypeId === 1||scope.row.quesTypeId === 2">
            <span class="quContent">{{ scope.row.choiceC }}</span></div>
          </template>
        </el-table-column>
        <el-table-column align="center" label="选项D">
          <template slot-scope="scope">
            <div v-if="scope.row.quesTypeId === 1||scope.row.quesTypeId === 2">
            <span class="quContent">{{ scope.row.choiceD }}</span></div>
          </template>
        </el-table-column>

        <el-table-column align="center" label="答案">
          <template slot-scope="scope">
            <span class="quContent">{{ scope.row.answer }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="题目解析">
          <template slot-scope="scope">
            <span class="quContent">{{ scope.row.quContent }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center"
                         label="难度">
          <template slot-scope="scope">
            <span class="quContent">{{ scope.row.difficulty }}</span>
            <!-- <span v-if="scope.row.level === 1">简单</span>
            <span v-if="scope.row.level === 2">中等</span>
            <span v-if="scope.row.level === 3">困难</span> -->
          </template>
        </el-table-column>

              <el-table-column label="分数" align="center">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.score" style="margin-left: 5px"></el-input>
                </template>
              </el-table-column>

              <el-table-column label="操作" width="80" align="center">
                <template slot-scope="scope">
                  <el-button type="danger" icon="el-icon-delete" circle
                             @click="delQuestion(scope.row.questionId)"></el-button>
                </template>
              </el-table-column>
            </el-table>

          </el-card>
        
        <el-button @click="returnback()" type="primary" size="small" style="margin-top:30px">返回</el-button>
        <el-button style="float:right;margin-top: 10px" v-show="curStep === 1" type="primary" @click=" submitQuestion()">提交</el-button>
        </div> 
        <!-- 随机组卷 -->
        
        <!-- 智能组卷 -->
        
      </el-card>

      
      </el-card>

     
    </el-main>

    <el-dialog title="添加题目" :visible.sync="showQuestionDialog" width="80%" center>
      <el-row>
        <el-select @change="typeChange" clearable v-model="queryInfo.quesTypeId" placeholder="请选择题目类型">
          <el-option
            v-for="item in quesTypeId"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>

        <el-select @change="bankChange" clearable v-model="queryInfo.questionBank" placeholder="请选择题库"
                   style="margin-left: 5px">
          <el-option
            v-for="item in allBank"
            :key="item.coursename"
            :label="item.coursename"
            :value="item.coursename">
          </el-option>
        </el-select>

        <el-input v-model="queryInfo.questionContent" @blur="getQuestionInfo" placeholder="题目内容"
                  style="margin-left: 5px;width: 220px"
                  prefix-icon="el-icon-search"></el-input>
        <el-button type="primary" @click="addQuToFree" style="float: right">确认{{ selectedTable.length }}项</el-button>
      </el-row>

      <el-table
        ref="questionTable"
        highlight-current-row
        
        :border="true"
        :data="questionInfo"
        tooltip-effect="dark"
        style="width: 100%;margin-top: 25px;" @selection-change="handleTableSectionChange">

        <el-table-column align="center"
                         type="selection"
                         width="55">
        </el-table-column>

        <el-table-column align="center"
                         label="题目类型">
          <template slot-scope="scope">
            <span v-if="scope.row.quesTypeId === 1">单选题</span>
                  <span v-else-if="scope.row.quesTypeId === 2">多选题</span>
                  <span v-else-if="scope.row.quesTypeId === 3">填空题</span>
                  <span v-else-if="scope.row.quesTypeId === 4">简答题</span>
                  <span v-else-if="scope.row.quesTypeId === 5">判断题</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="题目id">
          <template slot-scope="scope">
            <span class="quContent">{{ scope.row.id }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="题目主干">
          <template slot-scope="scope">
            <span class="quContent">{{ scope.row.stem }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center"  label="选项A">
          <template slot-scope="scope">
            <div v-if="scope.row.quesTypeId === 1||scope.row.quesTypeId === 2">
            <span class="quContent">{{ scope.row.choiceA }}</span></div>
          </template>
        </el-table-column>
        <el-table-column align="center" label="选项B">
          <template slot-scope="scope">
            <div v-if="scope.row.quesTypeId === 1||scope.row.quesTypeId === 2">
            <span class="quContent">{{ scope.row.choiceB }}</span></div>
          </template>
        </el-table-column>
        <el-table-column align="center" label="选项C">
          <template slot-scope="scope">
            <div v-if="scope.row.quesTypeId === 1||scope.row.quesTypeId === 2">
            <span class="quContent">{{ scope.row.choiceC }}</span></div>
          </template>
        </el-table-column>
        <el-table-column align="center" label="选项D">
          <template slot-scope="scope">
            <div v-if="scope.row.quesTypeId === 1||scope.row.quesTypeId === 2">
            <span class="quContent">{{ scope.row.choiceD }}</span></div>
          </template>
        </el-table-column>
        

        <el-table-column align="center" label="答案">
          <template slot-scope="scope">
            <span class="quContent">{{ scope.row.answer }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="题目解析">
          <template slot-scope="scope">
            <span class="quContent">{{ scope.row.quContent }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center"
                         label="难度">
          <template slot-scope="scope">
            <span class="quContent">{{ scope.row.difficulty }}</span>
            <!-- <span v-if="scope.row.level === 1">简单</span>
            <span v-if="scope.row.level === 2">中等</span>
            <span v-if="scope.row.level === 3">困难</span> -->
          </template>
        </el-table-column>

        <el-table-column align="center"
                         prop="quBankName"
                         label="所属题库">
        </el-table-column>

        <el-table-column align="center"
                         prop="createPerson"
                         label="创建人">
        </el-table-column>

        <el-table-column align="center"
                         label="创建时间">
          <template slot-scope="scope">
            {{ scope.row.createTime }}
          </template>
        </el-table-column>


      </el-table>
      <!-- 分页
      <el-pagination style="margin-top: 25px"
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="queryInfo.pageNo"
                     :page-sizes="[3, 5, 8, 50]"
                     :page-size="queryInfo.pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination> -->
      

      <!-- <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pageNo"
          :page-size="queryInfo.pageSize"
          :page-sizes="[10, 20, 30, 50]"
          class="page"
          layout="total,sizes, prev, pager, next, jumper"
          :total="total"
      style="margin-left: 35%">
      </el-pagination> -->
   
    </el-dialog>

  </el-container>
</template>



<script>
import Axios from 'axios'
import VXETable from 'vxe-table'
  export default {
    name: 'AddExam',
    data () {
      
      return {
      //   pagination: {
      //   total: 0,
      //   current:1,
      //   size:2,

      // },
      //智能组卷选择题库
      //智能组卷选择章节
       options2: [{
          value: '章节一',
          label: '章节一'
        }, {
          value: '章节二',
          label: '章节二'
        }, {
          value: '章节三',
          label: '章节三'
        }, {
          value: '章节四',
          label: '章节四'
        }, {
          value: '章节五',  
          label: '章节五'
        }],
        pointIds: [],
        value2: [],
      //智能组卷表单
      form: {
          
        },
        //题型，题数，题分
        list:[],
        //查询题目的参数
        queryInfo: {
          //题目类型下拉款所选的内容
          'quesTypeId': '',
          'questionBank': '',
          'questionContent': '',
          'pageNo': 1,
          'pageSize': null
        },
        //题目类型
        quesTypeId: [
          {
            id: 1,
            name: '单选题',
          },
          {
            id: 2,
            name: '多选题',
          },
          {
            id: 3,
            name: '填空题',
          },
          {
            id: 4,
            name: '简答题',
          },
          {
            id: 5,
            name: '判断题',
          },
        ],
        //当前的步骤
        curStep: 1,
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
        //提交的题目表格信息
        dataa:[],
        //所有题目的对话框中表格被选中
        selectedTable: [],
        //所有题目总数
        total: 0,
        //考试权限(1公开, 2密码)
        examAuthority: 1,
        //考试密码(权限为2时的密码)
        examPassword: '',
        
        
      }
    },
    props: ['tagInfo'],
    mounted(){
      // console.log('2222222222')
      // this. getQuestionInfo()
      // console.log(this.questionInfo)
    },
    created () {
      //一创建就改变头部的面包屑
      // this.$emit('giveChildChangeBreakInfo', '添加考试', '添加考试')
      // this.createTagsInParent()
      this.getBankInfo()
      let paperId = this.$route.query.paperId;//获取路由传递过来的试卷编号
      console.log(paperId)
    },
    methods: {
      
            //智能组卷提交
      onSubmitIN(){
        const _this = this;
        let paperId = this.$route.query.paperId;//获取路由传递过来的考试编号 
        this.form.coursename = this.queryInfo.questionBank
        let totalScore = this.$route.query.totalScore;//获取路由传递过来的考试编号
        for (let index = 0; index < 5; index++) {
          this.list.push({ typeId: "",num:"",score:"" });
          // this.list.push({num:""})
          // this.list.push({score:""})
          this.list[index].typeId= index+1;
        }
        // this.list.push({"typeId":1})
        // b.typeId.push("2")
        // c.typeId.push("1")
        // d.typeId.push("1")
        // e.typeId.push("1")

        this.list[0].num = this.form.type1num
        this.list[0].score = this.form.type1sco

        this.list[1].num = this.form.type2num
        this.list[1].score = this.form.type2sco

        this.list[2].num = this.form.type3num
        this.list[2].score = this.form.type3sco
    
        this.list[3].num = this.form.type4num
        this.list[3].score = this.form.type4sco
        
        this.list[4].num = this.form.type5num
        this.list[4].score = this.form.type5sco
        console.log('aaa');
        console.log(this.list)
              // console.log(this.form)
              // console.log(this.list)
              let ruleqnum = this.list
              console.log(this.pointIds)
              // let data = JSON.stringify(this.pointIds)
              // for(let i=0;i<this.pointIds.length;i++){
              //   this.form.pointIds.push(this.pointIds[i])
              // }
              this.form.pointIds = this.pointIds.join(',') //变成字符串后，以逗号分隔
              console.log(this.form.pointIds)
              this.form.ruleqnumList = ruleqnum//题目集合
              console.log(totalScore)
        console.log(_this.sumTotalScore2 )
        if(_this.sumTotalScore2 != totalScore){
          alert('请检查组卷总分是否和试卷总分一样')
          this.$router.go(0)
        }
        else{
        this.$axios({
          url: 'http://localhost:8080/MakePaper/autoProblem',
          method: 'post',
          headers: {
            'Content-Type': 'application/json'
          },
          data: _this.form,
          
        }).then(function(res) {
          const dataa = res.data.data
        
        // quesInfo.push(this.questionInfo)
          if(res.data.msg != 'success'){
            alert(res.data.msg)
          }
        
        else{
        _this.$axios({
          url: `http://localhost:8080/MakePaper/paperProblem/save/byListQuestion/${paperId}`,
          method: 'post',
          headers: {
            'Content-Type': 'application/json'
          },
          data: 
          dataa
            // questionInfoa: this.questionInfo
          //   paperId:paperId
          
        }).then(res => {
          if(res.data.msg === 'success') {
            alert('已完成智能组卷，请稍后预览')  
            _this.$router.go(0)
            _this.$router.go(-1)
            // this.$router.push({path: '/test2/test2-1-3'})
          }
        })
        }
        })
        
        }
      },

      //随机组卷提交
      onSubmit(){
        const _this = this;
        let paperId = this.$route.query.paperId;//获取路由传递过来的考试编号 
        let totalScore = this.$route.query.totalScore;//获取路由传递过来的考试编号 
        this.form.coursename = this.queryInfo.questionBank

        for (let index = 0; index < 5; index++) {
          this.list.push({ typeId: "",num:"",score:"" });
          // this.list.push({num:""})
          // this.list.push({score:""})
          this.list[index].typeId= index+1;
        }
        // this.list.push({"typeId":1})
        // b.typeId.push("2")
        // c.typeId.push("1")
        // d.typeId.push("1")
        // e.typeId.push("1")

        this.list[0].num = this.form.type1num
        this.list[0].score = this.form.type1sco

        this.list[1].num = this.form.type2num
        this.list[1].score = this.form.type2sco

        this.list[2].num = this.form.type3num
        this.list[2].score = this.form.type3sco
    
        this.list[3].num = this.form.type4num
        this.list[3].score = this.form.type4sco
        
        this.list[4].num = this.form.type5num
        this.list[4].score = this.form.type5sco
        console.log('aaa');
        console.log(this.list)
              // console.log(this.form)
              // console.log(this.list)
              let ruleqnum = this.list
              console.log(this.pointIds)
              // let data = JSON.stringify(this.pointIds)
              // for(let i=0;i<this.pointIds.length;i++){
              //   this.form.pointIds.push(this.pointIds[i])
              // }
              this.form.pointIds = this.pointIds.join(',') //变成字符串后，以逗号分隔
              console.log(this.form.pointIds)
              this.form.ruleqnumList = ruleqnum//题目集合
              console.log(totalScore)
        console.log(_this.sumTotalScore2 )
        if(_this.sumTotalScore2 != totalScore){
          alert('请检查组卷总分是否和试卷总分一样')
        }
        else{
        this.$axios({
          url: 'http://localhost:8080/MakePaper/autoProblem',
          method: 'post',
          headers: {
            'Content-Type': 'application/json'
          },
          data: _this.form,
          
        }).then(function(res) {

        const dataa = res.data.data
        
        // quesInfo.push(this.questionInfo)
        if(res.data.msg != 'success'){
            alert(res.data.msg)
          }
        else{
        _this.$axios({
          url: `http://localhost:8080/MakePaper/paperProblem/save/byListQuestion/${paperId}`,
          method: 'post',
          headers: {
            'Content-Type': 'application/json'
          },
          data: 
          dataa
            // questionInfoa: this.questionInfo
          //   paperId:paperId
          
        }).then(res => {
          if(res.data.msg === 'success') {
            alert('已完成随机组卷，请稍后预览')  
            _this.$router.go(0)
            _this.$router.go(-1)
            // this.$router.push({path: '/test2/test2-1-3'})
          }
        })
        }
        })
      }
      },
      //获取所有的题库信息
      getBankInfo () {
        const _this = this;
        // this.$axios(`http://localhost:8080/papers/getteacherallpaper/${this.pagination.current}/${this.pagination.size}`).then(res => {
        this.$axios({
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
              _this.allBank = res.data.data
              console.log(_this.allBank)


            },
            function (err) {
              console.log(err);
              // alert("服务器错误!稍后重试");
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
        this.queryInfo.quesTypeId = val
        this.getQuestionInfo()
      },
      //题库变化
      bankChange (val) {
        this.queryInfo.questionBank = val
        this.getQuestionInfo()
      },
      //获取题目信息
      getQuestionInfo () {
        // let coursename = this.$route.query.coursename //获取路由传递过来的试卷编号
        let coursename = this.queryInfo.questionBank
        let quesTypeId = this.queryInfo.quesTypeId
        console.log(quesTypeId)
        console.log('asd asd66666')
        const _this = this;
        //分页查询所有题目信息
        this.$axios({
          url: `http://localhost:8080/question/get/bycourse/${quesTypeId}/${1}/${1}`,
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
              _this.questionInfo =res.data.data.records
               console.log('666666')
              console.log(_this.questionInfo)
              
              
            },
            
            )
        
      },

      //发送手动组卷中选中的题目信息
      submitQuestion(){
        const dataa = this.addExamQuestion2
        let paperId = this.$route.query.paperId;//获取路由传递过来的考试编号 
        let totalScore = this.$route.query.totalScore;//获取路由传递过来的考试编号 
        // quesInfo.push(this.questionInfo)
        console.log(totalScore)
        console.log(this.sumTotalScore )
        if(this.sumTotalScore != totalScore){
          alert('请检查组卷总分是否和试卷总分一样')
        }
        else
        this.$axios({
          url: `http://localhost:8080/MakePaper/paperProblem/save/byListQuestion/${paperId}`,
          method: 'post',
          headers: {
            'Content-Type': 'application/json'
          },
          data: 
          dataa
            // questionInfoa: this.questionInfo
          //   paperId:paperId
          
        }).then(res => {
          if(res.data.msg === 'success') {
            this.$message({
              message: '组卷数据添加成功',
              type: 'success'
            })
            this.$router.go(0)
            this.$router.go(-1)
            // this.$router.push({path: '/test2/test2-1-3'})
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
              'id': item.id,
              'questionContent': item.questionContent,
              'quesTypeId': item.quesTypeId,
              'difficulty': item.difficulty,
              'stem':item.stem,
              'score': 6,
              'choiceA':item.choiceA,
              'choiceB':item.choiceB,
              'choiceC':item.choiceC,
              'choiceD':item.choiceD,
              'answer':item.answer
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
      //返回题库列表
        returnback(){
          this.$router.go(-1)
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
      },
        sumTotalScore2 () {
        if(this.makeModel === 1||this.makeModel === 3){
          let score = 0
          score = this.form.type1num*this.form.type1sco+this.form.type2num*this.form.type2sco+this.form.type3num*this.form.type3sco+this.form.type4num*this.form.type4sco+this.form.type5num*this.form.type5sco
          
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

  .el-container {
    animation: leftMoveIn .7s ease-in;
  }

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
  //智能组卷卡片样式
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

  .box-card {
    width: 1000px;
  }
</style>
