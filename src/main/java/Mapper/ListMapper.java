package Mapper;


import CustomException.ExceptionHandler;
import Entity.Employee;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListMapper implements RowMapper<Long>{

    @SneakyThrows
    @Override
    public ArrayList<Long> mapRow(ResultSet resultSet, int depth) {

        ArrayList<Long> indexesArray = new ArrayList<>();

        boolean check = true;
        while (check) {
            try{
                indexesArray.add(resultSet.getLong("team_id"));
            }catch (SQLException e){
                indexesArray.add(resultSet.getLong("id"));
            }
            while (!resultSet.next()){
                check = false;
                break;
            }
        }

        return indexesArray;
    }
}
