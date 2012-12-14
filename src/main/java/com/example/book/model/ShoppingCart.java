package com.example.book.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable {

	public void setContent(List<Book> content) {
		this.content = content;
	}

	List<Book> content = new ArrayList<Book>();

	public void add(Book book) {
		content.add(book);
	}

	public List<Book> getContent() {
		return content;
	}
}
