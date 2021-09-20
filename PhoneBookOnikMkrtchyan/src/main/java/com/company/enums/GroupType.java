package com.company.enums;

import java.util.HashMap;
import java.util.Map;

public enum GroupType {
    DEFAULT(1, "Default"),
    FRIENDS(2, "Friends"),
    CO_WORKERS(3, "CO Workers"),
    FAMILY(4, "Family");

    private final int code;
    private final String name;

    static final Map<Integer, GroupType> codeMap = new HashMap<>();

    static {
        for (GroupType s : GroupType.values())
            codeMap.put(s.getCode(), s);

    }

    GroupType(int code, String name) {
        this.code = code;
        this.name = name;
    }


    public int getCode() {
        return code;
    }

    public static GroupType getByCode(int code) {
        return codeMap.get(code);
    }

    public String getName() {
        return name;
    }
}
