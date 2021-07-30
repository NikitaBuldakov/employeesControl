import org.buldakov.employeeControl.Config.SpringConfig;
import org.buldakov.employeeControl.Entity.Employee;
import org.buldakov.employeeControl.Entity.EmployeeEnum.EnglishMastery;
import org.buldakov.employeeControl.Entity.EmployeeEnum.LevelOfDeveloper;
import org.buldakov.employeeControl.Entity.Feedback;
import org.buldakov.employeeControl.Entity.Project;
import org.buldakov.employeeControl.Entity.Team;
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
    private GregorianCalendar birthDate;
    private GregorianCalendar dateOfEmployment;
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
        employeeService = ctx.getBean(EmployeeService.class);
        employeeList = new ArrayList<>();
        modifiedList = new ArrayList<>();
        project.setId(1);
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

    @Test
    public void testAddedNewEmployee(){
        employeeList = employeeService.findAllUsers();
        employeeService.saveUser(employee);
        modifiedList = employeeService.findAllUsers();
        assertTrue(modifiedList.size() > employeeList.size());
    }


    @Test
    public void testSelectEmployee(){
    }

    @Test
    public void testDeleteEmployee(){
    }

    @Test
    public void tetsUpdateEmployee(){
    }

    @Test
    public void testSelectAllEmployee(){
    }
}
