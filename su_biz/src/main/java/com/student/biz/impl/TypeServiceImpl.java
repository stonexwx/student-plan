package com.student.biz.impl;

import com.student.biz.TypeService;
import com.student.dao.mapper.TypeDao;
import com.student.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
     * @param type 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Type> queryByPage(Type type, PageRequest pageRequest) {
        long total = this.typeDao.count(type);
        return new PageImpl<>(this.typeDao.queryAllByLimit(type, pageRequest), pageRequest, total);
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
    public boolean deleteById(Long id) {
        return this.typeDao.deleteById(id) > 0;
    }
}
