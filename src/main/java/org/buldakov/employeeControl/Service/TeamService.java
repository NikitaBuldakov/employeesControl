package org.buldakov.employeeControl.Service;

import org.buldakov.employeeControl.DAO.TeamDAOImpl;
import org.buldakov.employeeControl.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamService {
    @Autowired
    private final TeamDAOImpl teamDAO;

    public TeamService(TeamDAOImpl teamDAO){
        this.teamDAO = teamDAO;
    }

    public Team findTeam(long id) {
        return teamDAO.findById(id);
    }

    public void saveTeam(Team team) {
        teamDAO.save(team);
    }

    public void deleteTeam(Team team) {
        teamDAO.delete(team);
    }

    public void updateTeam(Team team) {
        teamDAO.update(team);
    }

    public List<Team> findAllTeams() {
        return teamDAO.findAll();
    }
}
