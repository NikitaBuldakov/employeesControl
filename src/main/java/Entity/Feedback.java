package Entity;

import DataBaseConnection.JDBCPostgreSQLConnector;
import Mapper.FeedbackMapper;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private long id;
    public String description;
    public Date dateOfCreating;
    protected String sql;

    public Feedback(String description, Date dateOfCreating){
        this.description = description;
        this.dateOfCreating = dateOfCreating;
    }


    @SneakyThrows
    public void addNewFeedback(){
        sql = "INSERT INTO feedback(description, dateofcreating)" +
                " VALUES( '" +
                description + "', " +
                dateOfCreating.getTime() + ") RETURNING id";
        setId(JDBCPostgreSQLConnector.insert(sql,""));
    }

    @SneakyThrows
    public boolean deleteFeedback(){
        sql = "DELETE FROM feedback WHERE id = " + this.id + ";";

        return JDBCPostgreSQLConnector.delete(sql,"");
    }

    @SneakyThrows
    public boolean updateFeedBack(){
        sql = "UPDATE feedback SET description = '" + description + "', dateofcreating = "
                + dateOfCreating.getTime() +" WHERE id = " + id;

        return JDBCPostgreSQLConnector.update(sql, "");
    }

    @SneakyThrows
    public void selectFeedBack(long feedback_id){
        sql = "SELECT * FROM feedback WHERE id = " + feedback_id;
        FeedbackMapper feedbackMapper = new FeedbackMapper();
        try {
            this.clone(feedbackMapper.mapRow(JDBCPostgreSQLConnector.select(sql, ""), 0));
        }catch (NullPointerException e){
            this.id = feedback_id;
        }
    }

    @SneakyThrows
    public void getLast(){
        sql = "SELECT * FROM feedback ORDER BY id DESC LIMIT 1";
        FeedbackMapper feedbackMapper = new FeedbackMapper();
        this.clone(feedbackMapper.mapRow(JDBCPostgreSQLConnector.select(sql,""),0));
    }

    public void clone(Feedback feedback){
        this.id = feedback.id;
        this.dateOfCreating = feedback.dateOfCreating;
        this.description = feedback.description;
    }
}
