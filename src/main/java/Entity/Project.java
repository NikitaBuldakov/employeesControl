package Entity;

import CustomException.ExceptionHandler;
import DataBaseConnection.JDBCPostgreSQLConnector;
import Mapper.EmployeeMapper;
import Mapper.ListMapper;
import Mapper.ProjectMapper;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private long id;
    public String nameOfProject;
    public String customer;
    public String duration;
    public String methodology;
    public String projectManager;
    public Team team;
    protected String sql = "";

    public Project(String nameOfProject, String customer, String duration, String methodology,
                   String projectManager, Team team) {
        this.nameOfProject = nameOfProject;
        this.customer = customer;
        this.duration = duration;
        this.methodology = methodology;
        this.projectManager = projectManager;
        this.team = team;
    }

}
