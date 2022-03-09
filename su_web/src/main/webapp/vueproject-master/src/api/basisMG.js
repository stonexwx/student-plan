import axios from 'axios';
import {req, upload} from './axiosFun';

/**
 * 任务管理
 **/
// 任务管理-获取任务列表
export const todoList = (params) => { return req("post", "http://localhost:8080/task/select_id", params) };
// 任务管理-增加任务
export const todoListAdd = (params) => { return req("post", "http://localhost:8080/task/insert", params) };
// 任务管理-点击删除任务
export const todoListDelete = (params) => { return req("post","http://localhost:8080/task/delete",params ) };
// 任务管理-点击完成任务
export const todoListSave = (params) => { return req("post", "http://localhost:8080/task/update", params) };


/**
 * 标签管理
 **/
// 标签管理-获取所有标签
export const TagsList = (params) => { return req("post", "http://localhost:8080/type/select_all", params) };
//标签管理-增加标签
export const TagAdd = (params) => { return req("post", "http://localhost:8080/type/insert", params) };
//标签管理-删除标签
export const TagDel = (params) => { return req("post", "http://localhost:8080/type/delete", params) };
export const TagDelAdmin = (params) => { return req("post", "http://localhost:8080/secondType/delete", params) };
//标签管理-增加标签(管理员)
export const ManTagAdd = (params) => { return req("post", "http://localhost:8080/admin_type/insert", params) };

/**
 *学习管理
 **/
// 标签管理-获取所有一级二级标签
export const TitleList = (params) => { return req("post", "http://localhost:8080/firstType/selectAll",params) };
// 题库管理-获取标签下题目
export const TestList = (params) => { return req("post", "http://localhost:8080/practice/select_all",params) };
// 题库管理-删除题库(管理员)
export const TestDel = (params) => { return req("post", "http://localhost:8080/practice/delete",params) };
// 题库管理-上传题库(管理员)
export const TestUpload = (params) => { return req("post", "http://localhost:8080/practice/insert",params) };
// 课程管理-获取标签下所有课程
export const CourseList = (params) => { return req("post", "http://localhost:8080/course/select_all",params) };
// 课程管理-删除课程(管理员)
export const CourseDel = (params) => { return req("post", "http://localhost:8080/course/delete",params) };
export const CourseInsert = (params) => { return req("post", "http://localhost:8080/course/insert",params) };
/**
 *学习管理
 **/
// 学习辅助-获取所有笔记资料
 export const NoteAndHelpList = (params) => { return req("post", "http://localhost:8080/information/select_all",params) };
// 学习辅助-删除笔记资料
export const NoteAndHelpDel = (params) => { return req("post", "http://localhost:8080/information/delete",params) };
export const NoteAndHelpInsert = (params) => { return req("post", "http://localhost:8080/information/insert",params) };
//数据可视化
export const Statistics = (params) => { return req("post", "http://localhost:8080/task/select_id",params) };
