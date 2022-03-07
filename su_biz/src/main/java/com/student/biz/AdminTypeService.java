package com.student.biz;

import com.student.entity.FirstType;
import com.student.entity.SecondType;

import java.util.List;
import java.util.Map;

public interface AdminTypeService {
    /**
     * 判断一级标题是否被注册
     * @param object
     * @param secondType
     * @return
     */
    Map<String,Object> queryByName(String object, List<SecondType> secondType);

}
