package com.demotuwei.demotuwei.interfaces.controller;

import com.demotuwei.demotuwei.interfaces.dto.DeleteDto;
import com.demotuwei.demotuwei.interfaces.dto.QueryDto;
import com.demotuwei.demotuwei.interfaces.dto.UserDto;
import com.demotuwei.demotuwei.service.UserService;
import com.demotuwei.demotuwei.uitl.JsonUtil;
import com.demotuwei.demotuwei.uitl.PageResult;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    @JsonView({UserDto.retForOne.class})
    @RequestMapping(value = "/listPage", method = RequestMethod.POST)
    public PageResult<List<UserDto>> listByPage(@RequestBody QueryDto queryDto) {
        PageResult<List<UserDto>> ret = userService.listPage(queryDto);
        return ret;
    }

    @JsonView({UserDto.retForOne.class})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserDto> list1() {
        List<UserDto> ret = userService.list();
        return ret;
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    public List<UserDto> list2() {
        List<UserDto> ret = userService.list();
        return ret;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Integer add(@RequestBody@Validated UserDto userDto) {

        ObjectMapper objectMapper = JsonUtil.objectMapper;
        userService.save(userDto);
        return 1;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Integer update(@RequestBody @Validated({UserDto.update.class}) UserDto role) {
        userService.update(role);
        return 1;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Integer update(@RequestBody DeleteDto ids) {
        userService.delete(ids.getIds());
        return 1;
    }

}
