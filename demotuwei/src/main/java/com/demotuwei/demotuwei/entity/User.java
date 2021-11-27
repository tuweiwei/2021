package com.demotuwei.demotuwei.entity;

import com.demotuwei.demotuwei.enums.FeeEnum;
import com.demotuwei.demotuwei.enums.GayEnum;
import com.demotuwei.demotuwei.enums.HobbyEnum;
import com.demotuwei.demotuwei.enums.SexEnum;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.JdbcType;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class User {

    private BigInteger id;
    private String username;
    private SexEnum sex;
    private Integer age;
    private String phone;
    private Date date;
    private GayEnum gay;

    private List<HobbyEnum> hobby;
    private List<FeeEnum> fee;

}
