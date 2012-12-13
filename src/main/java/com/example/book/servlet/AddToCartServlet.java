package com.example.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.book.model.Book;
import com.example.book.model.Constants;
import com.example.book.model.ShoppingCart;
import com.example.book.model.User;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddToCartServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String isbn = request.getParameter(Constants.ISBN);
		EntityManager em = ((EntityManager)getServletContext().getAttribute(Constants.ENTITY_MANAGER));
		Book book = (Book) em.find(Book.class, isbn);
		HttpSession session = request.getSession();
		ShoppingCart cart;
		User user = (User) session.getAttribute(Constants.CURR_USER);
		if (user == null) {
			((AtomicInteger)getServletContext().getAttribute(Constants.HIT_COUNT)).decrementAndGet();
			response.sendRedirect("UserLogin.html");
			return;
		}
		synchronized (session) {
			cart = (ShoppingCart) session.getAttribute(Constants.SHOPPING_CART);
			if (cart == null) {
				cart = new ShoppingCart();
				session.setAttribute(Constants.SHOPPING_CART, cart);
			}
		}
		cart.add(book);
		out.println("<html><body>");
		out.println("Book '" + book.getTitle() + "' added to the cart!<br>");
		out.println("In cart:");
		for (Book cartBook : cart.getContent()) {
			out.println("<li>" + cartBook.getTitle() + "</li>");
		}
		out.println("<br><a href='details?" + Constants.ISBN + "=" + book.getIsbn() + "'>back to book details</a>");
		printNavigation(out);
		out.println("<br><br>Hit count : " + ((AtomicInteger)getServletContext().getAttribute(Constants.HIT_COUNT)).get());
		out.println("</body></html>");
	}

	private void printNavigation(PrintWriter out) {
		out.println("<br><a href='SearchForm.html'>search</a>");
		out.println("<br><a href='catalog'>catalog</a>");
	}
}
