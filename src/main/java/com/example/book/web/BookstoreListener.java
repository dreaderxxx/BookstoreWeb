package com.example.book.web;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.example.book.model.BookList;
import com.example.book.model.Constants;

@WebListener
public class BookstoreListener implements ServletContextListener {

	EntityManagerFactory emf;
	EntityManager em;

	public BookstoreListener() {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();

		// Configuration configuration = new Configuration().configure();
		// ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
		// .applySettings(configuration.getProperties())
		// .buildServiceRegistry();

		// SessionFactory sessionFactory =
		// configuration.buildSessionFactory(serviceRegistry);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("hibernate");
		EntityManager em = emf.createEntityManager();
		context.setAttribute(Constants.ENTITY_MANAGER, em);
		BookList bookList = (BookList) em.find(BookList.class, 1);
		System.out.println(bookList);
		context.setAttribute(Constants.BOOK_LIST, bookList);
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		if (em != null) {
			em.close();
		}
		if (emf != null) {
			emf.close();
		}
	}
}
