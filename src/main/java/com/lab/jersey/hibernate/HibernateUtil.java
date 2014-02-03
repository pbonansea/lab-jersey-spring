/**
 * 
 */
package com.lab.jersey.hibernate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.lab.jersey.util.ApplicationConstant;

/**
 * @author paolobonansea
 * 
 */
public class HibernateUtil {
	
	private static Logger LOG = Logger.getLogger(HibernateUtil.class);
	
	private static Configuration configuration;	
	private static SessionFactory factory;
	private static StandardServiceRegistryBuilder serviceRegistry;
	private static ThreadLocal<Session> threadSessions = new ThreadLocal<Session>();

	static {
		try {
			configuration = new Configuration().configure();
			if (ApplicationConstant.Runtime.ENVIRONMENT.getValue()
					.equals(ApplicationConstant.Runtime.TEST_ENVIRONMENT.getValue())) {
				try {
					Properties hibernateProp = new Properties();
					hibernateProp.load(HibernateUtil.class.getResourceAsStream(
						        "/com/lab/jersey/test/config/hibernateTest.properties"));
					configuration.addProperties(hibernateProp);									
				} catch (FileNotFoundException ex) {
					LOG.error("error hibernate init: " + ex.getMessage(), ex);
					throw new ExceptionInInitializerError(ex.getMessage());
				} catch (IOException ex) {
					LOG.error("error hibernate init: " + ex.getMessage(), ex);
					throw new ExceptionInInitializerError(ex.getMessage());
				}
			}
			serviceRegistry = new StandardServiceRegistryBuilder().
					applySettings(configuration.getProperties());
			factory = configuration
					.buildSessionFactory(serviceRegistry.build());
		} catch (HibernateException ex) {
			LOG.error("error hibernate init: " + ex.getMessage(), ex);
			throw new ExceptionInInitializerError(ex.getMessage());
		}	
	}

	public static Session getSession() throws HibernateException {

		Session session = threadSessions.get();

		try {
			if (session == null) {
				session = factory.openSession();
				threadSessions.set(session);
			}
		} catch (HibernateException ex) {
			LOG.error("error hibernate get session: " + ex.getMessage(), ex);
			throw ex;
		}

		return session;
	}

	public static void closeSession() throws HibernateException {

		Session session = threadSessions.get();

		try {
			if (session != null && session.isOpen()) {
				session.close();
				threadSessions.set(null);
			}
		} catch (HibernateException ex) {
			LOG.error("error hibernate close session: " + ex.getMessage(), ex);
			throw ex;
		}
	}

}
