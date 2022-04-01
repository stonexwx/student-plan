/**
 * 任务管理 标签(管理员)
 * 删除二级标签直接点击标签后方的叉号 删除一级标签会连带删除旗下的所有二级标签
 */
 <template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/system/userManage' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>我的标签</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button
      type="primary"
      @click="dialogFormVisible = true"
      style="margin: 30px 0 0px 0px"
      >点击添加标签</el-button
    >
    <el-dialog title="添加标签" :visible.sync="dialogFormVisible" @close="dialogClose">
      <tag-insert  v-on:getDialogFormVisible="close"></tag-insert>
    </el-dialog>

    <div class="tagPage">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="标签id号" width="180">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.father.fid }}</span>
          </template>
        </el-table-column>
        <el-table-column label="一级标签" width="200">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.father.title }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="二级标签" width="580">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag
                closable
                size="medium"
                :type="colorShift(0, 4)"
                v-for="item in scope.row.child"
                :key="item.sid"
                style="margin-right: 5px; margin-bottom: 8px"
                @close="handleClose(item, scope.row.child, item.sid)"
                >{{ item.content }}</el-tag
              >
              <!-- "item.sid % 2 === 0 ? 'success' : ''" -->
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="danger" @click="handleDelete(scope.row.father.fid)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import {ManTagAdd, TitleList, TagDel, TagDelAdmin, TagFirstDel} from "../../api/basisMG";
import TagInsert from "./TagInsert";

export default {
  data() {
    return {
      //模拟数据
      tableData: [],
      //控制面板展开
      dialogFormVisible: false,
      //添加标题表单
      addTitleData: {
        title: "", //一级标题
        content: "", //二级标题
      },
    };
  },

  created() {
    //查询所有的标签
    this.getData();
  },

  methods: {

    //关闭组件
    close(data){
      console.log(data)
      this.dialogFormVisible = data
    },
    //保存表单
    dialogClose(){
      this.getData();
    },
    demo(data){
      this.dialogFormVisible = data
    },
    //标签颜色随机选择
    colorShift(min, max) {
      var num = Math.floor(Math.random() * (max - min + 1)) + min;
      var color = ["", "success", "info", "danger", "warning"];
      return color[num];
    },
    //二级标签删除
    handleClose(tag, array, sid) {
      let data = {"sid":sid}
      TagDelAdmin(data)
        .then((res) => {
          if (res.flag) {
            //重新查询所有标签
            this.getData();
            this.$message.success("您的二级标签已删除，剩余标签刷新成功！");
          } else {
            this.$message.error("标签删除失败");
          }
        })
        .catch((err) => {
          this.$message.error("删除失败，请稍后再试！(未请求)");
        });
    },
    //一级标签删除
    handleDelete(fid) {
      console.log(fid);
      let data = {"fid":fid}
      this.$confirm(
        "此操作将删除一级标签包括其下的二级标签, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          TagFirstDel(data)
            .then((res) => {
              if (res.flag) {
                //重新查询所有标签
                this.getData();
                this.$message.success("您的标签已删除，剩余标签刷新成功！");
              } else {
                this.$message.error("标签删除失败");
              }
            })
            .catch((err) => {
              this.$message.error("删除失败，请稍后再试！(未请求)");
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //获取数据
    getData() {
      //获取所有一二级标题

      TitleList()
        .then((res) => {
          if (res) {
            this.tableData = res;
            this.$store.commit("setUserTag",res);
            this.$message.success("标签刷新成功");
          } else {
            this.$message.error("标签刷新失败");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
  },

  // 注册组件
  components: {
    TagInsert
  },
};
</script>

<style scoped>
.tagPage {
  margin: 20px auto;
  padding: 15px;
  width: 98%;
  height: 400px;
  border: 1px rgb(240, 239, 239) solid;
}
</style>
