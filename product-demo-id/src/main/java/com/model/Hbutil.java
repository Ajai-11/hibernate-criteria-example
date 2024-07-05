package com.model;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Hbutil {

	private static SessionFactory sessionFactory;
	//public static StandardServiceRegistry registry=null;
	private static SessionFactory buildSessionFactory() {
		
			try 
			{
				//create the sessionfactory from hibernate .cfg.xml
				Configuration configuration=new Configuration();
				configuration.configure("hibernate.cfg.xml");
				System.out.println("Hibernate Configuration Loaded");
				
				ServiceRegistry serviceRegistry =new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				sessionFactory=configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;
				
			}
			catch(Throwable ex) 
			{
				System.err.println("Initial sessionFactory creation failed."+ex);
				ex.printStackTrace();
				throw new ExceptionInInitializerError(ex);
				
			}
	}
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory==null)
		{
			sessionFactory=buildSessionFactory();
			
		}
		return sessionFactory;
	}
	

}


