<template>
<div class="container" style="margin-left:15%;width:80%">
  <p>成绩列表</p>
    <el-table 
      :data="tableData" 
      style="width: 90%;margin-top:20px;margin-left:30px;border-radius: 10px" 
      :cell-style="{textAlign:'center'}"
      :header-cell-style="{textAlign:'center'}"
      border
    >
        
        <el-table-column prop="subject" label="课程名称" > </el-table-column>
        <!-- <el-table-column prop="classTeacher" label="教师" > </el-table-column> -->
        <el-table-column prop="etScore" label="分数"> </el-table-column>
        <el-table-column prop="isPass" label="是否及格"> </el-table-column>
    </el-table>
    </div>
</template>

  <script>
export default {
    data() {
        return {
            tableData: [
                {
                    examDate: '2016-05-02',
                    className:'软件工程',
                    classTeacher:'张三',
                    score:100,
                    isPass:'是'
                },
                {
                    examDate: '2016-05-02',
                    className:'软件工程',
                    classTeacher:'张三',
                    score:100,
                    isPass:'是'
                },
                {
                    examDate: '2016-05-02',
                    className:'软件工程',
                    classTeacher:'张三',
                    score:100,
                    isPass:'是'
                },
                {
                    examDate: '2016-05-02',
                    className:'软件工程',
                    classTeacher:'张三',
                    score:100,
                    isPass:'是'
                },
                {
                    examDate: '2016-05-02',
                    className:'软件工程',
                    classTeacher:'张三',
                    score:100,
                    isPass:'是'
                },
                
            ]
        };
    },
    mounted() {
    this.getstudentscore();
  },
    methods:{
      getstudentscore(){
        const _this = this;
        let phone = localStorage.getItem('ms_username')
        let studentId = phone
      // console.log(this.phoneNumber,this.passWord)
      this.$axios({
        url: `http://localhost:8080/score/${1}/${6}/${studentId}` ,
        method: 'get',
        headers: {
          'Content-Type': 'application/json'
        },
        
      }).then(function (res) {

            console.log(res);
            _this.tableData = res.data.data.records;
            console.log(res.data.data)
            console.log(_this.tableData)
            for(let i =0;i<_this.tableData.length;i++){
            if(_this.tableData[i].etScore<60){
              _this.tableData[i].isPass="未及格"
            }
            else _this.tableData[i].isPass="及格"
            }
          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
      }
    }
};
</script>