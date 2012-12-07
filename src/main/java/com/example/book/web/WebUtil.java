package com.example.book.web;

import javax.servlet.ServletContext;

import com.example.book.model.BookList;
import com.example.book.model.Constants;

public class WebUtil {

	public static BookList getBookList(ServletContext context) {
		return (BookList) context.getAttribute(Constants.BOOK_LIST);
	}
}
