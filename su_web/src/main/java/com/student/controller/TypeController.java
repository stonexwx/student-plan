package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.student.biz.TypeService;
import com.student.entity.PageRequest;
import com.student.entity.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 标签表(Type)表控制层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@RestController
@RequestMapping("type")
public class TypeController {
    /**
     * 服务对象
     */
    @Resource
    private TypeService typeService;

    /**
     * 分页查询
     *
     * @param type 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("select_all")
    public String queryByPage(Type type, PageRequest pageRequest) {
        return JSON.toJSONString(this.typeService.queryByPage(type, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Type> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.typeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param type 实体
     * @return 新增结果
     */
    @PostMapping("insert")
    public String add(Type type) {
        return JSON.toJSONString(this.typeService.insert(type));
    }

    /**
     * 编辑数据
     *
     * @param type 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Type> edit(Type type) {
        return ResponseEntity.ok(this.typeService.update(type));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @PostMapping("delete")
    public String deleteById(Long id) {
        return JSON.toJSONString(this.typeService.deleteById(id));
    }

}

