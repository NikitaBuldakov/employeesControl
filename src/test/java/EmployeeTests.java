import Entity.Employee;
import Entity.Feedback;
import Entity.Project;
import HibernateUtil.HibernateSessionFactoryUtil;
import Service.EmployeeService;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
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

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        employee = (Employee) applicationContext.getBean("employee");
        employeeList = new ArrayList<>();
        project = (Project) applicationContext.getBean("project");
        project.setId(1);
        feedback = (Feedback) applicationContext.getBean("feedback");
        feedback.setId(2);
        employeeService = (EmployeeService) applicationContext.getBean("employeeService");
        employeeList = new ArrayList<>();
        modifiedList = new ArrayList<>();


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
