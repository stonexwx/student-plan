/**
 * 学习辅助 学习资料 这个页面和笔记页面的富文本的方法都没写 就是传值给你的方法
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>学习资料</el-breadcrumb-item>
    </el-breadcrumb>

    <h4 style="color: #5aacff; text-align: center">请在页面最底部添加资料</h4>

    <el-divider></el-divider>

    <el-table :data="NoteData" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="center" class="demo-table-expand">
            <el-form-item label="所属标签">
              <el-tag type="success"
                ><span>{{ props.row.name }}</span></el-tag
              >
            </el-form-item>
            <el-form-item label="资料详情">
              <span style="color: #5aacff; font-weight: bold">{{
                props.row.content
              }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="所属标签" prop="name" width="200">
      </el-table-column>
      <el-table-column
        label="资料详情"
        prop="content"
        :show-overflow-tooltip="true"
        width="800"
      ></el-table-column>
      <el-table-column label="操作">
        <el-button-group slot-scope="scope">
          <el-button
            type="danger"
            size="mini"
            @click="DeleteNote(scope.row.iid)"
            >点击删除</el-button
          >
        </el-button-group>
      </el-table-column>
    </el-table>
    <el-divider></el-divider>

    <!-- 以下为富文本部分 -->
    <!-- 目前富文本版本为V4 交给你了 -->
    <!-- demo1为富文本容器 -->
    <div id="demo1"></div>
     
          <!--选择界面 -->
    <div class="Notefooter">
      <el-select v-model="value" placeholder="请选择标签">
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.name"
          :value="item.name"
        >
        </el-option>
      </el-select>
      <el-button type="primary" @click="submit()">点击提交资料</el-button>
    </div>
  </div>
</template>

<script>
import { NoteAndHelpList, TagsList, NoteAndHelpDel } from "../../api/basisMG";
import wangEditor from "wangeditor";
export default {
  data() {
    return {
      //editor
      editor: null,
      editorData: "",
      dialogFormVisible: false,
      //资料模拟数据
      NoteData: [
        {
          name: "语文",
          content: "111112222222。",
        },
        {
          name: "数学",
          content: "11111111111111111111",
        },
        {
          name: "英语",
          content: "222222222222222222",
        },
      ],
      // 标签数据
      options: [
        {
          id: "1",
          name: "数学",
        },
        {
          id: "2",
          name: "语文",
        },
        {
          id: "3",
          name: "英语",
        },
      ],
      value: "",
      activeName: "1",
    };
  },
  created() {
    // type为后台判定页面的标志 资料为1
    const type = "1";
    const userdata = localStorage.getItem("userdata");
    // 获取所有资料
    this.getHelpData(type, userdata.uid);
    //获取所有标签
    TagsList(userdata.uid)
      .then((res) => {
        if (res.success) {
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
  mounted() {
    //配置editor
    const editor = new wangEditor("#demo1");
    // 配置 onchange 回调函数，将数据同步到 vue 中
    editor.config.onchange = (newHtml) => {
      this.editorData = newHtml;
    };
    // 创建编辑器
    editor.create();
    this.editor = editor;
  },
  methods: {
    //获取所有资料
    getHelpData(type, uid) {
      NoteAndHelpList(type,uid)
        .then((res) => {
          if (res.success) {
            this.NoteData = res.data;
            this.$message.success("资料刷新成功");
          } else {
            this.$message.error("资料不想让你卷!");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    //删除资料
    DeleteNote(iid) {
      alert(iid + "资料已删除！");
      const type = "1";
      NoteAndHelpDel(iid, type)
        .then((res) => {
          if (res.success) {
            this.$message.success("资料删除成功");
          } else {
            this.$message.error("资料不想让你删掉他!");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    beforeDestroy() {
      // 调用销毁 API 对当前编辑器实例进行销毁
      this.editor.destroy();
      this.editor = null;
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

 
 

 