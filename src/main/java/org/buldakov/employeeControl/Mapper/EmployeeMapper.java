package org.buldakov.employeeControl.Mapper;

import org.buldakov.employeeControl.Entity.Employee;
import org.buldakov.employeeControl.Entity.EmployeeEnum.EnglishMastery;
import org.buldakov.employeeControl.Entity.EmployeeEnum.LevelOfDeveloper;
import org.buldakov.employeeControl.Entity.Feedback;
import org.buldakov.employeeControl.Entity.Project;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.Date;

public class EmployeeMapper implements RowMapper<Employee>{

    @SneakyThrows
    @Override
    public Employee mapRow(ResultSet resultSet, int depth){
        if(depth<=3){
            depth++;
            Employee employee = new Employee();
            Project project = new Project();
            Feedback feedback = new Feedback();

            feedback.selectFeedBack(resultSet.getLong("feedback"));
            project.selectProject(resultSet.getLong("project"), depth);

            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("username"));
            employee.setSurname(resultSet.getString("surname"));
            employee.setSecondname(resultSet.getString("secondname"));
            employee.setFio(resultSet.getString("fio"));
            employee.setEmail(resultSet.getString("email"));
            employee.setPhone(resultSet.getString("phone"));
            employee.setBirthday(new Date(Long.parseLong(resultSet.getString("birthday"))));
            employee.setWorkExperience(resultSet.getString("workexperience"));
            employee.setDateOfEmployment(new Date(Long.parseLong(resultSet.getString("dateofemployment"))));
            employee.setProject(project);
            employee.setLevelOfDeveloper(LevelOfDeveloper.valueOf(resultSet.getString("levelofdeveloper")));
            employee.setEnglishMastery(EnglishMastery.valueOf(resultSet.getString("englishmastery")));
            employee.setSkype(resultSet.getString("skype"));
            employee.setFeedback(feedback);

            return employee;
        }
        return null;
    }
}
