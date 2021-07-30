package Service;


import DAO.EmployeeDAOImpl;
import Entity.Employee;


import java.util.List;


public class EmployeeService {

    private final EmployeeDAOImpl employeeDAO;

    public EmployeeService(EmployeeDAOImpl employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    public Employee findEmployee(long id) {
        return employeeDAO.findById(id);
    }

    public void saveEmployee(Employee employee) {
        employeeDAO.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDAO.delete(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeDAO.findAll();
    }



}
