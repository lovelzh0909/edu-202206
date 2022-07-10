<template>
  <div class="content-box" >
    <div class="container">
      <p>监考场次列表</p>
      <div class="test-div">
        <el-tree
            :data="data"
            show-checkbox
            node-key="id"
            :props="defaultProps">
        </el-tree>
        <el-button type="primary" @click="Invigilate">跳转到监考页面</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      data: [{
        id: 1,
        label: '一级 1',
        children: [{
          id: 4,
          label: '二级 1-1',
          children: [{
            id: 9,
            label: '三级 1-1-1'
          }, {
            id: 10,
            label: '三级 1-1-2'
          }]
        }]
      }, {
        id: 2,
        label: '一级 2',
        children: [{
          id: 5,
          label: '二级 2-1'
        }, {
          id: 6,
          label: '二级 2-2'
        }]
      }, {
        id: 3,
        label: '一级 3',
        children: [{
          id: 7,
          label: '二级 3-1'
        }, {
          id: 8,
          label: '二级 3-2'
        }]
      }],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  methods:{
    Invigilate(){
      this.$router.push({path:'/test22-2-2-1'})
    }
  }
}
</script>

<style>
</style>