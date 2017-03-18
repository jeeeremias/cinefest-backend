package com.cinefest.util.enumeration;

/**
 * Created by jere on 18/03/17.
 */
public enum MovieParamsEnum {

    TYPE("type", "type"),
    NAME("name", "name"),
    CITY("city", "city"),
    STATE("state", "state"),
    INCOME_DATE("income_date", "incomeDate"),
    GENRE("genre", "genre"),
    RUNTIME("runtime", "runtime"),
    SCREENING_DATE_TIME("screening_date_time", "screeningDateTime"),
    DIRECTOR("director", "director"),
    SHORT_SYNOPSIS("short_synopsis", "shortSynopsis"),
    FULL_SYNOPSIS("full_synopsis", "fullSynopsis"),
    DIRECTOR_BIOGRAPHY("director_biography", "directorBiography"),
    DIRECTOR_EMAIL("director_email", "directorEmail");

    public String queryAttr;
    public String entityAttr;

    MovieParamsEnum(String queryAttr, String entityAttr) {
        this.queryAttr = queryAttr;
        this.entityAttr = entityAttr;
    }
}
