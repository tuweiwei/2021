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
public class QueryDto {

    private String username;

    private Integer curPage;
    private Integer pageSize;
}
