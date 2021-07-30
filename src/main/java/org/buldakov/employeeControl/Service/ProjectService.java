package org.buldakov.employeeControl.Service;

import org.buldakov.employeeControl.DAO.ProjectDAOImpl;
import org.buldakov.employeeControl.Entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectService {
    @Autowired
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

