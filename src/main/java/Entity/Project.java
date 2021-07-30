package Entity;

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
