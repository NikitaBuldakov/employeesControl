package Service;


import DAO.TeamDAOImpl;
import Entity.Team;

import java.util.List;


public class TeamService {

    private TeamDAOImpl teamDAO = new TeamDAOImpl();

    public TeamService(TeamDAOImpl teamDAO){
        this.teamDAO = teamDAO;
    }

    public TeamService(){}

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
