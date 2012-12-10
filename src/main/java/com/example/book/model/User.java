package com.example.book.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name = "users")
public class User {

	@Id
	private String login;
	private String password;
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
}
