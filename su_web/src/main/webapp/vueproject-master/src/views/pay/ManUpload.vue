/**
管理员 上传题目
*/
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/system/userManage' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>题目管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button
      style="margin: 30px 0 0px 0px"
      type="primary"
      @click="Modify(null,'0')"
    >点击添加题目
    </el-button
    >

    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form ref="text" :model="text">
        <el-input
          v-model="text.textarea"
          :rows="2"
          placeholder="请输入题目内容"
          prop="textarea"
          type="textarea"
        >
        </el-input>
        <el-input
          v-model="text.answer"
          :rows="2"
          placeholder="请输入题目答案"
          prop="answer"
          type="text.textarea"
        >
        </el-input>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('text')">取 消</el-button>
        <el-button type="primary" @click="onSubmit()">确 定</el-button>
      </div>
    </el-dialog>
    <el-table :data="question" style="width: 100%; margin-top: 30px">
      <el-table-column label="题目id" prop="id" width="100">
      </el-table-column>
      <el-table-column label="题目内容" prop="content" width="480">
      </el-table-column>
      <el-table-column label="题目答案" prop="answer" width="480">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="deleteQuestion(scope.row.id)"
          >删除
          </el-button
          >
          <el-button size="mini" type="warning" @click="Modify(scope.row,'1')"
          >修改
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        :current-page="pagination.currentPage"
        :page-size="pagination.size"
        :page-sizes="[5, 10, 20, 30]"
        :total="pagination.total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import {TestList, TestDel, TestUpload} from "../../api/basisMG";

export default {
  data() {
    return {
      title: "添加",
      //分页
      pagination: {
        currentPage: 1,
        total: Number,
        size: 5,
      },
      question: [],
      //控制面板
      dialogFormVisible: false,
      //添加题库时的文本
      text: {
        textarea: "",
        answer: ""
      },
      //二级标题id
      sid: this.$route.query.sid,
    };
  },
  created() {
    this.getTestData()
  },
  methods: {
    Modify(row, type) {
      this.dialogFormVisible = true;
      if (type == "1") {
        this.title = "修改"
        this.text.textarea = row.content
        this.text.answer = row.answer
        this.text.id = row.id
        this.text.flag=false
      } else if (type == "0") {
        this.title = "添加"
        this.text.textarea = ''
        this.text.answer = ''
        this.text.id = ""
        this.text.flag=true
      }
    },
    handUploadSuccess(response, file, fileList) {
      this.CourseList.video = response[0]
    },
    handleSizeChange(val) {
      this.pagination.size = val
      this.getTestData()
    },
    handleCurrentChange(val) {
      this.pagination.currentPage = val
      this.getTestData()
    },
    getTestData() {
      let data = {
        "sid": this.sid,
        "page": this.pagination.currentPage,
        "limit": this.pagination.size
      }
      //获取题目内容
      TestList(data)
        .then((res) => {
          if (res) {
            this.question = res.data;
            this.pagination.total = res.count
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
      let data = {
        "id": id
      }
      TestDel(data)
        .then((res) => {
          if (res) {
            this.getTestData();
            this.$message.success("删除成功");
          } else {
            this.$message.error("删除失败");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible = false;
    },
    onSubmit() {
      //提交文本域
      if (this.text.textarea == "" || this.text.answer == "") {
        this.$message.error("还有未输入的内容哦");
      } else {
        const userdata = localStorage.getItem("userdata");
        let id = JSON.parse(userdata);
        const testArr = {
          "sid": this.sid,
          "answer": this.text.answer,
          "content": this.text.textarea,
          "uid": id.uid,
          "id":this.text.id,
          "flag":this.text.flag
        }
        TestUpload(testArr)
          .then((res) => {
            if (res) {
              this.$message.success("添加成功");
              this.getTestData()
            } else {
              this.$message.error("添加失败");
            }
          })
          .catch((err) => {
            this.$message.error("请求失败，请稍后再试！");
          });
        this.dialogFormVisible = false;
      }
    },
  },
};
</script>

<style scoped>
</style>
