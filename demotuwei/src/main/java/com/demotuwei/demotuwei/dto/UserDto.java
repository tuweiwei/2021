package com.demotuwei.demotuwei.dto;

import com.demotuwei.demotuwei.config.Convert;
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
import java.util.stream.Collectors;

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
    @JsonDeserialize(using = Convert.class)
    private List<HobbyEnum> hobby;

    public String getHobby() {
        return hobby.stream().map(HobbyEnum::getCode).collect(Collectors.joining(","));
    }
}
