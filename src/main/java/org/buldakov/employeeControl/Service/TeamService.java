package org.buldakov.employeeControl.Service;

import org.buldakov.employeeControl.DAO.TeamDAOImpl;
import org.buldakov.employeeControl.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TeamService {
    @Autowired
    private final TeamDAOImpl teamDAO;

    public TeamService(TeamDAOImpl teamDAO){
        this.teamDAO = teamDAO;
    }

    public Team findUser(int id) {
        return teamDAO.findById(id);
    }

    public void saveUser(Team team) {
        teamDAO.save(team);
    }

    public void deleteUser(Team team) {
        teamDAO.delete(team);
    }

    public void updateUser(Team team) {
        teamDAO.update(team);
    }

    public List<Team> findAllUsers() {
        return teamDAO.findAll();
    }
}
