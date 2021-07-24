package Mapper;

import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper <E>{
    @SneakyThrows
    <E> E mapRow(ResultSet resultSet, int depth);
}
