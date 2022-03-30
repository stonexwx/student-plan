package com.student.entity;

import java.io.Serializable;

/**
 * 练习资料表(Practice)实体类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public class Practice implements Serializable {
    private static final long serialVersionUID = 681351921223275696L;
    /**
     * 练习id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 题目内容
     */
    private String content;
    /**
     * 类型
     */
    private Long sid;

    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

}

