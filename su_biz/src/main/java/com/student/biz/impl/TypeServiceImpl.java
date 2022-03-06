package com.student.biz.impl;

import com.student.biz.TypeService;
import com.student.dao.mapper.TypeDao;
import com.student.entity.PageRequest;
import com.student.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标签表(Type)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService {
    @Resource
    private TypeDao typeDao;

    @Autowired
    private HashMap<String,Object> map;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Type queryById(Long id) {
        return this.typeDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param type        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Map<String,Object> queryByPage(Type type, PageRequest pageRequest) {
        long total = this.typeDao.count(type);
        List<Type> list = this.typeDao.queryAllByLimit(type,pageRequest);
        map.put("flag",list.size()>0);
        map.put("data",list);
        map.put("count",total);
        return map;
    }

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type insert(Type type) {
        this.typeDao.insert(type);
        return type;
    }

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type update(Type type) {
        this.typeDao.update(type);
        return this.queryById(type.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Map<String, Object> deleteById(Long id) {
        map.put("flag",this.typeDao.deleteById(id) > 0);
        return map;
    }
}
