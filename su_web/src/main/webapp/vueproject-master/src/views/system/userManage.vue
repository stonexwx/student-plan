/**
* 系统管理 用户管理(管理员)
*/
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/system/userManage' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户信息管理(管理员)</el-breadcrumb-item>
    </el-breadcrumb>

    <el-button
      style="margin-top: 30px"
      type="primary"
      @click="userModifition(row)"
    >添加用户
    </el-button
    >
    <el-divider></el-divider>
    <el-table :data="userDataList" border style="width: 100%; margin-top: 40px" >
      <el-table-column label="用户id" prop="uid" width="80px">
      </el-table-column>
      <el-table-column label="用户姓名" prop="realName" width="150px">
      </el-table-column>
      <el-table-column label="用户昵称" prop="userName" width="180px">
      </el-table-column>
      <el-table-column label="用户角色" prop="roleId" width="100px" :formatter="roleShif">
      </el-table-column>
      <el-table-column label="用户年龄" prop="age" width="100px">
      </el-table-column>
      <el-table-column label="用户性别" prop="sex" width="100px">
      </el-table-column>
      <el-table-column label="用户电话" prop="phone" width="180px">
      </el-table-column>
      <el-table-column label="用户密码" prop="password" width="150px">
      </el-table-column>
      <el-table-column label="操作"
      >
        <el-button-group slot-scope="scope">
          <el-button
            size="mini"
            type="warning"
            @click="userModifition(scope.row)"
          >点击修改
          </el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="userDelete(scope.row.uid)"
          >点击删除
          </el-button
          >
        </el-button-group>
      </el-table-column>
    </el-table>


    <div class="block">
      <el-pagination
        :current-page="pagination.currentPage"
        :page-size="pagination.size"
        :page-sizes="[5, 10, 20, 30]"
        :total="pagination.total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>


    <el-dialog :title="this.title" :visible.sync="dialogFormVisible">
      <el-form ref="userForm" :model="userForm" :rules="rules">
        <el-form-item
          label="用户昵称"
          prop="userName"
        >
          <el-input v-model="userForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="用户姓名" prop="realName">
          <el-input v-model="userForm.realName"></el-input>
        </el-form-item>
        <el-form-item label="用户角色" prop="roleId">
          <el-select v-model="userForm.roleId" placeholder="请选择角色">
            <el-option
              label="管理员"
              value="0">
            </el-option>
            <el-option
              label="用户"
              value="1"
             >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item
          label="用户年龄"
          prop="age"
        >
          <el-input v-model="userForm.age"></el-input>
        </el-form-item>
        <el-form-item label="用户性别" prop="sex">
          <el-radio-group v-model="userForm.sex"
                          :rules="[{ required: true, message: '性别不能为空' }]">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="用户账号"
          prop="phone"

        >
          <el-input v-model="userForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="password">
          <el-input v-model="userForm.password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('userForm')">取 消</el-button>
        <el-button type="primary" @click="onSubmit('userForm');"
        >确 定
        </el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {isvalidPhone} from '../../../config/validate'
import {userList, userSave, userDelete, userInsert} from "../../api/userMG";

var validPhone = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入电话号码'))
  } else if (!isvalidPhone(value)) {
    callback(new Error('请输入正确的11位手机号码'))
  } else {
    callback()
  }
}
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
        roleId:"",
        age: "",
      },
      // 控制面板展开
      dialogFormVisible: false,
      title: "",
      //分页
      pagination: {
        currentPage: 1,
        total: Number,
        size: 5,
      },
      rules: {
        userName: [{required: true, message: '昵称不能为空', trigger: 'blur'}, {
          min: 2,
          max: 7,
          message: '长度在 2 到 7 个字符',
          trigger: 'blur'
        }],
        realName: [{required: true, message: '姓名不能为空', trigger: 'blur'},
          {
            min: 2,
            max:7,
            message: '长度在2 到 7个字符',
            trigger: 'blur'
          }],
        age: [{ required: true, message: '年龄不能为空且只能为数字', trigger: 'blur'}],
        sex: [{required: true, message: '性别不能为空', trigger: 'change'}],
        roleId: [{required: true, message: '角色不能为空', trigger: 'blur'}],
        phone: [{required: true, trigger: 'blur', validator: validPhone}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}],
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
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible = false;
    },
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
        "page": this.pagination.currentPage,
        "limit": this.pagination.size
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
        this.userForm.roleId = row.roleId;
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
        this.userForm.roleId = "";
        this.userForm.phone = "";
        this.userForm.sex = "";
        this.userForm.password = null;
      }
    },
    roleShif(row){
      return row.roleId == "1"?"用户":"管理员";
    },
    //删除用户信息
    userDelete(uid) {
      let data = {
        "uid": uid
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
          if (this.userForm.uid == "") {
            userInsert(this.userForm).then((res) => {
              if (res) {
                this.getdata()
                this.$message({
                  type: "success",
                  message: "数据保存成功！",
                });
                this.dialogFormVisible = false;
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
          } else {
            userSave(this.userForm)
              .then((res) => {
                if (res) {
                  this.getdata();
                  this.$message({
                    type: "success",
                    message: "数据保存成功！",
                  });
                  this.dialogFormVisible = false;
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
