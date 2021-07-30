package Entity;

import CustomException.ExceptionHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

    public String generateFIO(){
        return this.surname + " " +
                this.name.substring(0,1).toUpperCase() + ". " +
                this.secondname.substring(0,1).toUpperCase() + ".";
    }
}
