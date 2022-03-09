/**
* 自主学习 课程 (管理员和用户公用)
*/
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>课程</el-breadcrumb-item>
    </el-breadcrumb>

    <el-button
      type="primary"
      @click="dialogFormVisible = true"
      style="margin: 30px 0 0px 0px"
      v-if="role"
    >点击添加课程
    </el-button
    >
    <el-divider></el-divider>

    <!-- 课程表格 -->
    <el-table :data="CourseData" style="width: 100%; margin-top: 40px">
      <el-table-column prop="id" label="课程id" width="80"></el-table-column>
      <el-table-column prop="name" label="课程名" width="380">
      </el-table-column>
      <el-table-column prop="content" label="课程简介" width="580">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            type="danger"
            v-if="role"
            @click="deleteCourse(scope.row.id)"
          >点击删除
          </el-button
          >
          <el-button
            type="success"
            v-if="!role"
            @click="goVideo(scope.row.video)"
          >点击上课
          </el-button
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

    <!-- 点击添加展开面板 -->
    <el-dialog title="添加课程" :visible.sync="dialogFormVisible">
      <el-form :model="CourseList">
        <el-form-item label="名称">
          <el-input v-model="CourseList.courseName"></el-input>
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="CourseList.content"></el-input>
        </el-form-item>
        <el-form-item label="视频上传" prop="Video">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="http://localhost:8080/course/upload"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="handUploadSuccess"
            :file-list="fileList"
            :auto-upload="true"
            :limit="1"
            :on-exceed="handleExceed"
            accept=".mp4,.mkv,.avi,.rmvb"
            name="files"
          >
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传mp4/mkv/avi/rmvb文件，且不超过1GB</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit()">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog
      title="视频"
      :visible.sync="dialogVisible"
      width="60%">

           <div v-show="showVideo" >
            <video :src="videoUrl" ref="video" controls="controls" style="height: 100%;width: 100%"></video>
           </div>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
import {CourseDel, CourseInsert, CourseList} from "../../api/basisMG.js";

export default {
  methods: {
    handUploadSuccess(response, file, fileList) {
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
    //获取课程数据
    getCourseData() {
      // alert(this.sid);
      //this.sid为路由中携带的二级标题参数
      let data = {
        "sid": this.sid,
        "page": this.pagination.currentPage,
        "limit": this.pagination.size
      }
      CourseList(data)
        .then((res) => {
          if (res) {
            this.CourseData = res.data;
            this.pagination.total = res.count
            this.$message.success("课程刷新成功");
          } else {
            this.$message.error("课程刷新失败");
          }
        })
        .catch((err) => {
          this.$message.error("(未请求)");
        });
    },
    //删除课程
    deleteCourse(id) {
      // alert(id);
      let data = {
        "id": id
      }
      CourseDel(data)
        .then((res) => {
          if (res.flag) {
            this.$message.success("课程删除成功");
            //重新获取课程数据
            this.getCourseData();
          } else {
            this.$message.error("课程删除失败");
          }
        })
        .catch((err) => {
          this.$message.error("(未请求)");
        });
    },
    //这里提交整个表单 也是添加课程
    onSubmit() {
      //关闭面板
      this.dialogFormVisible = false;
      const userdata = localStorage.getItem("userdata");
      let id = JSON.parse(userdata);
      let data = {
        "uid": id.uid,
        "sid": this.sid,
        "courseName": this.CourseList.courseName,
        "content": this.CourseList.content,
        "video": "http://" + this.CourseList.video
      }

      CourseInsert(data)
        .then((res) => {
          if (res) {

            this.$message.success("上传成功")
            this.getCourseData()
          } else {
            this.$message.error("上传失败")
          }
        })
        .catch((err) => {
            this.$message.error("(未请求)");
          }
        )
    },
    //点击上课
    goVideo(video) {
      this.showVideo = true;
      this.videoUrl = video;
      this.dialogVisible = true
      this.$refs.video.src = video;
      // this.$refs.videoPlayer.src = this.videoUrl;
      // this.$nextTick(() => {
      //   this.$refs.videoPlay.load();
      // });
    },

    handleRemove(file, fileList) {
      file.response[0] = null
      this.CourseList.video = ""
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed() {
      this.$message.error("一次只能上传一个视频哦")
    }
  },
  created() {
    const userdata = localStorage.getItem("userdata");
    let id = JSON.parse(userdata);
    //判断不同的用户角色 显示不同的按钮
    this.role = id.roleId == "0";
    this.getCourseData();
  },
  data() {
    return {
      //动态视频路径
      videoUrl: "",
      //二级标题id
      sid: this.$route.query.sid,
      //用户角色
      role: true,
      //课程信息
      CourseData: [],
      //面板控制
      dialogFormVisible: false,
      dialogVisible: false,
      //点击添加课程表单
      CourseList: {
        courseName: "",
        content: "",
        video: ""
      },
      fileList: [],
      //控制视频展示
      showVideo: false,

      //分页
      pagination: {
        currentPage: 1,
        total: Number,
        size: 10,
      }
    };
  },
};
</script>

<style scoped>
.el-table .warning-row {
  background: oldlace;
}

/* .video_box {
  position: absolute;
  top: 3%;
  left: 12%;
  width: 80%;
  height: 720px;
  z-index: 99;
}
.video-js {
  width: 100%;
  height: 720px;
} */
.el-table .success-row {
  background: #f0f9eb;
}

.el-input {
  width: 680px;
}
</style>
