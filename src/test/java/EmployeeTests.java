import DataBaseConnection.ConnectionPool;
import DataBaseConnection.JDBCPostgreSQLConnector;
import Entity.Employee;
import Entity.EmployeeEnum.EnglishMastery;
import Entity.EmployeeEnum.LevelOfDeveloper;
import org.junit.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class EmployeeTests {

    private Employee employee;
    private GregorianCalendar birthDate;
    private GregorianCalendar dateOfEmployment;

    @BeforeClass
    public static void init(){
        JDBCPostgreSQLConnector.initConnection();
    }

    @Before
    public void setUp(){
        employee = new Employee();
        birthDate = new GregorianCalendar(2000, Calendar.APRIL, 24);
        dateOfEmployment = new GregorianCalendar(2021, Calendar.AUGUST, 12);
        /*employee = new Employee("Nikita",
                "Buldakov",
                "Konstantinovich",
                "nkb5@mail.ru",
                "89533553049",
                birthDate,
                "None",
                dateOfEmployment,
                12,
                LevelOfDeveloper.J1,
                EnglishMastery.B2,
                "some skypes adress",
                11);

         */
    }

    @AfterClass
    public static void destroy() throws SQLException {
        JDBCPostgreSQLConnector.closeConnection();
    }

    @Test
    public void testAddedNewEmployee(){
        employee.addNewEmployee();
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
    }
}
