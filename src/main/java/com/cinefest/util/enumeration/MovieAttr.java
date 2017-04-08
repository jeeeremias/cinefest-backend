package com.cinefest.util.enumeration;

import java.util.Arrays;

public enum MovieAttr {

    TYPE("type", "type", true, true, ParamType.CUSTOM),
    NAME("name", "name", true, true, ParamType.STRING),
    CITY("city", "city", true, true, ParamType.STRING),
    STATE("state", "state", true, true, ParamType.STRING),
    INCOME_DATE("income_date", "incomeDate", true, false, ParamType.DATE),
    GENRE("genre", "genre", true, true, ParamType.STRING),
    RUNTIME("runtime", "runtime", true, false, ParamType.DATE),
    SCREENING_DATE_TIME("screening_date_time", "screeningDateTime", true, false, ParamType.DATE),
    DIRECTOR("director", "director", true, true, ParamType.STRING),
    SHORT_SYNOPSIS("short_synopsis", "shortSynopsis", true, true, ParamType.STRING),
    FULL_SYNOPSIS("full_synopsis", "fullSynopsis", true, true, ParamType.STRING),
    DIRECTOR_BIOGRAPHY("director_biography", "directorBiography", true, true, ParamType.STRING),
    DIRECTOR_EMAIL("director_email", "directorEmail", true, true, ParamType.STRING);

    public final String queryAttr;
    public final String entityAttr;
    public final boolean sortable;
    public final boolean searchable;
    public final ParamType type;

    MovieAttr(String queryAttr, String entityAttr, boolean sortable, boolean searchable, ParamType type) {
        this.queryAttr = queryAttr;
        this.entityAttr = entityAttr;
        this.sortable = sortable;
        this.searchable = searchable;
        this.type = type;
    }

    public static MovieAttr fromQueryAttr(String queryAttr) {
        return Arrays.stream(MovieAttr.values())
                .filter(e -> e.queryAttr.equals(queryAttr))
                .findFirst()
                .orElse(null);
    }

    public static MovieAttr fromEntityAttr(String entityAttr) {
        return Arrays.stream(MovieAttr.values())
                .filter(e -> e.entityAttr.equals(entityAttr))
                .findFirst()
                .orElse(null);
    }
}
