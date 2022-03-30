<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>添加</el-breadcrumb-item>
    </el-breadcrumb>

    <home :options="options" :content="content" :iid="this.iid" :typeId="this.typeId"></home>
  </div>


</template>
<script>
import home from "./Home";

export default {
  name: "Editor",
  data(){
    return{
      iid:this.$route.query.iid,
      typeId:this.$route.query.typeId
    }
  },
  components: {home},
  computed: {
    options: function () {
        return this.$store.getters.getOptions;
    },
    content:function(){
      return this.$store.getters.getContent;
    }
  },
  // options: this.$store.getters.getOptions,
  created() {
    // console.log(this.options)
  }
}
</script>

<style scoped>

</style>
