package com.student.controller;

import com.student.biz.PracticeService;
import com.student.entity.Practice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @GetMapping
    public ResponseEntity<Page<Practice>> queryByPage(Practice practice, PageRequest pageRequest) {
        return ResponseEntity.ok(this.practiceService.queryByPage(practice, pageRequest));
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
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Practice> add(Practice practice) {
        return ResponseEntity.ok(this.practiceService.insert(practice));
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
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.practiceService.deleteById(id));
    }

}

