package com.demotuwei.demotuwei.enums;

import com.demotuwei.demotuwei.config.BaseEnumOfKeyValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum FeeEnum implements BaseEnumOfKeyValue<FeeEnum, Integer> {
    GOU(11, "狗收费"),
    ZHU(22, "猪收费"),
    MAO(33, "猫收费"),
    NIU(44, "牛收费");

    private Integer code;
    private String value;

    FeeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

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

    private static Map<Integer, FeeEnum> map = new HashMap<>(FeeEnum.values().length);
    static {
        for (FeeEnum sexEnum : FeeEnum.values()) {
            map.put(sexEnum.getCode(), sexEnum);
        }
    }

    public static FeeEnum getEnum(String code) {
        return map.get(code);
    }

    public static FeeEnum getByCode(Integer code) {
        for (FeeEnum p : values()) {
            if (p.getCode().equals(code)) {
                return p;
            }
        }
        return null;
    }

    public static FeeEnum getByValue(String value) {
        for (FeeEnum p : values()) {
            if (p.getValue().equalsIgnoreCase(value)) {
                return p;
            }
        }
        return null;
    }
}
