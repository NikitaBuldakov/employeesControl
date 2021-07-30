package org.buldakov.employeeControl.Service;

import org.buldakov.employeeControl.DAO.ProjectDAOImpl;
import org.buldakov.employeeControl.Entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectService {
    @Autowired
    private final ProjectDAOImpl projectDAO;

    public ProjectService(ProjectDAOImpl projectDAO){
        this.projectDAO = projectDAO;
    }

    public Project findUser(int id) {
        return projectDAO.findById(id);
    }

    public void saveUser(Project project) {
        projectDAO.save(project);
    }

    public void deleteUser(Project project) {
        projectDAO.delete(project);
    }

    public void updateUser(Project project) {
        projectDAO.update(project);
    }

    public List<Project> findAllUsers() {
        return projectDAO.findAll();
    }
}

