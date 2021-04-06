package com.cnbg.zs.ebook.core.handler;


import com.cnbg.zs.ebook.common.lang.RESUtils;
import com.cnbg.zs.ebook.core.alias.CryptType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/9 22:52
* @Description
*/
@MappedTypes(CryptType.class)
public class CryptTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String parameter, JdbcType jdbcType) throws SQLException {
        String encrypt = RESUtils.encrypt(parameter);
        preparedStatement.setString(i, encrypt);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String parameter = resultSet.getString(columnName);
        return RESUtils.decrypt(parameter);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String parameter = resultSet.getString(i);
        return RESUtils.decrypt(parameter);
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String parameter = callableStatement.getString(i);
        return RESUtils.decrypt(parameter);
    }
}
