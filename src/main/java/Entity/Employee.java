package Entity;

import Mapper.EmployeeMapper;
import DataBaseConnection.JDBCPostgreSQLConnector;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.Date;
import java.util.GregorianCalendar;

@Getter
@Setter
public class Employee {

    private long id;
    public String name;
    public String surname;
    public String secondname;
    public String fio;
    public String email;
    public String phone;
    public Date birthday;
    public String workExperience;
    public Date dateOfEmployment;
    public Project project;
    public Enum levelOfDeveloper;
    public Enum englishMastery;
    public String skype;
    public Feedback feedback;
    protected String sql;

    public Employee(){}

    public Employee(String name,
                    String surname,
                    String secondname,
                    String email,
                    String phone,
                    GregorianCalendar birthday,
                    String workExperience,
                    GregorianCalendar dateOfEmployment,
                    Project project,
                    Enum levelOfDeveloper,
                    Enum englishMastery,
                    String skype,
                    Feedback feedback) {
        this.name = name;
        this.surname = surname;
        this.secondname = secondname;
        this.fio = generateFIO();
        this.email = email;
        this.phone = phone;
        this.birthday = new Date(birthday.getTimeInMillis());
        this.workExperience = workExperience;
        this.dateOfEmployment = new Date(dateOfEmployment.getTimeInMillis());
        this.project = project;
        this.levelOfDeveloper = levelOfDeveloper;
        this.englishMastery = englishMastery;
        this.skype = skype;
        this.feedback = feedback;
    }

    @SneakyThrows
    public void addNewEmployee(){
        sql = "INSERT INTO employee( username, surname, secondname, fio, email, phone, birthday, workexperience, dateofemployment, project, levelofdeveloper, englishmastery, skype, feedback)" +
                "VALUES( '" +
                    name + "', '" +
                    surname + "', '" +
                    secondname + "', '" +
                    fio + "', '" +
                    email + "', " +
                    phone + ", '" +
                    birthday.getTime() + "', '" +
                    workExperience + "', '" +
                    dateOfEmployment.getTime() + "', " +
                    project + ", '" +
                    levelOfDeveloper + "', '" +
                    englishMastery + "', '" +
                    skype + "', " +
                    feedback +
                " ) RETURNING id;";

        setId(JDBCPostgreSQLConnector.insert(sql, "Not added new Employee in DataBase"));
    }

    @SneakyThrows
    public boolean deleteEmployee(){

        sql = "DELETE FROM employee where id = " + this.id + ";";

        return JDBCPostgreSQLConnector.delete(sql, "This employee doesn't exist");
    }

    @SneakyThrows
    public void updateEmployee(){

        sql = "UPDATE employee " +
                "SET username = '" + name + "', " +
                "surname = '" + surname + "', " +
                "secondname = '" + secondname + "', " +
                "fio = '" + fio + "', " +
                "email = '" + email + "', " +
                "phone = '" + phone + "', " +
                "birthday = '" + birthday.getTime() + "', " +
                "workexperience = '" +  workExperience + "', " +
                "dateofemployment = '" + dateOfEmployment.getTime() + "', " +
                "project = " + project + ", " +
                "levelofdeveloper = '" + levelOfDeveloper + "', " +
                "englishmastery = '" + englishMastery + "', " +
                "skype = '" + skype + "', " +
                "feedback = " + feedback +
                " WHERE id = " + id;
                JDBCPostgreSQLConnector.update(sql, "This employee doesn't exist");
    }

    @SneakyThrows
    public void getLastEmployee(){
        sql = "SELECT * FROM employee ORDER BY id DESC LIMIT 1";

        EmployeeMapper employeeMapper = new EmployeeMapper();

        this.clone(employeeMapper.mapRow(JDBCPostgreSQLConnector
                .select(sql, "Bad sql request or nothing to return"),1));
    }

    @SneakyThrows
    public void selectEmployee(long employee_id, int depth){

        sql = "SELECT * FROM employee WHERE id = " + employee_id +";";

        EmployeeMapper employeeMapper = new EmployeeMapper();

        try {
            this.clone(employeeMapper.mapRow(JDBCPostgreSQLConnector
                    .select(sql, "Bad sql request or nothing to return"), depth));
        }catch (NullPointerException e)
        {
            this.id = employee_id;
        }
    }

    public String generateFIO(){

        return this.surname + " " +
                this.name.substring(0,1).toUpperCase() + ". " +
                this.secondname.substring(0,1).toUpperCase() + ".";
    }

    public void clone(Employee employee){
        this.id = employee.id;
        this.name = employee.name;
        this.surname = employee.surname;
        this.secondname = employee.secondname;
        this.fio = employee.fio;
        this.email = employee.email;
        this.phone = employee.phone;
        this.birthday = employee.birthday;
        this.workExperience = employee.workExperience;
        this.dateOfEmployment = employee.dateOfEmployment;
        this.project = employee.project;
        this.levelOfDeveloper = employee.levelOfDeveloper;
        this.englishMastery = employee.englishMastery;
        this.skype = employee.skype;
        this.feedback = employee.feedback;

    }

    @Override
    public String toString() {
        return "employees{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surnanme='" + surname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", birthday=" + birthday +
                ", workExperience='" + workExperience + '\'' +
                ", dateOfEmployment=" + dateOfEmployment +
                ", project=" + project +
                ", levelOfDeveloper=" + levelOfDeveloper +
                ", englishMastery=" + englishMastery +
                ", skype='" + skype + '\'' +
                ", feedback=" + feedback +
                '}';
    }
}
