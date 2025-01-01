package com.cf.huaban.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Activeimformation)实体类
 *
 * @author makejava
 * @since 2024-12-28 19:08:33
 */
public class Active implements Serializable {
    private static final long serialVersionUID = -91353273424289044L;

    private Integer id;

    private String title;

    private String imfomation;

    private String produer;

    private Date date;

    private String img;

    private String video;


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

    public String getImfomation() {
        return imfomation;
    }

    public void setImfomation(String imfomation) {
        this.imfomation = imfomation;
    }

    public String getProduer() {
        return produer;
    }

    public void setProduer(String produer) {
        this.produer = produer;
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

}

