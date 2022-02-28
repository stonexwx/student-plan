/**
 * 标签分类组件
 */
<template>
  <div>
    <el-collapse v-model="activeName" accordion>
      <el-collapse-item
        :title="item.title"
        :name="item.fid"
        v-for="item in Title"
        :key="item.fid"
      >
        <el-card shadow="hover" v-for="item1 in item.child" :key="item1.sid">
          <!-- 前往题库 -->
          <router-link
            v-if="showing"
            :to="{
              path: '/pay/Exercise/',
              query: { sid: item1.sid, fid: item.fid },
            }"
            >{{ item1.content }}</router-link
          >
          <!-- 前往课程 -->
          <router-link
            v-if="!showing"
            :to="{
              path: '/pay/Course/',
              query: { sid: item1.sid, fid: item.fid },
            }"
            >{{ item1.content }}</router-link
          >
          <!-- path: '/pay/Exercise/', -->
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

 
 

 