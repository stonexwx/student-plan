package com.student.dao.mapper;

import com.student.entity.SecondType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 二级标题(SecondType)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface SecondTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param sid 主键
     * @return 实例对象
     */
    SecondType queryById(Long sid);

    /**
     * 查询指定行数据
     *
     * @param secondType 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
//    List<SecondType> queryAllByLimit(SecondType secondType, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param secondType 查询条件
     * @return 总行数
     */
    long count(SecondType secondType);

    /**
     * 新增数据
     *
     * @param secondType 实例对象
     * @return 影响行数
     */
    int insert(SecondType secondType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SecondType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SecondType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SecondType> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SecondType> entities);

    /**
     * 修改数据
     *
     * @param secondType 实例对象
     * @return 影响行数
     */
    int update(SecondType secondType);

    /**
     * 通过主键删除数据
     *
     * @param sid 主键
     * @return 影响行数
     */
    int deleteById(Long sid);

}

