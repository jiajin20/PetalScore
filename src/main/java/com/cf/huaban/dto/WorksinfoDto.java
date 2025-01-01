package com.cf.huaban.dto;

import com.cf.huaban.entity.Score;
import com.cf.huaban.entity.Works;

public class WorksinfoDto {

    private Works works;
    private Score score;

    public WorksinfoDto(Works works, Score score) {
        this.works = works;
        this.score = score;
    }

    public Works getWorks() {
        return works;
    }

    public void setWorks(Works works) {
        this.works = works;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}

