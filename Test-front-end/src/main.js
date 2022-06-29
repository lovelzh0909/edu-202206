import Vue from 'vue';
import VueRouter from 'vue-router';
import App from './App.vue';
import router from './router';
import axios from 'axios';
import VueResource from 'vue-resource';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import echarts from 'echarts';
import VueI18n from 'vue-i18n';
import { messages } from './components/common/i18n';
import './assets/css/main.css';
import 'babel-polyfill';
import api from './api/index';

import 'xe-utils'
import VXETable from 'vxe-table'
import 'vxe-table/lib/style.css'
Vue.use(VXETable);


Vue.use(VueRouter);
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
Vue.prototype.$axios = axios;
Vue.prototype.$api = api;
Vue.use(VueResource);
Vue.use(ElementUI);
Vue.use(ViewUI);
Vue.prototype.$echarts = echarts;
Vue.use(VueI18n);
const i18n = new VueI18n({
    locale: 'zh',
    messages
});
Vue.config.productionTip = false;

//使用钩子函数对路由进行权限跳转
// router.beforeEach((to, from, next) => {
//     if (curRouterObj && curRouterObj.access !== undefined) { // 需要判断权限的路由
//         if (curRouterObj.access <= parseInt(Cookies.get('access'))) {
// next()
//         }
//         else
//         {
//             next({
//                 replace: true,
//                 name: 'error-403'
//             });
//         }
//     } else { // 没有配置权限的路由, 直接通过
//  next()
//     }
// })

router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title} | 揽月`;
    //if(localStorage.getItem('ms_username1'))
    const role = localStorage.getItem('ms_userrole');
    // const name = localStorage.getItem('ms_username');
    if (!role && to.path !== '/login1') {
        next('/login1');
    }

    //判断权限
    else if (to.meta.permission3 && role === 'admin') {
        if(to.meta.permission1){
            next('/test1/test1-1')
        }
        if(to.meta.permission2)   {
            next('/test2/test2-1-1')
        }
        if(to.meta.permission4)   {
            next('/test3/test3-2')
        }
    
    }
    else if (to.meta.permission2) {
    // 如果是教师权限则可进入
    role === 'teacher' ? next('/test22/test22-1-1') : next('/403');    
    }
    else if (to.meta.permission1){
    // 如果是学生权限则可进入
    role === 'student' ? next('/test11/test11-1') : next('/403');    
    }

    else {   
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        } else {
            next();
        }
    }
});

new Vue({
    router,
    i18n,
    render: h => h(App)
}).$mount('#app');
