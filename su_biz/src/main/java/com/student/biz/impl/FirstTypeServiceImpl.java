package com.student.biz.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.student.biz.FirstTypeService;
import com.student.dao.mapper.FirstTypeDao;
import com.student.dao.mapper.SecondTypeDao;
import com.student.entity.FirstType;
import com.student.entity.SecondType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private SecondTypeDao secondTypeDao;
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
     * 联级查询
     * @return
     */
    @Override
    public JSONArray queryAll() {
        JSONObject fastType = new JSONObject();
        JSONObject fastTypeObject = new JSONObject();
        JSONArray allData = new JSONArray();
        JSONArray secondTypeArray = new JSONArray();
        List<FirstType> firstTypes = firstTypeDao.queryAllNO();
        for (FirstType type : firstTypes) {
            List<SecondType> secondTypes = secondTypeDao.queryAllByFid(type.getFid());
            secondTypeArray.addAll(secondTypes);
            fastTypeObject.put("fid",type.getFid());
            fastTypeObject.put("title",type.getContent());
            fastType.put("father",fastTypeObject);
            fastType.put("child",secondTypeArray);
            allData.add(fastType);
        }
        return allData;
    }

    /**
     * 分页查询
     *
     * @param firstType 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<FirstType> queryByPage(FirstType firstType, PageRequest pageRequest) {
//        long total = this.firstTypeDao.count(firstType);
//        return new PageImpl<>(this.firstTypeDao.queryAllByLimit(firstType, , pageRequest), pageRequest, total);
//    }

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
    public Map<String, Object> deleteById(Long fid) {
        Map<String, Object> result = new HashMap<>();
        result.put("flag",this.firstTypeDao.deleteById(fid) > 0);
        return result;
    }
}
