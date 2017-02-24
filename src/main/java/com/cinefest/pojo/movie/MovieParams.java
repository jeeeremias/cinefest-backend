package com.cinefest.pojo.movie;

import com.cinefest.pojo.QueryParams;

/**
 * Created by jere on 23/02/17.
 */
public class MovieParams extends QueryParams {
    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
