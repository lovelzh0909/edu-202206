<template>
<div>
    <el-aside>
        <SideMenu :items='items'></SideMenu>
    </el-aside>
    <el-main>
        <router-view></router-view>
    </el-main>
</div>
</template>

<script>
import SideMenu from '@/components/sidemenu/SideMenu';
export default {
    components:{
        SideMenu
    },

    data() {
        return {
            items: [

                {
                    index: 'test22-1',
                    title: '发布考试',
                    nextItems: [
                        {
                            index: 'test22-1-1',
                            title: '考试列表'
                        },
                        {
                            index: 'test22-1-2',
                            title: '发布考试'
                        },
                        {
                            index: 'test22-1-3',
                            title: '题库'
                        },
                        {
                            index: 'test22-1-4',
                            title: '试卷库'
                        },
                        // {
                        //     index: 'test22-1-5',
                        //     title: '成绩管理'
                        // },
                    ]
                },
                {
                    index: 'test22-2',
                    title: '我的监考',
                    nextItems: [
                        {
                            index: 'test22-2-2',
                            title: '监考场次'
                        },
                        
                    ]
                },
                {
                    index: 'test22-3',
                    title: '个人设置',
                    nextItems:[
                        {
                            index: 'test22-3-1',
                            title: '个人信息'
                        },
                        // {
                        //     index: 'test22-3-2',
                        //     title: '修改密码'
                        // },
                    ]                    
                },
                
            ]
        }
    },
}
</script>

<style scoped>
</style>