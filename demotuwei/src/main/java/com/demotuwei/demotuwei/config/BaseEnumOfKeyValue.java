package com.demotuwei.demotuwei.config;

import com.demotuwei.demotuwei.enums.SexEnum;

public interface BaseEnumOfKeyValue<E extends Enum, T> {
     T getCode();

     String getValue();
}
