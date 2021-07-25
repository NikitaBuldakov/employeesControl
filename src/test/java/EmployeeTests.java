import DataBaseConnection.ConnectionPool;
import DataBaseConnection.JDBCPostgreSQLConnector;
import Entity.Employee;
import Entity.EmployeeEnum.EnglishMastery;
import Entity.EmployeeEnum.LevelOfDeveloper;
import Entity.Feedback;
import Entity.Project;
import org.junit.*;

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

    @BeforeClass
    public static void init(){
        JDBCPostgreSQLConnector.initConnection();
    }

    @Before
    public void setUp(){
        employee = new Employee();
        employeeList = new ArrayList<>();
        Project project = new Project();
        project.setId(1);
        Feedback feedback = new Feedback();
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

    @AfterClass
    public static void destroy() throws SQLException {
        JDBCPostgreSQLConnector.closeConnection();
    }

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
}
