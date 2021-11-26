package com.demotuwei.demotuwei.config;

import com.demotuwei.demotuwei.enums.HobbyEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringHandler extends BaseTypeHandler<List<HobbyEnum>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<HobbyEnum> hobbyEnums, JdbcType jdbcType) throws SQLException {
        String str = hobbyEnums.stream().map(el -> el.getCode()).collect(Collectors.joining(","));
        preparedStatement.setString(i, str);
    }

    @Override
    public List<HobbyEnum> getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String s = resultSet.getString(columnName);
        String[] strings = StringUtils.delimitedListToStringArray(s, ",");
        List<HobbyEnum> collect = Arrays.stream(strings).map(el -> HobbyEnum.getByCode(el)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<HobbyEnum> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String s = resultSet.getString(i);
        String[] strings = StringUtils.delimitedListToStringArray(s, ",");
        List<HobbyEnum> collect = Arrays.stream(strings).map(el -> HobbyEnum.getByCode(el)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<HobbyEnum> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String s = callableStatement.getString(i);
        String[] strings = StringUtils.delimitedListToStringArray(s, ",");
        List<HobbyEnum> collect = Arrays.stream(strings).map(el -> HobbyEnum.getByCode(el)).collect(Collectors.toList());

        return collect;
    }
}
