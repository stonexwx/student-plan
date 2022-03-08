package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.student.biz.UsersService;
import com.student.entity.PageRequest;
import com.student.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户表(Users)表控制层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@RestController
@RequestMapping("users")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;

    /**
     * 分页查询
     *
     * @param users 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("select_all")
    public String queryByPage(Users users, PageRequest pageRequest) {
        return JSON.toJSONString(this.usersService.queryByPage(users, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param uid 主键
     * @return 单条数据
     */
    @GetMapping("select_id")
    public String queryById(Long uid) {
        return JSON.toJSONString(this.usersService.queryById(uid));
    }

    /**
     * 新增数据
     *
     * @param users 实体
     * @return 新增结果
     */
    @PostMapping("insert")
    public String add(Users users) {
        return JSON.toJSONString(this.usersService.insert(users));
    }

    /**
     * 编辑数据
     *
     * @param users 实体
     * @return 编辑结果
     */
    @PostMapping("update")
    public String edit(Users users) {
        Map<String,Object> map = new HashMap<>();
        map.put("data",this.usersService.update(users));
        return JSON.toJSONString(map);
    }

    /**
     * 删除数据
     *
     * @param uid 主键
     * @return 删除是否成功
     */
    @PostMapping("delete")
    public String deleteById(Long uid) {
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("flag",this.usersService.deleteById(uid));
        return jsonObject.toJSONString();
    }

}

