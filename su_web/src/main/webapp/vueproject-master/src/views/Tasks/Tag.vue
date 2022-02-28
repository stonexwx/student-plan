/**
    用户标签管理
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>我的标签</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button type="primary" @click="open" style="margin:30px 0 0px 0px">点击添加标签</el-button>
    
    <div class="tagPage">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="标签id号" width="280">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column label="标签名" width="380">
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { TagsList, TagAdd, TagDel } from "../../api/basisMG";
export default {
  data() {
    return {
      tableData: [
        {
          id: "1",
          name: "语文",
        },
        {
          id: "1",
          name: "乱七八糟的标签",
        },
        {
          id: "1",
          name: "哈哈哈哈哈",
        },
        {
          id: "1",
          name: "哦哟哦哟哟",
        },
        {
          id: "1",
          name: "语文",
        },
        {
          id: "1",
          name: "c",
        },
      ],
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10,
      },
    };
  },

  created() {
    //查询所有的标签
    const userdata = localStorage.getItem("userdata");
    this.getData(userdata.uid);
  },
  methods: {
    //标签删除
    handleDelete(row) {
      TagDel(row.id)
        .then((res) => {
          if (res.data.flag) {
            //重新查询所有标签
            this.getData(this.uid);
            this.$message.success("您的标签已删除，剩余标签刷新成功！");
          } else {
            this.$message.error("标签删除失败");
          }
        })
        .catch((err) => {
          this.$message.error("删除失败，请稍后再试！(未请求)");
        });
    },
    //查询所有标签信息
    getData(uid) {
      TagsList(uid)
        .then((res) => {
          if (res.success) {
            this.tableData = res.data;
            this.$message.success("标签刷新成功");
          } else {
            this.$message.error("标签请求失败");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    //添加标签
    open() {
      const userdata = localStorage.getItem("userdata");
      this.$prompt("请输入标签名", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
      })
        .then(({ value }) => {
          if (value == null) {
            this.$message.error("怎么给爷上个空的!");
            return;
          } else {
            TagAdd(value, userdata.uid)
              .then((res) => {
                if (res.success) {
                  const userdata = localStorage.getItem("userdata");
                  this.tableData = res.data;
                  //重新查询所有标签
                  this.getData(userdata.uid);
                  this.$message.success("标签刷新成功");

                  this.$message({
                    type: "success",
                    message: "你的输入的标签名是: " + value,
                  });
                } else {
                  this.$message.error("标签请求失败");
                }
              })
              .catch((err) => {
                this.$message.error("请求失败，请稍后再试！");
              });
          }
        })
        .catch(() => {
          this.$message({
            type: "error",
            message: "发生错误",
          });
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