package com.example.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.book.model.Book;
import com.example.book.model.BookList;
import com.example.book.model.Constants;
import com.example.book.model.User;
import com.example.book.web.VisitorListener;
import com.example.book.web.WebUtil;

@WebServlet("/BookCatalogServlet")
public class BookCatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookCatalogServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		BookList bookList = WebUtil.getBookList(getServletContext());
		
		EntityManager em = ((EntityManager)getServletContext().getAttribute(Constants.ENTITY_MANAGER));
		em.refresh(bookList);
		Collection<Book> books = bookList.getBooks();
		User user = ((User)request.getSession().getAttribute(Constants.CURR_USER));
		String login = user != null ? user.getLogin() : null;
		out.println("<html>");
		out.println("<body>");
		if (login != null) {
			out.println("Welcome back, " + login);
		}
		out.println("<br>  List of available books:");
		out.println("<ul>");
		sortByISBN(books);
		for (Book book : books) {
			out.println("<li><a href='details?isbn=" + book.getIsbn() + "'>" + book.getTitle() + "</a>" +
					"<a href='edit1?isbn=" + book.getIsbn() + "'>   (edit)</a></li>");
		}

		out.println("</ul>");
		out.println("<br><br>Currently online : " + VisitorListener.count + " visitors");
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	private void sortByISBN(Collection<Book> books) {
		List<Book> bookList = new ArrayList<Book>(books);
		Collections.sort(bookList, new Comparator<Book>() {

			@Override
			public int compare(Book o1, Book o2) {
				return o1.getIsbn().compareTo(o2.getIsbn());
			}
		});
	}
}
