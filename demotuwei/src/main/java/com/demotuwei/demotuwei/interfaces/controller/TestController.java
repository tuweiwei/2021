package com.demotuwei.demotuwei.interfaces.controller;

import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@RestController
@Validated
@RequestMapping("/user")
public class TestController {

    @GetMapping("/user")
    public ResponseEntity<Object> getUser(@NotBlank(message="手机号不能为空")
                                          @RequestParam String phone,

                                          @NotEmpty(message="用户名不能为空")
                                          @RequestParam String username,

                                          @Range(min = 1, max = 200, message = "年龄范围为1-200")
                                          @RequestParam Integer age)throws Exception {

        return ResponseEntity.ok().build();
    }
}
