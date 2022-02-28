package com.student.entity;

import java.io.Serializable;

/**
 * 二级标题(SecondType)实体类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public class SecondType implements Serializable {
    private static final long serialVersionUID = 710588933642270925L;
    /**
     * 二级id
     */
    private Long sid;
    /**
     * 二级标题内容
     */
    private String content;
    /**
     * 一级标题id
     */
    private Long fid;


    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

}

