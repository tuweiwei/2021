package com.demotuwei.demotuwei.uitl;

import com.demotuwei.demotuwei.enums.ResultCodeEnum;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class ResultVo<T> {
    private Integer code;
    private String msg;

    @JsonView(JsonViewProfile.All.class)
    private T data;

    public ResultVo() {
    }

    public ResultVo(T data) {
        this(ResultCodeEnum.SUCCESS, data);
    }

    public ResultVo(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
    }

    public ResultVo(ResultCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
        this.data = data;
    }

    public interface JsonViewProfile {
        interface All {}
    }


}
