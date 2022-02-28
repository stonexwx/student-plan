package com.student.biz.impl;

import com.student.biz.TaskService;
import com.student.dao.mapper.TaskDao;
import com.student.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 任务表(Task)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskDao taskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param tid 主键
     * @return 实例对象
     */
    @Override
    public Task queryById(Long tid) {
        return this.taskDao.queryById(tid);
    }

    /**
     * 分页查询
     *
     * @param task 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Task> queryByPage(Task task, PageRequest pageRequest) {
        long total = this.taskDao.count(task);
        return new PageImpl<>(this.taskDao.queryAllByLimit(task, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public Task insert(Task task) {
        this.taskDao.insert(task);
        return task;
    }

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public Task update(Task task) {
        this.taskDao.update(task);
        return this.queryById(task.getTid());
    }

    /**
     * 通过主键删除数据
     *
     * @param tid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long tid) {
        return this.taskDao.deleteById(tid) > 0;
    }
}
