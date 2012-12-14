package com.example.book.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class BookList implements Serializable {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	@Id
	private int id;
	@OneToMany
	@JoinTable(joinColumns = @JoinColumn(name = "booklist_id"), inverseJoinColumns = @JoinColumn(name = "book_isbn"))
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
