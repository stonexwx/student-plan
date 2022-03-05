package com.student.biz.impl;

import com.student.biz.TaskService;
import com.student.dao.mapper.TaskDao;
import com.student.dao.mapper.TypeMapperDao;
import com.student.dto.TaskDTO;
import com.student.entity.PageRequest;
import com.student.entity.Task;
import com.student.entity.TypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private TypeMapperDao typeMapperDao;
    @Resource
    HashMap<String,Object> map;
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
     * @param task        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Map<String,Object> queryByPage(Task task, PageRequest pageRequest) {
        long total = this.taskDao.count(task);
        map.put("task",this.taskDao.queryAllByLimit(task, pageRequest));
        map.put("total",total);
        return map;
    }

    /**
     * 标签任务综合查询
     *  @param task
     * @param pageRequest
     * @return
     */
    @Override
    public List<TaskDTO> queryByTid(Task task, PageRequest pageRequest) {
        return taskDao.queryAllById(task,pageRequest);
    }

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @param id
     * @return 实例对象
     */
    @Override
    public Task insert(Task task, Long id) {
        long tid =this.taskDao.insert(task);
        TypeMapper typeMapper =new TypeMapper();
        typeMapper.setTypeId(id);
        typeMapper.setTid(task.getTid());
        typeMapperDao.insert(typeMapper);
        return this.taskDao.queryById(tid);
    }

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public Task update(Task task) {
        task.setState("0");
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
    public Map<String, Object> deleteById(Long tid) {
        map.put("flag",this.taskDao.deleteById(tid) > 0);
        return map;
    }
}
