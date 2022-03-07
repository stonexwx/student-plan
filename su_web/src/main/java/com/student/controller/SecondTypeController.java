package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.student.biz.SecondTypeService;
import com.student.entity.SecondType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 二级标题(SecondType)表控制层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@RestController
@RequestMapping("secondType")
public class SecondTypeController {
    /**
     * 服务对象
     */
    @Resource
    private SecondTypeService secondTypeService;

    /**
     * 分页查询
     *
     * @param secondType 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @GetMapping
//    public ResponseEntity<Page<SecondType>> queryByPage(SecondType secondType, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.secondTypeService.queryByPage(secondType, pageRequest));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SecondType> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.secondTypeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param secondType 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<SecondType> add(SecondType secondType) {
        return ResponseEntity.ok(this.secondTypeService.insert(secondType));
    }

    /**
     * 编辑数据
     *
     * @param secondType 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<SecondType> edit(SecondType secondType) {
        return ResponseEntity.ok(this.secondTypeService.update(secondType));
    }

    /**
     * 删除数据
     *
     * @param sid 主键
     * @return 删除是否成功
     */
    @GetMapping("delete")
    public String deleteById(Long sid) {
        return JSON.toJSONString(this.secondTypeService.deleteById(sid));
    }

}

