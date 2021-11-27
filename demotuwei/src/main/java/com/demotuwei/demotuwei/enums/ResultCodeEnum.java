package com.demotuwei.demotuwei.enums;

public enum ResultCodeEnum {
    SUCCESS(0, "操作成功"),
    FAILED(-1, "服务器出错"),

    VALIDATE_FAILED(3000, "参数校验失败"),
    REQUEST_TYPE_ERROR(4000, "请求类型错误"),
    USERNAME_ERROR(5000, "用户名错误"),
    PASSWORD_ERROR(6000, "密码错误"),
    USERNAME_OR_PASSWORD_ERROR(7000, "账号或密码错误，请重新输入"),
    TOKEN_INVALID(8000, "权限错误"),
    INTER_NET_ERROR(9000, "网络错误")
    ;

    private int code;
    private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
