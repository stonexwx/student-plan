package com.student.dao.mapper;

import com.student.entity.PageRequest;
import com.student.entity.TypeMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类型映射(TypeMapper)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface TypeMapperDao {

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    TypeMapper queryById(Long mid);

    /**
     * 查询指定行数据
     *
     * @param typeMapper 查询条件
     * @param pageRequest
     * @return 对象列表
     */
    List<TypeMapper> queryAllByLimit(TypeMapper typeMapper, @Param("pageable")PageRequest pageRequest);

    /**
     * 统计总行数
     *
     * @param typeMapper 查询条件
     * @return 总行数
     */
    long count(TypeMapper typeMapper);

    /**
     * 新增数据
     *
     * @param typeMapper 实例对象
     * @return 影响行数
     */
    int insert(@Param("typeMapper") TypeMapper typeMapper);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TypeMapper> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TypeMapper> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TypeMapper> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TypeMapper> entities);

    /**
     * 修改数据
     *
     * @param typeMapper 实例对象
     * @return 影响行数
     */
    int update(TypeMapper typeMapper);

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 影响行数
     */
    int deleteById(Long mid);

}

