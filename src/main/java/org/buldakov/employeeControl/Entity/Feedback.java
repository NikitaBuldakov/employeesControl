package org.buldakov.employeeControl.Entity;

import org.buldakov.employeeControl.CustomException.ExceptionHandler;
import org.buldakov.employeeControl.DataBaseConnection.JDBCPostgreSQLConnector;
import org.buldakov.employeeControl.Mapper.FeedbackMapper;
import org.buldakov.employeeControl.Mapper.ListMapper;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Component
public class Feedback {
    private long id;
    public String description;
    public Date dateOfCreating;
    protected String sql;

    public Feedback(String description, Date dateOfCreating){
        this.description = description;
        this.dateOfCreating = dateOfCreating;
    }

    public Feedback(){
    }


    @SneakyThrows
    public void addNewFeedback(){
        sql = "INSERT INTO feedback(description, dateofcreating)" +
                " VALUES( '" +
                description + "', " +
                dateOfCreating.getTime() + ") RETURNING id";
        setId(JDBCPostgreSQLConnector.insert(sql,Feedback.class
                + " add method: the method in which the class was called "));
    }

    @SneakyThrows
    public boolean deleteFeedback(){
        sql = "DELETE FROM feedback WHERE id = " + this.id + ";";

        return JDBCPostgreSQLConnector.delete(sql,Feedback.class
                + " delete method: the method in which the class was called ");
    }

    @SneakyThrows
    public boolean updateFeedBack(){
        sql = "UPDATE feedback SET description = '" + description + "', dateofcreating = "
                + dateOfCreating.getTime() +" WHERE id = " + id;

        return JDBCPostgreSQLConnector.update(sql, Feedback.class
                + " update method: the method in which the class was called ");
    }

    @SneakyThrows
    public void selectFeedBack(long feedback_id){
        sql = "SELECT * FROM feedback WHERE id = " + feedback_id;
        FeedbackMapper feedbackMapper = new FeedbackMapper();
        try {
            this.clone(feedbackMapper.mapRow(JDBCPostgreSQLConnector.select(sql, Feedback.class
                    + " select method: the method in which the class was called "), 0));
        }catch (NullPointerException e){
            this.id = feedback_id;
        }
    }

    @SneakyThrows
    public void getLast(){
        sql = "SELECT * FROM feedback ORDER BY id DESC LIMIT 1";
        FeedbackMapper feedbackMapper = new FeedbackMapper();
        this.clone(feedbackMapper.mapRow(JDBCPostgreSQLConnector.select(sql,Feedback.class
                + " getLast method: the method in which the class was called "),0));
    }

    @SneakyThrows
    public List<Feedback> selectAll(){
        sql = "SELECT * FROM feedback";

        List<Feedback> feedbackList = new ArrayList<Feedback>();
        ListMapper listMapper = new ListMapper();
        FeedbackMapper feedbackMapper = new FeedbackMapper();


        List<Long> idArray = listMapper.mapRow(JDBCPostgreSQLConnector.selectAll(sql,Feedback.class
                + " select method: the method in which the class was called " +
                " Bad sql request or nothing to return"), 0);

        for (int i = 0; i < idArray.size() - 1; i++)
            feedbackList.add(feedbackMapper.mapRow(JDBCPostgreSQLConnector
                    .select("SELECT * FROM feedback WHERE id = " + idArray.get(i), Feedback.class
                            + " SelectAll method: the method in which the class was called "
                            + " Bad sql request or nothing to return"), 0));

        return feedbackList;
    }

    @SneakyThrows
    public void clone(Feedback feedback){
        if(feedback == null)
           throw new ExceptionHandler("The class in which the error was flown: " + Feedback.class
                   + ", an empty instance of the class was passed when the object was instantiated",
                   new NullPointerException());
        this.id = feedback.id;
        this.dateOfCreating = feedback.dateOfCreating;
        this.description = feedback.description;
    }
}
