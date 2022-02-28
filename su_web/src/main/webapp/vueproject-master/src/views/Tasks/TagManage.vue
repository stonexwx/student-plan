/**
 * 任务管理 标签(管理员)
 * 删除二级标签直接点击标签后方的叉号 删除一级标签会连带删除旗下的所有二级标签
 */
 <template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>我的标签</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button
      type="primary"
      @click="dialogFormVisible = true"
      style="margin: 30px 0 0px 0px"
      >点击添加标签</el-button
    >
    <el-dialog title="添加标签" :visible.sync="dialogFormVisible">
      <el-select
        v-model="addTitleData.title"
        filterable
        allow-create
        default-first-option
        placeholder="请选择或创建一级标签"
      >
        <el-option
          v-for="item in tableData"
          :key="item.fid"
          :label="item.title"
          :value="item.title"
        >
        </el-option>
      </el-select>
      <el-input
        v-model="addTitleData.content"
        placeholder="请输入二级标签"
        style="width: 450px; margin-left: 20px"
      ></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit()">确 定</el-button>
      </div>
    </el-dialog>

    <div class="tagPage">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="标签id号" width="180">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.fid }}</span>
          </template>
        </el-table-column>
        <el-table-column label="一级标签" width="200">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.title }}</el-tag>
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
            <el-button type="danger" @click="handleDelete(scope.row.fid)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { ManTagAdd, TitleList, TagDel } from "../../api/basisMG";
export default {
  data() {
    return {
      //模拟数据
      tableData: [
        {
          fid: "1",
          title: "文科",
          child: [
            {
              sid: "1",
              content: "语文",
            },
            {
              sid: "2",
              content: "地理",
            },
            {
              sid: "3",
              content: "历史",
            },
            {
              sid: "4",
              content: "政治",
            },
            {
              sid: "21",
              content: "新闻",
            },
            {
              sid: "33",
              content: "汉语言",
            },
            {
              sid: "42",
              content: "散文",
            },
          ],
        },
        {
          fid: "2",
          title: "编程",
          child: [
            {
              sid: "1",
              content: "C",
            },
            {
              sid: "2",
              content: "Java",
            },
            {
              sid: "21",
              content: "Python",
            },
          ],
        },
        {
          fid: "3",
          title: "天杀的",
          child: [
            {
              sid: "1",
              content: "数学",
            },
            {
              sid: "2",
              content: "物理",
            },
          ],
        },
      ],
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
    const userdata = localStorage.getItem("userdata");
    this.getData(userdata.uid);
  },
  methods: {
    //保存表单
    onSubmit() {
      // 请求方法
      var titlelist = [this.addTitleData.title, this.addTitleData.content];
      console.log(titlelist);
      if (this.addTitleData.title == "") {
        this.$message.error("您没有输入一级标题");
      } else {
        //正式请求后注释下面这两行
        // this.$message.success("okok");
        // this.dialogFormVisible = false;

        ManTagAdd(titlelist)
          .then((res) => {
            if (res.success) {
              //重新查询所有标签
              this.getData();
              this.$message.success("标签刷新成功！");
              this.dialogFormVisible = false;
            } else {
              this.$message.error("标签添加失败");
            }
          })
          .catch((err) => {
            this.$message.error("(未请求)");
          });
      }
    },
    //标签颜色随机选择
    colorShift(min, max) {
      var num = Math.floor(Math.random() * (max - min + 1)) + min;
      var color = ["", "success", "info", "danger", "warning"];
      return color[num];
    },
    //二级标签删除
    handleClose(tag, array, sid) {
      array.splice(array.indexOf(tag), 1);
      TagDel(sid)
        .then((res) => {
          if (res.data.flag) {
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
          TagDel(fid)
            .then((res) => {
              if (res.data.flag) {
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
          if (res.success) {
            this.tableData = res.data;
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