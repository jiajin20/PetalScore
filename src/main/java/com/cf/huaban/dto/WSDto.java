package com.cf.huaban.dto;

import java.util.Date;

// Works类
public class WSDto {
    private int id;
    private String title;
    private String introduction;
    private String producer;
    private Long categoryid;
    private Date date;
    private int viewcount;
    private int worksid;
    private int score_id;
    private Long score_worksid;
    private String score;
    private Long userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getViewcount() {
        return viewcount;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
    }

    public int getWorksid() {
        return worksid;
    }

    public void setWorksid(int worksid) {
        this.worksid = worksid;
    }

    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }

    public Long getScore_worksid() {
        return score_worksid;
    }

    public void setScore_worksid(Long score_worksid) {
        this.score_worksid = score_worksid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}

