package com.demotuwei.demotuwei.dto;

import com.demotuwei.demotuwei.config.Enum2String;
import com.demotuwei.demotuwei.config.Filed2Enum;
import com.demotuwei.demotuwei.enums.FeeEnum;
import com.demotuwei.demotuwei.enums.GayEnum;
import com.demotuwei.demotuwei.enums.HobbyEnum;
import com.demotuwei.demotuwei.enums.SexEnum;
import com.demotuwei.demotuwei.uitl.ResultVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class UserDto {
    @NotNull(groups = update.class, message = "请传入主键ID")
    private BigInteger id;

    @JsonView({retForOne.class})
    @NotBlank(message = "username不能为空")
    private String username;

    @JsonView({retForOne.class})
    private SexEnum sex;

    @JsonView({retForOne.class})
    @Range(min = 0, max = 100, message = "age不能大于100小于0")
    private Integer age;

    @JsonView({retForOne.class})
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    @JsonView({retForOne.class})
    private GayEnum gay;

    @JsonDeserialize(using = Filed2Enum.class)
    @JsonSerialize(using = Enum2String.class)
    private List<HobbyEnum> hobby;

    @JsonDeserialize(using = Filed2Enum.class)
    @JsonSerialize(using = Enum2String.class)
    private List<FeeEnum> fee;



    public interface insert extends Default {}
    public interface update extends Default {}

    public interface retForOne extends ResultVo.JsonViewProfile.All {}
    public interface retForTwo extends retForOne{}
}
