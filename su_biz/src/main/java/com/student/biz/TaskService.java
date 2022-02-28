package com.student.biz;

import com.student.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 任务表(Task)表服务接口
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface TaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param tid 主键
     * @return 实例对象
     */
    Task queryById(Long tid);

    /**
     * 分页查询
     *
     * @param task 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Task> queryByPage(Task task, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    Task insert(Task task);

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    Task update(Task task);

    /**
     * 通过主键删除数据
     *
     * @param tid 主键
     * @return 是否成功
     */
    boolean deleteById(Long tid);

}
