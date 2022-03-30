/**
 * 标签分类组件 -资料 笔记
 */
<template>
  <div>
        <el-card shadow="hover" v-for="item in Title" :key="item.id">
          <!-- 前往笔记 -->
          <router-link
            v-if="showing"
            :to="{
              path: '/LearnHelp/Note',
              query: { id: item.id},
            }"
            >{{ item.name }}</router-link
          >
          <!-- 前往资料 -->
          <router-link
            v-if="!showing"
            :to="{
              path: '/LearnHelp/Help',
              query: { id: item.id},
            }"
            >{{ item.name }}</router-link
          >
          <!-- path: '/pay/Exercise/', -->
        </el-card>
  </div>
</template>

<script>
// import { TagsList } from "../../api/basisMG";
export default {
  name: "TitleOTwo",
  data() {
    return {
      activeName: "1",
    };
  },
  props: ["Title","showing"],
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




