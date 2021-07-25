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

    @SneakyThrows
    public void addNewTeam(){
        for ( int i = 0; i < employeeList.size() - 1;i++) {
            Employee employee = employeeList.get(i);
            sql = "INSERT INTO team(team_id, employee_id)" +
                    " VALUES( " +
                    team_id + ", " +
                    employee.getId() +") RETURNING id";
            setId(JDBCPostgreSQLConnector.insert(sql, Team.class
                    + " add method: the method in which the class was called "));
        }
    }

    @SneakyThrows
    public boolean deleteTeam(){
        sql = "DELETE FROM team WHERE team_id = " + this.team_id;

        return JDBCPostgreSQLConnector.delete(sql,Team.class
                + " delete method: the method in which the class was called ");
    }

    @SneakyThrows
    public boolean updateTeam(){
        for ( int i = 0; i < employeeList.size();i++) {
            Employee employee = employeeList.get(i);
            sql = "UPDATE team SET team_id = " + team_id + ", employee_id = "
                    + employee.getId() +" WHERE id = " + id;
            return JDBCPostgreSQLConnector.update(sql, Team.class
                    + " update method: the method in which the class was called ");
        }
        return false;
    }


    @SneakyThrows
    public void selectTeam(long team_id, int depth){
        sql = "SELECT * FROM team WHERE team_id = " + team_id;
        TeamMapper teamMapper = new TeamMapper();
        try {
            this.clone(teamMapper.mapRow(JDBCPostgreSQLConnector.select(sql, Team.class
                    + " select method: the method in which the class was called "), depth));
        }catch (NullPointerException e){
            this.id = team_id;
        }
    }

    @SneakyThrows
    public void getLast(){
        sql = "SELECT * FROM team ORDER BY id DESC LIMIT 1";
        TeamMapper teamMapper = new TeamMapper();
        try {
            this.clone(
                    teamMapper.mapRow(
                    JDBCPostgreSQLConnector.select(sql, Team.class
                    + " getLast method: the method in which the class was called "), 0));
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public List<Team> selectAll(){
        sql = "SELECT * FROM team";

        List<Team> teamList = new ArrayList<>();
        ListMapper listMapper = new ListMapper();
        TeamMapper teamMapper = new TeamMapper();


        List<Long> idArray = listMapper.mapRow(JDBCPostgreSQLConnector.selectAll(sql,Employee.class
                + " select method: the method in which the class was called " +
                " Bad sql request or nothing to return"), 0);

        for (int i = 0; i < idArray.size() - 1; i++)
            teamList.add(teamMapper.mapRow(JDBCPostgreSQLConnector
                    .select("SELECT * FROM team WHERE id = " + idArray.get(i), Employee.class + " getLast method: the method in which the class was called "
                            + " Bad sql request or nothing to return"), 0));

        return teamList;
    }

    public void clone(Team team){
        this.id = team.id;
        this.team_id = team.team_id;
        this.employeeList = team.employeeList;
    }
}
