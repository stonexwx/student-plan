/**
 * 系统管理 用户管理 
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人信息管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 个人信息表单 -->
    <div class="user-form">
      <el-card class="box-card">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item
            label="昵称"
            prop="user_name"
            :rules="[{ required: true, message: '昵称不能为空' }]"
          >
            <el-input v-model="form.user_name"></el-input>
          </el-form-item>
          <el-form-item
            label="姓名"
            prop="real_name"
            :rules="[{ required: true, message: '报上名来' }]"
          >
            <el-input v-model="form.real_name"></el-input>
          </el-form-item>
          <el-form-item
            label="性别"
            prop="sex"
            :rules="[{ required: true, message: '性别不能为空' }]"
          >
            <el-radio-group v-model="form.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item
            label="年龄"
            prop="age"
            :rules="[
              { required: true, message: '年龄不能为空' },
              { type: 'number', message: '年龄必须为数字值' },
            ]"
          >
            <el-input
              type="age"
              v-model.number="form.age"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('form')">保存</el-button>
            <el-button @click="resetForm('form')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import { userMessage, userSave } from "../../api/userMG";
export default {
  data() {
    return {
      form: {
        user_name: "",
        real_name: "",
        sex: "",
        age: "",
      },
    };
  },
  // 注册组件
  components: {},

  /**
   * 数据发生改变
   */
  watch: {},

  /**
   * 创建完毕
   */
  created() {
    this.getdata(this.form);
  },
  methods: {
    getdata(user) {
      //获取用户信息
      userMessage(user).then(res => {
        //若调用失败
        if (res.success == false) {
          this.$message.error("请求发生错误");
        } else {
          this.form = res.data
        }
      })
    },
    //保存表单
    onSubmit(form) {
      // 请求方法
      this.$refs[form].validate((valid) => {
        if (valid) {
          userSave(form)
            .then((res) => {
              if (res.success) {
                //重新获取用户信息
                this.getdata(this.form);
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
    //重置按钮
    resetForm(form) {
      this.$refs[form].resetFields();
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
}
.el-input {
  width: 620px;
}
</style>

 