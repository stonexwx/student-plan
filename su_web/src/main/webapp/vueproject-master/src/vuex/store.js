import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
// 登录验证
export default new Vuex.Store({
    state: {
        user: false,
      options:[],
      content:"",
      userTag:{},
    },
  getters: {
    // 参数列表state指的是state数据
    getOptions(state) {
      return state.options;
    },
    getContent(state) {
      return state.content;
    },
    getUserTag(state) {
      return state.userTag;
    }
  },
    mutations: {
        // 登录
        login(state, user) {
            state.user = user;
            localStorage.setItem("userInfo", user);
        },
        // 退出
        logout(state, user) {
            state.user = "";
            localStorage.setItem("userInfo", "");
        },
      setCity(state, options){
        state.options = options;//将传参设置给state的city
      },
      setContent(state, content){
        state.content = content;
      },
      setUserTag(state,tableData){
          state.userTag = tableData
      }
    }
})
