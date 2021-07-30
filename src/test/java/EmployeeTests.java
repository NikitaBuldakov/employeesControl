import org.buldakov.employeeControl.Config.SpringConfig;
import org.buldakov.employeeControl.Entity.Employee;
import org.buldakov.employeeControl.Entity.EmployeeEnum.EnglishMastery;
import org.buldakov.employeeControl.Entity.EmployeeEnum.LevelOfDeveloper;
import org.buldakov.employeeControl.Entity.Feedback;
import org.buldakov.employeeControl.Entity.Project;
import org.buldakov.employeeControl.Entity.Team;
import org.buldakov.employeeControl.HibernateUtil.HibernateSessionFactoryUtil;
import org.buldakov.employeeControl.Service.EmployeeService;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeTests {

    private Employee employee;
    private Project project;
    private Feedback feedback;
    private List<Employee> employeeList;
    private List<Employee> modifiedList;
    private EmployeeService employeeService;

    @Before
    public void setUp(){

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringConfig.class);
        ctx.scan("org.buldakov.employeeControl");
        ctx.refresh();

        feedback = ctx.getBean(Feedback.class);
        project = ctx.getBean(Project.class);
        employee = ctx.getBean(Employee.class);
        employeeService = ctx.getBean(EmployeeService.class);
        employeeList = new ArrayList<>();
        modifiedList = new ArrayList<>();
        project.setId(1);
        feedback.setId(2);



    }
    @AfterClass
    public static void destroy(){
        HibernateSessionFactoryUtil.shutdown();
    }

    @Test
    public void testAddedNewEmployee(){
        employee = employeeService.findEmployee(2);
        employeeList = employeeService.findAllEmployees();
        employeeService.saveEmployee(employee);
        modifiedList = employeeService.findAllEmployees();
        assertTrue(modifiedList.size() > employeeList.size());
    }


    @Test
    public void testSelectEmployee(){
        employee = employeeService.findEmployee(2);
        assertNotNull(employee);
        assertEquals(2,employee.getId());
    }

    @Test
    public void testDeleteEmployee(){
        employee = employeeService.findEmployee(2);
        employee.setFeedback(feedback);
        employee.setProject(project);
        employeeService.saveEmployee(employee);
        modifiedList = employeeService.findAllEmployees();
        employeeService.deleteEmployee(employee);
        employeeList = employeeService.findAllEmployees();
        assertTrue(modifiedList.size() > employeeList.size());
    }

    @Test
    public void testUpdateEmployee(){
        employee = employeeService.findEmployee(2);
        employee.setName("Andrew");
        employee.setFeedback(feedback);
        employee.setProject(project);
        employee.setFio(employee.generateFIO());
        employeeService.updateEmployee(employee);
        Employee modified = employeeService.findEmployee(2);
        assertEquals("Andrew", modified.getName());
    }

    @Test
    public void testSelectAllEmployee(){
        employeeList = employeeService.findAllEmployees();
        assertTrue(employeeList.size() > 0);
    }
}
