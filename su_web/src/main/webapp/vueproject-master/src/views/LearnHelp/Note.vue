/**
 * 学习辅助 学习笔记
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>学习笔记</el-breadcrumb-item>
    </el-breadcrumb>

    <h4 style="color: #5aacff; text-align: center">请在页面最底部添加笔记</h4>

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
            <el-form-item label="笔记详情">
              <span style="color: #5aacff; font-weight: bold" v-html="props.row.content"></span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="所属标签" prop="name" width="200">
      </el-table-column>
      <el-table-column
        label="笔记详情"
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
    <home></home>


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
      <el-button type="primary" @click="submit()">点击提交笔记</el-button>
    </div>
  </div>
</template>

<script>
import { NoteAndHelpList, TagsList, NoteAndHelpDel } from "../../api/basisMG";
import home from "./Home";
export default {
  components: {home},
  data() {
    return {
      //笔记模拟数据
      NoteData: [
        {
          iid: "111",
          name: "语文",
          content: "春水碧于天,画船听雨眠,垆边人似月，皓腕凝霜雪。",
        },
        {
          iid: "112",
          name: "数学",
          content:
            "数学题是透过抽象化和逻辑推理的使用，由计数、计算、量度和对物体形状及运动的观察中产生的。数学题大致可分为填空题、判断题、选择题、计算题、应用题、证明题、作图题、思考题、阅读题、规律题、解答题。熟练地解题要靠平时的学习知识来灵活运用。",
        },
        {
          iid: "113",
          name: "英语",
          content:
            "<ul class=\"w-e-todo\"><li><span contenteditable=\"false\"><input type=\"checkbox\"/></span>若干个人工😇</li></ul>r institutions in society. Since competition is seen as the major source of progress and prosperity by most Americans, competitive business institutions are respected. Competition is not only good in itself, it is the means by which other basic American values such as individual freedom, equality of opportunity, and hard work are protected.",
        },
      ],
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
    // type为后台判定页面的标志 笔记为0
    const type = "0";
    const userdata = localStorage.getItem("userdata");
    this.getNoteData(type, userdata.uid);
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
  methods: {
    //获取所有笔记
    getNoteData(type, uid) {
      NoteAndHelpList(type,uid)
        .then((res) => {
          if (res.success) {
            this.NoteData = res.data;
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
      alert(iid+"笔记已删除！")
      const type = "0";
      NoteAndHelpDel(iid, type)
        .then((res) => {
          if (res.success) {
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




