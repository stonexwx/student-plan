package com.student.biz.impl;

import com.student.biz.SecondTypeService;
import com.student.dao.mapper.SecondTypeDao;
import com.student.entity.SecondType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 二级标题(SecondType)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@Service("secondTypeService")
public class SecondTypeServiceImpl implements SecondTypeService {
    @Resource
    private SecondTypeDao secondTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sid 主键
     * @return 实例对象
     */
    @Override
    public SecondType queryById(Long sid) {
        return this.secondTypeDao.queryById(sid);
    }

    /**
     * 分页查询
     *
     * @param secondType  筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<SecondType> queryByPage(SecondType secondType, PageRequest pageRequest) {
//        long total = this.secondTypeDao.count(secondType);
//        return new PageImpl<>(this.secondTypeDao.queryAllByLimit(secondType, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param secondType 实例对象
     * @return 实例对象
     */
    @Override
    public SecondType insert(SecondType secondType) {
        this.secondTypeDao.insert(secondType);
        return secondType;
    }

    /**
     * 修改数据
     *
     * @param secondType 实例对象
     * @return 实例对象
     */
    @Override
    public SecondType update(SecondType secondType) {
        this.secondTypeDao.update(secondType);
        return this.queryById(secondType.getSid());
    }

    /**
     * 通过主键删除数据
     *
     * @param sid 主键
     * @return 是否成功
     */
    @Override
    public Map<String, Object> deleteById(Long sid) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("flag",this.secondTypeDao.deleteById(sid) > 0);
        return map ;
    }
}
