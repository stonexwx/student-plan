package com.student.dao.mapper;

import com.student.entity.FirstType;
import com.student.entity.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 一级标题(FirstType)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface FirstTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param fid 主键
     * @return 实例对象
     */
    FirstType queryById(Long fid);

    /**
     * 查询指定行数据
     *
     * @param firstType 查询条件
     * @param pageRequest 分页对象
     * @return 对象列表
     */
    List<FirstType> queryAllByLimit(FirstType firstType, @Param("page")PageRequest pageRequest);

    /**
     * 统计总行数
     *
     * @param firstType 查询条件
     * @return 总行数
     */
    long count(FirstType firstType);

    /**
     * 新增数据
     *
     * @param firstType 实例对象
     * @return 影响行数
     */
    int insert(FirstType firstType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FirstType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<FirstType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FirstType> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<FirstType> entities);

    /**
     * 修改数据
     *
     * @param firstType 实例对象
     * @return 影响行数
     */
    int update(FirstType firstType);

    /**
     * 通过主键删除数据
     *
     * @param fid 主键
     * @return 影响行数
     */
    int deleteById(Long fid);

}

