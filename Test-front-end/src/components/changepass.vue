<template>
    <div class="box">
        <div>
            <h2 class="title">账户信息</h2>
            <ul class="mes-list">

                <li>
                    <span class="label">用户ID</span>
                    <span class="mes">{{ mes.phone }}</span>
                </li>
                <li>
                    <span class="label">真实姓名</span>
                    <span class="mes">{{ mes.name }}</span>
                </li>
            </ul>
        </div>
        <div>
            <h2 class="title">密码修改</h2>
            <ul class="mes-list">
                <li>
                    <span class="label">原始密码</span>
                    <span class="mes">{{ mes.password }}</span>
                </li>
               


            </ul>
        </div>
    </div>
</template>

<script>


export default{
    data() {
        return{

        }
    },
    mounted(){
        this.getInfo()
    },
    methods:{
        getInfo() {
        let phone = localStorage.getItem('ms_username');
        console.log(phone)
      const _this = this;
      this.$axios({
        url: "http://localhost:8080/user/information" ,
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        params:{
          phone: phone
        }
      }).then(function (res) {
            _this.mes = res.data.data;
            console.log('aaaa')
            console.log(_this.pagination.records)


          },
          function (err) {
            console.log(err);
            alert("服务器错误!稍后重试");
          })
        },
    }
}
</script>
