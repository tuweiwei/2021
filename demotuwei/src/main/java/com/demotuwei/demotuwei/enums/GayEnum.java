package com.demotuwei.demotuwei.enums;

import com.demotuwei.demotuwei.config.BaseEnumOfKeyValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.util.HashMap;
import java.util.Map;

public enum GayEnum implements BaseEnumOfKeyValue<GayEnum, Integer> {
    MALE(1, "弯弯"),
    FEMAL(2, "直的");

    private Integer code;
    private String value;

    GayEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    @JsonCreator
    public static GayEnum getEnum(Integer code) {
        return map.get(code);
    }

    @JsonValue
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }

    private static Map<Integer, GayEnum> map = new HashMap<>(GayEnum.values().length);
    static {
        for (GayEnum sexEnum : GayEnum.values()) {
            map.put(sexEnum.getCode(), sexEnum);
        }
    }


//    public static GayEnum fromValue(String code) {
//
//    }

    public static GayEnum getByCode(Integer code) {
        for (GayEnum p : values()) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }

    public static GayEnum getByValue(String value) {
        for (GayEnum p : values()) {
            if (p.getValue().equalsIgnoreCase(value)) {
                return p;
            }
        }
        return null;
    }


}
