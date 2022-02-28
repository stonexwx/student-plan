package com.student.biz;

import com.student.entity.Information;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Information> queryByPage(Information information, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param information 实例对象
     * @return 实例对象
     */
    Information insert(Information information);

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
    boolean deleteById(Long iid);

}
