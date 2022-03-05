package com.student.biz;

import com.student.entity.FirstType;

/**
 * 一级标题(FirstType)表服务接口
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface FirstTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param fid 主键
     * @return 实例对象
     */
    FirstType queryById(Long fid);

    /**
     * 分页查询
     *
     * @param firstType   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    Page<FirstType> queryByPage(FirstType firstType, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param firstType 实例对象
     * @return 实例对象
     */
    FirstType insert(FirstType firstType);

    /**
     * 修改数据
     *
     * @param firstType 实例对象
     * @return 实例对象
     */
    FirstType update(FirstType firstType);

    /**
     * 通过主键删除数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    boolean deleteById(Long fid);

}
