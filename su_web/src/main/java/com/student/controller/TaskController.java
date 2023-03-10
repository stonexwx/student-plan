package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.student.biz.TaskService;
import com.student.entity.PageRequest;
import com.student.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 任务表(Task)表控制层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@RestController
@RequestMapping("task")
public class TaskController {
    /**
     * 服务对象
     */
    @Resource
    private TaskService taskService;

    /**
     * 分页查询
     *
     * @param task 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("select_id")
    public String queryByPage(Task task, PageRequest pageRequest) {
        return JSON.toJSONString(this.taskService.queryByTid(task, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Task> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.taskService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param task 实体
     * @return 新增结果
     */
    @PostMapping("insert")
    public String add(Task task,Long id) {
        return JSON.toJSONString(this.taskService.insert(task,id));
    }

    /**
     * 编辑数据
     *
     * @param task 实体
     * @return 编辑结果
     */
    @PostMapping ("update")
    public String edit(Task task) {
        return JSON.toJSONString(this.taskService.update(task));
    }

    /**
     * 删除数据
     *
     * @param tid 主键
     * @return 删除是否成功
     */
    @PostMapping ("delete")
    public String deleteById(Long tid) {
        return JSON.toJSONString(this.taskService.deleteById(tid));
    }

}

