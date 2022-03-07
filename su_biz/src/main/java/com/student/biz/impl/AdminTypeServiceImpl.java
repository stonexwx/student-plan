package com.student.biz.impl;

import com.student.biz.AdminTypeService;
import com.student.dao.mapper.FirstTypeDao;
import com.student.dao.mapper.SecondTypeDao;
import com.student.entity.FirstType;
import com.student.entity.PageRequest;
import com.student.entity.SecondType;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("adminTypeService")
public class AdminTypeServiceImpl implements AdminTypeService {
    @Resource
    private FirstTypeDao firstTypeDao;
    @Resource
    private SecondTypeDao secondTypeDao;
    @Autowired
    HashMap<String,Object> map;
    /**
     * 判断一级标题是否被注册
     * @param object
     * @param secondType
     * @return
     */
    @Override
    public Map<String, Object> queryByName(String object, List<SecondType> secondType) {
        if(NumberUtils.isDigits(object)){
            FirstType firstType = new FirstType();
            firstType.setFid(Long.valueOf(object));
            PageRequest param = new PageRequest();
            param.setPage(1);
            param.setLimit(1);
            List<FirstType> list = this.firstTypeDao.queryAllByLimit(firstType,param);
            if(list.size()>0){
                for (SecondType secondType1:secondType) {
                    secondType1.setFid(Long.valueOf(object));
                }
                map.put("flag",secondTypeDao.insertBatch(secondType)>0);
            }else{
                map = secondInsert(object, secondType);
            }
        }else{
            map = secondInsert(object, secondType);
        }
        return map;
    }

    private HashMap<String,Object> secondInsert(String object, List<SecondType> secondType) {
        FirstType firstType1 = new FirstType();
        firstType1.setContent(object);
        if(firstTypeDao.insert(firstType1)>0){
            for (SecondType secondType1: secondType) {
                secondType1.setFid(firstType1.getFid());
            }
            map.put("flag",secondTypeDao.insertBatch(secondType)>0);
        }
        return map;
    }
}
