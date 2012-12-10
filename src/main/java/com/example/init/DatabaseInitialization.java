package com.example.init;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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
		
		Configuration configuration = new Configuration().configure();
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(bookList);
		session.save(book1);
		session.save(book2);
		session.save(book3);
		session.save(book4);
		session.save(user1);
		session.save(user2);

		session.getTransaction().commit();
		session.close();
	}

}
