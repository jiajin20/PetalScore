package com.cf.huaban.entity;

import java.io.Serializable;

/**
 * (Score)实体类
 *
 * @author makejava
 * @since 2024-12-28 20:05:29
 */
public class Score implements Serializable {
    private static final long serialVersionUID = 234205653047449531L;

    private Integer id;

    private Integer worksid;

    private String score;

    private String level;

    private Integer userid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorksid() {
        return worksid;
    }

    public void setWorksid(Integer worksid) {
        this.worksid = worksid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

}

