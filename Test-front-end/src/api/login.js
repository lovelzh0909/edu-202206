import request from "../utils/request";

export default{
  loginTosystem(data){
    return request({
      url:'/user/logon',
      method:'post',
      data:data
    })
  }
} 
