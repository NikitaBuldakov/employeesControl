package org.buldakov.employeeControl.DAO;

import org.buldakov.employeeControl.Entity.Employee;
import org.buldakov.employeeControl.Entity.Project;
import org.buldakov.employeeControl.HibernateUtil.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectDAOImpl implements DAOInterface<Project>{
    @Override
    public Project findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Project.class, id);
    }

    @Override
    public void save(Project project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(project);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Project project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(project);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Project project) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(project);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = (List<Project>)  HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From Project ").list();
        return projects;
    }
}
