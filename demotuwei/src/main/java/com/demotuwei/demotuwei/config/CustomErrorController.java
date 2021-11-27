package com.demotuwei.demotuwei.config;

import com.demotuwei.demotuwei.enums.ResultCodeEnum;
import com.demotuwei.demotuwei.uitl.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理框架抛出异常
 */
@RestController
public class CustomErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = ERROR_PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResultVo error(HttpServletRequest request, HttpServletResponse response) {
        int statusCode = response.getStatus();
        //获取异常信息
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        String msg = errorAttributes.getErrorAttributes(servletWebRequest, ErrorAttributeOptions.defaults()).toString();
        return new ResultVo( ResultCodeEnum.FAILED, "错误码：" +statusCode+ "," + msg);
    }
}
