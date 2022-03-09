/**
 * 自主学习 题库练习
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>题库练习</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 导航 -->
    <h1 style="color: #409eff; text-align: center">请在答题框内进行答题</h1>
    <!-- 倒计时 -->
    <h4 style="color: red; text-align: center">您还剩下{{ count }}</h4>
    <el-divider></el-divider>

    <router-view></router-view>

    <!-- 问题面板 -->
    <div class="question" v-for="item in question" :key="item.id">
      <el-card class="box-card">
        {{ item.content }}
      </el-card>
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入答题内容"
        v-model="item.text"
      >
      </el-input>
    </div>
    <el-divider></el-divider>
    <el-button type="primary" @click="submit()">提交</el-button>
  </div>
</template>

<script>
import { TestList } from "../../api/basisMG";
export default {
  data() {
    return {
      //二级标题id
      sid: this.$route.query.sid,
      //一级标题id
      fid: this.$route.query.fid,
      count: "", //倒计时
      seconds: 1800, // 30分钟的秒数
      question: [
        {
          id: "11",
          //答题内容 可不管 但你要传个空值给我
          text: "",
          content:
            "企业发放的奖金根据利润提成。利润(I)低于或等于10万元时,奖金可提10%;利润高于10万元,低于20万元时,低于10万元的部分按10%提成,高于10万元的部分,可可提成7.5%;20万到40万之间时,高于20万元的部分,可提成5%;40万到60万之间时高于40万元的部分,可提成3%;60万到100万之间时,高于60万元的部分,可提成1.5%,高于100万元时,超过100万元的部分按1%提成,从键盘输入当月利润I,求应发放奖金总数?",
        },
        {
          id: "112",
          text: "",
          content:
            "有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？",
        },
        {
          id: "113",
          text: "",
          content:
            "古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？",
        },
        {
          id: "114",
          text: "",
          content:
            "有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？",
        },
      ],

    };
  },
  created() {
    //获取题目内容
    let data = {
      "sid":this.sid
    }
    TestList(data)
        .then((res) => {
          if (res) {
            this.question = res.data;
            this.$message.success("题目刷新成功");
          } else {
            this.$message.error("题库不想让你卷!");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
  },
  mounted() {
    //入场提示
    this.$confirm("是否现在开始答题?", "提示", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning",
    })
      .then(() => {
        this.Time(); //调用定时器
      })
      .catch(() => {
        this.$message({
          type: "info",
          message: "已取消",
        });
        this.$router.push({ path: "/pay/Test/" });
      });
    this.listenPage();
  },
  methods: {
    //提交方法
    submit() {
      this.$confirm("是否提交答案?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "已提交",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
    },
    //监听刷新
    listenPage() {
      window.onbeforeunload = function (e) {
        e = e || window.event;
        if (e) {
          e.returnValue = "关闭提示";
        }
        return "关闭提示";
      };
    },
    // 天 时 分 秒 格式化函数
    countDown() {
      let d = parseInt(this.seconds / (24 * 60 * 60));
      d = d < 10 ? "0" + d : d;
      let h = parseInt((this.seconds / (60 * 60)) % 24);
      h = h < 10 ? "0" + h : h;
      let m = parseInt((this.seconds / 60) % 60);
      m = m < 10 ? "0" + m : m;
      let s = parseInt(this.seconds % 60);
      s = s < 10 ? "0" + s : s;
      this.count = d + "天" + h + "时" + m + "分" + s + "秒";
    },
    //定时器没过1秒参数减1
    Time() {
      setInterval(() => {
        if (this.seconds == 0) {
          this.$message.warning("您的时间已经用尽！");
        } else {
          this.seconds -= 1;
          this.countDown();
        }
      }, 1000);
    },
  },
};
</script>

<style scoped>
.h1 {
  margin: 30px 0px;
}
.question {
  margin: 30px 0px;
}
</style>
