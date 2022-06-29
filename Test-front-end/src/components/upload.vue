<template>
<div class="container" style="margin-left:15%;width:50%">
    <div class="box" >
        
       
        <div style="margin-top:80px">
            <h2 class="title" style="margin-top:-20px">请输入成绩单信息：</h2>
            <el-form>
                <el-form-item style="margin-top:30px" label="请输入考试编号：" >
                    <el-input  v-model="examcode" style="width:300px;margin-left:42px"></el-input>
                </el-form-item>
                <el-form-item label="请输入考试名称：" >
                    <el-input  v-model="subject" style="width:300px;margin-left:42px"></el-input>
                </el-form-item>
            </el-form>    
        </div>
        <div>
            <el-row>
                <div class="el-form-item__content">
                    <el-upload ref="upload"
                    style="margin-left:300px;margin-top:50px"
                        accept=".xls,.xlsx"
                        action="#"
                        :auto-upload="false"
                        :multiple="false"
                        :file-list="fileList"
                        :before-upload="beforeUpload"
                        :http-request="uploadHttpRequest"
                        :on-remove="fileRemove"
                        :on-change="fileChange">
                        <el-button size="small" type="primary">选择文件</el-button>
                        
                        
                    </el-upload>
                    <div slot="tip" class="el-upload__tip"  style="margin-left:200px">一次只能上传一个xls/xlsx文件，且不超过10M</div>
                        <h2 style="margin-left:200px">文件格式：</h2>
                        <h2 style="margin-left:200px">列名：学生学号和学生成绩</h2>
                </div>
            </el-row>
            <el-row style="margin-left:236px;margin-top:50px">
                <el-button size="small" @click="closeDialog">取 消</el-button>
                <el-button type="primary" size="small" @click="submitUpload">上 传</el-button>
                <el-button type="primary" size="small" @click="goback()">返 回</el-button>
            </el-row>

        </div>
    </div>
</div>
</template>

<script>
import axios from "axios";

export default {
    name: '',

    data() {
        return {
            
            examcode:'',
            subject:'',
        };
    },
   methods: {
    // 上传文件之前的钩子：判断上传文件格式、大小等，若返回false则停止上传
    beforeUpload(file) {
        //文件类型
        const isType = file.type === 'application/vnd.ms-excel'
        const isTypeComputer = file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
        const fileType = isType || isTypeComputer
        if(!fileType) {
            this.$message.error('上传文件只能是xls/xlsx格式！')
        }

        // 文件大小限制为10M
        const fileLimit = file.size / 1024 / 1024 < 10;
        if(!fileLimit) {
            this.$message.error('上传文件大小不超过10M！');
        }
        return fileType && fileLimit
    },
    // 自定义上传方法，param是默认参数，可以取得file文件信息，具体信息如下图
    uploadHttpRequest(param) {
        let examcode = this.examcode
        let subject = this.subject
        let phone = localStorage.getItem('ms_username')
        console.log(subject)
        const formData = new FormData() //FormData对象，添加参数只能通过append('key', value)的形式添加
        formData.append('file', param.file) //添加文件对象
        formData.append('uploadType', this.rulesType)
        const url = `http://localhost:8080/GetFile/excel/${phone}/${subject}/${examcode}` //上传地址
        console.log(formData)
        this.$axios.post(url, formData)
            .then( res => {
                const { data: { code, mark } } = res
                if(res.data.msg !='success'){
                alert(res.data.msg)}
                 else {
                    param.onSuccess()  // 上传成功的文件显示绿色的对勾
                    this.uploadMark = mark
                    alert('上传成功！')
                    this.$router.go(0)
                }
                // return this.countData(this.uploadMark) //根据响应的 mark 值调用统计结果接口，返回一个promise以便进行链式调用
                
            })
            .then( res => { //链式调用，统计结果的响应
                if(res.data.msg ='success'){
                const { data: { code, data } } = res
                if(code === 0) {
                    console.log('统计结果', data)
                    alert('上传成功')
                }
                }
            })
            // .catch( err => {
            //     console.log('失败', err)
            //     param.onError() //上传失败的文件会从文件列表中删除
            // })
    },
    // // 统计结果
    // countFile(mark) {
    //     return new Promise((resolve, reject) => {
    //         axios
    //             .get(`/operation/ruleImport/countData?mark=${mark}`)
    //             .then(res => {
    //                 resolve(res)
    //             })
    //             .catch(error => {
    //                 reject(error)
    //             })
    //     })
    // },
    // 点击上传：手动上传到服务器，此时会触发组件的http-request
    submitUpload() {
        this.$refs.upload.submit()
    },
    // 文件发生改变
    fileChange(file, fileList) {
        if (fileList.length > 0) {
            this.fileList = [fileList[fileList.length - 1]] // 展示最后一次选择的文件
        }
    },
    // 移除选择的文件
    fileRemove(file, fileList) {
        if(fileList.length < 1) {
            this.uploadDisabled = true //未选择文件则禁用上传按钮
        }
    },
    // 取消
    closeDialog() {
        this.$refs.upload.clearFiles() //清除上传文件对象
        this.fileList = [] //清空选择的文件列表
        this.$emit('close', false)
    },
    //返回
    goback(){
        this.$router.go(-1)
    }
}
}
</script>