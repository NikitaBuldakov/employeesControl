package Entity;

import DataBaseConnection.JDBCPostgreSQLConnector;
import Mapper.ProjectMapper;
import Mapper.TeamMapper;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private long id;
    public long team_id;
    public List<Employee> employeeList;
    protected String sql;

    public Team(long id, Employee employee, long team_id){
        this.id = id;
        this.employeeList.add(employee);
        this.team_id = team_id;
    }

    public Team(long id, List<Employee> employeeList, long team_id){
        this.id = id;
        this.employeeList = employeeList;
        this.team_id = team_id;
    }

    public Team(long id, long team_id){
        this.id = id;
        this.team_id = team_id;
    }

    @SneakyThrows
    public void selectProject(long team_id, int depth){
        sql = "SELECT * FROM team WHERE id = " + team_id;
        TeamMapper teamMapper = new TeamMapper();
        try {
            this.clone(teamMapper.mapRow(JDBCPostgreSQLConnector.select(sql, ""), depth));
        }catch (NullPointerException e){
            this.id = team_id;
        }
    }

    public void clone(Team team){
        this.id = team.id;
        this.team_id = team.team_id;
        this.employeeList = team.employeeList;
    }
}
