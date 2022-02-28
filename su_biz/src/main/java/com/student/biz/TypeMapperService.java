package com.student.biz;

import com.student.entity.TypeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 类型映射(TypeMapper)表服务接口
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface TypeMapperService {

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    TypeMapper queryById(Long mid);

    /**
     * 分页查询
     *
     * @param typeMapper 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<TypeMapper> queryByPage(TypeMapper typeMapper, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param typeMapper 实例对象
     * @return 实例对象
     */
    TypeMapper insert(TypeMapper typeMapper);

    /**
     * 修改数据
     *
     * @param typeMapper 实例对象
     * @return 实例对象
     */
    TypeMapper update(TypeMapper typeMapper);

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 是否成功
     */
    boolean deleteById(Long mid);

}
