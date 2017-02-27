package com.cinefest.util.enumeration;

public enum MovieType {

    NACIONAL("Nacional"),
    REGIONAL("Regional"),
    AMBIENTAL("Ambiental"),
    PARALELA("Paralela"),
    MINUTO("Minuto");

    private String desc;

    MovieType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
