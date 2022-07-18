<template>
<div>
    
    <!-- 一级菜单下面所拥有的二级菜单 -->
    <el-aside>
         <SideMenu :items='items'></SideMenu>
    </el-aside>

    <!-- 以及二级菜单所对应的页面 -->
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
    data(){
        return {
            items: [
            {
                index: 'test11-1',
                title: '考试列表'
            },
            {
                index: 'test11-2',
                title: '考试通知'
            },
            {
                index: 'test11-3',
                title: '个人信息'
            },
            // {
            //     index: 'test11-4',
            //     title: '错题集'
            // },
            // {
            //     index: 'test11-5',
            //     title: '成绩查询'
            // },
            // {
            //     index: 'test11-6',
            //     title: '修改密码'
            // }
        ],
        }
    }
}
</script>

<style scoped>
</style>