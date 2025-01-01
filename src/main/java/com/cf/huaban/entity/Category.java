package com.cf.huaban.entity;

import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2024-12-28 20:05:38
 */
public class Category implements Serializable {
    private static final long serialVersionUID = -56368814178331959L;

    private Integer id;

    private String categoryname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

}

