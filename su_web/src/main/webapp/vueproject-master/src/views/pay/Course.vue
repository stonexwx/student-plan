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
      >点击添加课程</el-button
    >
    <el-divider></el-divider>

    <!-- 课程表格 -->
    <el-table :data="CourseData" style="width: 100%; margin-top: 40px">
      <el-table-column prop="id" label="课程id" width="80"> </el-table-column>
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
            >点击删除</el-button
          >
          <el-button
            type="success"
            v-if="!role"
            @click="goVideo(scope.row.video)"
            >点击上课</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 点击添加展开面板 -->
    <el-dialog title="添加课程" :visible.sync="dialogFormVisible">
      <el-form :model="CourseList">
        <el-form-item label="名称">
          <el-input v-model="CourseList.course_name"></el-input>
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="CourseList.content"></el-input>
        </el-form-item>
        <el-form-item label="视频上传" prop="Video">
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">提示提示</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit()">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 视频播放 -->
    <div v-show="showVideo">
      <video :src="videoUrl" ref="video" controls="controls" autoplay></video>
    </div>
  </div>
</template>

<script>
import { CourseDel, CourseList } from "../../api/basisMG.js";
export default {
  methods: {
    //获取课程数据
    getCourseData() {
      // alert(this.sid);
      //this.sid为路由中携带的二级标题参数
      CourseList(this.sid)
        .then((res) => {
          if (res.success) {
            this.CourseData = res.data;
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
      CourseDel(id)
        .then((res) => {
          if (res.success) {
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
    },
    //点击上课 目前无效
    goVideo(video) {
      this.showVideo = true;
      this.videoUrl = video;
      console.log(this.videoUrl);
      console.log(video);
      this.$refs.video.src = video;
      // this.$refs.videoPlayer.src = this.videoUrl;
      // this.$nextTick(() => {
      //   this.$refs.videoPlay.load();
      // });
    },
  },
  created() {
    const userdata = localStorage.getItem("userdata");
    //判断不同的用户角色 显示不同的按钮
    // if(userdata.role_id == "0"){
    //   //判断用户是否为管理员 0为管理员
    //     this.role = true;
    // }else{
    //   this.role = false;
    // }
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
      CourseData: [
        {
          id: "1",
          name: "毛泽东思想与中国特色社会主义思想理论体系概论",
          video: "../../assets/img/20221.mp4",
          content:
            "毛泽东思想是马克思列宁主义基本原理和中国革命具体实际相结合的产物，是中国共产党人集体智慧的结晶。是由毛泽东倡导并在二十世纪中国革命中大范围实践的一种政治、军事、发展理论，一般认为其为马列主义在中国的发展。",
        },
        {
          id: "4",
          name: "零基础手绘-插画培训火星时代专业教育28年",
          video: "",
          content:
            "零基础手绘 插画培训 火星时代学习全面的技能,参与综合实训项目,增强学员竞争能力,适应于19-45岁",
        },
        {
          id: "2",
          name: "剪辑怎么学习-视频剪辑教程-必学变现技巧",
          video: "",
          content:
            "剪辑怎么学习,针对19-45岁人群,从入门到精通项目实战教学,贴合企业用人需求,全国22所直营分校,就近学习,高薪就业,",
        },
      ],
      //面板控制
      dialogFormVisible: false,
      //点击添加课程表单
      CourseList: {
        course_name: "",
        content: "",
      },
      //控制视频展示
      showVideo: false,
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
