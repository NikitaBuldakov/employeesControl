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
        if(depth>=2) {
            Team team = new Team();
            Employee employee = new Employee();
            team.setTeam_id(resultSet.getLong("team_id"));
            while (resultSet.next()) {
                employee.selectEmployee(resultSet.getLong("employee_id"), depth++);
                team.employeeList.add(employee);
            }
            return team;
        }
        return null;
    }
}
