package Mapper;

import DataBaseConnection.JDBCPostgreSQLConnector;
import Entity.Employee;
import Entity.Team;
import lombok.SneakyThrows;

import java.sql.ResultSet;


public class TeamMapper implements RowMapper<Team>{
    @Override
    @SneakyThrows
    public Team mapRow(ResultSet resultSet, int depth){
        if(depth<=3) {
            Team team = new Team();
            depth++;
            boolean check = true;
            Employee employee = new Employee();
            team.setId(resultSet.getLong("id"));
            team.setTeam_id(resultSet.getLong("team_id"));
            while (check) {
                employee.selectEmployee(resultSet.getLong("employee_id"), depth);
                team.employeeList.add(employee);
                while (!resultSet.next()){
                    check = false;
                    break;
                }
            }
            return team;
        }
        return null;
    }
}
