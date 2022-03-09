<template>
  <div class="home">
    <el-collapse v-model="activeName" @change.once="TagsList">
      <el-collapse-item title="添加笔记" name="1">
      <div id="demo1" ></div>

      <div class="Notefooter">
        <el-select v-model="value" placeholder="请选择标签" >
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
        <el-button type="primary" @click="submit()">点击提交笔记</el-button>
      </div>
      </el-collapse-item>
    </el-collapse>

  </div>
</template>

<script>

// 引入 wangEditor
import wangEditor from 'wangeditor'
import {NoteAndHelpInsert} from "../../api/basisMG";

export default {
  data() {
    return {
      editor: null,
      editorData: '',
      value:"",
      activeName:"0"

    }
  },
  props:['options','getNoteData','TagsList','pagination'],
  mounted() {
    const editor = new wangEditor(`#demo1`)

    // 配置 onchange 回调函数，将数据同步到 vue 中
    editor.config.onchange = (newHtml) => {
      this.editorData = newHtml
    }
    editor.config.uploadImgServer = 'http://localhost:8080/information/upload'
    editor.config.uploadFileName = 'files'
    editor.config.uploadImgMaxLength = 1
    editor.config.withCredentials = true
  // 创建编辑器
    editor.create()
    this.editor = editor
  },
  methods: {

    submit(){
      const userdata = localStorage.getItem("userdata");
      let json = JSON.parse(userdata);
      let data1={
        "uid":json.uid,
        "content":this.editorData,
        "id":this.value,
        "typeId":"1"
      }
      NoteAndHelpInsert(data1)
      .then((res)=>{
          if(res){
            this.$message.success("保存成功");
            const userdata = localStorage.getItem("userdata");
            let data1={
              "typeId":"1",
              "uid":JSON.parse(userdata).uid,
              "page":this.$props.pagination.currentPage,
              "limit":this.$props.pagination.size
            }
            this.$props.getNoteData(data1)
          }else {
            this.$message.error("保存失败");
          }
        })
      .cache((err)=>{
        this.$message.error("请求失败，请稍后再试！");
      })
    }
  },
  beforeDestroy () {
    // 调用销毁 API 对当前编辑器实例进行销毁
    this.editor.destroy()
    this.editor = null
  }
}
</script>

<style lang="css">

</style>
