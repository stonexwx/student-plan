package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.student.biz.AdminTypeService;
import com.student.biz.FirstTypeService;
import com.student.biz.SecondTypeService;
import com.student.entity.SecondType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin_type")
public class AdminTypeController {
    @Autowired
    private FirstTypeService firstTypeService;
    @Autowired
    private SecondTypeService secondTypeService;
    @Autowired
    private AdminTypeService adminTypeService;

    @RequestMapping("insert")
    public String typeInsert(String firstType, String[] content) {

        List<SecondType> secondType = new ArrayList<>();
        for (String str1 : content) {
            SecondType secondType1 = new SecondType();
            secondType1.setContent(str1);
            secondType.add(secondType1);
        }
        return JSON.toJSONString(adminTypeService.queryByName(firstType, secondType));
    }
}
