package com.student.biz.impl;

import com.student.biz.PracticeService;
import com.student.dao.mapper.PracticeDao;
import com.student.entity.PageRequest;
import com.student.entity.Practice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 练习资料表(Practice)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@Service("practiceService")
public class PracticeServiceImpl implements PracticeService {
    @Resource
    private PracticeDao practiceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Practice queryById(Long id) {
        return this.practiceDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param practice    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Map<String, Object> queryByPage(Practice practice, PageRequest pageRequest) {
        long total = this.practiceDao.count(practice);
        Map<String,Object> map = new HashMap<>();
        map.put("count",total);
        map.put("data",this.practiceDao.queryAllByLimit(practice, pageRequest));

        return map;
    }

    /**
     * 新增数据
     *
     * @param practice 实例对象
     * @return 实例对象
     */
    @Override
    public Practice insert(Practice practice) {
        this.practiceDao.insert(practice);
        return practice;
    }

    /**
     * 修改数据
     *
     * @param practice 实例对象
     * @return 实例对象
     */
    @Override
    public Practice update(Practice practice) {
        this.practiceDao.update(practice);
        return this.queryById(practice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.practiceDao.deleteById(id) > 0;
    }
}
