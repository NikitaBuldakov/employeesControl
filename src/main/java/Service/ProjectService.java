package Service;


import DAO.ProjectDAOImpl;
import Entity.Project;


import java.util.List;


public class ProjectService {

    private final ProjectDAOImpl projectDAO;

    public ProjectService(ProjectDAOImpl projectDAO){
        this.projectDAO = projectDAO;
    }

    public Project findProject(long id) {
        return projectDAO.findById(id);
    }

    public void saveProject(Project project) {
        projectDAO.save(project);
    }

    public void deleteProject(Project project) {
        projectDAO.delete(project);
    }

    public void updateProject(Project project) {
        projectDAO.update(project);
    }

    public List<Project> findAllProjects() {
        return projectDAO.findAll();
    }
}

