package com.demotuwei.demotuwei.mapper;

import com.demotuwei.demotuwei.dto.QueryDto;
import com.demotuwei.demotuwei.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

    List<User> select();
    void insert(User user);
    void update(User user);
    void delete(List<String> ids);
    List<User> selectPage(QueryDto queryDto);
    Long selectPageCount(QueryDto queryDto);
}
