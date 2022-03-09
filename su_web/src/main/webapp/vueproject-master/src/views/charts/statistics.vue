/**
* 图表界面
*/
<template>
  <!-- 组件主盒子 -->
  <div class="stbox">
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>数据可视化</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 统计图 -->
    <div>
      <el-row :gutter="23">
        <div id="main" style="width: 100%; height: 500px"></div>
      </el-row>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
import Chart from "echarts";
import { Statistics } from "../../api/basisMG";
export default {
  name: "welcome",
  data() {
    return {
      machineNo: "",
      type: "day",
      option: {
        tooltip: {
          trigger: "item",
        },
        legend: {
          top: "5%",
          left: "center",
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: ["40%", "70%"],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: "#fff",
              borderWidth: 2,
            },
            label: {
              show: false,
              position: "center",
            },
            emphasis: {
              label: {
                show: true,
                fontSize: "40",
                fontWeight: "bold",
              },
            },
            labelLine: {
              show: false,
            },
            data: [
              { value: 1, name: "已完成任务" },
              { value: 1, name: "未完成任务" },
            ],
          },
        ],
      },
    };
  },
  // 创建完毕状态(里面是操作)
  created() {
    //获取数据
    const userdata = localStorage.getItem("userdata");
    let uid = JSON.parse(userdata).uid
    let data={
      "uid":uid
    }
    Statistics(data)
      .then((res) => {
        if (res) {
          let a = res.data
          let b=0;
          let c=0;
          for(var i=0;i<a.length;i++){
            if(a[i].task.state == 0){
              b++
            }else {
              c++
            }
          }
          console.log(this.option.series[0].data[0])
          this.option.series[0].data[0].value=c
          this.option.series[0].data[1].value=b
          this.$message.success("数据可视化刷新成功");
          this.drawLine()
        } else {
          this.$message.error("数据可视化刷新失败!");
        }
      })
      .catch((err) => {
        this.$message.error("请求失败，请稍后再试！");
      });
  },
  // 挂载结束状态(里面是操作)
  // mounted() {
  //   this.drawLine();
  // },
  // 里面的函数只有调用才会执行
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = Chart.init(document.getElementById("main"));
      // 绘制图表
      myChart.setOption(this.option);
    },
  },
};
</script>
<style>
.stbox {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}
.stbgc {
  background-color: #fff;
  height: 60px;
  line-height: 60px;
  border-radius: 5px;
  padding: 0px 16px;
}
.stsearch {
  text-align: center;
}
.text-c {
  text-align: center;
}
.st-gbox {
  background-color: #fff;
  margin-top: 20px;
  border-radius: 5px;
  height: 30vh;
  box-sizing: border-box;
  padding: 10px;
}
.cavasbox {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
}
.paybox {
  width: 100%;
  background-color: #fff;
  box-sizing: border-box;
  border-radius: 5px;
  margin-top: 20px;
  height: 32vh;
}
</style>
