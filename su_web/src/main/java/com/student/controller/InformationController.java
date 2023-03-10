package com.student.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.student.biz.InformationService;
import com.student.entity.Information;
import com.student.entity.PageRequest;
import com.student.util.UploadUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 信息表(Information)表控制层
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
@RestController
@RequestMapping("information")
public class InformationController {
    /**
     * 服务对象
     */
    @Resource
    private InformationService informationService;

    /**
     * 分页查询
     *
     * @param information 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @PostMapping("select_all")
    public String queryByPage(Information information, PageRequest pageRequest,Long id) {
        return JSON.toJSONString(this.informationService.queryByPage(information, pageRequest,id));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Information> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.informationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param information 实体
     * @return 新增结果
     */
    @PostMapping("insert")
    public String add(Information information ,Long id,Boolean flag) {
        return JSON.toJSONString(this.informationService.insert(information,id, flag));
    }

    /**
     * 编辑数据
     *
     * @param information 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Information> edit(Information information) {
        return ResponseEntity.ok(this.informationService.update(information));
    }

    /**
     * 删除数据
     *
     * @param iid 主键
     * @return 删除是否成功
     */
    @PostMapping("delete")
    public String deleteById(Long iid) {
        return JSON.toJSONString(this.informationService.deleteById(iid));
    }

    /**
     * 富文本图片上传
     * @param files
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("upload")
    public String upload(@RequestParam MultipartFile[] files, HttpServletRequest request) throws IOException {
        Map<String, List<String>> map = UploadUtil.fileUploadFileNameByDate(files,request);

        return JSON.toJSONString(getJsonObject(map));
    }
    /**
     * 创建照片回调函数
     * @param map
     * @return
     */
    private JSONObject getJsonObject(Map<String, List<String>> map) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("errno",0);
        jsonObject2.put("url", map.get("photo").get(0));
        jsonObject2.put("alt","????");
        jsonObject2.put("href","");
        jsonArray.add(jsonObject2);
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }
}

