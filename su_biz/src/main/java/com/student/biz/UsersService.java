package com.student.biz;

import com.student.entity.PageRequest;
import com.student.entity.Users;

import java.util.Map;

/**
 * 用户表(Users)表服务接口
 *
 * @author makejava
 * @since 2022-02-28 09:02:23
 */
public interface UsersService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    Users queryById(Long uid);

    /**
     * 分页查询
     *
     * @param users       筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Map<String,Object> queryByPage(Users users, PageRequest pageRequest);

    /**
     * 登录验证
     *
     * @return
     */
    Map<String, Object> queryByPhoneAndPassword(Users users);

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    Users insert(Users users);

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    Users update(Users users);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Long uid);

}
