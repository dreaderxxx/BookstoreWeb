package com.example.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.book.model.Book;
import com.example.book.model.Constants;
import com.example.book.web.WebUtil;

@WebServlet("/BookEdit1Servlet")
public class BookEdit1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookEdit1Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter(Constants.ISBN);
		Book book = WebUtil.getBookList(getServletContext()).getBook(isbn);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<form method='POST' action='edit2'>" +
				"Author: <input type='text' name='" + Constants.AUTHOR + "' value='" + book.getAuthor() + "'/><br>" +
				"Title: <input type='text' name='" + Constants.TITLE + "' value='" + book.getTitle() + "'/><br>" +
				"Price: <input type='text' name='" + Constants.PRICE + "' value='" + book.getPrice() + "'/><br>" +
					   "<input type='hidden' name='" + Constants.ISBN + "' value='" + book.getIsbn() + "'/>" +
				"<input type='submit' value='ok'/>" +
				"</form>");
		out.println("</body>");
		out.println("</html>");
	}
}