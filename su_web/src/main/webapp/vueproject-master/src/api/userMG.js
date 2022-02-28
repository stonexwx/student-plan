import axios from 'axios';
import { loginreq, req } from './axiosFun';

// 登录接口 
export const login = (params) => { return loginreq("post", "/api/login", params) };
// 获取用户菜单
export const menu = (params) => { return axios.get("/api/menu?&token=" + localStorage.getItem('logintoken')).then(res => res.data) };
// 退出接口
export const loginout = () => { return axios.delete("/api/login?&token=" + localStorage.getItem('logintoken')).then(res => res.data) };

/**
 * 用户管理 
 **/
// 用户管理-获取用户列表(管理员)
export const userList = () => { return req("post", "/users/select_all") };
// 用户管理-获取用户信息
export const userMessage = (params) => { return req("post", "/users/select_id", params) };
// 用户管理-保存
export const userSave = (params) => { return req("post", "/users/update", params) };
// 用户管理-删除用户(管理员)
export const userDelete = (params) => { return axios.delete("/api/User/delete", params) };
// 用户管理-修改密码
export const userPwd = (params) => { return req("post", "/users/update", params) };