/**
 * 系统管理 修改密码
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人密码修改</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 个人信息表单 -->
    <div class="user-form">
      <el-card class="box-card">
        <el-form ref="Passwordform" :rules="rules" :model="Passwordform" label-width="130px">
          <el-form-item label="请输入当前密码" prop="password">
            <el-input type="password" v-model="Passwordform.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="请输入新密码" prop="rePassword">
            <el-input type="password" v-model="Passwordform.rePassword" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="请再次输入新密码" prop="newPassword">
            <el-input type="password" v-model="Passwordform.newPassword" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('Passwordform')"
              >保存</el-button
            >
            <el-button @click="resetForm('Passwordform')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import { userPwd } from "../../api/userMG";
export default {
  data() {
    //密码输入判定 勿删
    var validateOldPass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        let password = localStorage.getItem("userdata")
        let json =  JSON.parse(password)
        if(value!=json.password){
          callback(new Error("密码与当前不一致,请重新输入"))
        }
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.Passwordform.rePassword !== "") {
          this.$refs.Passwordform.validateField("rePassword");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.Passwordform.rePassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      Passwordform: {
        password: "",
        rePassword: "",
        newPassword: "",
      },
      rules: {
        password: [{ validator: validateOldPass, trigger: "blur" }],
        rePassword: [{ validator: validatePass2, trigger: "blur" }],
        newPassword: [{ validator: validatePass, trigger: "blur" }],
      },
    };
  },
  /**
   * 数据发生改变
   */
  watch: {},

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    //保存密码
    submitForm(Passwordform) {
      this.$refs[Passwordform].validate((valid) => {
        if (valid) {
            // 请求方法
          let uid =localStorage.getItem("userdata")
          let json =  JSON.parse(uid)
          console.log(json)
          let password = {
            "uid":json.uid,
            "password":this.Passwordform.newPassword
          }
            userPwd(password)
              .then((res) => {
                if (res) {
                  localStorage.setItem("userdata",JSON.stringify(res.data))
                  this.$message({
                    type: "success",
                    message: "数据保存成功！",
                  });

                } else if (res.data == null) {
                  this.$message.error("修改失败，当前密码输入错误！");
                } else {
                  this.$message.error("发生错误啦");
                }
              })
              .catch((err) => {
                this.$message.error("请求修改失败，组织不同意本次请求！");
              });
        } else {
          this.$message.error("提交错误 让我瞅瞅");
          return false;
        }
      });
    },
    //清空表单数据
    resetForm(Passwordform) {
      this.$refs[Passwordform].resetFields();
    },
  },
};
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}
.userRole {
  width: 100%;
}
.el-card {
  width: 800px;
  margin-top: 50px;
}
.user-form {
  display: flex;
  justify-content: center;
  align-items: center;
}
.el-form {
  margin-top: 40px;
}
.el-form-item__label {
  text-align: center;
  width: 120px;
}
.el-input {
  width: 600px;
}
</style>

