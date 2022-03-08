
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
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.currentPage"
        :page-sizes="[10, 50, 100, 200]"
        :page-size="pagination.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pagination.total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import { TestList, TestDel, TestUpload } from "../../api/basisMG";
export default {
  data() {
    return {
      //分页
      pagination: {
        currentPage: 1,
        total: Number,
        size: 10,
      },
      question: [
      ],
      //控制面板
      dialogFormVisible: false,
      //添加题库时的文本
      textarea: "",
      //二级标题id
      sid: this.$route.query.sid,
    };
  },
  created() {
    this.getTestData()
  },
  methods: {
    handUploadSuccess(response, file, fileList){
      this.CourseList.video = response[0]
    },
    handleSizeChange(val) {
      this.pagination.size = val
      this.getCourseData()
    },
    handleCurrentChange(val) {
      this.pagination.currentPage = val
      this.getCourseData()
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
      let data={
        "id":id
      }
      TestDel(data)
        .then((res) => {
          if (res) {
            this.getTestData();
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
      const userdata = localStorage.getItem("userdata");
      let id = JSON.parse(userdata);
        const testArr = {
            "sid":this.sid,
            "content":this.textarea,
            "uid":id.uid
        }
      TestUpload(testArr)
        .then((res) => {
          if (res) {
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
