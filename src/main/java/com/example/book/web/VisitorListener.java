package com.example.book.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class VisitorListener implements HttpSessionListener {

	public static int count;
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		count++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		count--;
	}

}
