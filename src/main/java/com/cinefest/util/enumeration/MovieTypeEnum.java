package com.cinefest.util.enumeration;

import java.util.Arrays;

public enum MovieTypeEnum {

    NACIONAL("Nacional"),
    REGIONAL("Regional"),
    AMBIENTAL("Ambiental"),
    PARALELA("Paralela"),
    MINUTO("Minuto");

    public final String desc;

    MovieTypeEnum(String desc) {
        this.desc = desc;
    }

    public static MovieTypeEnum fromDesc(String desc) {
        return Arrays.stream(MovieTypeEnum.values())
                .filter(e -> e.desc.equals(desc))
                .findFirst()
                .orElse(null);
    }
}
