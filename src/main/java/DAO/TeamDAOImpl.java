package DAO;


import Entity.Team;
import HibernateUtil.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;


public class TeamDAOImpl implements DAOInterface<Team>{
    @Override
    public Team findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Team.class, id);
    }

    @Override
    public void save(Team team) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(team);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Team team) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(team);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Team team) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(team);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Team> findAll() {
        List<Team> teams = (List<Team>)  HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From Team ").list();
        return teams;
    }
}
