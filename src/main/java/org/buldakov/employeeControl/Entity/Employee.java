package org.buldakov.employeeControl.Entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;


@Data
@Component
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    public String name;
    @Column(name = "surname")
    public String surname;
    @Column(name = "secondname")
    public String secondname;
    @Column(name = "fio")
    public String fio;
    @Column(name = "email")
    public String email;
    @Column(name = "phone")
    public String phone;
    @Column(name = "birthday")
    public Date birthday;
    @Column(name = "workexperience")
    public String workExperience;
    @Column(name = "dateofemployment")
    public Date dateOfEmployment;
    @Column(name = "project")

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public Project project;

    @Column(name = "levelofdeveloper")
    public Enum levelOfDeveloper;
    @Column(name = "englishmastery")
    public Enum englishMastery;
    @Column(name = "skype")
    public String skype;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public Feedback feedback;

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
