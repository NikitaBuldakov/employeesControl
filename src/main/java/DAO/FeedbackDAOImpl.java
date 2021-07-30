package DAO;

import Entity.Feedback;
import HibernateUtil.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;


public class FeedbackDAOImpl  implements DAOInterface<Feedback>{
    @Override
    public Feedback findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Feedback.class, id);
    }

    @Override
    public void save(Feedback feedback) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(feedback);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Feedback feedback) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(feedback);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Feedback feedback) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(feedback);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Feedback> findAll() {
        List<Feedback> feedbacks = (List<Feedback>)  HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From Feedback").list();
        return feedbacks;
    }
}
