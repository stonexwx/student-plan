package com.student.dao.mapper;

import com.student.entity.Course;
import com.student.entity.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程资料表(Course)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-28 09:02:19
 */
public interface CourseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Course queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param course 查询条件
     * @param pageRequest 分页条件
     * @return 对象列表
     */
    List<Course> queryAllByLimit(Course course, @Param("page")PageRequest pageRequest);

    /**
     * 统计总行数
     *
     * @param course 查询条件
     * @return 总行数
     */
    long count(Course course);

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 影响行数
     */
    int insert(Course course);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Course> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Course> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Course> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Course> entities);

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 影响行数
     */
    int update(Course course);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

