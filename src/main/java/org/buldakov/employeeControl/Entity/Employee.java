package org.buldakov.employeeControl.Entity;

import org.buldakov.employeeControl.CustomException.ExceptionHandler;
import org.buldakov.employeeControl.Mapper.EmployeeMapper;
import org.buldakov.employeeControl.DataBaseConnection.JDBCPostgreSQLConnector;
import org.buldakov.employeeControl.Mapper.ListMapper;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Data
@Component
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
                    project.getId() + ", '" +
                    levelOfDeveloper + "', '" +
                    englishMastery + "', '" +
                    skype + "', " +
                    feedback.getId() +
                " ) RETURNING id;";

        setId(JDBCPostgreSQLConnector.insert(sql,Employee.class + " add method: the method in which the class was called " +
                " Not added new Employee in DataBase"));
    }

    @SneakyThrows
    public boolean deleteEmployee(){

        sql = "DELETE FROM employee where id = " + this.id + ";";

            return JDBCPostgreSQLConnector.delete(sql, Employee.class + " delete method: the method in which the class was called " +
                    " This employee doesn't exist");
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
                "project = " + project.getId() + ", " +
                "levelofdeveloper = '" + levelOfDeveloper + "', " +
                "englishmastery = '" + englishMastery + "', " +
                "skype = '" + skype + "', " +
                "feedback = " + feedback.getId() +
                " WHERE id = " + id;
            JDBCPostgreSQLConnector.update(sql, Employee.class + " update method: the method in which the class was called " +
                    " This employee doesn't exist");
    }

    @SneakyThrows
    public void getLastEmployee(){
        sql = "SELECT * FROM employee ORDER BY id DESC LIMIT 1";

        EmployeeMapper employeeMapper = new EmployeeMapper();

        try {
            this.clone(employeeMapper.mapRow(JDBCPostgreSQLConnector
                    .select(sql, Employee.class + " getLast method: the method in which the class was called " + " Bad sql request or nothing to return"), 1));
        }catch (NullPointerException e){
            throw new ExceptionHandler("The class in which the error was flown: " + Employee.class
                    + ". In getLastEmployee method.",e);
        }
    }

    @SneakyThrows
    public void selectEmployee(long employee_id, int depth){

        sql = "SELECT * FROM employee WHERE id = " + employee_id +";";

        EmployeeMapper employeeMapper = new EmployeeMapper();

        try {
            this.clone(employeeMapper.mapRow(JDBCPostgreSQLConnector
                    .select(sql, Employee.class + " select method: the method in which the class was called " +
                            " Bad sql request or nothing to return"), depth));
        }catch (NullPointerException e)
        {
            this.id = employee_id;
        }
    }

    @SneakyThrows
    public List<Employee> selectAll(){
        sql = "SELECT * FROM employee";

        List<Employee> employeeList = new ArrayList<Employee>();
        ListMapper listMapper = new ListMapper();
        EmployeeMapper employeeMapper = new EmployeeMapper();


        List<Long> idArray = listMapper.mapRow(JDBCPostgreSQLConnector.selectAll(sql,Employee.class
                + " select method: the method in which the class was called " +
                " Bad sql request or nothing to return"), 0);

        for (int i = 0; i < idArray.size() - 1; i++)
            employeeList.add(employeeMapper.mapRow(JDBCPostgreSQLConnector
                    .select("SELECT * FROM employee WHERE id = " + idArray.get(i), Employee.class
                            + " selectAll method: the method in which the class was called "
                            + " Bad sql request or nothing to return"), 0));

        return employeeList;
    }

    public String generateFIO(){
        return this.surname + " " +
                this.name.substring(0,1).toUpperCase() + ". " +
                this.secondname.substring(0,1).toUpperCase() + ".";
    }

    @SneakyThrows
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
