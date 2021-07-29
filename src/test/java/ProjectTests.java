import org.buldakov.employeeControl.Entity.Team;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectTests {

    private Team project;
    List<Team> projectList;
    Team team;

    @BeforeClass
    public static void init(){
        JDBCPostgreSQLConnector.initConnection();
    }

    @Before
    public void setUp(){
        team = new Team();
        team.selectTeam(1,0);
        project = new Team("Test project", "Test customer", "Test duration",
                "Test methodology", "Test project manager", team);
        projectList = new ArrayList<>();
    }

    @AfterClass
    public static void destroy() throws SQLException {
        JDBCPostgreSQLConnector.closeConnection();
    }

    @Test
    public void testAddedNewProject(){
        project.addProject();
        Team projectLastAdded = new Team();
        projectLastAdded.getLast();
        assertEquals(projectLastAdded.getId(),project.getId());
    }

    @Test
    public void testSelectProject(){
        project.selectProject(2,0);
        assertEquals(2, project.getId());
    }

    @Test
    public void testDeleteProject(){
        project.addProject();
        project.getLast();
        assertTrue(project.deleteProject());

    }

    @Test
    public void tetsUpdateProject(){
        project.addProject();
        project.getLast();
        project.setNameOfProject("Not test project, but test");
       assertTrue(project.updateProject());
    }

    @Test
    public void testSelectAllProject(){
        projectList = project.selectAll();
        assertNotNull(projectList);
    }
}
