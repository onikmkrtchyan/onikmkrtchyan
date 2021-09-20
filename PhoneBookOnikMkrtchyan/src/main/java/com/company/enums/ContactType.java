package com.company.enums;

import java.util.HashMap;
import java.util.Map;

public enum ContactType {
    HOME(1, "Home"),
    MOBILE(2, "Mobile"),
    WORK(3, "Work");

    private final int code;
    private final String name;

    static final Map<Integer, ContactType> codeMap = new HashMap<>();

    static {
        for (ContactType s : ContactType.values())
            codeMap.put(s.getCode(), s);

    }

    ContactType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public static ContactType getByCode(int code) {
        return codeMap.get(code);
    }

    public String getName() {
        return name;
    }
}
