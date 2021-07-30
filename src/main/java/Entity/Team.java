package Entity;

import CustomException.ExceptionHandler;
import DataBaseConnection.JDBCPostgreSQLConnector;
import Mapper.EmployeeMapper;
import Mapper.ListMapper;
import Mapper.ProjectMapper;
import Mapper.TeamMapper;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private long id;
    public long team_id;
    public List<Employee> employeeList = new ArrayList<>();
    protected String sql;

    public Team(long team_id, Employee employee){
        this.id = id;
        this.employeeList.add(employee);
        this.team_id = team_id;
    }

    public Team(  long team_id, List<Employee> employeeList){
        this.id = id;
        this.employeeList = employeeList;
        this.team_id = team_id;
    }

    public Team(long team_id){
        this.id = id;
        this.team_id = team_id;
    }

}
