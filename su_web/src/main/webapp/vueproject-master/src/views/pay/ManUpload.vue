
 /**
  管理员 上传题目
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>题目管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button
      type="primary"
      @click="dialogFormVisible = true"
      style="margin: 30px 0 0px 0px"
      >点击添加题目</el-button
    >

    <el-dialog title="添加题目" :visible.sync="dialogFormVisible">
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入题目内容"
        v-model="textarea"
      >
      </el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit()">确 定</el-button>
      </div>
    </el-dialog>
    <el-table :data="question" style="width: 100%; margin-top: 30px">
      <el-table-column label="题目id" prop="id" width="100">
      </el-table-column>
      <el-table-column label="题目内容" prop="content" width="1080">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="danger" @click="deleteQuestion(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { TestList, TestDel, TestUpload } from "../../api/basisMG";
export default {
  data() {
    return {
      question: [
        {
          id: "11",
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
      //控制面板
      dialogFormVisible: false,
      //添加题库时的文本
      textarea: "",
      //二级标题id
      sid: this.$route.query.sid,
    };
  },
  created() {},
  methods: {
    getTestData() {
      //获取题目内容
      TestList(this.sid)
        .then((res) => {
          if (res.success) {
            this.question = res.data;
            this.$message.success("题目刷新成功");
          } else {
            this.$message.error("题目刷新失败");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    deleteQuestion(id) {
      TestDel(id)
        .then((res) => {
          if (res.success) {
            this.$message.success("删除ok");
          } else {
            this.$message.error("删除失败");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    onSubmit() {
      //提交文本域
        const testArr = {
            sid:this.sid,
            content:this.textarea
        }
      console.log(testArr);
      TestUpload(testArr)
        .then((res) => {
          if (res.success) {
            this.$message.success("添加成功");
            this.getTestData()
            this.dialogFormVisible = false;
          } else {
            this.$message.error("添加失败");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
  },
};
</script>

<style scoped>
</style>