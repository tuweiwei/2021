package com.demotuwei.demotuwei.interfaces.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryDto {

    private String username;

    private Integer curPage;
    private Integer pageSize;
}
