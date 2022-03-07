package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.student.biz.FirstTypeService;
import com.student.entity.FirstType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 一级标题(FirstType)表控制层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@RestController
@RequestMapping("firstType")
public class FirstTypeController {
    /**
     * 服务对象
     */
    @Resource
    private FirstTypeService firstTypeService;

    /**
     * 分页查询
     *
     * @param firstType 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @GetMapping
//    public ResponseEntity<Page<FirstType>> queryByPage(FirstType firstType, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.firstTypeService.queryByPage(firstType, pageRequest));
//    }

    /**
     * 联级查询
     */
    @PostMapping("selectAll")
    public String queryAll(){
        return JSON.toJSONString(this.firstTypeService.queryAll());
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<FirstType> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.firstTypeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param firstType 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<FirstType> add(FirstType firstType) {
        return ResponseEntity.ok(this.firstTypeService.insert(firstType));
    }

    /**
     * 编辑数据
     *
     * @param firstType 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<FirstType> edit(FirstType firstType) {
        return ResponseEntity.ok(this.firstTypeService.update(firstType));
    }

    /**
     * 删除数据
     *
     * @param fid 主键
     * @return 删除是否成功
     */
    @GetMapping("/delete")
    public String deleteById(Long fid) {
        return JSON.toJSONString(this.firstTypeService.deleteById(fid));
    }

}

