package com.demotuwei.demotuwei.enums;

import com.demotuwei.demotuwei.config.BaseEnumOfKeyValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum SexEnum implements BaseEnumOfKeyValue<SexEnum, String> {
    MALE("JJ", "男性"),
    FEMAL("PP", "女性");

    private String code;
    private String value;

    SexEnum(String code, String value) {
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

    private static Map<String, SexEnum> map = new HashMap<>(SexEnum.values().length);
    static {
        for (SexEnum sexEnum : SexEnum.values()) {
            map.put(sexEnum.getCode(), sexEnum);
        }
    }

    @JsonCreator
    public static SexEnum getEnum(String code) {
        return map.get(code);
    }

    public static SexEnum getByCode(String code) {
        for (SexEnum p : values()) {
            if (p.getCode() == code) {
                return p;
            }
        }
        return null;
    }

    public static SexEnum getByValue(String value) {
        for (SexEnum p : values()) {
            if (p.getValue().equalsIgnoreCase(value)) {
                return p;
            }
        }
        return null;
    }


}
