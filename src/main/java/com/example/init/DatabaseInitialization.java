package com.example.init;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.book.model.Book;
import com.example.book.model.BookList;
import com.example.book.model.User;

public class DatabaseInitialization {

	public static void main(String[] args) {
		
		Book book1 = new Book("1", "First My Book", "Me", 99);
		Book book2 = new Book("2", "My Book The Second", "Me", 100);
		Book book3 = new Book("3", "My Book Third Edition", "Me", 121);
		Book book4 = new Book("4", "My Book The Best", "Me", 56);
		
		BookList bookList = new BookList(1);
		bookList.addBook(book1);
		bookList.addBook(book2);
		bookList.addBook(book3);
		bookList.addBook(book4);
		
		User user1 = new User("dima", "dima");
		User user2 = new User("andrew", "andrew");
		
//		Configuration configuration = new Configuration().configure();
//		
//		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
//				.applySettings(configuration.getProperties())
//				.buildServiceRegistry();
//		
//		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//		Session session = sessionFactory.openSession();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("hibernate");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(bookList);
		em.persist(book1);
		em.persist(book2);
		em.persist(book3);
		em.persist(book4);
		em.persist(user1);
		em.persist(user2);

		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
