package com.cf.huaban.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Works)实体类
 *
 * @author makejava
 * @since 2024-12-28 18:00:47
 */
public class Works implements Serializable {
    private static final long serialVersionUID = -18977178179203109L;

    private Integer id;

    private String title;

    private Integer categoryid;

    private Integer viewcount;

    private String introduction;

    private String producer;

    private Date date;

    private String img;

    private String video;

    private Integer commentid;

    private Integer worksid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 设置日期格式
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getWorksid() {
        return worksid;
    }

    public void setWorksid(Integer worksid) {
        this.worksid = worksid;
    }

}

