import org.buldakov.employeeControl.Config.SpringConfig;
import org.buldakov.employeeControl.Entity.Employee;
import org.buldakov.employeeControl.Entity.Feedback;
import org.buldakov.employeeControl.Entity.Project;
import org.buldakov.employeeControl.Entity.Team;
import org.buldakov.employeeControl.HibernateUtil.HibernateSessionFactoryUtil;
import org.buldakov.employeeControl.Service.EmployeeService;
import org.buldakov.employeeControl.Service.ProjectService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectTests {

    private Project project;
    private Team team;
    private List<Project> projectList;
    private List<Project> modifiedList;
    private ProjectService projectService;

    @Before
    public void setUp(){

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringConfig.class);
        ctx.scan("org.buldakov.employeeControl");
        ctx.refresh();

        project = ctx.getBean(Project.class);
        projectService = ctx.getBean(ProjectService.class);
        team = ctx.getBean(Team.class);
        projectList = new ArrayList<>();
        modifiedList = new ArrayList<>();
        team.setId(2);
        team.setTeam_id(1);

    }
    @AfterClass
    public static void destroy(){
        HibernateSessionFactoryUtil.shutdown();
    }

    @Test
    public void testAddedNewProject(){
        project = projectService.findProject(2);
        projectList = projectService.findAllProjects();
        projectService.saveProject(project);
        modifiedList = projectService.findAllProjects();
        assertTrue(modifiedList.size() > projectList.size());
    }


    @Test
    public void testSelectProject(){
        project = projectService.findProject(2);
        assertNotNull(project);
        assertEquals(2,project.getId());
    }

    @Test
    public void testDeleteProject(){
        project = projectService.findProject(2);
        projectService.saveProject(project);
        project.setTeam(team);
        modifiedList = projectService.findAllProjects();
        projectService.deleteProject(project);
        projectList = projectService.findAllProjects();
        assertTrue(modifiedList.size() > projectList.size());
    }

    @Test
    public void testUpdateProject(){
        project = projectService.findProject(2);
        project.setNameOfProject("Andersen");
        project.setTeam(team);
        projectService.updateProject(project);
        Project modified = projectService.findProject(2);
        assertEquals("Andersen",modified.getNameOfProject());
    }

    @Test
    public void testSelectAllProjects(){
        projectList = projectService.findAllProjects();
        assertTrue(projectList.size() > 0);
    }
}
