package com.demotuwei.demotuwei.enums;

import com.demotuwei.demotuwei.config.BaseEnumOfKeyValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum HobbyEnum implements BaseEnumOfKeyValue<HobbyEnum, String> {
    MUSIC("H1", "音乐"),
    BASKETBALL("H2", "篮球"),
    SPORT("H3", "运动"),
    CLIME("H4", "爬山");

    private String code;
    private String value;

    HobbyEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }

    private static Map<String, HobbyEnum> map = new HashMap<>(HobbyEnum.values().length);
    static {
        for (HobbyEnum sexEnum : HobbyEnum.values()) {
            map.put(sexEnum.getCode(), sexEnum);
        }
    }

    @JsonCreator
    public static HobbyEnum getEnum(String code) {
        return map.get(code);
    }

    public static HobbyEnum getByCode(String code) {
        for (HobbyEnum p : values()) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }

    public static HobbyEnum getByValue(String value) {
        for (HobbyEnum p : values()) {
            if (p.getValue().equalsIgnoreCase(value)) {
                return p;
            }
        }
        return null;
    }


}
