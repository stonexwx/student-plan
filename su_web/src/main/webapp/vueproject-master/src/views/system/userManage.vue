/**
 * 系统管理 用户管理(管理员)
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户信息管理(管理员)</el-breadcrumb-item>
    </el-breadcrumb>

    <el-button
      type="primary"
      @click="userModifition(row)"
      style="margin-top: 30px"
      >添加用户</el-button
    >
    <el-divider></el-divider>
    <el-table :data="userDataList" border style="width: 100%; margin-top: 40px">
      <el-table-column prop="uid" label="用户id" width="80px">
      </el-table-column>
      <el-table-column prop="realName" label="用户姓名" width="150px">
      </el-table-column>
      <el-table-column prop="userName" label="用户昵称" width="180px">
      </el-table-column>
      <el-table-column prop="age" label="用户年龄" width="100px">
      </el-table-column>
      <el-table-column prop="sex" label="用户性别" width="100px">
      </el-table-column>
      <el-table-column prop="phone" label="用户电话" width="180px">
      </el-table-column>
      <el-table-column prop="password" label="用户密码" width="150px">
      </el-table-column>
      <el-table-column label="操作"
        ><el-button-group slot-scope="scope">
          <el-button
            type="warning"
            size="mini"
            @click="userModifition(scope.row)"
            >点击修改</el-button
          >
          <el-button
            type="danger"
            size="mini"
            @click="userDelete(scope.row.uid)"
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
        :page-sizes="[10, 50, 100, 200]"
        :page-size="pagination.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pagination.total">
      </el-pagination>
    </div>


    <el-dialog :title="this.title" :visible.sync="dialogFormVisible">
      <el-form :model="userForm" ref="userForm">
        <el-form-item
          prop="userName"
          label="用户昵称"
          :rules="[{ required: true, message: '昵称不能为空' }]"
        >
          <el-input v-model="userForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="用户姓名" prop="realName">
          <el-input v-model="userForm.realName"></el-input>
        </el-form-item>
        <el-form-item
          label="用户年龄"
          prop="age"
          :rules="[{ required: true, message: '年龄不能为空' }]"
        >
          <el-input v-model="userForm.age"></el-input>
        </el-form-item>
        <el-form-item label="用户性别" prop="sex">
          <el-input v-model="userForm.sex"></el-input>
        </el-form-item>
        <el-form-item
          label="用户电话"
          prop="phone"
          :rules="[{ required: true, message: '电话不能为空' }]"
        >
          <el-input v-model="userForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="password">
          <el-input v-model="userForm.password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit('userForm');dialogFormVisible = false"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {userList, userSave, userDelete, userInsert} from "../../api/userMG";
export default {
  data() {
    return {
      userDataList: [],
      // 添加用户表单
      userForm: {
        uid: "",
        realName: "",
        userName: "",
        phone: "",
        password: "",
        sex: "",
        age: "",
      },
      // 控制面板展开
      dialogFormVisible: false,
      title: "",
      //分页
      pagination:{
        currentPage:1,
        total:Number,
        size:10,
      }

    };
  },

  /**
   * 数据发生改变
   */
  watch: {},

  /**
   * 创建完毕
   */
  created() {
    //获取数据
    this.getdata();
  },
  methods: {
    handleSizeChange(val) {
      this.pagination.size = val
      this.getdata()
    },
    handleCurrentChange(val) {
      this.pagination.currentPage = val
      this.getdata()
    },
    getdata() {
      //获取用户信息
        let pageRequest = {
            "page":this.pagination.currentPage,
            "limit":this.pagination.size
        }
        userList(pageRequest)
          .then((res) => {
            if (res.flag) {
              this.userDataList = res.data;
              this.pagination.total = res.count;
              this.$message.success("用户列表刷新成功");
            } else {
              this.$message.error("请求发生错误");
            }
          })
          .catch((err) => {
            this.$message.error("请求失败，请稍后再试！");
          });
    },
    //修改、添加用户信息
    userModifition(row) {
      this.dialogFormVisible = true;
      //当点击的按钮为修改用户信息时
      if (row != undefined && row != "undefined") {
        this.title = "修改用户";
        this.userForm.uid = row.uid;
        this.userForm.userName = row.userName;
        this.userForm.realName = row.realName;
        this.userForm.age = row.age;
        this.userForm.phone = row.phone;
        this.userForm.sex = row.sex;
        this.userForm.password = row.password;
      } else {
        //当点击的用户为增加用户时
        //添加用户
        this.dialogFormVisible = true;
        this.title = "添加用户";
        this.userForm.uid = "";
        this.userForm.userName = "";
        this.userForm.realName = "";
        this.userForm.age = "";
        this.userForm.phone = "";
        this.userForm.sex = "";
        this.userForm.password = null;
      }
    },
    //删除用户信息
    userDelete(uid) {
      let data={
        "uid":uid
      }
         userDelete(data)
          .then((res) => {
            if (res.flag) {
              //刷新用户数据
              this.getdata();
              this.$message({
                type: "success",
                message: "数据删除成功！",
              });
            } else {
              this.$message({
                type: "info",
                message: res.msg,
              });
            }
          })
          .catch((err) => {
            this.$message.error("删除失败，请稍后再试！");
          });
    },
    //保存表单
    onSubmit(userForm) {
      // 请求方法
      this.$refs[userForm].validate((valid) => {
        if (valid) {
          if(this.userForm.uid==""){
            userInsert(this.userForm).then((res)=>{
              if(res){
                this.getdata()
                this.$message({
                  type: "success",
                  message: "数据保存成功！",
                });
              } else {
                this.$message({
                  type: "info",
                  message: res.msg,
                });
              }
            })
              .catch((err) => {
                this.$message.error("保存失败，请稍后再试！");
              })
          }else {
            userSave(this.userForm)
              .then((res) => {
                if (res) {
                  this.getdata();
                  this.$message({
                    type: "success",
                    message: "数据保存成功！",
                  });
                } else {
                  this.$message({
                    type: "info",
                    message: res.msg,
                  });
                }
              })
              .catch((err) => {
                this.$message.error("保存失败，请稍后再试！");
              });
          }

        } else {
          this.$message.error("发生错误啦");
        }
      });
    },
  },
};
</script>

<style scoped>

</style>
