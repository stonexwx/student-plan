package com.student.entity;

import java.io.Serializable;

/**
 * 标签表(Type)实体类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public class Type implements Serializable {
    private static final long serialVersionUID = 698510990439310829L;
    /**
     * 标签id
     */
    private Long id;
    /**
     * 标签内容
     */
    private String name;
    /**
     * 用户id
     */
    private Long uid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

}

