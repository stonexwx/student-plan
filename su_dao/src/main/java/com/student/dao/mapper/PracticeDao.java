package com.student.dao.mapper;

import com.student.entity.Practice;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 练习资料表(Practice)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface PracticeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Practice queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param practice 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Practice> queryAllByLimit(Practice practice, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param practice 查询条件
     * @return 总行数
     */
    long count(Practice practice);

    /**
     * 新增数据
     *
     * @param practice 实例对象
     * @return 影响行数
     */
    int insert(Practice practice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Practice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Practice> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Practice> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Practice> entities);

    /**
     * 修改数据
     *
     * @param practice 实例对象
     * @return 影响行数
     */
    int update(Practice practice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

