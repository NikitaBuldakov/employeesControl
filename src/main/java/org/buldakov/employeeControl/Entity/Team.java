package org.buldakov.employeeControl.Entity;

import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NaturalId
    @Column(name = "team_id")
    public long team_id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    public List<Employee> employeeList = new ArrayList<>();

    public Team(){}

    public Team(long team_id, Employee employee){
        this.id = id;
        this.employeeList.add(employee);
        this.team_id = team_id;
    }

    public Team(  long team_id, List<Employee> employeeList){
        this.id = id;
        this.employeeList = employeeList;
        this.team_id = team_id;
    }

    public Team(long team_id){
        this.id = id;
        this.team_id = team_id;
    }

}
