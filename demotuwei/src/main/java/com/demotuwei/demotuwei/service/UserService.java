package com.demotuwei.demotuwei.service;

import com.demotuwei.demotuwei.interfaces.dto.QueryDto;
import com.demotuwei.demotuwei.interfaces.dto.UserDto;
import com.demotuwei.demotuwei.entity.User;
import com.demotuwei.demotuwei.mapper.UserMapper;
import com.demotuwei.demotuwei.uitl.PageResult;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.*;

@Service
public class UserService {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> list() {
        List<User> users = userMapper.select();


        List<UserDto> objects = new ArrayList<>();
        for (User e : users) {

            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(e, userDto);
            objects.add(userDto);
        }
        return objects;
    }

    public void save(UserDto userDto) {

        if (Objects.isNull(userDto.getId())) {
            userDto.setId(new BigInteger(UUID.randomUUID().toString().substring(1, 6).replaceAll("-", "").replaceAll("[A-Za-z]", "")));
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        userMapper.insert(user);
    }

    public void update(UserDto role) {
        User user = new User();
        BeanUtils.copyProperties(role, user);

        userMapper.update(user);

    }

    public void delete(List<String> ids) {
        userMapper.delete(ids);
    }

    public PageResult<List<UserDto>> listPage(QueryDto queryDto) {
        List<User> users = userMapper.selectPage(queryDto);
        PageResult<List<UserDto>> objectPageResult = new PageResult<>();

        if (CollectionUtils.isEmpty(users)) {
            return objectPageResult;
        }

        List<UserDto> objects = new ArrayList<>();
        for (User e : users) {

            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(e, userDto);
            objects.add(userDto);
        }

        Long aLong = userMapper.selectPageCount(queryDto);
        objectPageResult.setData(objects);
        objectPageResult.setTotal(aLong);
        return objectPageResult;

    }
}
