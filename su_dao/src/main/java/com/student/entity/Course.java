package com.student.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程资料表(Course)实体类
 *
 * @author makejava
 * @since 2022-02-28 09:02:20
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 706539166789817058L;
    /**
     * 课程id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 添加时间
     */
    private Date addtime;
    /**
     * 类型
     */
    private Long sid;
    /**
     * 状态
     */
    private String state;
    /**
     * 内容
     */
    private String content;
    /**
     * 封面
     */
    private String photo;
    /**
     * 视频
     */
    private String video;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

}

