import { layout } from 'echarts/lib/layout/barGrid';
import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);


export default new Router({
    
    routes: [
        {
            path: '/',
            component:()=>import('../page/Login.vue'),
            
        },
        {
            path: '/register',
            component:()=>import('../page/Register.vue'),

        },
        {
            path: '/exam-paper',
            component:() => import('../components/exam/ExamPaper.vue'),
            meta: {
                title: '考试页面'
            }
        },
        {
            path: '/QuestionBank',
            component:() => import('../components/questionbank/QuestionBank.vue'),
            meta: {
                title: '题库详情页面'
            }
        },
        {
            path: '/testpaper',
            component:() => import('../components/testpaper/TestPaper.vue'),
            meta: {
                title: '组卷页面'
            }
        },
        {
            path: '/addquestion',
            component:() => import('../components/addquestion/AddQuestion.vue'),
            meta: {
                title: '添加题目到题库页面'
            }
        },


        
        
        
        {
            //学生端整体界面Whole1路由表
            path: '/Whole1',                      
            component: () => import('../components/common/Whole1.vue'),                  
            redirect: '/dashboard1', // 该配置是若点击选择父目录时，默认选中该父目录下的子路径页面
            meta: {
                title: '整体页面布局'
            },
            
            children: [
                {
                    path: '/dashboard1',
                    component: () => import('../page/dashboard/StudentHome.vue'),
                    meta: {
                        title: '首页'
                    },
                    redirect:'/Home1',     // 该配置是若点击选择父目录时，默认选中该父目录下的子路径页面
                        children: [{
                            path: '/Home1',
                            component: () => import('../page/dashboard/StudentHome.vue'),
                            meta: {
                                title: '首页'
                            },
                        }               
                ]
                },
                {
                    path: '/test11',
                    access:0,
                    component: () => import('../page/studentweb/indexStudent.vue'),
                    meta: {
                        title: '一级菜单1',
                        permission1: true,
                        permission2: false,
                        permission3: true,
                    },
                    
                        children:[
                            {
                                path: 'test11-1',
                                component: () => import('../page/studentweb/ExamList.vue'),
                                meta: {
                                    title: '考试列表'
                                },
                            },
                            
                            
                        ]
                },
                
                // {
                //     // 国际化组件
                //     path: '/i18n',
                //     component: () => import('../components/common/I18n.vue'),
                //     meta: {
                //         title: '国际化',
                //     }
                // },
               
                {
                    path: '/404',
                    component: () => import('../page/errorpage/404.vue'),
                    meta: {
                        title: '404'
                    }
                },
                {
                    path: '/403',
                    component: () => import('../page/errorpage/403.vue'),
                    meta: {
                        title: '403'
                    }
                },
            ]
        },
    
        {
            //教师端整体界面Whole2路由表
            path: '/Whole2',                      
            component: () => import('../components/common/Whole2.vue'),                  
            redirect:'/dashboard2' ,// 该配置是若点击选择父目录时，默认选中该父目录下的子路径页面
            meta: {
                title: '整体页面布局'
            },
            
            children: [
                {
                    path: '/dashboard2',
                    component: () => import('../page/dashboard/TeacherHome.vue'),
                    meta: {
                        title: '首页'
                    },
                    redirect:'/Home2',     // 该配置是若点击选择父目录时，默认选中该父目录下的子路径页面
                        children: [{
                            path: '/Home2',
                            component: () => import('../page/dashboard/TeacherHome.vue'),
                            meta: {
                                title: '首页'
                            },
                        }               
                ]
                },
                
                {
                    path: '/test22',
                    access:1,
                    component: () => import('../page/teacherweb/indexTeacher.vue'),
                    meta: {
                        title: '一级菜单2',
                        permission2: true,
                        permission1: false,
                        permission3: true,
                    },
                    
                        children:[
                            
                            {
                                path: 'test22-1-1',
                                component: () => import('../page/teacherweb/exam/ExamList.vue'),
                                meta: {
                                    title: '考试列表'
                                }
                            },
                            {
                                path: 'test22-1-2',
                                component: () => import('../page/teacherweb/exam/ExamPublish.vue'),
                                meta: {
                                    title: '发布考试'
                                }
                             },
                             {
                                path: 'test22-1-3',
                                component: () => import('../page/teacherweb/exam/MyquestionBank.vue'),
                                meta: {
                                    title: '题库'
                                }
                             },
                            {
                                path: 'test22-1-4',
                                component: () => import('../page/teacherweb/exam/Paper.vue'),
                                meta: {
                                    title: '试卷库'
                                }
                            },

                             
                            {
                                path: 'test22-2-2',
                                component: () => import('../page/teacherweb/invigilate/InvigilateList.vue'),
                                meta: {
                                    title: '监考场次'
                                }
                             },
                             
                             
                         ]
                },
                // {
                //     // 国际化组件
                //     path: '/i18n',
                //     component: () => import('../components/common/I18n.vue'),
                //     meta: {
                //         title: '国际化',
                //     }
                // },
               
                {
                    path: '/404',
                    component: () => import('../page/errorpage/404.vue'),
                    meta: {
                        title: '404'
                    }
                },
                {
                    path: '/403',
                    component: () => import('../page/errorpage/403.vue'),
                    meta: {
                        title: '403'
                    }
                },
            ]
        },

        {
            path: '/Whole3',                      
            component: () => import('../components/common/Whole3.vue'),                  
            redirect:'/dashboard3',  
            meta: {
                title: '整体页面布局'
            },
            
            children: [
                {
                    path: '/dashboard3',
                    component: () => import('../page/dashboard/AdminHome.vue'),
                    meta: {
                        title: '首页'
                    },
                    redirect:'/Home3',     // 该配置是若点击选择父目录时，默认选中该父目录下的子路径页面
                        children: [{
                            path: '/Home3',
                            component: () => import('../page/dashboard/AdminHome.vue'),
                            meta: {
                                title: '首页'
                            },
                        }               
                ]
                },
                {
                    path: '/test1',
                    access:0,
                    
                    component: () => import('../page/studentweb/indexAdmin.vue'),
                    meta: {
                        title: '一级菜单1',
                        permission1: true,
                        permission2: false,
                        permission3: true,
                    },
                    
                        children:[
                            {
                                path: 'test1-1',
                                component: () => import('../page/studentweb/ExamList.vue'),
                                meta: {
                                    title: '考试列表'
                                },
                            },
                           
                        ]
                },
                {
                    path: '/test2',
                    access:1,
                    component: () => import('../page/teacherweb/indexAdmin.vue'),
                    meta: {
                        title: '一级菜单2',
                        permission2: true,
                        permission1: false,
                        permission3: true,
                    },
                    
                        children:[
                            
                            {
                                path: 'test2-1-1',
                                component: () => import('../page/teacherweb/exam/ExamList.vue'),
                                meta: {
                                    title: '考试列表'
                                }
                            },
                            {
                                path: 'test2-1-2',
                                component: () => import('../page/teacherweb/exam/ExamPublish.vue'),
                                meta: {
                                    title: '发布考试'
                                }
                             },
                             {
                                path: 'test2-1-3',
                                component: () => import('../page/teacherweb/exam/MyquestionBank.vue'),
                                meta: {
                                    title: '题库'
                                }
                             },
                            {
                                path: 'test2-1-4',
                                component: () => import('../page/teacherweb/exam/Paper.vue'),
                                meta: {
                                    title: '试卷库'
                                }
                            },
                         
                            {
                                path: 'test2-2-2',
                                component: () => import('../page/teacherweb/invigilate/InvigilateList.vue'),
                                meta: {
                                    title: '监考场次'
                                }
                             },
                            
                         ]
                },
                {
                    path: '/test3',
                    access:0,
                    
                    component: () => import('../page/adminweb/indexAdmin.vue'),
                    meta: {
                        title: '一级菜单3',
                        permission1: false,
                        permission2: false,
                        permission3: true,
                        permission4: true,
                    },
                    
                        children:[
                          
                            {
                                path: 'test3-2',
                                component: () => import('../page/adminweb/InstitutionManage.vue'),
                                meta: {
                                    title: '组织机构管理'
                                },
                            },
                            {
                                path: 'test3-3',
                                component: () => import('../page/adminweb/UserManage.vue'),
                                meta: {
                                    title: '角色权限管理'
                                },
                            },
                            
                            
                        ]
                },
                
                // {
                //     // 国际化组件
                //     path: '/i18n',
                //     component: () => import('../components/common/I18n.vue'),
                //     meta: {
                //         title: '国际化',
                //     }
                // },
                
                {
                    path: '/404',
                    component: () => import('../page/errorpage/404.vue'),
                    meta: {
                        title: '404'
                    }
                },
                {
                    path: '/403',
                    component: () => import('../page/errorpage/403.vue'),
                    meta: {
                        title: '403'
                    }
                },
            ]
        },

        {
            // 登录页面1
            path: '/login1',
            component: () => import('../page/Login.vue'),
            meta: {
                title: '登录'
            }
        },
        {
            path: '*',
            redirect: '/404'
        }
         
        
//*************************************** */
// {
//     path: '/grade',
//     component:()=>import('../components/gradeManage/grade.vue'),
    
// },

// {
//     path: '/tuxing',
//     component:()=>import('../components/gradeManage/tuxing.vue'),
    
// },
// {
//     path: '/upload',
//     component:()=>import('../components/upload.vue'),

// },

// {
//     path: '/test22-2-2-1',
//     component:()=>import('../components/invigilate.vue'),
//     meta: {
//         title:'监考页面'
//     }
// },

// {
//     path: '/yuejuan-paper',
//     component:() => import('../components/yuejuan.vue'),
//     meta: {
//         title: '阅卷页面'
//     }
// },
// {
//     path: '/look-paper',
//     component:() => import('../components/lookpaper.vue'),
//     meta: {
//         title: '查看试卷页面'
//     }
// },
    ]
})



