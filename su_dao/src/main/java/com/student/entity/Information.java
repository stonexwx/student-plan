package com.student.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 信息表(Information)实体类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public class Information implements Serializable {
    private static final long serialVersionUID = 604910684030292075L;
    /**
     * 信息id
     */
    private Long iid;
    /**
     * 添加用户id
     */
    private Long uid;
    /**
     * 内容
     */
    private String content;
    /**
     * 添加时间
     */
    private Date addtime;
    /**
     * 照片地址
     */
    private String photo;
    /**
     * 视频地址
     */
    private String vidio;
    /**
     * 类型
     */
    private String typeId;
    /**
     * 状态
     */
    private String state;


    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getVidio() {
        return vidio;
    }

    public void setVidio(String vidio) {
        this.vidio = vidio;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}

