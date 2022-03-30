// 导入组件
import Vue from 'vue';
import Router from 'vue-router';
// 登录
import login from '@/views/login';
// 首页
import index from '@/views/index';
/**
 * 基础菜单
 */
// 任务管理
import Tasks from '@/views/Tasks/Tasks';
// 标签管理
import Tag from '@/views/Tasks/Tag';


// 题库分类
import Test from '@/views/pay/Test';
//题库
import Exercise from '@/views/pay/Exercise';
//课程
import Course from '@/views/pay/Course';
//课程分类
import GoCourse from '@/views/pay/GoCourse';
//笔记
import Note from '@/views/LearnHelp/Note';
import Editor from '@/views/LearnHelp/Editor';
//资料
import Help from '@/views/LearnHelp/Help';
import RouterHelp from '@/views/LearnHelp/RouterHelp';
import RouterNote from '@/views/LearnHelp/RouterNote';
/**
 * 系统管理
 */
// 用户管理
import user from '@/views/system/user';
import userPassword from '@/views/system/userPassword';
//管理员用户管理
import userManage from '@/views/system/userManage';
import TagManage from '@/views/Tasks/TagManage';
import ManUpload from '@/views/pay/ManUpload';
import ManExercise from '@/views/pay/ManExercise';
// 图表界面
import statistics from '@/views/charts/statistics';

// 启用路由
Vue.use(Router);

// 导出路由
export default new Router({
    routes: [{
        path: '/',
        name: '',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/login',
        name: '登录',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/index',
        name: '首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/Tasks/Tasks',
            name: '任务管理',
            component: Tasks,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/Tasks/Tag',
            name: '标签管理',
            component: Tag,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/pay/GoCourse',
            name: '课程分类',
            component: GoCourse,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/pay/Course',
            name: '课程',
            component: Course,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/pay/Test',
            name: '题库分类',
            component: Test,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/pay/Exercise/',
            name: '题库',
            component: Exercise,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/system/user',
            name: '用户管理',
            component: user,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/system/userPassword',
            name: '修改密码',
            component: userPassword,
            meta: {
                requireAuth: true
            }
        },
          {
            path: '/LearnHelp/RouterHelp',
            name: '笔记资料分类',
            component: RouterHelp,
            meta: {
              requireAuth: true
            }
          },
          {
            path: '/LearnHelp/RouterNote',
            name: '笔记资料分类2',
            component: RouterNote,
            meta: {
              requireAuth: true
            }
          },
          {
            path: '/LearnHelp/Editor',
            name: '添加',
            component: Editor,
            meta: {
              requireAuth: true
            }
          },
        {
            path: '/LearnHelp/Note',
            name: '学习笔记',
            component: Note,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/LearnHelp/Help',
            name: '学习资料',
            component: Help,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/charts/statistics',
            name: '数据可视化',
            component: statistics,
            meta: {
                requireAuth: true
            }
        },{
            path: '/system/userManage',
            name: '管理员版本用户管理',
            component: userManage,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/Tasks/TagManage',
            name: '管理员版本标签',
            component: TagManage,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/pay/ManExercise',
            name: '管理员版本题库',
            component: ManExercise,
            meta: {
                requireAuth: true
            }
        },
        {
            path: '/pay/ManUpload',
            name: '管理员版本上传题库',
            component: ManUpload,
            meta: {
                requireAuth: true
            }
        },
        ]
    }]
})
