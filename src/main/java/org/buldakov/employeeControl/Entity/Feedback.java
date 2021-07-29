package org.buldakov.employeeControl.Entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;


@Data
@Component
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "description")
    public String description;
    @Column(name = "dateofcreating")
    public Date dateOfCreating;


    public Feedback(String description, Date dateOfCreating){
        this.description = description;
        this.dateOfCreating = dateOfCreating;
    }

    public Feedback(){}
}
