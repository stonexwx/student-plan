package com.student.controller;

import com.student.biz.TypeMapperService;
import com.student.entity.TypeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 类型映射(TypeMapper)表控制层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@RestController
@RequestMapping("typeMapper")
public class TypeMapperController {
    /**
     * 服务对象
     */
    @Resource
    private TypeMapperService typeMapperService;

    /**
     * 分页查询
     *
     * @param typeMapper 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<TypeMapper>> queryByPage(TypeMapper typeMapper, PageRequest pageRequest) {
        return ResponseEntity.ok(this.typeMapperService.queryByPage(typeMapper, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TypeMapper> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.typeMapperService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param typeMapper 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TypeMapper> add(TypeMapper typeMapper) {
        return ResponseEntity.ok(this.typeMapperService.insert(typeMapper));
    }

    /**
     * 编辑数据
     *
     * @param typeMapper 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TypeMapper> edit(TypeMapper typeMapper) {
        return ResponseEntity.ok(this.typeMapperService.update(typeMapper));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.typeMapperService.deleteById(id));
    }

}

