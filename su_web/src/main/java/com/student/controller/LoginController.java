package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.student.biz.UsersService;
import com.student.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UsersService usersService;
    @PostMapping
    public String login(Users users){
        return JSON.toJSONString(this.usersService.queryByPhoneAndPassword(users));
    }
}
