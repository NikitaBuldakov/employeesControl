package org.buldakov.employeeControl.HibernateUtil;

import lombok.SneakyThrows;
import org.buldakov.employeeControl.CustomException.ExceptionHandler;
import org.buldakov.employeeControl.Entity.Employee;
import org.buldakov.employeeControl.Entity.Feedback;
import org.buldakov.employeeControl.Entity.Project;
import org.buldakov.employeeControl.Entity.Team;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    @SneakyThrows
    @PostConstruct
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Feedback.class);
                configuration.addAnnotatedClass(Project.class);
                configuration.addAnnotatedClass(Team.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                throw new ExceptionHandler("session creation error in " + HibernateSessionFactoryUtil.class, e);
            }
        }
        return sessionFactory;
    }

    @PreDestroy
    public static void shutdown() {
        getSessionFactory().close();
    }
}
