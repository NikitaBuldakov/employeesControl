import org.buldakov.employeeControl.Config.SpringConfig;
import org.buldakov.employeeControl.Entity.Employee;
import org.buldakov.employeeControl.Entity.Project;
import org.buldakov.employeeControl.Entity.Team;
import org.buldakov.employeeControl.HibernateUtil.HibernateSessionFactoryUtil;
import org.buldakov.employeeControl.Service.EmployeeService;
import org.buldakov.employeeControl.Service.TeamService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringConfig.class);
        ctx.scan("org.buldakov.employeeControl");
        ctx.refresh();

        team = ctx.getBean(Team.class);
        employee = ctx.getBean(Employee.class);
        employeeList = new ArrayList<>();
        teamList = new ArrayList<>();
        modifiedList = new ArrayList<>();
        teamService = ctx.getBean(TeamService.class);
        employeeService = ctx.getBean(EmployeeService.class);
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
