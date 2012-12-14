package com.example.book.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbinit")
public class DatabaseInitialization extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DatabaseInitialization() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.example.init.DatabaseInitialization.main(null);
	}
}
