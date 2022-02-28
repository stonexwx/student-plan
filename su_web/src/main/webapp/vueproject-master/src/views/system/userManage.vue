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
        <el-button type="primary" @click="onSubmit('userForm')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { userList, userSave, userDelete } from "../../api/userMG";
export default {
  data() {
    return {
      userDataList: [
        {
          uid: "1",
          realName: "张三",
          userName: "法外狂徒",
          phone: "12345678",
          password: "123456781111",
          sex: "男",
          age: "20",
        },
        {
          uid: "2",
          realName: "里斯",
          userName: "法外狂徒2",
          phone: "123456782",
          password: "12zzz3456781111",
          sex: "女",
          age: "20",
        },
        {
          uid: "3",
          realName: "张三",
          userName: "法外狂徒3",
          phone: "12345678",
          password: "1sfafaege",
          sex: "男",
          age: "20",
        },
        {
          uid: "4",
          realName: "张三",
          userName: "法外狂徒4",
          phone: "12345678",
          password: "1jjjjjjjjj",
          sex: "男",
          age: "20",
        },
        {
          uid: "5",
          realName: "张三",
          userName: "法外狂徒5",
          phone: "12345678",
          password: "1ssffghhv",
          sex: "男",
          age: "20",
        },
      ],
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
    getdata() {
      获取用户信息
        userList()
          .then((res) => {
            if (res.success) {
              this.userDataList = res.data;
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
        this.userForm.password = "";
      }
    },
    //删除用户信息
    userDelete(uid) {
         userDelete(uid)
          .then((res) => {
            if (res.success) {
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
          userSave(userForm)
            .then((res) => {
              if (res.success) {
                this.getdata(this.userForm);
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
