import Entity.Employee;
import Entity.EmployeeEnum.EnglishMastery;
import Entity.EmployeeEnum.LevelOfDeveloper;
import Entity.Feedback;
import Entity.Project;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;


public class EmployeeTests {

    private Employee employee;
    private GregorianCalendar birthDate;
    private GregorianCalendar dateOfEmployment;
    private List<Employee> employeeList;



    @Before
    public void setUp(){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        employee = (Employee) applicationContext.getBean("employee");
        employeeList = new ArrayList<>();
        Project project = (Project) applicationContext.getBean("project");
        project.setId(1);
        Feedback feedback = (Feedback) applicationContext.getBean("feedback");
        feedback.setId(2);
        birthDate = new GregorianCalendar(2000, Calendar.APRIL, 24);
        dateOfEmployment = new GregorianCalendar(2021, Calendar.AUGUST, 12);
        employee = new Employee("Nikita",
                "Buldakov",
                "Konstantinovich",
                "nkb5@mail.ru",
                "89533553049",
                birthDate,
                "None",
                dateOfEmployment,
                project,
                LevelOfDeveloper.J1,
                EnglishMastery.B2,
                "some skypes adress",
                feedback);


    }

/*
    @Test
    public void testAddedNewEmployee(){

        employee.addNewEmployee();
        Employee employeeLastAdded = new Employee();
        employeeLastAdded.getLastEmployee();
        assertEquals(employeeLastAdded.getId(),employee.getId());
    }


    @Test
    public void testSelectEmployee(){
        employee.selectEmployee(11, 0);
        assertEquals(11, employee.getId());
    }

    @Test
    public void testDeleteEmployee(){
        employee.addNewEmployee();
        employee.getLastEmployee();
        assertTrue(employee.deleteEmployee());
    }

    @Test
    public void tetsUpdateEmployee(){
        employee.getLastEmployee();
        employee.setName("Andrew");
        employee.updateEmployee();
        employee.getLastEmployee();
        assertEquals("Andrew",employee.getName());
    }

    @Test
    public void testSelectAllEmployee(){
        employeeList = employee.selectAll();
        assertNotNull(employeeList);
    }

 */
}
