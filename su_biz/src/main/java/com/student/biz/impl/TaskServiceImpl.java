package com.student.biz.impl;

import com.student.biz.TaskService;
import com.student.dao.mapper.TaskDao;
import com.student.dao.mapper.TypeMapperDao;
import com.student.entity.PageRequest;
import com.student.entity.Task;
import com.student.entity.TypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
        if(String.valueOf(pageRequest.getPage())!=null || String.valueOf(pageRequest.getPage())!=""){
            pageRequest.setPage(1);
            pageRequest.setLimit((int) total);
        }
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
    public Map<String, Object> queryByTid(Task task, PageRequest pageRequest) {
        long total = taskDao.count(task);
        if(String.valueOf(pageRequest.getPage())!=null || String.valueOf(pageRequest.getPage())!=""){
            pageRequest.setPage(1);
            pageRequest.setLimit((int) total);
        }
        map.put("count",total);
        map.put("data",taskDao.queryAllById(task,pageRequest));
        return map;
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
        this.taskDao.insert(task);
        TypeMapper typeMapper =new TypeMapper();
        typeMapper.setTypeId(id);
        typeMapper.setTid(task.getTid());
        typeMapperDao.insert(typeMapper);
        return this.taskDao.queryById(task.getTid());
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
    public Map<String, Object> deleteById(Long tid) {
        map.put("flag",this.taskDao.deleteById(tid) > 0);
        return map;
    }
}
