/**这是个测试界面 不要管 */

<template>
  <div class="block">
    <!-- 时间提取测试
    <el-date-picker
      v-model="value6"
      @change="dateChangebirthday1"
      type="datetimerange"
      format="yyyy-MM-dd HH:mm"
      value-format="yyyy-MM-dd HH:mm"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      :default-time="['12:00:00']"
    ></el-date-picker> -->

    <!-- 分类组件测试
    <title-one :Title="array"></title-one>
    <el-divider></el-divider> -->

    <!-- 笔记 资料部分测试 -->
    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="center" class="demo-table-expand">
            <el-form-item label="所属标签">
              <el-tag type="success"
                ><span>{{ props.row.name }}</span></el-tag
              >
            </el-form-item>
            <el-form-item label="笔记详情">
              <span style="color: #5aacff; font-weight: bold">{{
                props.row.content
              }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="所属标签" prop="name" width="200">
      </el-table-column>
      <el-table-column
        label="笔记详情"
        prop="content"
        :show-overflow-tooltip="true"
      ></el-table-column>
    </el-table>

    <el-table :data="tableData2" style="width: 100%; margin-top: 40px">
      <el-table-column prop="id" label="课程id" width="80"> </el-table-column>
      <el-table-column prop="name" label="课程名" width="380">
      </el-table-column>
      <el-table-column prop="content" label="课程简介" width="580">
      </el-table-column>
      <el-table-column label="操作">
        <el-button type="danger" @click="deleteCourse(scope.row.id)"
          >点击删除</el-button
        >
        <el-button type="success">点击上课</el-button>
      </el-table-column>
    </el-table>

    <el-table :data="tableData3" style="width: 100%; margin-top: 40px">
      <el-table-column prop="id" label="日期" width="80"> </el-table-column>
      <el-table-column prop="name" label="姓名" width="180"> </el-table-column>
      <el-table-column prop="content" label="地址"> </el-table-column>
      <el-table-column label="操作"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
import TitleOne from "../../components/TitleOne.vue";

export default {
  components: { TitleOne },
  data() {
    return {
      // value6: "",
      // startTime: "",
      // array: [
      //   {
      //     fid: "1",
      //     title: "文",
      //     child: [
      //       {
      //         sid: "1",
      //         content: "语文",
      //       },
      //       {
      //         sid: "2",
      //         content: "地理",
      //       },
      //     ],
      //   },
      // ],
      tableData: [
        {
          name: "语文",
          content: "春水碧于天,画船听雨眠,垆边人似月，皓腕凝霜雪。",
        },
        {
          name: "数学",
          content:
            "数学题是透过抽象化和逻辑推理的使用，由计数、计算、量度和对物体形状及运动的观察中产生的。数学题大致可分为填空题、判断题、选择题、计算题、应用题、证明题、作图题、思考题、阅读题、规律题、解答题。熟练地解题要靠平时的学习知识来灵活运用。",
        },
        {
          name: "英语",
          content:
            "One reason is that Americans view business as being more firmly based on the ideal of competition than other institutions in society. Since competition is seen as the major source of progress and prosperity by most Americans, competitive business institutions are respected. Competition is not only good in itself, it is the means by which other basic American values such as individual freedom, equality of opportunity, and hard work are protected.",
        },
      ],
      tableData2: [
        {
          id: "1",
          name: "毛泽东思想与中国特色社会主义思想理论体系概论",
          content:
            "毛泽东思想是马克思列宁主义基本原理和中国革命具体实际相结合的产物，是中国共产党人集体智慧的结晶。是由毛泽东倡导并在二十世纪中国革命中大范围实践的一种政治、军事、发展理论，一般认为其为马列主义在中国的发展。",
        },
        {
          id: "4",
          name: "零基础手绘-插画培训火星时代专业教育28年",
          content:
            "零基础手绘 插画培训 火星时代学习全面的技能,参与综合实训项目,增强学员竞争能力,适应于19-45岁",
        },
        {
          id: "2",
          name: "剪辑怎么学习-视频剪辑教程-必学变现技巧",
          content:
            "剪辑怎么学习,针对19-45岁人群,从入门到精通项目实战教学,贴合企业用人需求,全国22所直营分校,就近学习,高薪就业,",
        },
      ],
      tableData3: [
        {
          id: "1",
          name: "毛泽东思想与中国特色社会主义思想理论体系概论",
          content:
            "毛泽东思想是马克思列宁主义基本原理和中国革命具体实际相结合的产物，是中国共产党人集体智慧的结晶。是由毛泽东倡导并在二十世纪中国革命中大范围实践的一种政治、军事、发展理论，一般认为其为马列主义在中国的发展。",
        },
        {
          id: "4",
          name: "零基础手绘-插画培训火星时代专业教育28年",
          content:
            "零基础手绘 插画培训 火星时代学习全面的技能,参与综合实训项目,增强学员竞争能力,适应于19-45岁",
        },
        {
          id: "2",
          name: "剪辑怎么学习-视频剪辑教程-必学变现技巧",
          content:
            "剪辑怎么学习,针对19-45岁人群,从入门到精通项目实战教学,贴合企业用人需求,全国22所直营分校,就近学习,高薪就业,",
        },
      ],
    };
  },
  methods: {
    dateChangebirthday1(val) {
      this.startTime = val;
      console.log(this.value6);
      //   console.log(this.startTime);
      //   console.log(this.startTime[0]);
    },
  },
};
</script>
<style scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.demo-table-expand .el-form-item[data-v-0911b4fb] {
  width: 100%;
}
</style>