/**
 * 分类组件
 */
<template>
  <div>
      <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>题库</el-breadcrumb-item>
    </el-breadcrumb>
    <h4 style="color: #409eff; text-align: center">请从下方分类选一项进入</h4>
    <el-divider></el-divider>
    <el-collapse v-model="activeName" accordion>
      <el-collapse-item
        :title="item.father.title"
        :name="item.father.fid"
        v-for="item in Title"
        :key="item.father.fid"
      >
        <el-card shadow="hover" v-for="item1 in item.child" :key="item1.sid">
          <!-- 前往题库上传 -->
          <router-link
            :to="{
              path: '/pay/ManUpload/',
              query: { sid: item1.sid, fid: item.fid },
            }"
            >{{ item1.content }}</router-link
          >
        </el-card>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
// import { TitleList } from "../../api/basisMG";
import {TitleList} from "../../api/basisMG";

export default {
  name: "TitleOne",
  data() {
    return {
      activeName: "1",
      Title: [

      ],
    };
  },
  created() {
    //获取所有一二级标题
    TitleList()
      .then((res) => {
        if (res) {
          this.Title = res;
          this.$message.success("标签刷新成功");
        } else {
          this.$message.error("标签分类不想让你卷!");
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
