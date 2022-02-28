package com.student.biz.impl;

import com.student.biz.FirstTypeService;
import com.student.dao.mapper.FirstTypeDao;
import com.student.entity.FirstType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 一级标题(FirstType)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@Service("firstTypeService")
public class FirstTypeServiceImpl implements FirstTypeService {
    @Resource
    private FirstTypeDao firstTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param fid 主键
     * @return 实例对象
     */
    @Override
    public FirstType queryById(Long fid) {
        return this.firstTypeDao.queryById(fid);
    }

    /**
     * 分页查询
     *
     * @param firstType 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<FirstType> queryByPage(FirstType firstType, PageRequest pageRequest) {
        long total = this.firstTypeDao.count(firstType);
        return new PageImpl<>(this.firstTypeDao.queryAllByLimit(firstType, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param firstType 实例对象
     * @return 实例对象
     */
    @Override
    public FirstType insert(FirstType firstType) {
        this.firstTypeDao.insert(firstType);
        return firstType;
    }

    /**
     * 修改数据
     *
     * @param firstType 实例对象
     * @return 实例对象
     */
    @Override
    public FirstType update(FirstType firstType) {
        this.firstTypeDao.update(firstType);
        return this.queryById(firstType.getFid());
    }

    /**
     * 通过主键删除数据
     *
     * @param fid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long fid) {
        return this.firstTypeDao.deleteById(fid) > 0;
    }
}
