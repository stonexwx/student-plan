<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item >首页</el-breadcrumb-item>
      <el-breadcrumb-item>笔记</el-breadcrumb-item>
    </el-breadcrumb>
    <h1 style="color: #409eff; text-align: center">
      请从下方分类选一项进入
    </h1>
    <el-divider></el-divider>
    <!-- //分类组件 -->
    <title-two :Title="array" :showing="true"></title-two>
  </div>
</template>

<script>
import TitleTwo from "../../components/TitleTwo";
import {TagsList,} from "../../api/basisMG";

export default {
  name: "RouterCenter",
components: { TitleTwo },
  data() {
    return {
      //标题数据
      array: [
      ],
    };
  },
  created() {
    //获取所有一级标题
    TagsList()
      .then((res) => {
        if (res) {
          this.array = res.data;
          this.$message.success("标签刷新成功");
        } else {
          this.$message.error("标签分类不想让你卷!");
        }
      })
      .catch((err) => {
        this.$message.error("请求失败，请稍后再试！");
      });
  }
}
</script>

<style scoped>

</style>
