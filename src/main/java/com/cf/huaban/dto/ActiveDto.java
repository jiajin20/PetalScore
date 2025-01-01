package com.cf.huaban.dto;

import com.cf.huaban.entity.Active;

import java.util.List;

public class ActiveDto {

    private Active active;
    private List<String> imageList;

    public ActiveDto(Active active, List<String> imageList) {
        this.active = active;
        this.imageList = imageList;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
