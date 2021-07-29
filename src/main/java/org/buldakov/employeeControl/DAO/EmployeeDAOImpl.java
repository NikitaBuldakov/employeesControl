package org.buldakov.employeeControl.DAO;

import org.buldakov.employeeControl.Entity.Employee;
import org.buldakov.employeeControl.HibernateUtil.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDAOImpl implements DAOInterface<Employee>{

    @Override
    public Employee findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(employee);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(employee);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(employee);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employee = (List<Employee>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From employee").list();
        return employee;
    }
}
