import org.buldakov.employeeControl.Entity.Employee;
import org.buldakov.employeeControl.Entity.Team;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TeamTests {

    private Team team;
    private Employee employee;
    private List<Employee> employeeList;
    private List<Team> teamList;

    @BeforeClass
    public static void init(){
        JDBCPostgreSQLConnector.initConnection();
    }

    @Before
    public void setUp(){
        employee = new Employee();
        employeeList = new ArrayList<>();
        teamList = new ArrayList<>();
        employeeList = employee.selectAll();
        team = new Team(2, employeeList);
    }

    @AfterClass
    public static void destroy() throws SQLException {
        JDBCPostgreSQLConnector.closeConnection();
    }

    @Test
    public void testAddedNewTeam(){
        team.addNewTeam();
        Team teamLastAdded = new Team();
        teamLastAdded.getLast();
        assertEquals(teamLastAdded.getTeam_id(), team.team_id);
    }

    @Test
    public void testSelectTeam(){
        team.selectTeam(2,0);
        assertEquals(2, team.getTeam_id());
    }

    @Test
    public void testDeleteTeam(){
        team.addNewTeam();
        team.getLast();
        assertTrue(team.deleteTeam());

    }

    @Test
    public void tetsUpdateTeam(){
        team.addNewTeam();
        team.getLast();
        team.setTeam_id(4);
        assertTrue(team.updateTeam());
    }

    @Test
    public void testSelectAllTeam(){
        teamList = team.selectAll();
        assertNotNull(teamList);
    }
}
