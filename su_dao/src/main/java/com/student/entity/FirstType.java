package com.student.entity;

import java.io.Serializable;

/**
 * 一级标题(FirstType)实体类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public class FirstType implements Serializable {
    private static final long serialVersionUID = 911158523368694406L;
    /**
     * 一级标题id
     */
    private Long fid;
    /**
     * 一级标题内容
     */
    private String content;


    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

