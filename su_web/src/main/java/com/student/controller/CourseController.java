package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.student.biz.CourseService;
import com.student.entity.Course;
import com.student.util.UploadUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 课程资料表(Course)表控制层
 *
 * @author makejava
 * @since 2022-02-28 09:02:18
 */
@RestController
@RequestMapping("course")
public class CourseController {
    /**
     * 服务对象
     */
    @Resource
    private CourseService courseService;

    /**
     * 分页查询
     *
     * @param course      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
//    @GetMapping
//    public ResponseEntity<Page<Course>> queryByPage(Course course, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.courseService.queryByPage(course, pageRequest));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Course> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.courseService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param course 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Course> add(Course course) {
        return ResponseEntity.ok(this.courseService.insert(course));
    }

    /**
     * 编辑数据
     *
     * @param course 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Course> edit(Course course) {
        return ResponseEntity.ok(this.courseService.update(course));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.courseService.deleteById(id));
    }
    /**
     * 文件上传
     */
    @PostMapping("upload")
    public String upload(@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException{
        Map<String, Object> map = UploadUtil.fileUploadFileNameByDate(myfiles,request);
        JSONObject jsonObject = getJsonObject(map);
        return JSON.toJSONString(jsonObject);
    }

    /**
     * 创建照片回调函数
     * @param map
     * @return
     */
    private JSONObject getJsonObject(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("errno",0);
        jsonObject2.put("url", map.get("photo"));
        jsonObject2.put("alt","????");
        jsonObject2.put("href","");
        jsonArray.add(jsonObject2);
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }
}

