/**
* 学习辅助 学习笔记
*/
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>学习资料</el-breadcrumb-item>
    </el-breadcrumb>

    <el-button
      type="primary"
      size="mini"
      @click="addNote"
      style="margin-top:15px"
    >点击添加笔记</el-button
    >

    <el-divider></el-divider>

    <el-table :data="NoteData" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="center" class="demo-table-expand">
            <el-form-item label="所属标签">
              <el-tag type="success"
              ><span>{{ props.row.typeMapper.name }}</span></el-tag
              >
            </el-form-item>
            <el-form-item label="笔记概览">
              <span style="color: #5aacff; font-weight: bold" v-html="props.row.information.show"></span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="所属标签" prop="typeMapper.name" width="200">
      </el-table-column>
      <el-table-column
        label="笔记概览"
        prop="information.show"
        :show-overflow-tooltip="true"
        width="800"
      ></el-table-column>
      <el-table-column label="操作">
        <el-button-group slot-scope="scope">
          <el-button
            type="warning"
            size="mini"
            @click="selectOne(scope.row.information.content,scope.row.information.iid,)"
          >查看详情</el-button
          >
          <el-button
            type="danger"
            size="mini"
            @click="DeleteNote(scope.row.information.iid)"
          >点击删除</el-button
          >

        </el-button-group>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.currentPage"
        :page-sizes="[5, 10, 20, 30]"
        :page-size="pagination.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pagination.total">
      </el-pagination>
    </div>
    <el-divider></el-divider>
  </div>
</template>

<script>
import {NoteAndHelpDel, NoteAndHelpList, TagsList} from "../../api/basisMG";

export default {
  data() {
    return {
      //笔记模拟数据
      NoteData: [

      ],
      options: [
      ],
      activeName: "1",
      //分页
      pagination: {
        currentPage: 1,
        total: Number,
        size: 5,
      },
    };
  },
  created() {
    // console.log(this.$route.query.id)
    // type为后台判定页面的标志 笔记为0
    const userdata = localStorage.getItem("userdata");
    let data1={
      "typeId":"1",
      "uid":JSON.parse(userdata).uid,
      "page":this.pagination.currentPage,
      "limit":this.pagination.size,
      "id":this.$route.query.id
    }

    this.getNoteData(data1);
    this.Tags();
  },
  methods: {
    Tags(){
      const userdata = localStorage.getItem("userdata");
      let data={
        "uid":JSON.parse(userdata).uid
      }
      //获取所有标签
      TagsList(data)
        .then((res) => {
          if (res.flag) {
            //查询标签数据
            this.options = res.data;
            this.$message.success("标签列表刷新成功");
          } else {
            this.$message.error("标签列表刷新发生错误");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    handleSizeChange(val) {
      this.pagination.size = val
      const userdata = localStorage.getItem("userdata");
      let data1={
        "typeId":"0",
        "uid":JSON.parse(userdata).uid,
        "page":this.pagination.currentPage,
        "limit":this.pagination.size,
        "id":this.$route.query.id
      }
      this.getNoteData(data1);
    },
    handleCurrentChange(val) {
      this.pagination.currentPage = val
      const userdata = localStorage.getItem("userdata");
      let data1={
        "typeId":"0",
        "uid":JSON.parse(userdata).uid,
        "page":this.pagination.currentPage,
        "limit":this.pagination.size,
        "id":this.$route.query.id
      }
      this.getNoteData(data1);
    },
    addNote(){
      console.log(this.options)
      this.$store.commit("setCity", this.options);
      this.$store.commit("setContent", null);
      this.$router.push({path:"/LearnHelp/Editor",query:{typeId:"1"}});
    },
    selectOne(content,iid){
      this.$store.commit("setCity", this.options);
      this.$store.commit("setContent", content);
      this.$router.push({path:"/LearnHelp/Editor",query:{iid:iid,typeId:"1"}});
    },
    //获取所有笔记
    getNoteData(data) {
      NoteAndHelpList(data)
        .then((res) => {
          if (res) {
            this.NoteData = res.data;
            this.pagination.total=res.count
            this.$message.success("笔记刷新成功");
          } else {
            this.$message.error("笔记不想让你卷!");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    //删除笔记
    DeleteNote(iid) {
      let data={
        "iid":iid
      }
      NoteAndHelpDel(data)
        .then((res) => {
          if (res.flag) {
            const userdata = localStorage.getItem("userdata");
            let data1={
              "typeId":"1",
              "uid":JSON.parse(userdata).uid,
              "page":this.pagination.currentPage,
              "limit":this.pagination.size,
              "id":this.$route.query.id
            }
            this.getNoteData(data1)
            this.$message.success("笔记删除成功");
          } else {
            this.$message.error("笔记不想让你删掉他!");
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
.home {
  width: 1200px;
  margin: auto;
  position: relative;
}
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
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 100%;
}

.Notefooter {
  width: 400px;
  margin: 30px auto;
}
/* .el-select{
  width:200px;
  display: flex;
  justify-content: center;
  align-items: center;
} */
</style>
<style>
.w-e-menu {
  z-index: 2 !important;
}
.w-e-text-container {
  z-index: 1 !important;
}
</style>




