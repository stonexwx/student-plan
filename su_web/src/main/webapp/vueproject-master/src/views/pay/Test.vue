/**
 * 自主学习 题库练习
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>题库</el-breadcrumb-item>
    </el-breadcrumb>
    <h1 style="color: #409eff; text-align: center">
      请从下方分类选一项进入
    </h1>
    <el-divider></el-divider>
    <!-- //分类组件 -->
    <title-one :Title="array" :showing="true"></title-one>
  </div>
</template>

<script>
import TitleOne from "../../components/TitleOne.vue";
import { TitleList } from "../../api/basisMG";
export default {
  components: { TitleOne },
  data() {
    return {
      //标题数据
      array: [

      ],
      activeName: "1",
    };
  },
  created() {
    //获取所有一二级标题
    TitleList()
      .then((res) => {
        if (res) {
          this.array = res;
          this.$message.success("标签刷新成功");
        } else {
          this.$message.error("标签不想让你卷!");
        }
      })
      .catch((err) => {
        this.$message.error("请求失败，请稍后再试！");
      });
  },
};
</script>

<style scoped>
.el-card {
  width: 60%;
  margin: 20px auto;
  text-align: center;
  background-color: #5aacff;
  color: white;
  font-size: 16px;
  font-weight: bold;
  border-radius: 40px;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.h1 {
  margin: 30px 0px;
}
a {
  color: white;
  text-decoration: none;
}
</style>
