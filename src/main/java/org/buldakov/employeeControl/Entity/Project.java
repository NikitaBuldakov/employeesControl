package org.buldakov.employeeControl.Entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Component
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    public String nameOfProject;
    @Column(name = "customer")
    public String customer;
    @Column(name = "duration")
    public String duration;
    @Column(name = "methodology")
    public String methodology;
    @Column(name = "projectmanager")
    public String projectManager;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    public Team team;


    public Project(String nameOfProject, String customer, String duration, String methodology,
                   String projectManager, Team team) {
        this.nameOfProject = nameOfProject;
        this.customer = customer;
        this.duration = duration;
        this.methodology = methodology;
        this.projectManager = projectManager;
        this.team = team;
    }

    public Project(){}

}
