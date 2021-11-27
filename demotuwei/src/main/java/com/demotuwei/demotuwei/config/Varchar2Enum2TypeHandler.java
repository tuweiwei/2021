package com.demotuwei.demotuwei.config;

import com.demotuwei.demotuwei.enums.FeeEnum;
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

public class Varchar2Enum2TypeHandler extends BaseTypeHandler<List<FeeEnum>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<FeeEnum> es, JdbcType jdbcType) throws SQLException {
        String str = es.stream().map(el -> String.valueOf(el.getCode())).collect(Collectors.joining(","));
        preparedStatement.setString(i, str);
    }

    @Override
    public List<FeeEnum> getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String s = resultSet.getString(columnName);
        String[] strings = StringUtils.delimitedListToStringArray(s, ",");

        List<FeeEnum> collect = Arrays.stream(strings).map(el -> FeeEnum.getByCode(Integer.valueOf(el))).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<FeeEnum> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String s = resultSet.getString(i);
        String[] strings = StringUtils.delimitedListToStringArray(s, ",");

        List<FeeEnum> collect = Arrays.stream(strings).map(el -> FeeEnum.getByCode(Integer.valueOf(el))).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<FeeEnum> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String s = callableStatement.getString(i);
        String[] strings = StringUtils.delimitedListToStringArray(s, ",");

        List<FeeEnum> collect = Arrays.stream(strings).map(el -> FeeEnum.getByCode(Integer.valueOf(el))).collect(Collectors.toList());

        return collect;
    }
}
