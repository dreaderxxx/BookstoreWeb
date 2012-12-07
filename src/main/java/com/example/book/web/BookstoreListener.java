package com.example.book.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.example.book.model.BookList;
import com.example.book.model.Constants;

@WebListener
public class BookstoreListener implements ServletContextListener {

	public BookstoreListener() {
    }

    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext context = arg0.getServletContext();
    	BookList bookList = new BookList();
    	context.setAttribute(Constants.BOOK_LIST, bookList);
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    }
}
