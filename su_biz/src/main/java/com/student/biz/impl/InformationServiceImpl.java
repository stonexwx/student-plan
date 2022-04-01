package com.student.biz.impl;

import com.github.binarywang.java.emoji.EmojiConverter;
import com.student.biz.InformationService;
import com.student.dao.mapper.InformationDao;
import com.student.dao.mapper.TypeMapperDao;
import com.student.entity.Information;
import com.student.entity.PageRequest;
import com.student.entity.TypeMapper;
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

    @Resource
    private TypeMapperDao typeMapperDao;
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
     * @param id
     * @return 查询结果
     */
    @Override
    public Map<String, Object> queryByPage(Information information, PageRequest pageRequest, Long id) {
        long total = this.informationDao.count(information);
        Map<String, Object> map = new HashMap<>();
        map.put("count",total);
        map.put("data",this.informationDao.queryAllByLimit(information, pageRequest,id));
        return map;
    }

    /**
     * 新增数据
     *
     * @param information 实例对象
     * @param id
     * @param flag
     * @return 实例对象
     */
    @Override
    public Information insert(Information information, long id, Boolean flag) {
        if(flag){
            information.setAddtime(new Date());
            EmojiConverter emoji = EmojiConverter.getInstance();
            information.setContent(emoji.toHtml(information.getContent()));
            information.setShow(emoji.toHtml(information.getShow()));
            this.informationDao.insert(information);
            TypeMapper typeMapper =new TypeMapper();
            typeMapper.setTypeId(id);
            typeMapper.setIid(information.getIid());
            typeMapperDao.insert(typeMapper);
        }else{
            EmojiConverter emoji = EmojiConverter.getInstance();
            information.setContent(emoji.toHtml(information.getContent()));
            information.setShow(emoji.toHtml(information.getShow()));
            this.update(information);
            TypeMapper typeMapper =new TypeMapper();
            typeMapper.setIid(information.getIid());
            TypeMapper typeMapper1 = typeMapperDao.queryAllByLimit(typeMapper);
            typeMapper1.setTypeId(id);
            typeMapperDao.update(typeMapper1);
        }
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
    public Map<String, Object> deleteById(Long iid) {
        Map<String, Object> map = new HashMap<>();
        map.put("flag",this.informationDao.deleteById(iid) > 0);
        return map;
    }
}
