package com.cinefest.util.enumeration;

public enum MovieTypeEnum {

    NACIONAL("Nacional"),
    REGIONAL("Regional"),
    AMBIENTAL("Ambiental"),
    PARALELA("Paralela"),
    MINUTO("Minuto");

    private String desc;

    MovieTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
