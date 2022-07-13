
<template>
  <div class="body">
    <div class="paper-main">
      <div class="paper-header">
        <el-form label-position="top" label-width="100px" :model="dataSource" style="padding-top:0px; ">
          <el-row>
            <el-col :span="4" :offset="1">
              <el-form-item label="试卷">
                {{papername}}
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="考生编号">
                {{studentId}}
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="总分">
                {{totalscore}}
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="考试时长">
                {{lasttime}}分
              </el-form-item>
            </el-col>
            <el-col :span="4" v-if="this.type===1">
              <el-form-item label="倒计时间">
                <span class="downTime" >{{hour? hourString+':'+minuteString+':'+secondString : minuteString+':'+secondString}}</span>
              </el-form-item>
            </el-col>
            
          </el-row>
        </el-form>
      </div>
      <div ref="paperLeft" class="paper-left">
        <div class="paper-title">
          <h1><i class="el-icon-s-grid"></i>答题卡</h1>
        </div>
        <el-collapse v-model="answerCardActiveName">
          <el-collapse-item v-for="item in convertDatas"   :name="item.code" >
            <template slot="title">
              <h2>{{item.name}}</h2><span>共{{item.count}}题</span>
            </template>
            <el-button circle size="small" v-for="index of item.count" :id="'answer'+item.code+index"  @click.native="jump(item.code+index)">{{index}}</el-button>
          </el-collapse-item>
        </el-collapse>
      </div>

      <div ref="paperContent" class="paper-content" style="margin-width:80%">
        <div class="subject" v-for="item in convertDatas">
          <div class="subject-title" >
            
            <h2>{{item.name}}</h2><span>（共 {{item.count}} 题，合计 {{item.totalscore}} 分）</span>
          </div>
          <el-card class="box-card" v-for="(sub,index) in item.childs" :id="item.code+(index+1)">
            <div slot="header" class="clearfix">
              <el-tag effect="dark"> {{index+1}} </el-tag>
              <span>{{sub.stem}}</span>
              <span>({{sub.score}}分)</span>
              <div v-if="type===2 || type===3" style="float: right; padding: 3px 0">
                <el-radio-group v-model="sub.isHook">
                  <el-radio-button :disabled="disabledRead" :label="1"  @change.native="isHookButtionCheck(sub)"><i class="el-icon-check"/></el-radio-button>
                  <el-radio-button :disabled="disabledRead" :label="2"  @change.native="isHookButtionCheck(sub)"><i class="el-icon-close"/></el-radio-button>
                </el-radio-group>
                <div v-if="sub.quesTypeId===1 ||sub.quesTypeId===2||sub.quesTypeId===3" style="display: inline;">
                  <el-input :disabled="true" v-model="sub.score" style="width:50px" ></el-input><span>分</span>
                </div>
                <div v-else style="display: inline;">
                  <el-input :disabled="disabledRead" v-model="sub.score" style="width:50px" ></el-input><span>分</span>
                </div>

              </div>
            </div>
            <el-radio-group v-if="sub.quesTypeId===1"  v-model="sub.answer">
              
              <el-radio :disabled="disabledAnswer" :label="sub.choiceA" class="answer-radio" @change="answerButtionCheck1($event,item,sub)">{{'A'}}.{{sub.choiceA}}</el-radio>
              <el-radio :disabled="disabledAnswer" :label="sub.choiceB" class="answer-radio" @change="answerButtionCheck1($event,item,sub)">{{'B'}}.{{sub.choiceB}}</el-radio>
              <el-radio :disabled="disabledAnswer" :label="sub.choiceC" class="answer-radio" @change="answerButtionCheck1($event,item,sub)">{{'C'}}.{{sub.choiceC}}</el-radio>
              <el-radio :disabled="disabledAnswer" :label="sub.choiceD" class="answer-radio" @change="answerButtionCheck1($event,item,sub)">{{'D'}}.{{sub.choiceD}}</el-radio>
            </el-radio-group>
            <!-- <el-checkbox-group v-if="sub.quesTypeId===2" v-model="mAnswer" >
              <el-checkbox :disabled="disabledAnswer" v-for="(o,index) in sub.content" :label="index+1" class="answer-checkbox" @change="answerButtionCheck1($event,item,sub)">{{index+1}}.{{o}}</el-checkbox>
            </el-checkbox-group> -->
            <!-- <el-checkbox-group v-if="sub.quesTypeId===2" v-for="(item,index) in Manswer" v-model="item.answer" @change="getValue(item.answer)"> -->
               <el-checkbox-group v-if="sub.quesTypeId===2"  v-model="answer1[index]" >
              <el-checkbox :disabled="disabledAnswer" :label="sub.choiceA"  class="answer-checkbox" @change="answerButtionCheck1($event,item,sub)">{{'A'}}.{{sub.choiceA}}</el-checkbox>
              <el-checkbox :disabled="disabledAnswer" :label="sub.choiceB"  class="answer-checkbox" @change="answerButtionCheck1($event,item,sub)">{{'B'}}.{{sub.choiceB}}</el-checkbox>
              <el-checkbox :disabled="disabledAnswer" :label="sub.choiceC"  class="answer-checkbox" @change="answerButtionCheck1($event,item,sub)">{{'C'}}.{{sub.choiceC}}</el-checkbox>
              <el-checkbox :disabled="disabledAnswer" :label="sub.choiceD"  class="answer-checkbox" @change="answerButtionCheck1($event,item,sub)">{{'D'}}.{{sub.choiceD}}</el-checkbox>
              <!-- <el-checkbox :disabled="disabledAnswer" v-for="(o,index) in sub.content" :label="index+1" class="answer-checkbox" @change="answerButtionCheck1($event,item,sub)">{{index+1}}.{{o}}</el-checkbox> -->
            </el-checkbox-group>
        
              <!-- <el-checkbox-group
                v-if="sub.quesTypeId===2"
                v-model="sub.answer"
              >
                <el-checkbox label="A">{{ sub.choiceA}}</el-checkbox>
                <el-checkbox label="B">{{ sub.choiceB}}</el-checkbox>
                <el-checkbox label="C">{{ sub.choiceC}}</el-checkbox>
                <el-checkbox label="D">{{ sub.choiceD }}</el-checkbox>
             
              </el-checkbox-group> -->
            
   
            <el-radio-group v-if="sub.quesTypeId===5" v-model="sub.answer">
              <el-radio :disabled="disabledAnswer" label="对" class="answer-radio" @change="answerButtionCheck1($event,item,sub)">对</el-radio>
              <el-radio :disabled="disabledAnswer" label="错" class="answer-radio" @change="answerButtionCheck1($event,item,sub)">错</el-radio>
            </el-radio-group>
            <el-input :disabled="disabledAnswer"  v-if="sub.quesTypeId===3" type="textarea" :rows="2" v-model="sub.answer"  resize="none" maxlength="150" @blur="answerButtionCheck1($event,item,sub)"> </el-input>
            <el-input :disabled="disabledAnswer"  v-if="sub.quesTypeId===4" type="textarea" :rows="10" v-model="sub.answer" resize="none" maxlength="2000" @blur="answerButtionCheck1($event,item,sub)"> </el-input>
            <div v-if="type!==1" class="subject-remark">
              <div class="item">
                <span class="title">考生答案：</span>
                <span>{{converAnswerStr(sub.answer)}}</span>
              </div>
              <div class="item">
                <span class="title">正确答案：</span>
                <span>{{converAnswerStr(sub.correctAnswer)}}</span>
              </div>
              <div class="item">
                <span class="title">考生答案：</span>
                <span>{{sub.answerAnalysis}}</span>
              </div>
            </div>
          </el-card>
        </div>
      </div>

      <div class="paper-footer">
        <el-button v-if="type===1" type="success" @click.native="btnClick('handPaper')">交卷</el-button>
        <el-button v-if="type===2" type="success" @click.native="btnClick('readPaper')">阅卷</el-button>
        <el-button v-if="type===2" type="success" @click.native="btnClick('readPaperUpper')">上一个</el-button>
        <el-button v-if="type===2" type="success" @click.native="btnClick('readPaperNext')">下一个</el-button>
      </div>
      <!-- <div>
        <video style="margin-left:85%;"id="videoCamera" :width="videoWidth" :height="videoHeight"  autoplay></video>
        <canvas style="display:none;margin-left:85%;" id="canvasCamera" :width="videoWidth" :height="videoHeight"></canvas>

        <!-- <button plain  @click="setImage()" style="margin-left:85%;">手动拍照</button>
        <button style="left:00px;" @click="stopNavigator()">关闭摄像头</button> -->
        <p class="fail_tips" style="margin-left:85%;">考试途中会有随机抓拍，请考生尽量正脸面向摄像头</p>
        <!-- 给外面盒子设置宽高，可以限制拍照图片的大小位置范围 -->
        <!-- <div class="result_img" style="margin-left:85%;">
          <img :src="imgSrc" alt class="tx_img" width="100%" />
        </div>
        <p class="res_tips" style="margin-left:1500px;">抓拍效果展示</p> -->
      </div> -->
      <!--    防切屏提示的弹窗-->
      <el-dialog title="提示" :visible.sync="tipsFlag" width="480px" class="commonDialog multi clickLight" center :close-on-click-modal="false" :close-on-press-escape= false>
        <div class="dialogTipsbox" v-if="tips===1">你还有试题未作答，确认要交卷？</div>
        <div class="dialogTipsbox" v-if="tips===2">
          最多只能切屏{{switchPageTimes}}次，你还可切换{{remaTimes}}次，
          <br />
          超过{{switchPageTimes}}次将强行交卷！
        </div>
        <div style="font-size:18px;">{{Sencond}}秒后自动交卷。</div>
        <span slot="footer" class="dialog-footer">
        <el-button @click="tipsFlag = false" v-if="tips===1">取 消</el-button>
        <el-button type="primary" @click="onConfirmTip" v-if="tips===1">确 定</el-button>
        <el-button type="primary" @click="onConfirmTip" v-if="tips===2">我知道了</el-button>
      </span>
      </el-dialog>
      <!--    浏览器返回的弹窗-->
      <div class="deleteTip pubsontext" v-show="tipshow&&!summit">
        <div class="tipSon">
          <p>确定要退出当考试？</p>
          <div class="footerBut">
            <span class="cancle" @click="tipClose">取消</span>
            <span class="sure" @click="tipBacks">确定</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from 'axios';

export default {

  name: 'examinationPaper',
  props: {
    //试卷类型 1 考试 2 阅卷 3 查看
    type: {
      type: Number,
      default: 1
    },
    
    //数据源
    dataSource: {
      type: Object,
      default: () => {
        return {
          //试卷ID
          paperId: '001',

           
          //试卷名称
          paperName: '测试试卷1',
          //考生ID
          examineId: '012',
          //考生名称
          examineName: '张三',
          //分数
          score: 100,
          //考试时长(分钟)
          examDuration: 10,
          //交卷时间
          submissionTime: '',
          list:[{
              // 题目类型 1.单选题 2.多选题 3.判断题 4.填空题 5.简答题
              quesTypeId:null,
              // 题目ID
              id: null,
              // 题号
              no: null,
              
              // 题目
              stem: "asd dsgfdsdagsfg",
              // 题目总分
              totalscore: null,
              // 答案集合
              answers: [
                {
                  // 答案id
                  id: "",
                  // 答案序号
                  no: "",
                  // 答案
                  answer: ""
                }
              ],
              // 考生答案
              answer: null, // 1.单选题 '' 2.多选题 ['1','2'] 3.判断题 '' 4.填空题  ['1','2'] 5.简答题 ''
              // 正确答案
              correctAnswer: null, // 1.单选题 '' 2.多选题 ['1','2'] 3.判断题 '' 4.填空题  ['1','2'] 5.简答题 ''
              // 答案解析
              answerAnalysis: "",
              // 是否对错  1.对 0.错
              isHook: null,
              // 得分
              score: null
              }]
        }
      }
    }
  },
  data() {
    return {
      answer1:[],
      i:0,
      //
      abcdlist:[
        {
          choice:[],
        }
      ],
      choice:[],   
      papername:'',
      studentId:'',
      studentname:'',
      lasttime:'',
      i:null,
      //多选答案 集合
      Manswer:[
        {
        answer:[]
        }
      ],
      flag:0,
      //倒计小时
      hour: '',
      //倒计分钟
      minute: '',
      //倒计秒
      second: '',
      //计时器
      promiseTimer: '',
      //数据源
      // tempDataSource: {},
      //答题卡激活项
      answerCardActiveName: [],
      //组装后数据集
      convertDatas: [],
      //禁止答题
      disabledAnswer:false,
      //禁止阅卷
      disabledRead:false,
      //防切屏
      code:"0",
      remaTimes: 2,
      isStop: false,
      switchPageTimes:3,
      tipsFlag:false,
      tips:0,
      //计时
      Sencond: 5,//设置初始倒计时
      isDisabled: false,
      //浏览器返回
      tipshow:false,
      summit:false,
      backStatu:false,
      // 视频调用相关数据开始
      videoWidth: 300,
      videoHeight: 450,
      imgSrc: "",
      thisCancas: null,
      thisContext: null,
      thisVideo: null,

      //视频调用相关数据结束
      postVideoImg:'',// 图片上传后获取的url链接
      time:'',
      //交卷提供数据
      list:
          [
            {
              questionId:'001',
              studentId:'123456',
              studentAnswer:'爱上内存空间',
              correctAnswer:'白醋网络科技',
              justify:'6',
              score:'001',
            },
            {
              questionId:'002',
              studentId:'001',
              studentAnswer:'001',
              correctAnswer:'001',
              justify:'001',
              score:'001',
            },
          ],
        dataa:[],
        datab: [],
        //多选题答案集合
        mAnswer:[],
    }
  },
  beforeRouteLeave(to,from,next){
    if(this.backStatu){
      next(true);
    }else{
      next(false);
      console.log(to)
      console.log(from)
    }
    this.tipshow = true;
  },
  // watch: {
  //   dataSource(newValue, oldValue) {
  //     Object.assign(this.tempDataSource, newValue)
  //     this.convertData()
  //   }
  // },
  created() {
    
    this.$nextTick(() => {
                // 禁用右键
                document.oncontextmenu = new Function("event.returnValue=false");
                // 禁用选择
                document.onselectstart = new Function("event.returnValue=false");
            });

    // this.getTest()
    //  this.getTest()
    // Object.assign(this.tempDataSource, this.dataSource)
    // this.convertData()
    // console.log(this.convertDatas)
    console.log('1111111111')
    // console.log(this.tempDataSource)
    console.log(this.dataSource.list)
    if(this.type===2)
    {
      this.disabledAnswer=true
    }
    if(this.type===3)
    {
      this.disabledAnswer=true
      this.disabledRead=true
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
    

    console.log('00000000000000000')
    this.getinfo()
    this.getTest()
    
    console.log('00000000000000000')
    // console.log(this.dataSource)
    // console.log(this.dataSource.list)
    // this.convertData()


    if(this.type===2 || this.type===3)
    {
      this.convertDatas.forEach(t=>{

        t.childs.forEach(c=>{
          this.answerButtionCheck(c.answer,t,c);
        });
      });
    }
    // this.getTest()
    // console.log(this.convertDatas.item)
    //  console.log(this.dataSource)
    // console.log('222222')
    // this.convertData()
    // console.log(this.tempDataSource)

    // 第一步打开摄像头
    console.log(1);
    this.getCompetence() //调用摄像头
    this.timer = setTimeout(this.handleRefresh, 2000);//第一张人脸照片
    this.timer = setInterval(this.handleRefresh,60*30*1000)//抓拍照片
    // 防切屏功能代码
    window.addEventListener("scroll",this.handleScroll);
    window.addEventListener("resize",this.getLeftDistance);
    window.addEventListener("visibilitychange",this.pageHidden);
    this.$nextTick(function (){
      let body = document.querySelector("body");
      body.style.overflow="auto";
      this.flexLeft = (body.offsetWidth-1200)/2
    })

  },
  methods: {
    // getValue(){
    //   // console.log(value)
    //   // for(let i=0;i<this.answ)
    //   // this.mAnswer = value
    //   let i = []
    //   this.answer1.push(i)
    // },
    //标签头信息获取
    getinfo(){
      const _this = this;
      let testId = this.$route.query.testId;//获取路由传递过来的考试编号 
      let phone = localStorage.getItem('ms_username')
      this.$axios({
        url: 'http://localhost:8080/showpaper/getpaper/bytestId',
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params: {testId:testId}
        
      }).then(res=>{
            console.log(res);
      _this.papername = res.data.data.description
      _this.studentId = phone
      _this.lasttime = res.data.data.totalTime
      _this.totalscore = res.data.data.totalScore
          if(_this.type===1)
    {
      let remainTime=_this.lasttime*60;
      console.log('111')
      console.log(_this.lasttime)
      if (remainTime> 0) {
        _this.hour = Math.floor((remainTime / 3600) % 24)
        _this.minute = Math.floor((remainTime / 60) % 60)
        _this.second = Math.floor(remainTime % 60)
        _this.countDowm()
      }
    }

          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
    },
    //试卷获取
    getTest(){
      const _this = this;
      let testId = this.$route.query.testId;//获取路由传递过来的考试编号 
      console.log(testId)
      this.$axios({
        url: 'http://localhost:8080/showpaper/getQuestion/bytestId',
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params: {testId:testId}
        
      }).then(res=>{
            console.log(res);
            // this.dataSource = 
            this.dataSource.list = res.data.data
            
            // console.log(this.abcdlist[i].choice)
            console.log('aaaaaa');
            
            
            console.log(this.dataSource.list);
            console.log(this.dataSource.list[1].content);
            for(let i=0; i<this.dataSource.list.length; i++){
            this.dataSource.list[i].content = this.dataSource.list[i].content.split(',') //字符串按逗号分隔成数组
            let j = []
            this.answer1.push(j)
            // this.Manswer[i].answer[i] = this.dataSource.list[i].content
            }
            // for(let i=0; i<this.dataSource.list.length; i++){
              
            //   // this.abcdlist[i].choice = this.dataSource.list[i].choiceE.split(',') //字符串按逗号分隔成数组
            //   let tempData = []
            //   tempData.push(this.dataSource.list[i].choiceA)
            //   tempData.push(this.dataSource.list[i].choiceB)
            //   tempData.push(this.dataSource.list[i].choiceC)
            //   tempData.push(this.dataSource.list[i].choiceD)
            //   this.abcdlist[i].choice = tempData
            // }
            // console.log('000')
            // for(let i=0;i<_this.dataSource.list.length;i++){
            //   this.Manswer[i].answer = _this.dataSource.list[i].answer.split(',')
            // }
            //   console.log('000')
            //   console.log(this.Manswer)
            this.convertData();
          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
    },

    
    //视频调用相关方法
    handleRefresh(){
      //执行方法逻辑
      //打印检查运行结果
      console.log("time")
      //执行拍照
      this.setImage();
    },

    // 第三步、拍照图转换file格式上传，
    // 第四步、获取图片url链接
postImg(){
      let formData = new FormData()
      formData.append('video', this.imgSrc)
      formData.append("phone", localStorage.getItem('ms_username'))// 额外参数
      var jsonData={};
      formData.forEach((value, key) => jsonData[key] = value);
      // 对应的后台上传图片接口 === app/StudentVideoController/uploadFile
      this.$axios.post('http://localhost:8080/studentvideo/save',jsonData,{headers: {'Content-Type': 'application/json'}}).then(res => {
        // console.log(res);
        if(res.data.code == '00'){
          // 图片文件传至后台 == 获取到该图片的url路径
          this.postVideoImg = res.data.FilePath
          //获得图片的url后，需要做什么
          //做的事情......

        }

      }).catch(error => {
        console.log(error);
      })

    },

    // 调用权限（打开摄像头功能）
    getCompetence() {
      var _this = this;
      _this.thisCancas = document.getElementById("canvasCamera");
      _this.thisContext = this.thisCancas.getContext("2d");
      _this.thisVideo = document.getElementById("videoCamera");
      _this.thisVideo.style.display = 'block';
      // 获取媒体属性，旧版本浏览器可能不支持mediaDevices，我们首先设置一个空对象
      if (navigator.mediaDevices === undefined) {
        navigator.mediaDevices = {};
      }
      // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
      // 使用getUserMedia，因为它会覆盖现有的属性。
      // 这里，如果缺少getUserMedia属性，就添加它。
      if (navigator.mediaDevices.getUserMedia === undefined) {
        navigator.mediaDevices.getUserMedia = function(constraints) {
          // 首先获取现存的getUserMedia(如果存在)
          var getUserMedia =
              navigator.webkitGetUserMedia ||
              navigator.mozGetUserMedia ||
              navigator.getUserMedia;
          // 有些浏览器不支持，会返回错误信息
          // 保持接口一致
          if (!getUserMedia) {//不存在则报错
            return Promise.reject(
                new Error("getUserMedia is not implemented in this browser")
            );
          }
          // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
          return new Promise(function(resolve, reject) {
            getUserMedia.call(navigator, constraints, resolve, reject);
          });
        };
      }
      var constraints = {
        audio: false,
        video: {
          width: this.videoWidth,
          height: this.videoHeight,
          transform: "scaleX(-1)"
        }
      };
      navigator.mediaDevices
          .getUserMedia(constraints)
          .then(function(stream) {
            // 旧的浏览器可能没有srcObject
            if ("srcObject" in _this.thisVideo) {
              _this.thisVideo.srcObject = stream;
            } else {
              // 避免在新的浏览器中使用它，因为它正在被弃用。
              _this.thisVideo.src = window.URL.createObjectURL(stream);
            }
            _this.thisVideo.onloadedmetadata = function(e) {
              _this.thisVideo.play();
            };
          })
          .catch(err => {
            console.log(err);
          });
    },
    //  第二步、绘制图片（拍照功能）
    setImage() {
      var _this = this;
      // canvas画图
      _this.thisContext.drawImage(
          _this.thisVideo,
          0,
          0,
      );
      // 获取图片base64链接
      var image = this.thisCancas.toDataURL("image/png");
      _this.imgSrc = image;//赋值并预览图片

      //这里是调用上传图片接口=====
      this.postImg() // 绘制完图片调用图片上传接口

    },
    // 关闭摄像头
    stopNavigator() {
      this.thisVideo.srcObject.getTracks()[0].stop();
    },

    // base64 转为 file
    base64ToFile(urlData, fileName) {
      let arr = urlData.split(',');
      let mime = arr[0].match(/:(.*?);/)[1];
      let bytes = atob(arr[1]); // 解码base64
      let n = bytes.length
      let ia = new Uint8Array(n);
      while (n--) {
        ia[n] = bytes.charCodeAt(n);
      }
      return new File([ia], fileName, { type: mime });
    },
    /**
     * 按钮点击事件
     */
    btnClick(type){
      console.log('66666666666666666')
      console.log(this.convertDatas);
      switch (type) {
          //交卷
        case 'handPaper':
          let questionId=[]
          let phone = localStorage.getItem('ms_username')
          let testId = this.$route.query.testId;//获取路由传递过来的考试编号 
          for(let i =0;i<this.dataSource.list.length;i++){
            questionId[i] = this.dataSource.list[i].id
          }
          console.log(questionId)
          // for(let i=0;i<5;i++){
          //     this.dataa[i] = this.convertDatas[i].childs
          //     // this.datab = this.dataa[i].answer
          // }

          console.log('333333333333')
          console.log(this.dataa)
          console.log(this.answer1)
          //所有题目的个数
          // let len =0
          // len = this.dataa[0].length+this.dataa[1].length+this.dataa[2].length+this.dataa[3].length+this.dataa[4].length
          // console.log(len)
          
          
          
          
          for(let a=0;a<this.convertDatas[1].childs.length;a++){
              this.dataa[this.convertDatas[0].childs.length+a].answer = JSON.stringify(this.answer1[a])
            }
          
          
          for(let i=0;i<this.dataa.length;i++){
            this.dataa[i].content = null
          if(this.dataa[i].quesTypeId ==1 || this.dataa[i].quesTypeId ==5){
            this.dataa[i].answer = this.dataa[i].answer.split(',')
            this.dataa[i].answer = JSON.stringify(this.dataa[i].answer)
          }
          
          }
          console.log(this.dataa)
          let dataq = this.dataa
          this.$axios({
            // url: 'http://192.168.3.48:8080/paperJustify/saveall',
            url:`http://localhost:8080/paperJustify/saveall/${phone}/${testId}`,
            method: 'post',
            data: dataq
              

          }).then(res => {
            if(res.data.msg = 'success') {
              
              alert('交卷成功')
              this.isStop = true;
              this.tipsFlag = false;
              this.summit = true;
              this.backStatu = true;
              this.$router.go(-1)
            }
          })
          // if(this.code === "0"){
          //   this.isStop = true;
          //   this.tipsFlag = false;
          //   this.summit = true;
          //   this.backStatu = true;

          //   alert('交卷成功')
          //   // window.location.href = "http://localhost:8080/#/test11-1";
            
          //     
            
          // }
          break
          //阅卷
        case 'readPaper':
          this.$emit('paperRead',this.tempDataSource)
          break
          //阅卷 上一个
        case 'readPaperUpper':
          this.$emit('paperReadUpper')
          break
          //阅卷 下一个
        case 'readPaperNext':
          this.$emit('paperReadNext')
          break
      }
    },
    /**
     * 锚点定位
     */
    jump(postion) {
      let jump = this.$refs.paperContent.querySelectorAll("#"+postion);
      // 获取需要滚动的距离
      let total = jump[0].offsetTop;
      //实现form锚点定位
      this.$refs.paperContent.scrollTop = jump[0].offsetTop;
    },
    /**
     *对错选择
     */
    isHookButtionCheck(val) {
      if(val.quesTypeId===1 || val.quesTypeId===2 || val.quesTypeId===3)
      {
        if(val.isHook===1)
        {
          val.score=val.totalscore;
        }
        if(val.isHook===2)
        {
          val.score=0;
        }
      }
    },
    /**
     *答题卡选中
     */
    answerButtionCheck(value,parent,child){
      // this.flag = true
      console.log(value,parent,child)
      // console.log(answer)
      let answerId='answer'+parent.code+child.answer
      let but = this.$refs.paperLeft.querySelectorAll("#"+answerId);
      console.log(but)
      if(but.length>0)
      {
        if(but[0].className.indexOf('answer-button-check')>-1)
        {
          if(child.answer && child.answer.length==0){
            but[0].classList.remove("answer-button-check");
          }
        }
        else{
          if (child.answer && child.answer.length > 0) {
            but[0].classList.add("answer-button-check");
          }

        }

      }
    },
    answerButtionCheck1(value,parent,child){
      this.flag = 1
      console.log(parent)
      // if(parent.code =="Single"){
      //   this.flag = 1
      // }
      // else if(parent.code =="Multiple"){
      //   this.flag = 2
      // }
      // else if(parent.code =="Judgment"){
      //   this.flag = 3
      // }
      // else if(parent.code =="Blank"){
      //   this.flag = 4
      // }
      // else if(parent.code =="Answer"){
      //   this.flag = 5
      // }
      console.log(child)
      console.log(value,parent,child)
      // console.log(answer)
      let answerId='answer'+parent.code+child.answer
      let but = this.$refs.paperLeft.querySelectorAll("#"+answerId);
      console.log(but)
      if(but.length>0)
      {
        if(but[0].className.indexOf('answer-button-check')>-1)
        {
          if(child.answer && child.answer.length==0){
            but[0].classList.remove("answer-button-check");
          }
        }
        else{
          if (child.answer && child.answer.length > 0) {
            but[0].classList.add("answer-button-check");
          }

        }

      }
    },
    /**
     * 转换答案
     */
    converAnswerStr(answer){
      if(answer instanceof Array)
      {
        return answer.join('  ')
      }
      return  answer
    },
    /**
     * 转换数据
     */
    convertData() {
      console.log('8888888888888888')
      console.log(this.dataSource)
      const _this = this;
      let sorted = this.groupBy(_this.dataSource.list, function(item) {
        // console.log(_this.dataSource.list)
        return [item.quesTypeId]

      })
      // console.log(sorted)
      this.convertDatas = []
      this.answerCardActiveName=[]
      this.orderBy(sorted, 'key', 'asc')
      sorted.forEach(item => {
        let totalscore = 0
        // let answer
        item.value.forEach(t => {
          totalscore += t.score
          
        })
        
        switch (item.key) {
          case "[1]":
            this.convertDatas.push({
              name: '单选题',
              code: 'Single',
              
              count: item.value.length,
              totalscore: totalscore,
              childs:item.value
            })
            this.answerCardActiveName.push('Single')
            break
          case "[2]":
            this.convertDatas.push({
              name: '多选题',
              code: 'Multiple',
              count: item.value.length,
              totalscore: totalscore,
              childs:item.value
            })
            this.answerCardActiveName.push('Multiple')
            break
          case "[5]":
            this.convertDatas.push({
              name: '判断题',
              code: 'Judgment',
              count: item.value.length,
              totalscore: totalscore,
              childs:item.value
            })
            this.answerCardActiveName.push('Judgment')
            break
          case "[3]":
            this.convertDatas.push({
              name: '填空题',
              code: 'Blank',
              count: item.value.length,
              totalscore: totalscore,
              childs:item.value
            })
            this.answerCardActiveName.push('Blank')
            break
          case "[4]":
            this.convertDatas.push({
              name: '简答题',
              code: 'Answer',
              count: item.value.length,
              totalscore: totalscore,
              childs:item.value
            })
            this.answerCardActiveName.push('Answer')
            break
        }

      })
      let i=0
      let j=0
      console.log('aaa')
      // console.log(this.mAnswer)
      // for(let a=0;a<this.convertDatas[1].length;a++){
      //   this.convertDatas[1].childs[a].answer = this.mAnswer[a]
      // }
      console.log(this.convertDatas)
      for(i;i<this.convertDatas.length;i++){
        
        for(j;j<this.convertDatas[i].childs.length;j++){
          // console.log(this.convertDatas[i].childs[j])
          this.convertDatas[0].childs[j].answer = this.mAnswer
          this.dataa.push(this.convertDatas[i].childs[j])
        }
        j=0
      }
      // this.dataa.push(this.answer)
      console.log(this.dataa)
      console.log('999999999999')
      console.log(this.convertDatas)
    },
    /**
     * 排序
     * @param {} datas 数组
     * @param {} col 列
     * @param {} type 类型 desc,asc
     * @returns {}
     */
    orderBy(datas, col, type) {
      let m
      for (let i = 0; i < datas.length; i++) {
        for (let k = 0; k < datas.length; k++) {
          if (type === 'asc') {
            if (datas[i][col] < datas[k][col]) {
              m = datas[k]
              datas[k] = datas[i]
              datas[i] = m
            }
          } else if (type === 'desc') {
            if (datas[i][col] > datas[k][col]) {
              m = datas[k]
              datas[k] = datas[i]
              datas[i] = m
            }
          }
        }
      }
      return datas
    },
    /**
     * 分组
     * @param array 数据集
     * @param f 函数
     * let sorted = groupBy(list, function(item){ return [item.name];});
     */
    groupBy(array, f) {
      const groups = {}
      const keyValues = []
      array.forEach(function(o) {
        const group = JSON.stringify(f(o))
        groups[group] = groups[group] || []
        groups[group].push(o)
      })
      Object.keys(groups).map(function(group) {
        return keyValues.push({ key: group, value: groups[group] })
      })
      return keyValues
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
    // 防切屏函数代码
    //isReduce 0扣次数，1不扣次数 router 判断是否为路由跳转
    pageHidden(e=null, isReduce = 0,router = false){
      if(document.visibilityState === "hidden" || router){
        if(this.code === "0"){
          if(this.remaTimes >= 0 && !this.isStop && (this.remaTimes !== this.switchPageTimes || (this.remaTimes!==0&&this.switchPageTimes!==0))&&this.switchPageTimes!==1000){
            this.tipsFlag = true;
            //计时
            this.getSencond();
            this.tips = 2;
          }else if(this.remaTimes<0&&!this.isStop){
            this.btnClick('handPaper');
          }
        }
      }
    },
    onConfirmTip(){
      this.tipsFlag = false;
    },
    getSencond () {
      const that = this
      const interval = window.setInterval(function () {
        --that.Sencond
        if (that.Sencond === 0) {
          that.isDisabled = false
          window.clearInterval(interval)
          if(that.tipsFlag===true) {
            that.btnClick('handPaper')
          }
        }
      }, 1000)},
    tipClose(){
      this.tipshow = !this.tipshow
    },
    tipBacks(){
      this.backStatu = true;
      this.btnClick('handPaper');
      // this.$router.go(-1);
    },
    destroyed() {
      window.removeEventListener("visibilitychange", this.pageHidden);
      window.removeEventListener("scroll", this.handleScroll);
      window.removeEventListener("resize", this.getLeftDistance);
      clearInterval(this.countdownTime); // 计时器
      this.stopNavigator() // 关闭摄像头
      clearInterval(this.timer)
    }
  }

}
</script>

<style scoped>
.paper-main {
  margin: 10px;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  overflow: hidden
}

.paper-header {
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

.paper-content {
  position: absolute;
  left: 305px;
  top: 60px;
  right: 15%;
  bottom: 45px;
  overflow-x: hidden;
  overflow-y: auto;
  box-sizing: border-box;
  padding: 10px;
  border: 1px solid #e4e4e4;
  border-top: none;
}

.paper-footer {
  position: absolute;
  padding: 5px 10px;
  left: 305px;
  right: 0;
  bottom: 0px;
  height: 45px;
  overflow: hidden;
  box-sizing: border-box;
  background-color: #f7f7f7;
  box-shadow: 0 1px 1px 0 rgba(0, 0, 0, .1);
  -webkit-box-shadow: 0 1px 1px 0 rgba(0, 0, 0, .1);
  text-align: center;
}

.paper-title {
  padding-left: 10px;
  width: 100%;
  height: 45px;
  line-height: 45px;
  background: #f7f7f7;
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
.answer-button{
  padding: 0px;
  color: #0a0a0a;
  background-color: #ffffff;
  border-color: #e4e4e4;
  margin-left: 10px;
  width: 30px;
  height: 30px;
}
.answer-button:hover{
  background: #ecf1ef;
  border-color: #e4e4e4;
  color: #0a0a0a;
}
.answer-button-check{
  background: #13ce66;
  border-color: #30B08F;
}

.answer-radio{
  display: list-item;
  margin: 5px 0px;
}

.answer-checkbox{
  display: list-item;
  margin: 5px 0px;
}

.subject-title{
  padding-left: 10px;
  width: 100%;
  height: 45px;
  line-height: 45px;
  background: #f7f7f7;
  box-shadow: 0 1px 1px 0 rgba(0, 0, 0, .1);
  -webkit-box-shadow: 0 1px 1px 0 rgba(0, 0, 0, .1);
}
.subject-title h2{
  font-size: 16px;
  display: inline-block;
}
.subject-title span {
  font-size: 16px;
  display: inline-block;
}

.subject-remark{
  background: #f7f7f7;
}
.subject-remark .item{
  display: block;
  padding: 5px;
}
.subject-remark .title{
  font-weight: bold;
}
.el-radio>>>.el-radio__input.is-checked .el-radio__inner {
  background-color: #13ce66;
  border-color: #13ce66;
}

.el-radio-button>>>.el-radio-button__inner {
  padding: 10px;
}

.el-collapse-item h2 {
  width: 150px;
  font-size: 14px;
  display: inline-block;
}
.el-form--label-top >>> .el-form-item__label {
  float: none;
  display: inline-block;

  text-align: center;
  padding: 0px;
}
.el-form--label-top >>> .el-form-item__content {
  line-height: 15px;
  position: absolute;
  font-size: 14px;
  text-align: center;

}

.el-card{
  margin: 10px;
}

.el-card >>>.el-card__header {
  background-color: #ffffff;
  padding: 0px 10px;
  line-height: 35px;
  font-size: 16px;
}
.el-card >>>.el-card__body {
  padding: 5px 20px;
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

</style>


        