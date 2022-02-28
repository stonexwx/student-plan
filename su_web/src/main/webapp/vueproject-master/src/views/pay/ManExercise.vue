/**
 * 分类组件
 */
<template>
  <div>
      <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>题库</el-breadcrumb-item>
    </el-breadcrumb>
    <h4 style="color: #409eff; text-align: center">请从下方分类选一项进入</h4>
    <el-divider></el-divider>
    <el-collapse v-model="activeName" accordion>
      <el-collapse-item
        :title="item.title"
        :name="item.fid"
        v-for="item in Title"
        :key="item.fid"
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
export default {
  name: "TitleOne",
  data() {
    return {
      activeName: "1",
      Title: [
        {
          fid: "1",
          title: "文科",
          child: [
            {
              sid: "1",
              content: "语文",
            },
            {
              sid: "2",
              content: "地理",
            },
            {
              sid: "3",
              content: "地理333",
            },
          ],
        },
      ],
    };
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
