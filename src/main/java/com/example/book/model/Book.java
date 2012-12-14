package com.example.book.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book implements Serializable {
	@Id
	private String isbn;
	private String title;
	private String author;
	private int price;

	public Book() {
	}

	public Book(String isbn, String title, String author, int price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[" + isbn + ", " + title + ", " + author + ", " + price + "]";
	}
}
