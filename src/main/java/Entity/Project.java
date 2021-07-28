package Entity;

import CustomException.ExceptionHandler;
import DataBaseConnection.JDBCPostgreSQLConnector;
import Mapper.EmployeeMapper;
import Mapper.ListMapper;
import Mapper.ProjectMapper;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@NoArgsConstructor
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

    @SneakyThrows
    public void addProject(){
        sql = "INSERT INTO project(name, customer, duration, methodology, projectmanager, team)" +
                " VALUES( '" +
                nameOfProject + "', '" +
                customer  + "', '" +
                duration + "', '" +
                methodology + "', '" +
                projectManager + "', " +
                team.getTeam_id()+ ") RETURNING id";
        setId(JDBCPostgreSQLConnector.insert(sql,Project.class
                + " add method: the method in which the class was called "));
    }

    @SneakyThrows
    public boolean deleteProject(){
        sql = "DELETE FROM project WHERE id = " + this.id + ";";

        return JDBCPostgreSQLConnector.delete(sql,Project.class
                + " delete method: the method in which the class was called ");
    }

    @SneakyThrows
    public boolean updateProject(){
        sql = "UPDATE project SET name = '" +
                nameOfProject + "', customer = '" +
                customer  + "', duration = '" +
                duration + "', methodology = '" +
                methodology + "', projectmanager = '" +
                projectManager + "', team = " +
                team.getTeam_id() + "WHERE id = " + id;

        return JDBCPostgreSQLConnector.update(sql, Project.class
                + " update method: the method in which the class was called ");
    }


    @SneakyThrows
    public void selectProject(long project_id, int depth){
        sql = "SELECT * FROM project WHERE id = " + project_id;
        ProjectMapper projectMapper = new ProjectMapper();
        try {
            this.clone(projectMapper.mapRow(JDBCPostgreSQLConnector.select(sql, Project.class
                    + " select method: the method in which the class was called "), depth));
        }catch (NullPointerException e){
            this.id = project_id;
        }
    }

    @SneakyThrows
    public void getLast(){
        sql = "SELECT * FROM project ORDER BY id DESC LIMIT 1";
        ProjectMapper projectMapper = new ProjectMapper();
        try {
            this.clone(projectMapper.mapRow(JDBCPostgreSQLConnector.select(sql, Project.class
                    + " getLast method: the method in which the class was called "), 0));
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public List<Project> selectAll(){
        sql = "SELECT * FROM project";

        List<Project> projectList = new ArrayList<>();
        ListMapper listMapper = new ListMapper();
        ProjectMapper projectMapper = new ProjectMapper();


        List<Long> idArray = listMapper.mapRow(JDBCPostgreSQLConnector.selectAll(sql,Project.class
                + " select method: the method in which the class was called " +
                " Bad sql request or nothing to return"), 0);

        for (int i = 0; i < idArray.size() - 1; i++)
            projectList.add(projectMapper.mapRow(JDBCPostgreSQLConnector
                    .select("SELECT * FROM project WHERE id = " + idArray.get(i), Project.class
                            + " selectAll method: the method in which the class was called "
                            + " Bad sql request or nothing to return"), 0));

        return projectList;
    }

    @SneakyThrows
    public void clone(Project project){
        this.id = project.id;
        this.nameOfProject = project.nameOfProject;
        this.customer = project.customer;
        this.duration = project.duration;
        this.methodology = project.methodology;
        this.projectManager = project.projectManager;
        this.team = project.team;
    }
}
