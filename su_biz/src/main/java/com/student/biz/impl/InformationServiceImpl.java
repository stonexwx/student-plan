package com.student.biz.impl;

import com.student.biz.InformationService;
import com.student.dao.mapper.InformationDao;
import com.student.entity.Information;
import com.student.entity.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 信息表(Information)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@Service("informationService")
public class InformationServiceImpl implements InformationService {
    @Resource
    private InformationDao informationDao;
    @Autowired
    HashMap<String,Object> map;
    /**
     * 通过ID查询单条数据
     *
     * @param iid 主键
     * @return 实例对象
     */
    @Override
    public Information queryById(Long iid) {
        return this.informationDao.queryById(iid);
    }

    /**
     * 分页查询
     *
     * @param information 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Map<String, Object> queryByPage(Information information, PageRequest pageRequest) {
        long total = this.informationDao.count(information);
        map.put("count",total);
        map.put("data",this.informationDao.queryAllByLimit(information, pageRequest));
        return map;
    }

    /**
     * 新增数据
     *
     * @param information 实例对象
     * @return 实例对象
     */
    @Override
    public Information insert(Information information) {
        information.setAddtime(new Date());
        this.informationDao.insert(information);
        return information;
    }

    /**
     * 修改数据
     *
     * @param information 实例对象
     * @return 实例对象
     */
    @Override
    public Information update(Information information) {
        this.informationDao.update(information);
        return this.queryById(information.getIid());
    }

    /**
     * 通过主键删除数据
     *
     * @param iid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long iid) {
        return this.informationDao.deleteById(iid) > 0;
    }
}
