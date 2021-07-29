package org.buldakov.employeeControl.Service;

import org.buldakov.employeeControl.DAO.EmployeeDAOImpl;
import org.buldakov.employeeControl.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeService {

    @Autowired
    private EmployeeDAOImpl employeeDAO;

    public EmployeeService(EmployeeDAOImpl employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public Employee findUser(int id) {
        return employeeDAO.findById(id);
    }

    public void saveUser(Employee employee) {
        employeeDAO.save(employee);
    }

    public void deleteUser(Employee employee) {
        employeeDAO.delete(employee);
    }

    public void updateUser(Employee employee) {
        employeeDAO.update(employee);
    }

    public List<Employee> findAllUsers() {
        return employeeDAO.findAll();
    }


}
