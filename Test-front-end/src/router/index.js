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
            path: '/grade',
            component:()=>import('../components/gradeManage/grade.vue'),
            
        },

        {
            path: '/tuxing',
            component:()=>import('../components/gradeManage/tuxing.vue'),
            
        },
        {
            path: '/upload',
            component:()=>import('../components/upload.vue'),

        },
        
        {
            path: '/test22-2-2-1',
            component:()=>import('../components/invigilate.vue'),
            meta: {
                title:'监考页面'
            }
        },
        
        {
            path: '/yuejuan-paper',
            component:() => import('../components/yuejuan.vue'),
            meta: {
                title: '阅卷页面'
            }
        },
        {
            path: '/look-paper',
            component:() => import('../components/lookpaper.vue'),
            meta: {
                title: '查看试卷页面'
            }
        },
        {
            path: '/QuestionBank',
            component:() => import('../components/questionbank/QuestionBank.vue'),
            meta: {
                title: '试卷详情页面'
            }
        },
        {
            path: '/addexam',
            component:() => import('../components/addexam.vue'),
            meta: {
                title: '组卷页面'
            }
        },
        {
            path: '/addquestion',
            component:() => import('../components/addquestion.vue'),
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
                    component: () => import('../page/Dashboard1.vue'),
                    meta: {
                        title: '首页'
                    },
                    redirect:'/Home1',     // 该配置是若点击选择父目录时，默认选中该父目录下的子路径页面
                        children: [{
                            path: '/Home1',
                            component: () => import('../page/Dashboard1.vue'),
                            meta: {
                                title: '首页'
                            },
                        }               
                ]
                },
                {
                    path: '/test11',
                    access:0,
                    component: () => import('../page/test1/indexS.vue'),
                    meta: {
                        title: '一级菜单1',
                        permission1: true,
                        permission2: false,
                        permission3: true,
                    },
                    
                        children:[
                            {
                                path: 'test11-1',
                                component: () => import('../page/test1/test1-1.vue'),
                                meta: {
                                    title: '考试列表'
                                },
                            },
                            {
                                path: 'test11-2',
                                component: () => import('../page/test1/test1-2.vue'),
                                meta: {
                                    title: '考试通知'
                                },
                            },
                            {
                                path: 'test11-3',
                                component: () => import('../page/test1/test1-3.vue'),
                                meta: {
                                    title: '个人信息'
                                },
                            },
                            {
                                path: 'test11-4',
                                component: () => import('../page/test1/test1-4.vue'),
                                meta: {
                                    title: '错题集'
                                },
                            },
                            {
                                path: 'test11-5',
                                component: () => import('../page/test1/test1-5.vue'),
                                meta: {
                                    title: '成绩查询'
                                },
                            },
                            {
                                path: 'test11-6',
                                component: () => import('../page/test1/test1-6.vue'),
                                meta: {
                                    title: '修改密码'
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
                    component: () => import('../page/404.vue'),
                    meta: {
                        title: '404'
                    }
                },
                {
                    path: '/403',
                    component: () => import('../page/403.vue'),
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
                    component: () => import('../page/Dashboard2.vue'),
                    meta: {
                        title: '首页'
                    },
                    redirect:'/Home2',     // 该配置是若点击选择父目录时，默认选中该父目录下的子路径页面
                        children: [{
                            path: '/Home2',
                            component: () => import('../page/Dashboard2.vue'),
                            meta: {
                                title: '首页'
                            },
                        }               
                ]
                },
                
                {
                    path: '/test22',
                    access:1,
                    component: () => import('../page/test2/indexT.vue'),
                    meta: {
                        title: '一级菜单2',
                        permission2: true,
                        permission1: false,
                        permission3: true,
                    },
                    
                        children:[
                            
                            {
                                path: 'test22-1-1',
                                component: () => import('../page/test2/test2-1/test2-1-1.vue'),
                                meta: {
                                    title: '考试列表'
                                }
                            },
                            {
                                path: 'test22-1-2',
                                component: () => import('../page/test2/test2-1/test2-1-2.vue'),
                                meta: {
                                    title: '发布考试'
                                }
                             },
                             {
                                path: 'test22-1-3',
                                component: () => import('../page/test2/test2-1/test2-1-3.vue'),
                                meta: {
                                    title: '题库'
                                }
                             },
                            {
                                path: 'test22-1-4',
                                component: () => import('../page/test2/test2-1/test2-1-4.vue'),
                                meta: {
                                    title: '试卷库'
                                }
                            },
                            {
                                path: 'test22-1-5',
                                component: () => import('../page/test2/test2-1/test2-1-5.vue'),
                                meta: {
                                    title: '成绩管理'
                                }
                            },
                             {
                                path: 'test22-2-1',
                                component: () => import('../page/test2/test2-2/test2-2-1.vue'),
                                meta: {
                                    title: '通知'
                                }
                            },
                            {
                                path: 'test22-2-2',
                                component: () => import('../page/test2/test2-2/test2-2-2.vue'),
                                meta: {
                                    title: '监考场次'
                                }
                             },
                             {
                                path: 'test22-2-3',
                                component: () => import('../page/test2/test2-2/test2-2-3.vue'),
                                meta: {
                                    title: '结算'
                                }
                             },
                             {
                                path: 'test22-3-1',
                                component: () => import('../page/test2/test2-3/test2-3-1.vue'),
                                meta: {
                                    title: '个人信息'
                                }
                             },
                             {
                                path: 'test22-3-2',
                                component: () => import('../page/test2/test2-3/test2-3-2.vue'),
                                meta: {
                                    title: '修改密码'
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
                    component: () => import('../page/404.vue'),
                    meta: {
                        title: '404'
                    }
                },
                {
                    path: '/403',
                    component: () => import('../page/403.vue'),
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
                    component: () => import('../page/Dashboard3.vue'),
                    meta: {
                        title: '首页'
                    },
                    redirect:'/Home3',     // 该配置是若点击选择父目录时，默认选中该父目录下的子路径页面
                        children: [{
                            path: '/Home3',
                            component: () => import('../page/Dashboard3.vue'),
                            meta: {
                                title: '首页'
                            },
                        }               
                ]
                },
                {
                    path: '/test1',
                    access:0,
                    
                    component: () => import('../page/test1/indexA.vue'),
                    meta: {
                        title: '一级菜单1',
                        permission1: true,
                        permission2: false,
                        permission3: true,
                    },
                    
                        children:[
                            {
                                path: 'test1-1',
                                component: () => import('../page/test1/test1-1.vue'),
                                meta: {
                                    title: '考试列表'
                                },
                            },
                            {
                                path: 'test1-2',
                                component: () => import('../page/test1/test1-2.vue'),
                                meta: {
                                    title: '考试通知'
                                },
                            },
                            {
                                path: 'test1-3',
                                component: () => import('../page/test1/test1-3.vue'),
                                meta: {
                                    title: '个人信息'
                                },
                            },
                            {
                                path: 'test1-4',
                                component: () => import('../page/test1/test1-4.vue'),
                                meta: {
                                    title: '错题集'
                                },
                            },
                            {
                                path: 'test1-5',
                                component: () => import('../page/test1/test1-5.vue'),
                                meta: {
                                    title: '设置'
                                },
                            },
                            {
                                path: 'test1-6',
                                component: () => import('../page/test1/test1-6.vue'),
                                meta: {
                                    title: '修改密码'
                                },
                            },
                        ]
                },
                {
                    path: '/test2',
                    access:1,
                    component: () => import('../page/test2/indexA.vue'),
                    meta: {
                        title: '一级菜单2',
                        permission2: true,
                        permission1: false,
                        permission3: true,
                    },
                    
                        children:[
                            
                            {
                                path: 'test2-1-1',
                                component: () => import('../page/test2/test2-1/test2-1-1.vue'),
                                meta: {
                                    title: '考试列表'
                                }
                            },
                            {
                                path: 'test2-1-2',
                                component: () => import('../page/test2/test2-1/test2-1-2.vue'),
                                meta: {
                                    title: '发布考试'
                                }
                             },
                             {
                                path: 'test2-1-3',
                                component: () => import('../page/test2/test2-1/test2-1-3.vue'),
                                meta: {
                                    title: '题库'
                                }
                             },
                            {
                                path: 'test2-1-4',
                                component: () => import('../page/test2/test2-1/test2-1-4.vue'),
                                meta: {
                                    title: '试卷库'
                                }
                            },
                            {
                                path: 'test2-1-5',
                                component: () => import('../page/test2/test2-1/test2-1-5.vue'),
                                meta: {
                                    title: '成绩管理'
                                }
                            },
                             {
                                path: 'test2-2-1',
                                component: () => import('../page/test2/test2-2/test2-2-1.vue'),
                                meta: {
                                    title: '通知'
                                }
                            },
                            {
                                path: 'test2-2-2',
                                component: () => import('../page/test2/test2-2/test2-2-2.vue'),
                                meta: {
                                    title: '监考场次'
                                }
                             },
                             {
                                path: 'test2-2-3',
                                component: () => import('../page/test2/test2-2/test2-2-3.vue'),
                                meta: {
                                    title: '结算'
                                }
                             },
                             {
                                path: 'test2-3-1',
                                component: () => import('../page/test2/test2-3/test2-3-1.vue'),
                                meta: {
                                    title: '个人信息'
                                },
                            },
                            {
                                path: 'test2-3-2',
                                component: () => import('../page/test2/test2-3/test2-3-2.vue'),
                                meta: {
                                    title: '修改密码'
                                },
                            },
                         ]
                },
                {
                    path: '/test3',
                    access:0,
                    
                    component: () => import('../page/test3/indexA.vue'),
                    meta: {
                        title: '一级菜单3',
                        permission1: false,
                        permission2: false,
                        permission3: true,
                        permission4: true,
                    },
                    
                        children:[
                            {
                                path: 'test3-1',
                                component: () => import('../page/test3/test3-1.vue'),
                                meta: {
                                    title: '功能模块管理'
                                },
                            },
                            {
                                path: 'test3-2',
                                component: () => import('../page/test3/test3-2.vue'),
                                meta: {
                                    title: '组织机构管理'
                                },
                            },
                            {
                                path: 'test3-3',
                                component: () => import('../page/test3/test3-3.vue'),
                                meta: {
                                    title: '角色权限管理'
                                },
                            },
                            {
                                path: 'test3-4',
                                component: () => import('../page/test3/test3-4.vue'),
                                meta: {
                                    title: '操作日志管理'
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
                    component: () => import('../page/404.vue'),
                    meta: {
                        title: '404'
                    }
                },
                {
                    path: '/403',
                    component: () => import('../page/403.vue'),
                    meta: {
                        title: '403'
                    }
                },
            ]
        },

        {
            // 登录页面1
            path: '/login1',
            component: () => import('../page/Login1.vue'),
            meta: {
                title: '登录'
            }
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})
