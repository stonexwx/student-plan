package com.student.biz;

import com.student.entity.SecondType;

import java.util.Map;

/**
 * 二级标题(SecondType)表服务接口
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface SecondTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param sid 主键
     * @return 实例对象
     */
    SecondType queryById(Long sid);

    /**
     * 分页查询
     *
     * @param secondType  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    Page<SecondType> queryByPage(SecondType secondType, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param secondType 实例对象
     * @return 实例对象
     */
    SecondType insert(SecondType secondType);

    /**
     * 修改数据
     *
     * @param secondType 实例对象
     * @return 实例对象
     */
    SecondType update(SecondType secondType);

    /**
     * 通过主键删除数据
     *
     * @param sid 主键
     * @return 是否成功
     */
    Map<String,Object> deleteById(Long sid);

}
