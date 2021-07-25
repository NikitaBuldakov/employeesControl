package Mapper;

import Entity.Project;
import Entity.Team;
import lombok.SneakyThrows;

import java.sql.ResultSet;


public class ProjectMapper implements RowMapper<Project> {
    @Override
    @SneakyThrows
    public Project mapRow(ResultSet resultSet, int depth){

        if(depth<=3){
            depth++;
            Project project = new Project();
            Team team = new Team();

            team.selectTeam(resultSet.getLong("team"), depth);

            project.setId(resultSet.getLong("id"));
            project.setNameOfProject(resultSet.getString("name"));
            project.setCustomer(resultSet.getString("customer"));
            project.setDuration(resultSet.getString("duration"));
            project.setMethodology(resultSet.getString("methodology"));
            project.setProjectManager(resultSet.getString("projectmanager"));
            project.setTeam(team);

            return project;
        }
        return null;
    }
}
