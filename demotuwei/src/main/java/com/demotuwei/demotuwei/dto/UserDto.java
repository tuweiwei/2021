package com.demotuwei.demotuwei.dto;

import com.demotuwei.demotuwei.config.Filed2Enum;
import com.demotuwei.demotuwei.config.Enum2String;
import com.demotuwei.demotuwei.enums.FeeEnum;
import com.demotuwei.demotuwei.enums.GayEnum;
import com.demotuwei.demotuwei.enums.HobbyEnum;
import com.demotuwei.demotuwei.enums.SexEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

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

    @JsonDeserialize(using = Filed2Enum.class)
    @JsonSerialize(using = Enum2String.class)
    private List<HobbyEnum> hobby;

    @JsonDeserialize(using = Filed2Enum.class)
    @JsonSerialize(using = Enum2String.class)
    private List<FeeEnum> fee;
}
