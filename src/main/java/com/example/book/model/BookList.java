package com.example.book.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookList {
	private Map<String, Book> books;

	public BookList() {
		books = new HashMap<String, Book>();
		books.put("1", new Book("1", "First My Book", "Me", 99));
		books.put("2", new Book("2", "My Book The Second", "Me", 100));
		books.put("3", new Book("3", "My Book Third Edition", "Me", 121));
		books.put("4", new Book("4", " My Book The Best", "Me", 56));
	}

	public List<Book> getBooks() {
		return new ArrayList<Book>(books.values());
	}

	public Book getBook(String isbn) {
		return books.get(isbn);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("{");
		for (Book book : books.values()) {
			stringBuilder.append(book.toString()).append(",");
		}
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
}
