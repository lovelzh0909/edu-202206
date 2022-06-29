<template>
<div class="content-box" style="margin-right:30%; width:70%">
        <div class="container" >
            
  <div class="app-container" >
    <div>
      <el-form
          ref="form"
          :model="form"
          class="form"
          
      >
        <h3 class="card-label" style="margin-top:5%">添加题目至该题库</h3>
        <h4 class="card-label">/*选择题请先添加选项内容再勾选正确答案*/</h4>
        <question
            v-for="item in form.questions"
            :key="item.id"
            :question="item"
            class="question-choiceA"
            @removeQuestion="removeQuestion"
        />
        <div class="question-type">
          <el-button
              v-for="item in questionType"
              :key="item.typeId"
              style="border-color: #2A82E4; color: #2A82E4"
              @click="addQuestion(item.typeId,item.typeName)"
          >
<!--            <svg-icon :icon-class="item.icon" />-->
            {{ item.typeName }}
          </el-button>
        </div>
        <el-button
            type="primary"
            class="submit"
            :loading="loading"
            style="margin-top: 20px"
            @click="submit"
        >
          提交题目
        </el-button>
       <el-button type="primary" style="margin-left:20px;margin-top: 20px " @click="returnback()">返回</el-button>
      </el-form>
    </div>
    <!-- <el-dialog :visible.sync="consoleName">
      <div>请输入题库名称</div>
      <el-input v-model="courseName" placeholder="请输入题库名"></el-input>
      <div style="display: flex" class="surebutton">
      <el-button @click="summitCourse">确定</el-button>
      </div>
    </el-dialog> -->
  </div>
        </div>
</div>
</template>

<script>
// import crudRoles from '@/api/system/role'
// import crudExam from '@/api/exam/exam'
import question from '@/components/question'
import axios from "axios";
// import crudList from '@/api/exam/list'
export default {
  // name: 'Create',
  components: {
    question
  },
  data() {
    return {
      dialogVisible: false,
      consoleName:false,
      courseName:'',
      loading: false,
      questionId: 0,
      form: {
        questions: []
      },
      questionType: [
        {
          typeId: 1,
          icon:'',
          typeName:'单选题',
        },
        {
          typeId: 2,
          icon:'',
          typeName:'多选题',
        },
        {
          typeId: 3,
          icon:'',
          typeName:'填空题',
        },
        {
          typeId: 4,
          icon:'',
          typeName:'简答题',
        },
        {
          typeId: 5,
          icon:'',
          typeName:'判断题',
        }
      ],
    }
  },
  created() {
    this.courseName = this.$route.query.subject;
  },
  methods: {
    // getQuestionType() {
    //   crudExam.getQuestionType().then(res => {
    //     this.questionType = res
    //   })
    // },
    addQuestion(typeId,typeName) {
      const question = {
        id: this.questionId,
        quesTypeId: typeId,
        type: typeName,
        difficulty: 0,
        answer: [],
        content:[],
        // choice:
        //   {choiceA:,
        //   choiceB:,
        //   choiceC:,
        //   choiceD:,
        //   choiceE:},

        stem: '',
        coursename:'',
        choiceA:'',
        choiceB:'',
        choiceC:'',
        choiceD:'',
        choiceE:'',
        choiceF:''
      }
      this.form.questions.push(question)
      this.questionId++
      console.log(question)
    },
    removeQuestion(id) {
      for (let i = 0; i < this.form.questions.length; i++) {
        if (this.form.questions[i].id === id) {
          this.form.questions.splice(i, 1)
        }
      }
    },
    // summitCourse(){
    //   if (this.courseName===''){
    //     alert('请输入题库名！')
    //   }else {
        
    //   }
    // },
    submit() {
      if (this.form.questions.length === 0) {
        this.$notify({
          title: '警告',
          message: '请添加试题',
          type: 'warning'
        })
        return
      }
      const form = JSON.parse(JSON.stringify(this.form))
      console.log('aaaa')
      console.log(form)
      let isSubmit = true
      let message = ''
      this.loading = true
      this.$refs['form'].validate(res => {
        if (!res) {
          this.loading = false
          return
        }
        for (let i = 0; i < form.questions.length; i++) {
          const question = form.questions[i]
          if (question.stem === '') {
            isSubmit = false
            message = '请设置题目题干'
            break
          }
          if ((question.quesTypeId === 1 || question.quesTypeId === 2) && question.content.length === 0) {
            isSubmit = false
            message = '请设置选择题题答案'
            break
          }
          if ((question.quesTypeId === 1 || question.quesTypeId === 2 || question.quesTypeId === 5) && question.answer.length === 0) {
            isSubmit = false
            message = '请设置客观题选项'
            break
          }
        }
        if (!isSubmit) {
          this.$notify({
            title: '警告',
            message: message,
            type: 'warning'
          })
          this.loading = false
          return
        }
        console.log(this.courseName)
        form.questions.forEach(function(question) {
          question.answer = JSON.stringify(question.answer)

          console.log(question.answer)
          // questionType.typeId
          question.choiceA = question.content[0]
          question.choiceB = question.content[1]
          question.choiceC = question.content[2]
          question.choiceD = question.content[3]
          question.content = JSON.stringify(question.content)
          // question.choiceA = JSON.stringify(question.choiceA)
          // question.choiceB = JSON.stringify(question.choiceB)
          // question.choiceC = JSON.stringify(question.choiceC)
          // question.choiceD = JSON.stringify(question.choiceD)
          // question.choiceE = JSON.stringify(question.choiceE)
          // question.choiceF = JSON.stringify(question.choiceF)
          // question.coursename=JSON.stringify(question.coursename)
        })
        for (let i = 0; i < form.questions.length; i++) {
          form.questions[i].coursename = this.courseName
          form.questions[i].userId = localStorage.getItem('ms_username')
          console.log( this.form.questions[i].coursename)
        }
        const dataq = []
        form.questions.forEach(function (question){
          dataq.push(question)
        })
        console.log(dataq)
        this.$axios({
          method: 'post',
          url:`http://localhost:8080/question/saveall`,
          data:dataq,
          // params:params,
        }).then(res =>{
          alert('提交成功')
          this.loading=false
          for (let i = 0; i < form.questions.length; i++) {
            this.removeQuestion(i)
          }
          this.$router.go(-1) 
        }).catch(err=>{
          alert('提交失败')
          this.loading =false
        })

      })
    },
    //返回题库列表
    returnback(){
      this.$router.go(-1)
    },
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.app-container{
  margin-left: 5%;
  margin-right: 5%;
  margin-top: 0px;
}
.card-label {
  margin: 30px 0 15px;
}
.card-panel {
  display: flex;
  flex-direction: row;
  padding: 17px 15px 0;
  color: #666;
  box-shadow: 0 0 3px 1px #e7e7e7;
  border-color: #e7e7e7;

  .settings-wrap {
    margin-right: 4%;
  }
}
.content-label {
  display: block;
  padding-bottom: 5px;
}
.question-type {
  margin-top: 20px;
}
.question-content {
  margin-top: 20px;
  color: #666;
  box-shadow: 0 0 4px 2px rgba(0, 0, 0, .05);
  border-color: rgba(0, 0, 0, .05);
}
.surebutton{
  margin-top: 10px;
}
</style>
