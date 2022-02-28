<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>我的待办</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 按钮组 -->
    <div class="clickButton">
      <el-button type="primary" round @click="dialogVisible = true"
        >点我添加待办事项哦</el-button
      >
      <el-button type="warning" round @click="OkAndNoTask()"
        >所有任务</el-button
      >
      <el-button type="success" round @click="OkAndNoTask(1)"
        >查看已完成任务</el-button
      >
      <el-button type="info" round @click="OkAndNoTask(0)"
        >查看未完成任务</el-button
      >
    </div>
    <!-- 添加代办 -->
    <el-dialog
      title="添加待办"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form :model="todoList">
        <el-date-picker
          @change="dateChangebirthday1"
          v-model="Time"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          type="daterange"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :clearable="false"
          style="width: 420px"
        >
        </el-date-picker>
        <el-form-item label="待办内容" prop="content">
          <el-input type="textarea" v-model="todoList.content"></el-input>
        </el-form-item>
        <el-select
          v-model="todoList.name"
          placeholder="请选择标签"
          style="width: 420px"
        >
          <el-option
            v-for="item in options"
            :key="item.options"
            :label="item.name"
            :value="item.name"
            style="width: 420px"
          >
          </el-option>
        </el-select>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="ListAdd()">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 所有任务表格 -->
    <el-table
      ref="filterTable"
      :data="todoListShow"
      style="width: 100%"
      v-if="showTable"
    >
      <el-table-column
        prop="task.startTime"
        label="起始日期"
        sortable
        width="170"
        column-key="startTime"
      >
      </el-table-column>
      <el-table-column prop="task.content" label="内容" width="250">
      </el-table-column>
      <el-table-column
        prop="task.endTime"
        sortable
        label="结束日期"
        width="170"
        :formatter="formatter"
      >
      </el-table-column>
      <el-table-column
        prop="type.name"
        label="标签"
        width="170"
        :filters="option"
        :filter-method="filterTag"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.type.name === '收藏夹' ? 'primary' : 'success'"
            disable-transitions
            >{{ scope.row.type.name }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label="倒计时" width="170">
        <span slot-scope="scope">
          {{ countDown(scope.row.task.endTime) }}
        </span>
      </el-table-column>
      <el-table-column label="状态" width="100">
        <!-- 当完成时显示的按钮 -->
        <el-button
          size="mini"
          :type="showStatetype(scope.row.task.state)"
          :icon="showStateicon(scope.row.task.state)"
          circle
          prop="task.state"
          slot-scope="scope"
        ></el-button>
      </el-table-column>
      <el-table-column label="操作">
        <el-button-group slot-scope="scope">
          <el-button
            prop="task.state"
            type="success"
            size="mini"
            v-if="!scope.row.task.state"
            @click="Accomplish(scope.row.task.tid)"
            >点击完成</el-button
          >
          <el-button
            type="danger"
            size="mini"
            @click="Delete(scope.row.task.tid)"
            >点击删除</el-button
          >
        </el-button-group>
      </el-table-column>
    </el-table>

    <!-- 查看已完成任务 -->
    <el-table
      ref="filterTable"
      :data="OkTaskfilter"
      style="width: 100%"
      v-if="OkTable"
    >
      <el-table-column
        prop="task.startTime"
        label="起始日期"
        sortable
        width="170"
        column-key="startTime"
      >
      </el-table-column>
      <el-table-column prop="task.content" label="内容" width="250">
      </el-table-column>
      <el-table-column
        prop="task.endTime"
        sortable
        label="结束日期"
        width="170"
        :formatter="formatter"
      >
      </el-table-column>
      <el-table-column
        prop="type.name"
        label="标签"
        width="170"
        :filters="option"
        :filter-method="filterTag"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.type.name === '收藏夹' ? 'primary' : 'success'"
            disable-transitions
            >{{ scope.row.type.name }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label="倒计时" width="170">
        <span slot-scope="scope">
          {{ countDown(scope.row.task.endTime) }}
        </span>
      </el-table-column>
      <el-table-column label="状态" width="100">
        <!-- 当完成时显示的按钮 -->
        <el-button
          size="mini"
          :type="showStatetype(scope.row.task.state)"
          :icon="showStateicon(scope.row.task.state)"
          circle
          prop="task.state"
          slot-scope="scope"
        ></el-button>
      </el-table-column>
      <el-table-column label="操作">
        <el-button-group slot-scope="scope">
          <el-button
            prop="task.state"
            type="success"
            size="mini"
            v-if="!scope.row.task.state"
            @click="Accomplish(scope.row.task.tid)"
            >点击完成</el-button
          >
          <el-button
            type="danger"
            size="mini"
            @click="Delete(scope.row.task.tid)"
            >点击删除</el-button
          >
        </el-button-group>
      </el-table-column>
    </el-table>

    <!-- 查看未完成任务 -->
    <el-table
      ref="filterTable"
      :data="NoTaskfilter"
      style="width: 100%"
      v-if="NoTable"
    >
      <el-table-column
        prop="task.startTime"
        label="起始日期"
        sortable
        width="170"
        column-key="startTime"
      >
      </el-table-column>
      <el-table-column prop="task.content" label="内容" width="250">
      </el-table-column>
      <el-table-column
        prop="task.endTime"
        sortable
        label="结束日期"
        width="170"
        :formatter="formatter"
      >
      </el-table-column>
      <el-table-column
        prop="type.name"
        label="标签"
        width="170"
        :filters="option"
        :filter-method="filterTag"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.type.name === '收藏夹' ? 'primary' : 'success'"
            disable-transitions
            >{{ scope.row.type.name }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label="倒计时" width="170">
        <span slot-scope="scope">
          {{ countDown(scope.row.task.endTime) }}
        </span>
      </el-table-column>
      <el-table-column label="状态" width="100">
        <!-- 当完成时显示的按钮 -->
        <el-button
          size="mini"
          :type="showStatetype(scope.row.task.state)"
          :icon="showStateicon(scope.row.task.state)"
          circle
          prop="task.state"
          slot-scope="scope"
        ></el-button>
      </el-table-column>
      <el-table-column label="操作">
        <el-button-group slot-scope="scope">
          <el-button
            prop="task.state"
            type="success"
            size="mini"
            v-if="!scope.row.task.state"
            @click="Accomplish(scope.row.task.tid)"
            >点击完成</el-button
          >
          <el-button
            type="danger"
            size="mini"
            @click="Delete(scope.row.task.tid)"
            >点击删除</el-button
          >
        </el-button-group>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {
  todoList,
  todoListAdd,
  todoListDelete,
  todoListSave,
  TagsList,
} from "../../api/basisMG";
export default {
  data() {
    return {
      //模拟数据
      todoListShow: [
        {
          task: {
            tid: "1",
            startTime: "2016-05-02",
            content: "今天的目标是赚他一个亿",
            endTime: "2022-05-02",
            state: 1,
          },
          type: {
            id: "21",
            name: "语文",
          },
        },
        {
          task: {
            tid: "2",
            startTime: "2016-05-02",
            content: "今天的目标是赚他一个亿",
            endTime: "2022-02-02",
            state: 0,
          },
          type: {
            id: "23",
            name: "c",
          },
        },
      ],
      //是否展开面板
      dialogVisible: false,
      //用户增加待办的表单
      todoList: {
        content: "",
        startTime: "",
        endTime: "",
        name: "",
      },
      //时间选择器取值中间量
      Time: "",
      options: [
        {
          id: "1",
          name: "数学",
        },
        {
          id: "2",
          name: "语文",
        },
        {
          id: "3",
          name: "英语",
        },
      ],
      option: [],
      one: [],
      //控制所有任务表格显示
      showTable: true,
      //控制已完成任务
      OkTable: false,
      //控制未完成任务
      NoTable: false,
    };
  },
  created() {
    //获取任务数据
    this.getListData();
    //获取标签数据
    this.getTagsData();
  },
  computed: {
    //区分完成及未完成任务筛选器 不要动这个
    OkTaskfilter() {
      return this.todoListShow.filter((item) => item.task.state === 1);
    },
    NoTaskfilter() {
      return this.todoListShow.filter((item) => item.task.state === 0);
    },
  },
  methods: {
    //获取页面数据
    getListData() {
      //获取登陆中存储的用户信息userdata中的uid
      const userdata = localStorage.getItem("userdata");
      todoList(userdata.uid)
        .then((res) => {
          if (res.success) {
            //查询页面数据
            this.todoListShow = res.data;
            this.$message.success("任务列表刷新成功");
          } else {
            this.$message.error("列表刷新发生错误");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    //获取标签数据
    getTagsData() {
      //获取登陆中存储的用户信息userdata中的uid
      const userdata = localStorage.getItem("userdata");
      TagsList(userdata.uid)
        .then((res) => {
          if (res.success) {
            //查询标签数据
            this.options = res.data;
            //这里是你写的标签筛选 不起作用 我之前也是这么写的
            for (var i = 0; i < res.data.length; i++) {
              this.option[i].text = res.data[i].name;
              this.option[i].value = res.data[i].name;
            }
            this.$message.success("标签列表刷新成功");
          } else {
            this.$message.error("标签列表刷新发生错误");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    //显示状态 控制样式
    showStatetype(state) {
      if (state == 0) {
        return "info";
      }
      if (state == 1) {
        return "success";
      }
    },
    showStateicon(state) {
      if (state == 0) {
        return "el-icon-close";
      }
      if (state == 1) {
        return "el-icon-check";
      }
    },
    //区分完成及未完成任务
    OkAndNoTask(state) {
      this.showTable = false;
      if (state == 1) {
        this.OkTable = true;
        this.NoTable = false;
      } else if (state == 0) {
        this.NoTable = true;
        this.OkTable = false;
      } else {
        this.showTable = true;
        this.NoTable = false;
        this.OkTable = false;
      }
    },

    //日期提取
    dateChangebirthday1(val) {
      this.Time = val;
      // console.log(this.Time[0])
      this.todoList.startTime = this.Time[0];
      this.todoList.endTime = this.Time[1];
    },
    //点击完成
    Accomplish(tid) {
      // this.$message.success("恭喜您完成了这个任务");
      todoListSave(tid)
        .then((res) => {
          if (res.success) {
            //重新查询页面数据
            this.$message.success("恭喜您完成了这个任务");
            //重新查询页面任务
            this.getListData();
          } else {
            this.$message.error("完成任务失败 你仔细想想");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    //点击删除
    Delete(tid) {
      this.$message.success("删除成功");
      todoListDelete(tid)
        .then((res) => {
          if (res.success) {
            //重新查询页面数据
            this.$message.success("删除成功");
            this.getListData();
          } else {
            this.$message.error("想删掉你干的那点好事？做梦！");
          }
        })
        .catch((err) => {
          this.$message.error("请求失败，请稍后再试！");
        });
    },
    //增加任务
    ListAdd() {
      //判断表单是否填写完成
      if (
        this.todoList.content == "" ||
        this.todoList.name == "" ||
        this.todoList.startTime == "" ||
        this.todoList.endTime == ""
      ) {
        this.$message.error("您还有信息没填写哦，请重新填写！");
        return;
      } else {
        console.log(this.todoList);
        this.dialogVisible = false;
        todoListAdd(todoList)
          .then((res) => {
            if (res.success) {
              //查询页面数据
              this.getListData();
              this.$message.success("任务列表刷新成功");
              this.todoList.content == "";
              this.todoList.name == "";
              this.todoList.startTime == "";
              this.todoList.endTime == "";
            } else {
              this.$message.error("列表刷新发生错误");
            }
          })
          .catch((err) => {
            this.$message.error("请求失败，请稍后再试！");
            this.todoList.content == "";
            this.todoList.name == "";
            this.todoList.startTime == "";
            this.todoList.endTime == "";
          });
      }
    },
    //倒计时功能
    countDown(date1) {
      //结束时间
      var dateTwo = new Date(date1);
      //当前时间
      var dateOne = new Date();
      var Year1 = dateOne.getFullYear();
      var Year2 = dateTwo.getFullYear();
      var Mouth1 = dateOne.getMonth() + 1;
      var Mouth2 = dateTwo.getMonth() + 1;
      var day1 = dateOne.getDate();
      var day2 = dateTwo.getDate();
      var lastYear = Year2 - Year1;
      if (Mouth2 < Mouth1) {
        var lastMouth = 12 - Mouth1 + Mouth2;
      } else {
        var lastMouth = Mouth2 - Mouth1;
      }
      var lastDay = day2 - day1;
      // alert((lastYear*365+lastMouth*30+lastDay)+"天")
      var lastTime;
      lastTime = lastYear * 365 + lastMouth * 30 + lastDay;
      if (lastTime == 0 || lastTime < 0) {
        lastTime = "时间已经用完啦";
      } else {
        lastTime = lastYear * 365 + lastMouth * 30 + lastDay + "天";
      }
      return lastTime;
    },
    formatter(row, column) {
      return row.task.endTime;
    },
    filterTag(value, row) {
      return row.name === value;
    },
    //关闭添加待办框
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
  },
};
</script>

<style scoped>
.el-input {
  width: 300px;
}
.icon {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  height: 100%;
}
.clickButton {
  display: flex;
  width: 100%;
  justify-content: flex-start;
  margin: 30px 0px;
}
</style>