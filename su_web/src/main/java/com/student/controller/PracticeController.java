package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.student.biz.PracticeService;
import com.student.entity.PageRequest;
import com.student.entity.Practice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 练习资料表(Practice)表控制层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@RestController
@RequestMapping("practice")
public class PracticeController {
    /**
     * 服务对象
     */
    @Resource
    private PracticeService practiceService;

    /**
     * 分页查询
     *
     * @param practice 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("select_all")
    public String queryByPage(Practice practice, PageRequest pageRequest) {
        return JSON.toJSONString(this.practiceService.queryByPage(practice, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Practice> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.practiceService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param practice 实体
     * @param flag
     * @return 新增结果
     */
    @PostMapping("insert")
    public String add(Practice practice,Boolean flag) {
        return JSON.toJSONString(this.practiceService.insert(practice, flag));
    }

    /**
     * 编辑数据
     *
     * @param practice 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Practice> edit(Practice practice) {
        return ResponseEntity.ok(this.practiceService.update(practice));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @PostMapping("delete")
    public String deleteById(Long id) {
        return JSON.toJSONString(this.practiceService.deleteById(id));
    }

}

