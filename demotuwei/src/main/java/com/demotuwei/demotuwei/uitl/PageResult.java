package com.demotuwei.demotuwei.uitl;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class PageResult<T> {
    @JsonView(ResultVo.JsonViewProfile.All.class)
    private T data;

    @JsonView(ResultVo.JsonViewProfile.All.class)
    private Long total;
}
