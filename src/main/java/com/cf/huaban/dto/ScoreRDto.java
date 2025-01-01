package com.cf.huaban.dto;

public class ScoreRDto {
    private int worksid;
    private long score;

    public ScoreRDto(int worksid, long score) {
        this.worksid = worksid;
        this.score = score;

    }

    public int getWorksid() {
        return worksid;
    }

    public void setWorksid(Integer worksid) {
        this.worksid = worksid;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
