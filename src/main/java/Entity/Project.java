package Entity;

import DataBaseConnection.JDBCPostgreSQLConnector;
import Mapper.ProjectMapper;
import lombok.*;

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

    @SneakyThrows
    public void selectProject(long project_id, int depth){
        sql = "SELECT * FROM project WHERE id = " + project_id;
        ProjectMapper projectMapper = new ProjectMapper();
        try {
            this.clone(projectMapper.mapRow(JDBCPostgreSQLConnector.select(sql, ""), depth));
        }catch (NullPointerException e){
            this.id = project_id;
        }
    }

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
