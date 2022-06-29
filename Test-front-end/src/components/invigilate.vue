
<template>
  <div class="body">
    <div class="invigilate-main">
      <div class="invigilate-header">
        <el-form label-position="top" label-width="100px" :model="tempDataSource" style="padding-top:0px">
          <el-row>
            <el-col :span="4" :offset="1">
              <el-form-item label="试卷">
                <div class="labelDetail">
                  {{tempDataSource.source}}
                </div>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="出题老师id">
                <div class="labelDetail">
                  {{tempDataSource.phone}}
                </div>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="考试时长">
                <div class="labelDetail">
                  {{tempDataSource.totalTime}}分
                </div>
              </el-form-item>
            </el-col>
            <!-- <el-col :span="4">
              <el-form-item label="考试人数">
                <div class="labelDetail">
                  {{tempDataSource.pepleNum}}人
                </div>
              </el-form-item>
            </el-col> -->
<!--            <el-col :span="4">-->
<!--              <el-form-item label="倒计时间">-->
<!--                <div class="labelDetail">-->
<!--                  <span class="downTime" >{{hour? hourString+':'+minuteString+':'+secondString : minuteString+':'+secondString}}</span>-->
<!--                </div>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
          </el-row>
        </el-form>
      </div>
      <div ref="paperLeft" class="paper-left">
        <div style="display: flex">
          <el-input
              v-model="search"
              placeholder="输入考生姓名进行查询" style="margin-top: 20px"><i slot="prefix" class="el-input__icon el-icon-search"></i></el-input>
          <el-button size="small" type="primary" style="margin-left: 2px;margin-top: 20px">重置</el-button>
        </div>
        <div style="display: flex">
          <el-button type="primary" plain style="margin-top: 20px">发布紧急通知</el-button>
          <p style="font-size: smaller;color: #999999;margin-top: 25px;margin-left: 5px">当您需要告知考生更改题目等信息可以使用此功能</p>
        </div>
        <div style="display: flex">
          <el-button type="primary" plain style="margin-top: 20px">刷新考试信息</el-button>
          <p style="font-size: smaller;color: #999999;margin-top: 25px;margin-left: 5px">当您需要刷新本场考试信息可以使用此功能</p>
        </div>
        <div style="display: flex">
          <el-button type="primary" plain style="margin-top: 20px">查看申诉列表</el-button>
          <p style="font-size: smaller;color: #999999;margin-top: 25px;margin-left: 5px">当您需要查看强制交卷考生的申诉信息可以使用此功能</p>
        </div>
        <div style="display: flex">
          <el-button type="primary" plain style="margin-top: 20px">延迟考试时间</el-button>
          <p style="font-size: smaller;color: #999999;margin-top: 25px;margin-left: 5px">当您需要延迟本次考试交卷时间可以使用此功能</p>
        </div>
        <div style="display: flex">
          <el-button type="primary" plain style="margin-top: 20px">结束本场考试</el-button>
          <p style="font-size: smaller;color: #999999;margin-top: 25px;margin-left: 5px">当您需要正常结束或提前结束本次考试可以使用此功能</p>
        </div>
      </div>
      <div class="testing">
        <el-table
            ref="testTable"
            :data="testStudent.filter(data=>search ||data.name.toLowerCase().includes(search.toLowerCase()))"
            border
            style="width: 80%"
            highlight-current-row
            @selection-change = "handleSelection"
            max-height="500"
            :default-sort="{prop:'time',order:'descending'}">

          <el-table-column
              type="index"
              width="50"
              label="序号">
          </el-table-column>
          <el-table-column
              label="考生姓名"
              style ="width：18%"
              prop="name">
          </el-table-column>
          <el-table-column
              label="考生学号"
              style ="width：18%"
              prop="studentId">
          </el-table-column>
          <el-table-column
              label="考试状态"
              style ="width：18%"
              prop="teststatus"
              >
          </el-table-column>
          <!-- <el-table-column
              label="进入时间"
              width="150"
              prop="time"
              sortable>
          </el-table-column> -->

          <!-- <el-table-column
              label="切屏次数"
              width="120"
              prop="change"
              sortable>
          </el-table-column> -->
         
          <el-table-column
              fixed="right"
              label="标记考生"
              
              style ="width：20%">
            <template slot-scope="scope">
              <el-button
                  @click="flag(scope.row.studentId)"
                  type="warning"
                  round
                  plain
                  size="small">
                标记考生
              </el-button>
            </template>
          </el-table-column>
          <el-table-column
              fixed="right"
              label="操作"
              style ="width：20%">
            <template slot-scope="scope">
              <el-button
                  @click="openDetail(scope.row)"
                  type="primary"
                  plain
                  size="small"
                  round>
                查看详情
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog title="考生考试情况详情" :visible.sync="testDialog" style="width:100%;margin-height:1500px">
          <div style="display: flex">
            <div class="left">
              <el-card class="box-card1">
                <div slot="header" class="clearfix1">
                  <i class="el-icon-user-solid"></i>
                  <span style="margin-left: 5px">学生信息</span>
                </div>
                <div class="thisname" style="display: flex">
                  <div class="label1">姓名:</div>
                  <div style="margin-left: 5px">{{testData.name}}</div>
                  <div class="label1"style="margin-left: 10px">学号:</div>
                  <div style="margin-left: 5px">{{testData.studentId}}</div>
                  
                </div>
              </el-card>
              <el-card class="box-card1" style="margin-top: 30px">
                <div slot="header" class="clearfix1">
                  <i class="el-icon-s-order"></i>
                  <span style="margin-left: 5px">考生状态</span>
                </div>
                <div class="thisstatus" style="display: flex">
                  <div class="label1" style="padding-top: 5px;margin-left: 5px">考试状态:</div>
                  <div style="padding-top: 5px;margin-left: 5px">{{testData.teststatus}};</div>
                  <div class="label1" style="padding-top: 5px;margin-left: 5px">抓拍次数:</div>
                  <div style="padding-top: 5px;margin-left: 5px">2</div>
                  <div class="label2" style="padding-top: 5px;margin-left: 10px">切屏次数:</div>
                  <div style="padding-top: 5px;margin-left: 5px">暂无</div>
              
                </div>
              </el-card>
             
              <el-popover
                placement="bottom"
                
                width="300"
                trigger="click"
                
                >
                <p>请等待人脸识别匹配结果：</p>
                <div>
                  人脸相似度：{{similar}}
                </div>
                <el-button slot="reference" @click="facelook(testData.studentId)" type="primary" plain size="small" style="margin-left:0px;margin-top:30px">进行人脸识别匹配</el-button>
              </el-popover>

              <div class="right" style="margin-left:550px;margin-top:-350px">
                <el-card class="box-card3">
                  <div style="padding: 14px;">
                    <span>所抓拍的电脑前置摄像头照片集</span>
                    <div class="bottom clearfix">
                      <time class="time">{{ testData.time }}</time>
                    </div>
                  </div>
                  <el-image
                      :src="testData.url"
                      class="image">
                  </el-image>
                  <el-image
                      :src="testData.urllist"
                      class="image">
                  </el-image>
                </el-card>
              </div>
            </div>
            <!-- <div class="right">
              <el-card class="box-card3">
                <div slot="header" class="clearfix1">
                  <i class="el-icon-menu"></i>
                  <span style="margin-left: 5px">监考老师可执行操作</span>
                </div>
                <div style="display: flex;margin-top: 5px">
                  <el-button type="primary">手动抓拍</el-button>
                  <p style="padding-top: 10px;font-size: small;color: #999999;padding-left: 10px">您可以随时使用这个功能对学生当前状态的照片进行采集</p>
                </div>
                <div style="display: flex;margin-top: 20px">
                  <el-button type="primary">标记考生</el-button>
                  <p style="padding-top: 10px;font-size: small;color: #999999;padding-left: 10px">若您认为该考生疑似作弊，您可以使用这个功能进行标记</p>
                </div>
                <div style="display: flex;margin-top: 20px">
                  <el-button type="primary">提醒考生</el-button>
                  <p style="padding-top: 10px;font-size: small;color: #999999;padding-left: 10px">若您认为该考生疑似作弊，您可以使用这个功能提醒学生</p>
                </div>
                <div style="display: flex;margin-top: 20px">
                  <el-button type="primary">强制收卷</el-button>
                  <p style="padding-top: 10px;font-size: small;color: #999999;padding-left: 10px">若您认为该考生出现严重作弊行为可使用这个功能强行终止学生答题</p>
                </div>
              </el-card>
            </div> -->
          </div>
          <!--          <el-table :data="testData">-->
          <!--            <el-table-column label="姓名" width="150" property="name"></el-table-column>-->
          <!--          </el-table>-->
        </el-dialog>
      </div>
      <!--      &lt;!&ndash;    浏览器返回的弹窗&ndash;&gt;-->
      <!--      <div class="deleteTip pubsontext" v-show="tipshow">-->
      <!--        <div class="tipSon">-->
      <!--          <p>确定要退出当监考？</p>-->
      <!--          <div class="footerBut">-->
      <!--            <span class="cancle" @click="tipClose">取消</span>-->
      <!--            <span class="sure" @click="tipBacks">确定</span>-->
      <!--          </div>-->
      <!--        </div>-->
      <!--      </div>-->
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {

  name: 'invigilate',
  data() {
    return {
      //相似度
      similar:'',
      //倒计小时
      hour: '',
      //倒计分钟
      minute: '',
      //倒计秒
      second: '',
      //计时器
      promiseTimer: '',
      //数据源
      tempDataSource: {},
      //浏览器返回
      tipshow: false,
      backStatu: false,
      //数据源
      invigilateSource: {
        //试卷ID
        paperId: '001',
        //试卷名称
        paperName: '测试试卷1',
        //监考老师ID
        teacherId: '012',
        //监考老师名称
        teacherName: '张大炮',
        //分数
        allscore: 100,
        //考试时长(分钟)
        examDuration: 10,
        pepleNum:2,
      },
      testTable:[],
      testStudent:[],
      testDialog:false,
      testData:{ name:'',
        studentId:'',
        time:'',
        status:'',
        ip:'',
        url:'',
        urllist:[],
        change:0,
        note:'',},
      url:"https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
      urllist:['https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png',''],
      search:''
    }
  },
  // beforeRouteLeave(to,from,next){
  //   if(this.backStatu){
  //     next(true);
  //   }else{
  //     next(false);
  //     console.log(to)
  //     console.log(from)
  //   }
  //   this.tipshow = true;
  // },

  watch: {
    invigilateSource(newValue, oldValue) {
      Object.assign(this.tempDataSource, newValue)
      //  this.convertData()
    }
  },
  computed: {
    hourString () {
      return this.hour < 10 ? '0' + this.hour : '' + this.hour
    },
    minuteString () {
      return this.minute < 10 ? '0' + this.minute : '' + this.minute
    },
    secondString () {
      return this.second < 10 ? '0' + this.second : '' + this.second
    }
  },
  mounted () {
    this.gettestheader();
    this.getTestvideolist();
    
    let remainTime=this.invigilateSource.examDuration*60;
    if (remainTime> 0) {
      this.hour = Math.floor((remainTime / 3600) % 24)
      this.minute = Math.floor((remainTime / 60) % 60)
      this.second = Math.floor(remainTime % 60)
      this.countDowm()
    }

  },
  methods: {

    facelook(){
      let studentID = this.testData.studentId
      let testid = 1
      const _this = this
      axios({
        url:'http://localhost:8080/CompareFace/compareFaces',
        method:'post',
        headers:{ 'Content-Type': 'application/json'},
        params :{
          studentID,
        testid
        }
      
      }).then(function(res){

        if(res.data.msg !='success'){
          alert(res.data.msg)
        }
        else{
        _this.similar = Number(res.data.data*100).toFixed(2);
        _this.similar += "%"
        console.log(_this.similar)
        console.log('bbb')
      }})
    },

    base64ImgtoFile(dataurl, filename = 'file') {
      
      
      
      if(dataurl ){
      let arr = dataurl.split(',')
      let mime = arr[0].match(/:(.*?);/)[1]
      
      let suffix = mime.split('/')[1]
      let bstr = atob(arr[1])
      let n = bstr.length
      let u8arr = new Uint8Array(n)
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
      }
      return new File([u8arr], `${filename}.${suffix}`, {
        type: mime
      })}
    },
    // // base64 转为 file
    // base64ToFile(urlData, fileName) {
    //   let arr = urlData.split(',');
    //   let mime = arr[0].match(/:(.*?);/)[1];
    //   let bytes = atob(arr[1]); // 解码base64
    //   let n = bytes.length
    //   let ia = new Uint8Array(n);
    //   while (n--) {
    //     ia[n] = bytes.charCodeAt(n);
    //   }
    //   return new File([ia], fileName, { type: mime });
    // },
//获取标头信息
    gettestheader(){
      let testId = this.$route.query.testId;//获取路由传递过来的考试编号 
      const _this = this
      axios({
        url:'http://localhost:8080/showpaper/getpaper/bytestId',
        method:'post',
        headers:{ 'Content-Type': 'application/json'},
        params :{testId: testId}
      
      }).then(function(res){
        console.log(res);
        console.log('aaa')
        // console.log(_this.url)
        _this.tempDataSource = res.data.data
        console.log(_this.tempDataSource)
      })
    },
    getTestvideolist() {
      const _this = this
      let testId = this.$route.query.testId;//获取路由传递过来的考试编号 
       axios({
        url: `http://localhost:8080/studentvideo/invigilate` ,
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params :{testId: testId}
      }).then(function (res) {

            console.log(res);
            _this.testStudent = res.data.data;
            console.log(_this.testStudent)
            for(let i=0;i<_this.testStudent.length;i++){
              // _this.student.push("teststatus:")
              if(_this.testStudent[i].status == 1){
                _this.testStudent[i].teststatus = '未进入考试'
              }
              else if(_this.testStudent[i].status == 2){
                _this.testStudent[i].teststatus = '正在考试'
              }
              else _this.testStudent[i].teststatus = '已交卷'
            }
            
            // // base64编码的图片
            // for(let i =0;i<res.data.data.length;i++){
            //   _this.url = res.data.data[0].video
            //   _this.urllist[i] = res.data.data[i].video
            // }
            
            // console.log(base64Img)
            //转换图片文件
            // var imgFile = _this.base64ImgtoFile(base64Img);
            // console.log(imgFile)
            // // _this.urllist.push(res.data.data.video)
            // _this.url = imgFile
            
            

          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
      // const _this = this;
      // // let phone = localStorage.getItem('ms_username')
      // // console.log(this.phoneNumber,this.passWord)
      // axios.post("http://localhost:8080/studentvideo/get", {
      //       //.post("https://10.135.242.126:8080/user/logon", {
      //       //user: JSON.stringify({
      //       phone: this.testStudent.name,

      //       //}),
      //     }) //具体phoneNumber和passWord对象跟后端对接口时改
      //     .then(function (res) {
      //           console.log(res);
      //           _this.tableData = res.data.data;
      //         console.log(_this.tableData)
      //         },
      //         function (err) {
      //           console.log(err);
      //           alert("服务器错误!稍后重试");
      //         }
      //     );
    },
    /**
     * 倒计时
     */
    countDowm () {
      let self = this
      clearInterval(this.promiseTimer)
      this.promiseTimer = setInterval(function () {
        if(self.hour===0 && self.minute===0 && self.second===0)
        {
          self.disabledAnswer=true;
        }
        if (self.hour === 0) {
          if (self.minute !== 0 && self.second === 0) {
            self.second = 59
            self.minute -= 1
          } else if (self.minute === 0 && self.second === 0) {
            self.second = 0
            self.$emit('countDowmEnd', true)
            clearInterval(self.promiseTimer)
          } else {
            self.second -= 1
          }
        } else {
          if (self.minute !== 0 && self.second === 0) {
            self.second = 59
            self.minute -= 1
          } else if (self.minute === 0 && self.second === 0) {
            self.hour -= 1
            self.minute = 59
            self.second = 59
          } else {
            self.second -= 1
          }
        }
      }, 1000)
    },
    // tipBacks(){
    //   this.backStatu = true;
    //   this.$router.go(-1);
    // },
    handleSelection(val) {
      this.testTable=val
    },
    noteHandler(value,row,column){
      const property = column['property'];
      return row[property] === value;
    },
    statusHandler(value,row,column){
      const property = column['property'];
      return row[property] === value;
    },
    openDetail(items){
      console.log(items);
      this.testData.name=items.name;
      this.testData.change=items.change;
      this.testData.studentId=items.studentId;
      this.testData.ip=items.ip;
      this.testData.note=items.note;
      console.log(items.status)
      this.testData.teststatus=items.teststatus;
      this.testData.time = items.time;
      console.log('aaa')
      console.log(items.video)
      this.testData.url = items.video;
      
      // this.testData.urllist = items.video
      console.log(this.testData)
      this.testDialog = true;
    },
    //标记考生事件
    flag(studentId){
       let testId = this.$route.query.testId;//获取路由传递过来的考试编号 
        axios({
        url:'http://localhost:8080//studenttestflag/save',
        method:'post', 
        headers:{ 'Content-Type': 'application/json'},
        data :{
          studentId:studentId,
          testId:testId}
      
      }).then(function(res){
        // console.log(res);
        // console.log('aaa')
        // // console.log(_this.url)
        // _this.tempDataSource = res.data.data
        // console.log(_this.tempDataSource)
      })
      
    },
    destroyed() {
    }
  }

}
</script>

<style scoped>
.invigilate-main {
  margin: 10px;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: hidden
}

.invigilate-header {
  width: 100%;
  height: 60px;
  background-color: #f7f7f7;
  position: absolute;
  top: 0;
  z-index: 1000;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, .1);
  -webkit-box-shadow: 0 2px 2px 0 rgba(0, 0, 0, .1);
}

.paper-left {
  position: absolute;
  padding: 10px;
  left: 0;
  top: 60px;
  bottom: 0;
  width: 300px;
  overflow-x: hidden;
  overflow-y: auto;
  border: 1px solid #e4e4e4;
  border-top: none;
}

.paper-title h1 {
  font-size: 1.2em;
  margin: 0;
}

.downTime{
  color: rgb(230, 93, 110);
  font-size: 16px;
  font-weight: bold;
  left:50px;
}
.subject-title h2{
  font-size: 16px;
  display: inline-block;
}
.subject-title span {
  font-size: 16px;
  display: inline-block;
}




.el-collapse-item h2 {
  width: 150px;
  font-size: 14px;
  display: inline-block;
}
/*浏览器返回*/
.deleteTip {
  height: 100%;
  width: 100%;
  position: fixed;
  background: rgba(0, 0, 0, 0.5);
  top: 0;
  z-index: 4;
}
.pubsontext .tipSon {
  top: 30%;
}
.deleteTip .tipSon {
  text-align: center;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  margin: auto 0;
  background: #fff;
  border-radius: 0.2rem;
  padding: 1rem 0.4rem 0.5rem;
}
.pubsontext .tipSon p {
  text-align: center;
  font-size: 0.3rem;
}
.deleteTip .tipSon p {
  color: #000000;
  line-height: 0.42rem;
  padding-bottom: 0.5rem;
}
.deleteTip .tipSon .footerBut {
  display: flex;
  line-height: 0.7rem;
  font-size: 0.36rem;
  color: #666;
}
.deleteTip .tipSon .footerBut .cancle {
  background: #4293e5;
  color: #fff;
  margin-right: 0.1rem;
}
.deleteTip .tipSon .footerBut .sure {
  color: #4293e5;
  margin-left: 0.1rem;
}
.deleteTip .tipSon .footerBut .cancle, .deleteTip .tipSon .footerBut .sure {
  flex: 1;
  font-size: 0.36rem;
  border: 0.01rem solid #4293e5;
  border-radius: 0.5rem;
}
.labelDetail {
  line-height: 0px;
}
.testing{
  padding: 10px 0px;
  margin-left: 320px;
  margin-top: 80px;
}
.text1 {
  font-size: 14px;
}

.item1 {
  margin-bottom: 18px;
}

.clearfix1:before,
.clearfix1:after {
  display: table;
  content: "";
}
.clearfix1:after {
  clear: both
}

.box-card1 {
  width: 480px;

}
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}


.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
.right{
  margin-left: 50px;
}

</style>


