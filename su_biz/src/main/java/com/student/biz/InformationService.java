package com.student.biz;

import com.student.entity.Information;
import com.student.entity.PageRequest;

import java.util.Map;

/**
 * 信息表(Information)表服务接口
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface InformationService {

    /**
     * 通过ID查询单条数据
     *
     * @param iid 主键
     * @return 实例对象
     */
    Information queryById(Long iid);

    /**
     * 分页查询
     *
     * @param information 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Map<String,Object> queryByPage(Information information, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param information 实例对象
     * @param id
     * @return 实例对象
     */
    Information insert(Information information,long id);

    /**
     * 修改数据
     *
     * @param information 实例对象
     * @return 实例对象
     */
    Information update(Information information);

    /**
     * 通过主键删除数据
     *
     * @param iid 主键
     * @return 是否成功
     */
    Map<String,Object> deleteById(Long iid);

}
