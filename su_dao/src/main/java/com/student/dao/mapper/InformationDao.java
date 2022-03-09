package com.student.dao.mapper;

import com.student.entity.Information;
import com.student.entity.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 信息表(Information)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface InformationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param iid 主键
     * @return 实例对象
     */
    Information queryById(Long iid);

    /**
     * 查询指定行数据
     *
     * @param information 查询条件
     * @param pageRequest 分页对象
     * @return 对象列表
     */
    List<Information> queryAllByLimit(@Param("info") Information information, @Param("page")PageRequest pageRequest);

    /**
     * 统计总行数
     *
     * @param information 查询条件
     * @return 总行数
     */
    long count(Information information);

    /**
     * 新增数据
     *
     * @param information 实例对象
     * @return 影响行数
     */
    int insert(Information information);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Information> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Information> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Information> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Information> entities);

    /**
     * 修改数据
     *
     * @param information 实例对象
     * @return 影响行数
     */
    int update(Information information);

    /**
     * 通过主键删除数据
     *
     * @param iid 主键
     * @return 影响行数
     */
    int deleteById(Long iid);

}

