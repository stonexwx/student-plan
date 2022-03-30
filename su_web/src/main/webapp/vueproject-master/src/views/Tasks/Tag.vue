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
              prop="id"
              type="danger"
              @click="handleDelete(scope.row.id)"
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
  </div>
</template>

<script>
import { TagsList, TagAdd, TagDel } from "../../api/basisMG";
export default {
  data() {
    return {
      tableData: [
      ],
      //分页
      pagination: {
        currentPage: 1,
        total: Number,
        size: 10,
      },
    };
  },

  created() {
    //查询所有的标签
    const userdata = localStorage.getItem("userdata");
    let uid = JSON.parse(userdata)
    let data= {
      "uid":uid.uid
    }
    this.getData(data);
  },
  methods: {
    handleSizeChange(val) {
      this.pagination.size = val
      this.getCourseData()
    },
    handleCurrentChange(val) {
      this.pagination.currentPage = val
      this.getCourseData()
    },
    //标签删除
    handleDelete(value) {
      let data={
        "id":value
      }
      TagDel(data)
        .then((res) => {
          if (res.flag) {
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
          if (res.flag) {
            this.tableData = res.data;
            this.pagination.total = res.count
            this.$message.success("标签刷新成功");
          } else {
            this.$message.error("标签请求失败");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    //查询重复值
    findIndexByKeyValue(arr, key, valuetosearch) {
      for (var i = 0; i < arr.length; i++) {
        if (arr[i][key] == valuetosearch) {
          return i;
        }
      }
      return -1;
    },

    //添加标签
    open() {
      const userdata = localStorage.getItem("userdata");
      let uid = JSON.parse(userdata).uid
      this.$prompt("请输入标签名", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
      })
        .then(({ value }) => {
          if (value == null) {
            this.$message.error("标签名为空!");
            return;}
          else {
        if(this.findIndexByKeyValue(this.tableData, 'name', value) != -1){
            this.$message({
              type: "warning",
              message: "您输入的标签已存在！",
            });
            return;
          }else{
          let data = {
            "name":value,
            "uid":uid
          }
          TagAdd(data)
            .then((res) => {
              if (res) {
                const userdata = localStorage.getItem("userdata");
                this.tableData = res.data;
                //重新查询所有标签
                let uid = JSON.parse(userdata)
                let data= {
                  "uid":uid.uid
                }
                this.getData(data);
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

          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消添加",
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
  border: 1px rgb(240, 239, 239) solid;
}
</style>
