package com.example.book.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.book.model.Constants;
import com.example.book.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(Constants.LOGIN);
		String password = request.getParameter(Constants.PASSWORD);
		if (password.length() != 4) {
			response.sendRedirect("UserLogin.html");
		} else {
			request.getSession().setAttribute(Constants.CURR_USER, new User(login, password));
			request.getRequestDispatcher("catalog").forward(request, response);
		}
	}
}
