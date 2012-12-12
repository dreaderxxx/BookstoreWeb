package com.example.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.book.model.Book;
import com.example.book.model.Constants;

@WebServlet("/BookEdit2Servlet")
public class BookEdit2Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    public BookEdit2Servlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String isbn = request.getParameter(Constants.ISBN);
		String author = request.getParameter(Constants.AUTHOR);
		String title = request.getParameter(Constants.TITLE);
		Session session = ((SessionFactory)getServletContext().getAttribute(Constants.SESSION_FACTORY)).openSession();
		session.beginTransaction();
		Book book = (Book) session.get(Book.class, isbn);
		int price = book.getPrice();
		String message = "Book successfully edited!";
		try {
			price = Integer.valueOf(request.getParameter(Constants.PRICE));
		} catch (NumberFormatException e) {
			message = "Error! Price should be valid number!";
		}
		book.setAuthor(author);
		book.setTitle(title);
		book.setPrice(price);
		
		session.getTransaction().commit();
		session.close();
		
		out.println("<html><body>");
		out.println(message);
		printNavigation(out);
		out.println("</body></html>");
	}

	private void printNavigation(PrintWriter out) {
		out.println("<br><a href='SearchForm.html'>search</a>");
		out.println("<br><a href='catalog'>catalog</a>");
	}
	
}
