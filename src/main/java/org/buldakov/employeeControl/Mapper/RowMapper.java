package org.buldakov.employeeControl.Mapper;

import lombok.SneakyThrows;

import java.sql.ResultSet;

public interface RowMapper <E>{
    @SneakyThrows
    <E> E mapRow(ResultSet resultSet, int depth);
}
