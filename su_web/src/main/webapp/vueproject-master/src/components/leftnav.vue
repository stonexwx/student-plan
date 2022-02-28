/**
* 左边菜单
*/ 
<template>
  <el-menu
    default-active="2"
    :collapse="collapsed"
    collapse-transition
    router
    unique-opened
    class="el-menu-vertical-demo"
    background-color="#378beb"
    text-color="white"
    active-text-color="#d8d400"
  >
    <div class="logobox">
      <img class="logoimg" src="../assets/img/logo1.png" alt="" />
    </div>
    <el-submenu
      v-for="menu in allmenu"
      :key="menu.menuid"
      :index="menu.menuname"
    >
      <template slot="title">
        <span>{{ menu.menuname }}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item
          v-for="chmenu in menu.menus"
          :index="'/' + chmenu.url"
          :key="chmenu.menuid"
        >
          <span>{{ chmenu.menuname }}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>
<script>
export default {
  name: "leftnav",
  data() {
    return {
      collapsed: false,
      allmenu: [],
    };
  },
  created() {
    let res = {
      success: true,
      data: [
        {
          menuid: 1,
          menuname: "待办任务",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 2,
              menuname: "任务管理",
              hasThird: "N",
              url: "Tasks/Tasks",
              menus: null,
            },{
              menuid: 3,
              menuname: "标签管理",
              hasThird: "N",
              url: "Tasks/Tag",
              menus: null,
            },
            {
              menuid: 4,
              menuname: "测试",
              hasThird: "N",
              url: "Tasks/One",
              menus: null,
            },
          ],
        },
        {
          menuid: 33,
          menuname: "开始学习",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 34,
              menuname: "题库练习",
              hasThird: "N",
              url: "pay/Test",
              menus: null,
            },
            {
              menuid: 31,
              menuname: "课程",
              hasThird: "N",
              url: "pay/GoCourse",
              menus: null,
            },
          ],
        },
        {
          menuid: 71,
          menuname: "用户管理",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 72,
              menuname: "个人信息管理",
              hasThird: "N",
              url: "system/user",
              menus: null,
            },
             {
              menuid: 73,
              menuname: "修改密码",
              hasThird: "N",
              url: "system/userPassword",
              menus: null,
            }
          ],
        },
        {
          menuid: 128,
          menuname: "学习辅助",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 129,
              menuname: "学习资料",
              hasThird: "N",
              url: "LearnHelp/Help",
              menus: null,
            },
            {
              menuid: 175,
              menuname: "学习笔记",
              hasThird: "N",
              url: "LearnHelp/Note",
              menus: null,
            },
          ],
        },
        {
          menuid: 150,
          menuname: "任务统计",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 159,
              menuname: "数据可视化",
              hasThird: "N",
              url: "charts/statistics",
              menus: null,
            },
          ],
        },
      ],

      //管理员版本
      Mandata: [
        {
          menuid: 71,
          menuname: "用户管理",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 74,
              menuname: "用户管理",
              hasThird: "N",
              url: "system/userManage",
              menus: null,
            },
            {
              menuid: 72,
              menuname: "个人信息管理",
              hasThird: "N",
              url: "system/user",
              menus: null,
            },
            {
              menuid: 73,
              menuname: "修改密码",
              hasThird: "N",
              url: "system/userPassword",
              menus: null,
            },
          ],
        },
        {
          menuid: 1,
          menuname: "标签",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 3,
              menuname: "标签管理",
              hasThird: "N",
              url: "Tasks/TagManage",
              menus: null,
            },
          ],
        },
        {
          menuid: 99,
          menuname: "发布",
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 333,
              menuname: "课程",
              hasThird: "N",
              url: "pay/GoCourse",
              menus: null,
            },
            {
              menuid: 322,
              menuname: "题库",
              hasThird: "N",
              url: "pay/ManExercise",
              menus: null,
            },
          ],
        },
      ],
      msg: "success",
    };
    // const userdata = localStorage.getItem("userdata");
    const userdata = {
      role_id:"0"
    }
    if (userdata.role_id == "0") {
      this.allmenu = res.Mandata;
    } else {
      this.allmenu = res.data;
    }

    // 监听
    this.$root.Bus.$on("toggle", (value) => {
      this.collapsed = !value;
    });
  },
};
</script>
<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  border: none;
  text-align: left;
}
.el-menu-item-group__title {
  padding: 0px;
}
.el-menu-bg {
  background-color: #1f2d3d !important;
}
.el-menu {
  border: none;
}
.logobox {
  height: 40px;
  line-height: 40px;
  color: black;
  font-size: 20px;
  text-align: center;
  padding: 20px 0px;
}
.logoimg {
  height: 40px;
}
</style>