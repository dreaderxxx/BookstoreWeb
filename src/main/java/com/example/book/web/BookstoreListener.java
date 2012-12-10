package com.example.book.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.example.book.model.BookList;
import com.example.book.model.Constants;

@WebListener
public class BookstoreListener implements ServletContextListener {

	public BookstoreListener() {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();

		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		context.setAttribute(Constants.SESSION_FACTORY, sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		BookList bookList = (BookList) session.get(BookList.class, 1);
		session.getTransaction().commit();
		session.close();
		context.setAttribute(Constants.BOOK_LIST, bookList);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
