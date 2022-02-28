package com.student.entity;

import java.io.Serializable;

/**
 * 类型映射(TypeMapper)实体类
 *
 * @author makejava
 * @since 2022-02-28 09:02:22
 */
public class TypeMapper implements Serializable {
    private static final long serialVersionUID = 976963093805447560L;
    /**
     * 映射id
     */
    private Long mid;
    /**
     * 类型id
     */
    private Long typeId;
    /**
     * 信息id
     */
    private Long iid;
    /**
     * 任务id
     */
    private Long tid;


    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

}

