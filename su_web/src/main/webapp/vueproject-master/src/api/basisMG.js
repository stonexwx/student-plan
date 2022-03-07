import axios from 'axios';
import { req } from './axiosFun';

/**
 * 任务管理
 **/
// 任务管理-获取任务列表
export const todoList = (params) => { return req("post", "/api/Goods/list", params) };
// 任务管理-增加任务
export const todoListAdd = (params) => { return req("post", "/api/Goods/save", params) };
// 任务管理-点击删除任务
export const todoListDelete = (params) => { return axios.delete("/api/Goods/delete?ids=" + params + "&token=" + localStorage.getItem('logintoken')).then(res => res.data) };
// 任务管理-点击完成任务
export const todoListSave = (params) => { return req("post", "/api/Goods/save", params) };


/**
 * 标签管理
 **/
// 标签管理-获取所有标签
export const TagsList = (params) => { return req("post", "/type/select_all", params) };
//标签管理-增加标签
export const TagAdd = (params) => { return req("post", "/type/insert", params) };
//标签管理-删除标签
export const TagDel = (params) => { return req("get", "/type/delete", params) };
export const TagDelAdmin = (params) => { return req("get", "/type/delete", params) };
//标签管理-增加标签(管理员)
export const ManTagAdd = (params) => { return req("post", "/admin_type/insert", params) };

/**
 *学习管理
 **/
// 标签管理-获取所有一级二级标签
export const TitleList = (params) => { return req("post", "/firstType/selectAll",params) };
// 题库管理-获取标签下题目
export const TestList = (params) => { return req("post", "/firstType/selectAll",params) };
// 题库管理-删除题库(管理员)
export const TestDel = (params) => { return req("post", "/firstType/selectAll",params) };
// 题库管理-上传题库(管理员)
export const TestUpload = (params) => { return req("post", "/firstType/selectAll",params) };
// 课程管理-获取标签下所有课程
export const CourseList = (params) => { return req("post", "/firstType/selectAll",params) };
// 课程管理-删除课程(管理员)
export const CourseDel = (params) => { return req("post", "/firstType/selectAll",params) };
/**
 *学习管理
 **/
// 学习辅助-获取所有笔记资料
 export const NoteAndHelpList = (params) => { return req("post", "/firstType/selectAll",params) };
// 学习辅助-删除笔记资料
export const NoteAndHelpDel = (params) => { return req("post", "/firstType/selectAll",params) };

//数据可视化
export const Statistics = (params) => { return req("post", "/firstType/selectAll",params) };
