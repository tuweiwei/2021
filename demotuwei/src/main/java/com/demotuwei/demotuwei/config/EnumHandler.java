package com.demotuwei.demotuwei.config;

import com.demotuwei.demotuwei.enums.GayEnum;
import com.demotuwei.demotuwei.enums.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

public class EnumHandler<E extends BaseEnumOfKeyValue> extends BaseTypeHandler<E> {

    private Class<E> type;
    private E[] enums;

    public EnumHandler(Class<E> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
                                    JdbcType jdbcType) throws SQLException {
        System.out.println("worieiieiieie");
        ps.setObject(i, parameter.getCode(), jdbcType.TYPE_CODE);
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Object i = rs.getObject(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的value值，定位PersonType子类
            return locateEnumStatus(i);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放String类型
        Object i = rs.getObject(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的value值，定位PersonType子类
            return locateEnumStatus(i);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放String类型
        Object i = cs.getObject(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的value值，定位PersonType子类
            return locateEnumStatus(i);
        }
    }

    /**
     * 枚举类型转换，由于构造函数获取了枚举的子类enums，让遍历更加高效快捷
     * @param value 数据库中存储的自定义value属性
     * @return value对应的枚举类
     */
    private E locateEnumStatus(Object value) {
        for(E e : enums) {
            if(e.getCode().equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + value + ",请核对" + type.getSimpleName());
    }
}
