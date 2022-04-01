package com.student.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.student.biz.CourseService;
import com.student.dao.mapper.CourseDao;
import com.student.entity.Course;
import com.student.entity.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    /**
     * 分页查询
     * @param course      筛选条件
     * @param pageRequest 分页对象
     * @return
     */
    @Override
    public Map<String, Object> queryByPage(Course course, PageRequest pageRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put("count",courseDao.count(course));
        List<Course> list = courseDao.queryAllByLimit(course,pageRequest);
        List<JSONObject> list1 =new ArrayList<>();
        for (Course c : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",c.getId());
            jsonObject.put("name",c.getCourseName());
            jsonObject.put("video",c.getVideo());
            jsonObject.put("content",c.getContent());
            list1.add(jsonObject);
        }
        map.put("data",list1);
        map.put("flag",list1.size()>0);
        return map;
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
     * @param flag
     * @return 实例对象
     */
    @Override
    public Course insert(Course course, Boolean flag) {
        if(flag){
            course.setAddtime(new Date());
            course.setState("1");
            this.courseDao.insert(course);
        }else{
            this.update(course);
        }
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
    public Map<String, Object> deleteById(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag",this.courseDao.deleteById(id) > 0);
        return map;
    }
}
