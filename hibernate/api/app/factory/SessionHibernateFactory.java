package factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import utils.AuditorInterceptor;

public class SessionHibernateFactory {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	public static synchronized Session getHibernateSession() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			
			configuration.setInterceptor(new AuditorInterceptor());

			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()
			).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}

		return sessionFactory.getCurrentSession();
	}
}
