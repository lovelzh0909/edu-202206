import axios from 'axios';
import global from '../global/global';

axios.defaults.baseURL = global.baseURL;


// // 创建axios实例
// // eslint-disable-next-line no-unused-vars
// const service = axios.create({
//     // baseURL: '/', // api的base_Url
//     // 后端的请求路径
//     baseURL: 'http://localhost:8080', // api的base_Url
//     timeout: 50000 // 请求超时时间
// })

// 请求拦截器
axios.interceptors.request.use(
    function (config) {
        // 在发送请求之前做些什么
        return config
    },
    function (error) {
        // 对请求错误做些什么
        return Promise.reject(error)
    }
)

// 响应拦截器
axios.interceptors.response.use(
    function (config) {
        // 对响应数据做点什么
        return config
    },
    function (error) {
        // 对响应错误做点什么
        return Promise.reject(error)
    }
)


const http = {
    request ({ url, data = {}, method = 'GET', responseType }) {
        return new Promise((resolve, reject) => {
            this._request(url, resolve, reject, data, method, responseType);
        });
    },
    _request (url, resolve, reject, data = {}, method = 'GET', responseType) {
        const format = method.toLocaleLowerCase() == 'get' ? 'params' : 'data';
        axios({
            url: url,
            method: method,
            [format]: data,
            header: {
                'content-type': 'application/json'
            },
            responseType
        }).then(res => {
                if (res.status == 200) {
                    resolve(res.data);
                } else if (res.code == -1) {
                    resolve(res);
                }
            }).catch(error => {
                reject(error);
            })
    }
}

const base="http://localhost:8080"
export const postRequest=(_url,params)=>{
    return axios({
        method:'post',
        url:'http://localhost:8080/'+_url,
        data:params
    })
}

// export default service;
