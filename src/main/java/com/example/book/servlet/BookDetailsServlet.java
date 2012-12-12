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

@WebServlet("/GetBookServlet")
public class BookDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookDetailsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String isbn = request.getParameter(Constants.ISBN);
		if (isbn == null) {
			out.println(Constants.ISBN + " parameter missing!");
			printNavigation(out);
			return;
		}
		Session session = ((SessionFactory)getServletContext().getAttribute(Constants.SESSION_FACTORY)).openSession();
		Book book = (Book) session.get(Book.class, isbn);
		session.close();
		if (book == null) {
			out.println("Book not found!");
			printNavigation(out);
			return;
		}
		out.println("<html>");
		out.println("<body>");
		out.println("<table border='1'>" 
						+ "<tr>"
						+ "<td>ISBN</td><td>Author</td><td>Title</td><td>Price</td><td></td>" 
						+ "</tr>" 
						+ "<tr>"
						+ "<td>" + book.getIsbn() + "</td><td>" + book.getAuthor() + "</td><td>" + book.getTitle() + "</td><td>" + book.getPrice() 
						+ "</td><td><a href='edit1?" + Constants.ISBN + "=" + book.getIsbn() + "'>edit</a></td>" 
						+ "</tr>" 
					+ "</table>");
		out.println("<form action='addToCart' method='POST'>" +
				"<input type='hidden' name='" + Constants.ISBN + "' value='" + book.getIsbn() + "'>" +
				"<input type='submit' value='add to cart'>" +
				"</form>");
		printNavigation(out);
		out.println("</body>");
		out.println("</html>");
	}

	private void printNavigation(PrintWriter out) {
		out.println("<br><a href='SearchForm.html'>search</a>");
		out.println("<br><a href='catalog'>catalog</a>");
	}
}
