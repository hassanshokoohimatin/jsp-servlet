package config;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    static {
        sessionFactory = new Configuration()
                .configure("main/webapp/WEB-INF/hibernate.cfg.xml")
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static Session getSession(){
        return session;
    }
}


