package com.student.biz.impl;

import com.student.biz.UsersService;
import com.student.dao.mapper.UsersDao;
import com.student.entity.PageRequest;
import com.student.entity.Users;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用户表(Users)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 09:02:23
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersDao usersDao;


    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public Users queryById(Long uid) {
        return this.usersDao.queryById(uid);
    }

    /**
     * 分页查询
     *
     * @param users       筛选条件
     * @return 查询结果
     */
    @Override
    public Map<String, Object> queryByPage(Users users, PageRequest pageRequest) {
        long total = this.usersDao.count(users);
        Map<String, Object> map = new HashMap<>();
        map.put("flag",true);
        map.put("data",this.usersDao.queryAllByLimit(users, pageRequest));
        map.put("count",total);
        return map;
    }

    @Override
    public Map<String, Object> queryByPhoneAndPassword(Users users) {
        long total = this.usersDao.count(users);
        List<Users> users1 = this.usersDao.queryAllByLimit(users, new PageRequest(1,10));
        Map<String, Object> map = new HashMap<>();
        if (users1.size()>0) {

            map.put("flag", true);
            map.put("data", users1.get(0));
            map.put("count", total);
            return map;
        }
        map.put("flag", false);
        return map;
    }

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users insert(Users users) {
        users.setAddtime(new Date());
        this.usersDao.insert(users);
        return users;
    }

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users update(Users users) {
        this.usersDao.update(users);
        return this.queryById(users.getUid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long uid) {
        return this.usersDao.deleteById(uid) > 0;
    }
}
