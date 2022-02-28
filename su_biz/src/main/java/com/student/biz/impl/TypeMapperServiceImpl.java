package com.student.biz.impl;

import com.student.biz.TypeMapperService;
import com.student.dao.mapper.TypeMapperDao;
import com.student.entity.TypeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 类型映射(TypeMapper)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@Service("typeMapperService")
public class TypeMapperServiceImpl implements TypeMapperService {
    @Resource
    private TypeMapperDao typeMapperDao;

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    @Override
    public TypeMapper queryById(Long mid) {
        return this.typeMapperDao.queryById(mid);
    }

    /**
     * 分页查询
     *
     * @param typeMapper 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<TypeMapper> queryByPage(TypeMapper typeMapper, PageRequest pageRequest) {
        long total = this.typeMapperDao.count(typeMapper);
        return new PageImpl<>(this.typeMapperDao.queryAllByLimit(typeMapper, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param typeMapper 实例对象
     * @return 实例对象
     */
    @Override
    public TypeMapper insert(TypeMapper typeMapper) {
        this.typeMapperDao.insert(typeMapper);
        return typeMapper;
    }

    /**
     * 修改数据
     *
     * @param typeMapper 实例对象
     * @return 实例对象
     */
    @Override
    public TypeMapper update(TypeMapper typeMapper) {
        this.typeMapperDao.update(typeMapper);
        return this.queryById(typeMapper.getMid());
    }

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long mid) {
        return this.typeMapperDao.deleteById(mid) > 0;
    }
}
