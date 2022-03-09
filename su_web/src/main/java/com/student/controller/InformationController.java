package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.student.biz.InformationService;
import com.student.entity.Information;
import com.student.entity.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 信息表(Information)表控制层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@RestController
@RequestMapping("information")
public class InformationController {
    /**
     * 服务对象
     */
    @Resource
    private InformationService informationService;

    /**
     * 分页查询
     *
     * @param information 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("select_all")
    public String queryByPage(Information information, PageRequest pageRequest) {
        return JSON.toJSONString(this.informationService.queryByPage(information, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Information> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.informationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param information 实体
     * @return 新增结果
     */
    @PostMapping("insert")
    public String add(Information information) {
        return JSON.toJSONString(this.informationService.insert(information));
    }

    /**
     * 编辑数据
     *
     * @param information 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Information> edit(Information information) {
        return ResponseEntity.ok(this.informationService.update(information));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.informationService.deleteById(id));
    }

}

