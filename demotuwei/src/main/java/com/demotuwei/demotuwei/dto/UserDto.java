package com.demotuwei.demotuwei.dto;

import com.demotuwei.demotuwei.enums.GayEnum;
import com.demotuwei.demotuwei.enums.SexEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class UserDto {
    private Integer id;
    private String username;
    private SexEnum sex;
    private Integer age;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
    private GayEnum gay;

}
