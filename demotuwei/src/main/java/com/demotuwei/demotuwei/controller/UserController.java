package com.demotuwei.demotuwei.controller;

import com.demotuwei.demotuwei.dto.UserDto;
import com.demotuwei.demotuwei.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

// 角色控制器
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> findAllRoles() {
        List<UserDto> ret = userService.list();
        return ret;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Integer add(@RequestBody UserDto role) {
        userService.save(role);
        return 1;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Integer update(@RequestBody UserDto role) {
        userService.update(role);
        return 1;
    }

}
