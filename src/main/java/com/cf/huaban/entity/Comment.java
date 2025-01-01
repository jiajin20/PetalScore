package com.cf.huaban.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2024-12-28 18:46:58
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = -67483840342918670L;

    private Integer id;
/**
     * 评论人
     */
    private Integer userid;
/**
     * 评论内容
     */
    private String comment;
/**
     * 评论时间
     */
    private Date date;

    private Integer worksid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getWorksid() {
        return worksid;
    }

    public void setWorksid(Integer worksid) {
        this.worksid = worksid;
    }

}

