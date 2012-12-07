package com.example.book.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	List<Book> content = new ArrayList<Book>();
	
	public void add(Book book) {
		content.add(book);
	}
	
	public List<Book> getContent() {
		return content;
	}
}
