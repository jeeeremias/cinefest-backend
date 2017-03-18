package com.cinefest.util.enumeration;

/**
 * Created by jere on 18/03/17.
 */
public enum MovieAttrsEnum {

    TYPE("type", "type", true, true),
    NAME("name", "name", true, true),
    CITY("city", "city", true, true),
    STATE("state", "state", true, true),
    INCOME_DATE("income_date", "incomeDate", true, true),
    GENRE("genre", "genre", true, true),
    RUNTIME("runtime", "runtime", true, true),
    SCREENING_DATE_TIME("screening_date_time", "screeningDateTime", true, true),
    DIRECTOR("director", "director", true, true),
    SHORT_SYNOPSIS("short_synopsis", "shortSynopsis", true, true),
    FULL_SYNOPSIS("full_synopsis", "fullSynopsis", true, true),
    DIRECTOR_BIOGRAPHY("director_biography", "directorBiography", true, true),
    DIRECTOR_EMAIL("director_email", "directorEmail", true, true);

    public final String queryAttr;
    public final String entityAttr;
    public final boolean sortable;
    public final boolean searchable;

    MovieAttrsEnum(String queryAttr, String entityAttr, boolean sortable, boolean searchable) {
        this.queryAttr = queryAttr;
        this.entityAttr = entityAttr;
        this.sortable = sortable;
        this.searchable = searchable;
    }

    public static MovieAttrsEnum fromQueryAttr(String queryAttr) {
        for (MovieAttrsEnum e : MovieAttrsEnum.values()) {
            if (e.queryAttr.equals(queryAttr)) {
                return e;
            }
        }
        return null;
    }

    public static MovieAttrsEnum fromEntityAttr(String entityAttr) {
        for (MovieAttrsEnum e : MovieAttrsEnum.values()) {
            if (e.entityAttr.equals(entityAttr)) {
                return e;
            }
        }
        return null;
    }
}
