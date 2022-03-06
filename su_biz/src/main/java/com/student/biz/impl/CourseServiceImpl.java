package com.student.biz.impl;

import com.student.biz.CourseService;
import com.student.dao.mapper.CourseDao;
import com.student.entity.Course;
import com.student.entity.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 课程资料表(Course)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 09:02:21
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;
    @Autowired
    private HashMap<String,Object> map;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Course queryById(Long id) {
        return this.courseDao.queryById(id);
    }

    @Override
    public Map<String, Object> queryByPage(Course course, PageRequest pageRequest) {
        return null;
    }

    /**
     * 分页查询
     *
     * @param course 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<Course> queryByPage(Course course, PageRequest pageRequest) {
//        long total = this.courseDao.count(course);
//        return new PageImpl<>(this.courseDao.queryAllByLimit(course, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    @Override
    public Course insert(Course course) {
        this.courseDao.insert(course);
        return course;
    }

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    @Override
    public Course update(Course course) {
        this.courseDao.update(course);
        return this.queryById(course.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.courseDao.deleteById(id) > 0;
    }
}
