package com.example.book.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;

@Entity
public class BookList {
	@Id
	private int id;
	@ElementCollection
	@JoinTable(name="BOOKS")
	private Collection<Book> books;

	public BookList() {
		books = new ArrayList<Book>();
	}
	
	public BookList(int id) {
		this();
		this.id = id;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public Book getBook(String isbn) {
		Book result = null;
		for (Book book : books) {
			if (book.getIsbn().equals(isbn)) {
				result = book;
				break;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("{");
		for (Book book : books) {
			stringBuilder.append(book.toString()).append(",");
		}
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
}
