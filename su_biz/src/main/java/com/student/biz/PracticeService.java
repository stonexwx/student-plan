package com.student.biz;

import com.student.entity.Practice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 练习资料表(Practice)表服务接口
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public interface PracticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Practice queryById(Long id);

    /**
     * 分页查询
     *
     * @param practice 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Practice> queryByPage(Practice practice, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param practice 实例对象
     * @return 实例对象
     */
    Practice insert(Practice practice);

    /**
     * 修改数据
     *
     * @param practice 实例对象
     * @return 实例对象
     */
    Practice update(Practice practice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
