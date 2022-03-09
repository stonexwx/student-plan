package com.student.biz;

import com.student.entity.PageRequest;
import com.student.entity.Task;

import java.util.Map;

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
     * @param task        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Map<String,Object> queryByPage(Task task, PageRequest pageRequest);
    /**
     *标签任务综合查询
     * @return
     */
    Map<String,Object> queryByTid(Task task, PageRequest pageRequest);
    /**
     * 新增数据
     *
     * @param task 实例对象
     * @param id
     * @return 实例对象
     */
    Task insert(Task task,Long id);

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
    Map<String,Object> deleteById(Long tid);

}
