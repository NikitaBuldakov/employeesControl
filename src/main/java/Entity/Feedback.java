package Entity;

import CustomException.ExceptionHandler;
import DataBaseConnection.JDBCPostgreSQLConnector;
import Mapper.EmployeeMapper;
import Mapper.FeedbackMapper;
import Mapper.ListMapper;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


}
