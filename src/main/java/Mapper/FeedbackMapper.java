package Mapper;

import Entity.Feedback;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.Date;


public class FeedbackMapper implements RowMapper<Feedback> {
    @Override
    @SneakyThrows
    public Feedback mapRow(ResultSet resultSet, int depth){
        Feedback feedback = new Feedback();

        feedback.setId(resultSet.getLong("id"));
        feedback.setDescription(resultSet.getString("description"));
        feedback.setDateOfCreating(new Date(Long.parseLong(resultSet.getString("dateofcreating"))));

        return feedback;
    }
}
