import Entity.Employee;
import Entity.Team;
import HibernateUtil.HibernateSessionFactoryUtil;
import Service.EmployeeService;
import Service.TeamService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTests {

    private Team team;
    private Employee employee;
    private List<Employee> employeeList;
    private List<Team> teamList;
    private List<Team> modifiedList;
    private TeamService teamService;
    private EmployeeService employeeService;


    @Before
    public void setUp(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        team = (Team) applicationContext.getBean("team");
        employee = (Employee) applicationContext.getBean("employee");
        employeeList = new ArrayList<>();
        teamList = new ArrayList<>();
        modifiedList = new ArrayList<>();
        teamService = (TeamService) applicationContext.getBean("teamService");
        employeeService = (EmployeeService) applicationContext.getBean("employeeService");
        employeeList = employeeService.findAllEmployees();
    }

    @AfterClass
    public static void destroy(){
        HibernateSessionFactoryUtil.shutdown();
    }

    @Test
    public void testAddedNewTeam(){
        team.setTeam_id(5);
        team.setEmployeeList(employeeList);
        teamList = teamService.findAllTeams();
        teamService.saveTeam(team);
        modifiedList = teamService.findAllTeams();
        assertTrue(modifiedList.size() > teamList.size());
    }


    @Test
    public void testSelectTeam(){
        team = teamService.findTeam(2);
        assertNotNull(team);
        assertEquals(2,team.getId());
    }

    @Test
    public void testDeleteTeam(){
        team = teamService.findTeam(2);
        List<Employee> modifiedEmployeeList = employeeService.findAllEmployees();
        team.setEmployeeList(modifiedEmployeeList);
        team.setTeam_id(10);
        teamService.saveTeam(team);
        teamList = teamService.findAllTeams();
        teamService.deleteTeam(team);
        modifiedList = teamService.findAllTeams();
        assertTrue(modifiedList.size() < teamList.size());
    }

    @Test
    public void testUpdateTeam(){
        team = teamService.findTeam(2);
        List<Employee> modifiedEmployeeList = employeeService.findAllEmployees();
        team.setEmployeeList(modifiedEmployeeList);
        teamService.updateTeam(team);
        Team modified = teamService.findTeam(2);
        assertNotNull(modified.getEmployeeList());

    }

    @Test
    public void testSelectAllTeams(){
        teamList = teamService.findAllTeams();
        assertTrue(teamList.size() > 0);
    }
}

